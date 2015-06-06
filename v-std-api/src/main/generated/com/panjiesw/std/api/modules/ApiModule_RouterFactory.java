package com.panjiesw.std.api.modules;

import dagger.internal.Factory;
import io.vertx.ext.web.Router;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApiModule_RouterFactory implements Factory<Router> {
  private final ApiModule module;

  public ApiModule_RouterFactory(ApiModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public Router get() {  
    Router provided = module.router();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Router> create(ApiModule module) {  
    return new ApiModule_RouterFactory(module);
  }
}

