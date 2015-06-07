package com.panjiesw.std.api;

import com.panjiesw.std.api.components.ApiComponent;
import com.panjiesw.std.api.components.DaggerApiComponent;
import com.panjiesw.std.api.modules.ApiModule;
import com.panjiesw.std.service.user.UserService;
import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author PanjieSW.
 */
@SuppressWarnings("unused")
@Slf4j
public class ApiVerticle extends AbstractVerticle {

  public static final List<String> allowedServices = Arrays.asList("user", "role");
  public static final List<String> deploymentIDs = new ArrayList<>();

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    log.info("Starting ApiVerticle");
    this.initServices(startFuture);
  }

  @Override
  public void stop(Future<Void> stopFuture) throws Exception {
    vertx.undeploy(deploymentIDs.get(0), res -> stopFuture.complete());
  }

  private void initServices(Future<Void> startFuture) {
    JsonObject serviceConf = config().getJsonObject("services");

    if (serviceConf == null) {
      startFuture.fail("Have to specify which service to be deployed and or configured");
      return;
    }

    Iterator<Entry<String, Object>> serviceIterator =  serviceConf.iterator();
    while (serviceIterator.hasNext()) {
      Entry<String, Object> serviceEntry = serviceIterator.next();
      if (allowedServices.contains(serviceEntry.getKey())) {
        JsonObject serviceConfig = serviceConf.getJsonObject(serviceEntry.getKey());
        if (serviceConfig == null) {
          String message = String.format("Invalid config object for service %s", serviceEntry.getKey());
          log.error(message);
          startFuture.fail(new IllegalArgumentException(message));
          break;
        }
        if (serviceConfig.getBoolean("local", true)) {
          String className = serviceConfig.getString("name");
          if (className == null) {
            String message = String.format("No class name specified for service %s", serviceEntry.getKey());
            log.error(message);
            startFuture.fail(new IllegalArgumentException(message));
            break;
          }
          JsonObject serviceDeploymentConf = serviceConfig.getJsonObject("config", new JsonObject());
          DeploymentOptions serviceOptions = new DeploymentOptions().setConfig(serviceDeploymentConf);
          vertx.deployVerticle(className, serviceOptions,
            this.handleService(startFuture, serviceIterator.hasNext()));
        }
      }
    }
  }

  private Handler<AsyncResult<String>> handleService(Future<Void> startFuture, boolean hasNext) {
    return res -> {
      if (res.succeeded()) {
        log.info("UserService has successfully deployed");
        deploymentIDs.add(res.result());
        if (!hasNext) {
          this.startModule(startFuture);
        }
      } else {
        log.error("Error deploying UserService", res.cause());
        startFuture.fail(res.cause());
      }
    };
  }

  private void startModule(Future<Void> startFuture) {
    ApiComponent app = DaggerApiComponent.builder()
      .apiModule(new ApiModule(vertx).userService(
        UserService.createEventBusProxy(vertx, "com.panjiesw.std.service-user")))
      .build();
    Server.init(app).start();
    startFuture.complete();
  }
}
