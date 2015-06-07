package com.panjiesw.std.api.handlers.user.impl;

import com.panjiesw.std.service.user.UserService;
import dagger.MembersInjector;
import io.vertx.ext.web.Router;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class UserOneHandlerImpl_MembersInjector implements MembersInjector<UserOneHandlerImpl> {
  private final Provider<Router> routerProvider;
  private final Provider<UserService> userServiceProvider;

  public UserOneHandlerImpl_MembersInjector(Provider<Router> routerProvider, Provider<UserService> userServiceProvider) {  
    assert routerProvider != null;
    this.routerProvider = routerProvider;
    assert userServiceProvider != null;
    this.userServiceProvider = userServiceProvider;
  }

  @Override
  public void injectMembers(UserOneHandlerImpl instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.router = routerProvider.get();
    instance.userService = userServiceProvider.get();
  }

  public static MembersInjector<UserOneHandlerImpl> create(Provider<Router> routerProvider, Provider<UserService> userServiceProvider) {  
      return new UserOneHandlerImpl_MembersInjector(routerProvider, userServiceProvider);
  }
}

