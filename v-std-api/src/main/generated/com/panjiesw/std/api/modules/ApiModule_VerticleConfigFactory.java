package com.panjiesw.std.api.modules;

import dagger.internal.Factory;
import io.vertx.core.json.JsonObject;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApiModule_VerticleConfigFactory implements Factory<JsonObject> {
  private final ApiModule module;

  public ApiModule_VerticleConfigFactory(ApiModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public JsonObject get() {  
    JsonObject provided = module.verticleConfig();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<JsonObject> create(ApiModule module) {  
    return new ApiModule_VerticleConfigFactory(module);
  }
}

