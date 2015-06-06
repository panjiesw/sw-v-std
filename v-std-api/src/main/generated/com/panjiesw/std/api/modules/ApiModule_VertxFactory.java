package com.panjiesw.std.api.modules;

import dagger.internal.Factory;
import io.vertx.core.Vertx;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApiModule_VertxFactory implements Factory<Vertx> {
  private final ApiModule module;

  public ApiModule_VertxFactory(ApiModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public Vertx get() {  
    Vertx provided = module.vertx();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Vertx> create(ApiModule module) {  
    return new ApiModule_VertxFactory(module);
  }
}

