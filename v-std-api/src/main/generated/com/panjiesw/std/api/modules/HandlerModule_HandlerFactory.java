package com.panjiesw.std.api.modules;

import com.panjiesw.std.api.handlers.ApiHandler;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class HandlerModule_HandlerFactory implements Factory<ApiHandler> {
  private final HandlerModule module;

  public HandlerModule_HandlerFactory(HandlerModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public ApiHandler get() {  
    ApiHandler provided = module.handler();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<ApiHandler> create(HandlerModule module) {  
    return new HandlerModule_HandlerFactory(module);
  }
}

