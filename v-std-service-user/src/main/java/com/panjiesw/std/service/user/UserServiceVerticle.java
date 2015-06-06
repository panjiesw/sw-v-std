package com.panjiesw.std.service.user;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

import java.util.concurrent.CountDownLatch;

/**
 * @author PanjieSW.
 */
public class UserServiceVerticle extends AbstractVerticle {

  private UserService service;

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    final JsonObject conf = config();
    final CountDownLatch cdl = new CountDownLatch(1);
    final Handler<AsyncResult<Void>> simpleEndHandler = res -> {
      cdl.countDown();
      if (res.failed()) {
        startFuture.fail(res.cause());
      } else {
        if (cdl.getCount() == 0 && !startFuture.isComplete()) {
          startFuture.complete();
        }
      }
    };

    final String address = "com.panjiesw.std.service-user";
    service = UserService.create(vertx, conf);
    ProxyHelper.registerService(UserService.class, vertx, service, address);
    service.start(simpleEndHandler);
  }
}
