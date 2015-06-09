package com.panjiesw.std.api.modules;

import com.panjiesw.std.service.user.UserService;
import dagger.Module;
import dagger.Provides;
import io.vertx.core.Vertx;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.Router;

import javax.inject.Singleton;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author PanjieSW.
 */
@Module
public class ApiModule {
  private final Vertx vertx;
  private final Router router;

  private JWTAuth authProvider;
  private UserService userService;

  public ApiModule(Vertx vertx) {
    this.vertx = vertx;
    this.router = Router.router(this.vertx);
  }

  @Singleton
  @Provides
  public Vertx vertx() {
    return vertx;
  }

  @Singleton
  @Provides
  public Router router() {
    return router;
  }

  @Singleton
  @Provides
  public Validator validator() {
    return Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Singleton
  @Provides
  public UserService userService() {
    return userService;
  }

  public ApiModule userService(UserService userService) {
    this.userService = userService;
    return this;
  }

  @Singleton
  @Provides
  public JWTAuth authProvider() {
    return authProvider;
  }

  public ApiModule authProvider(JWTAuth authProvider) {
    this.authProvider = authProvider;
    return this;
  }
}
