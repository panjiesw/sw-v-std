package com.panjiesw.std.api.modules;

import dagger.internal.Factory;
import io.vertx.ext.auth.jwt.JWTAuth;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApiModule_AuthProviderFactory implements Factory<JWTAuth> {
  private final ApiModule module;

  public ApiModule_AuthProviderFactory(ApiModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public JWTAuth get() {  
    JWTAuth provided = module.authProvider();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<JWTAuth> create(ApiModule module) {  
    return new ApiModule_AuthProviderFactory(module);
  }
}

