package com.panjiesw.std.api.handlers.user.impl;

import com.panjiesw.std.api.components.ApiComponent;
import com.panjiesw.std.api.handlers.ApiHandler;
import com.panjiesw.std.api.handlers.user.impl.UserOneHandlerImpl.HandlerComponent;
import com.panjiesw.std.api.modules.HandlerModule;
import com.panjiesw.std.api.modules.HandlerModule_HandlerFactory;
import com.panjiesw.std.service.user.UserService;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.ScopedProvider;
import io.vertx.ext.web.Router;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerUserOneHandlerImpl_HandlerComponent implements HandlerComponent {
  private Provider<ApiHandler> handlerProvider;
  private Provider<Router> routerProvider;
  private Provider<UserService> userServiceProvider;
  private MembersInjector<UserOneHandlerImpl> userOneHandlerImplMembersInjector;

  private DaggerUserOneHandlerImpl_HandlerComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.handlerProvider = ScopedProvider.create(HandlerModule_HandlerFactory.create(builder.handlerModule));
    this.routerProvider = new Factory<Router>() {
      @Override public Router get() {
        Router provided = builder.apiComponent.router();
        if (provided == null) {
          throw new NullPointerException("Cannot return null from a non-@Nullable component method");
        }
        return provided;
      }
    };
    this.userServiceProvider = new Factory<UserService>() {
      @Override public UserService get() {
        UserService provided = builder.apiComponent.userService();
        if (provided == null) {
          throw new NullPointerException("Cannot return null from a non-@Nullable component method");
        }
        return provided;
      }
    };
    this.userOneHandlerImplMembersInjector = UserOneHandlerImpl_MembersInjector.create(routerProvider, userServiceProvider);
  }

  @Override
  public ApiHandler apiHandler() {  
    return handlerProvider.get();
  }

  @Override
  public UserOneHandlerImpl inject(UserOneHandlerImpl userOneHandler) {  
    userOneHandlerImplMembersInjector.injectMembers(userOneHandler);
    return userOneHandler;
  }

  public static final class Builder {
    private HandlerModule handlerModule;
    private ApiComponent apiComponent;
  
    private Builder() {  
    }
  
    public HandlerComponent build() {  
      if (handlerModule == null) {
        throw new IllegalStateException("handlerModule must be set");
      }
      if (apiComponent == null) {
        throw new IllegalStateException("apiComponent must be set");
      }
      return new DaggerUserOneHandlerImpl_HandlerComponent(this);
    }
  
    public Builder handlerModule(HandlerModule handlerModule) {  
      if (handlerModule == null) {
        throw new NullPointerException("handlerModule");
      }
      this.handlerModule = handlerModule;
      return this;
    }
  
    public Builder apiComponent(ApiComponent apiComponent) {  
      if (apiComponent == null) {
        throw new NullPointerException("apiComponent");
      }
      this.apiComponent = apiComponent;
      return this;
    }
  }
}

