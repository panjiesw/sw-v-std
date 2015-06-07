package com.panjiesw.std.api.handlers.user.impl;

import dagger.MembersInjector;
import io.vertx.ext.web.Router;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class UserOneHandlerImpl_MembersInjector implements MembersInjector<UserOneHandlerImpl> {
  private final Provider<Router> routerProvider;

  public UserOneHandlerImpl_MembersInjector(Provider<Router> routerProvider) {  
    assert routerProvider != null;
    this.routerProvider = routerProvider;
  }

  @Override
  public void injectMembers(UserOneHandlerImpl instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.router = routerProvider.get();
  }

  public static MembersInjector<UserOneHandlerImpl> create(Provider<Router> routerProvider) {  
      return new UserOneHandlerImpl_MembersInjector(routerProvider);
  }
}

