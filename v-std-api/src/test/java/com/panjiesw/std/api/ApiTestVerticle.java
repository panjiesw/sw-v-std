package com.panjiesw.std.api;

import com.panjiesw.std.api.components.ApiComponent;
import com.panjiesw.std.api.components.DaggerApiComponent;
import com.panjiesw.std.api.modules.ApiModule;
import io.vertx.core.AbstractVerticle;

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
    ApiComponent app = DaggerApiComponent.builder()
      .apiModule(apiTestModule)
      .build();
    Server.init(app).start();
  }
}
