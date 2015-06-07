package com.panjiesw.std.api.handlers.user.impl;

import com.panjiesw.std.api.annotations.PerHandler;
import com.panjiesw.std.api.components.AbstractHandlerComponent;
import com.panjiesw.std.api.components.ApiComponent;
import com.panjiesw.std.api.handlers.user.UserOneHandler;
import com.panjiesw.std.api.modules.HandlerModule;
import com.panjiesw.std.service.user.UserService;
import dagger.Component;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.net.HttpURLConnection;

/**
 * @author PanjieSW.
 */
@Slf4j
public class UserOneHandlerImpl implements UserOneHandler {

  @Inject
  Router router;

  @Inject
  UserService userService;

  @PerHandler
  @Component(modules = HandlerModule.class, dependencies = ApiComponent.class)
  public interface HandlerComponent extends AbstractHandlerComponent {
    UserOneHandlerImpl inject(UserOneHandlerImpl userOneHandler);
  }

  @Override
  public void create() {
    log.info("Registering handler [GET] /users/:id to [{}]", getClass().getName());
    router.route("/users/:id").handler(this);
  }

  @Override
  public void handle(RoutingContext context) {
    userService.one(Long.valueOf(context.request().getParam("id")), res -> {
      HttpServerResponse response = context.response().putHeader("content-type", "application/json");
      if (res.succeeded()) {
        response.end(res.result().encode());
      } else {
        response.setStatusCode(HttpURLConnection.HTTP_NOT_FOUND)
          .end(new JsonObject().put("message", "Not Found").encode());
      }
    });
  }
}
