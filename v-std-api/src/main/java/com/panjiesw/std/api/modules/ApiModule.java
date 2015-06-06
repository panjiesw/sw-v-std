package com.panjiesw.std.api.modules;

import com.panjiesw.std.service.user.UserService;
import dagger.Module;
import dagger.Provides;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

import javax.inject.Singleton;

/**
 * @author PanjieSW.
 */
@Module
public class ApiModule {
  private final Vertx vertx;
  private final Router router;

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
  public UserService userService() {
    return UserService.createEventBusProxy(vertx, "com.panjiesw.std.service-user");
  }
}
