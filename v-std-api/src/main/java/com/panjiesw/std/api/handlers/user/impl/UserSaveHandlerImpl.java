package com.panjiesw.std.api.handlers.user.impl;

import com.panjiesw.std.api.annotations.PerHandler;
import com.panjiesw.std.api.components.AbstractHandlerComponent;
import com.panjiesw.std.api.components.ApiComponent;
import com.panjiesw.std.api.handlers.user.UserSaveHandler;
import com.panjiesw.std.api.modules.HandlerModule;
import com.panjiesw.std.common.dto.User;
import com.panjiesw.std.service.user.UserService;
import dagger.Component;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author PanjieSW.
 */
@Slf4j
public class UserSaveHandlerImpl implements UserSaveHandler {
  @Inject
  Router router;

  @Inject
  UserService userService;

  @Inject
  Validator validator;

  @PerHandler
  @Component(modules = HandlerModule.class, dependencies = ApiComponent.class)
  public interface HandlerComponent extends AbstractHandlerComponent {
    UserSaveHandlerImpl inject(UserSaveHandlerImpl userOneHandler);
  }

  @Override
  public void create() {
    log.info("Registering handler [POST] /users to [{}]", getClass().getName());
    router.post("/users").handler(this);
  }

  @Override
  public void handle(RoutingContext context) {
    JsonObject payload = context.getBodyAsJson();
    User user = User.fromJson(payload);
    Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
    HttpServerResponse response = context.response();
    if (constraintViolations.isEmpty()) {
      userService.save(payload, res -> {
        if (res.succeeded()) {
          response.setStatusCode(HttpURLConnection.HTTP_CREATED).end();
        } else {
          response.setStatusCode(HttpURLConnection.HTTP_INTERNAL_ERROR).end();
        }
      });
    } else {
      JsonObject errors = new JsonObject();
      constraintViolations
        .stream()
        .forEach(e -> errors.put(e.getPropertyPath().toString(), e.getMessage()));
      response
        .setStatusCode(HttpURLConnection.HTTP_BAD_REQUEST)
        .end(new JsonObject().put("errors", errors).encode());
    }
  }
}
