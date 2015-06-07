package com.panjiesw.std.api.handlers.user.impl;

import com.panjiesw.std.service.user.UserService;
import dagger.MembersInjector;
import io.vertx.ext.web.Router;
import javax.annotation.Generated;
import javax.inject.Provider;
import javax.validation.Validator;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class UserSaveHandlerImpl_MembersInjector implements MembersInjector<UserSaveHandlerImpl> {
  private final Provider<Router> routerProvider;
  private final Provider<UserService> userServiceProvider;
  private final Provider<Validator> validatorProvider;

  public UserSaveHandlerImpl_MembersInjector(Provider<Router> routerProvider, Provider<UserService> userServiceProvider, Provider<Validator> validatorProvider) {  
    assert routerProvider != null;
    this.routerProvider = routerProvider;
    assert userServiceProvider != null;
    this.userServiceProvider = userServiceProvider;
    assert validatorProvider != null;
    this.validatorProvider = validatorProvider;
  }

  @Override
  public void injectMembers(UserSaveHandlerImpl instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.router = routerProvider.get();
    instance.userService = userServiceProvider.get();
    instance.validator = validatorProvider.get();
  }

  public static MembersInjector<UserSaveHandlerImpl> create(Provider<Router> routerProvider, Provider<UserService> userServiceProvider, Provider<Validator> validatorProvider) {  
      return new UserSaveHandlerImpl_MembersInjector(routerProvider, userServiceProvider, validatorProvider);
  }
}

