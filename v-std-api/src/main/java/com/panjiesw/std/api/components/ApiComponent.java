package com.panjiesw.std.api.components;

import com.panjiesw.std.api.ApiRouter;
import com.panjiesw.std.api.Server;
import com.panjiesw.std.api.modules.ApiModule;
import com.panjiesw.std.service.user.UserService;
import dagger.Component;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.Router;

import javax.inject.Singleton;
import javax.validation.Validator;

/**
 * @author PanjieSW.
 */
@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {
  Vertx vertx();
  Router router();
//  JsonObject verticleConfig();
  JWTAuth authProvider();
  UserService userService();
  Validator validator();
  void inject(Server server);
  void inject(ApiRouter router);
}
