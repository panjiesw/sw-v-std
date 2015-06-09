package com.panjiesw.std.api;

import com.panjiesw.std.api.components.ApiComponent;
import com.panjiesw.std.api.handlers.user.UserOneHandler;
import com.panjiesw.std.api.handlers.user.UserSaveHandler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.JWTAuthHandler;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author PanjieSW.
 */
@Slf4j
public final class ApiRouter {

  @Inject
  JWTAuth authProvider;

  @Inject
  Router router;

  @Inject
  Vertx vertx;

  @Inject
  @Named("verticleConfig")
  JsonObject verticleConfig;

  private ApiRouter() {
  }

  public static void create(ApiComponent app) {
    log.info("Registering ApiRouter");

    ApiRouter apiRouter = new ApiRouter();
    app.inject(apiRouter);

    JsonObject authConfig = apiRouter.verticleConfig.getJsonObject("auth");
    if (authConfig.getBoolean("enable", true)) {
      JWTAuthHandler authHandler = JWTAuthHandler.create(apiRouter.authProvider);
      apiRouter.router.route().handler(authHandler);
    }

    UserOneHandler.init(app).create();
    UserSaveHandler.init(app).create();
  }
}
