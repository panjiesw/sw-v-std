package com.panjiesw.std.api;

import com.panjiesw.std.api.components.ApiComponent;
import com.panjiesw.std.api.components.DaggerApiComponent;
import com.panjiesw.std.api.modules.ApiModule;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;

/**
 * @author PanjieSW.
 */
public class ApiTestVerticle extends AbstractVerticle {

  private final ApiModule apiTestModule;

  public ApiTestVerticle(ApiModule apiTestModule) {
    this.apiTestModule = apiTestModule;
  }

  @Override
  public void start() throws Exception {
    JWTAuth authProvider = JWTAuth.create(
      new JsonObject()
        .put("keyStoreURI", "classpath:///keystore-test.jceks")
        .put("keyStoreType", "jceks")
        .put("keyStorePassword", "secret"));

    ApiComponent app = DaggerApiComponent.builder()
      .apiModule(apiTestModule.authProvider(authProvider).verticleConfig(config()))
      .build();
    Server.init(app).start();
  }
}
