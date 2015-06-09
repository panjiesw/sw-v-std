package com.panjiesw.std.api;

import dagger.MembersInjector;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.Router;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApiRouter_MembersInjector implements MembersInjector<ApiRouter> {
  private final Provider<JWTAuth> authProvider;
  private final Provider<Router> routerProvider;

  public ApiRouter_MembersInjector(Provider<JWTAuth> authProvider, Provider<Router> routerProvider) {  
    assert authProvider != null;
    this.authProvider = authProvider;
    assert routerProvider != null;
    this.routerProvider = routerProvider;
  }

  @Override
  public void injectMembers(ApiRouter instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.authProvider = authProvider.get();
    instance.router = routerProvider.get();
  }

  public static MembersInjector<ApiRouter> create(Provider<JWTAuth> authProvider, Provider<Router> routerProvider) {  
      return new ApiRouter_MembersInjector(authProvider, routerProvider);
  }
}

