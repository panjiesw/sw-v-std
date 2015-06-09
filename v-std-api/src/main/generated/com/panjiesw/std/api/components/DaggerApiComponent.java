package com.panjiesw.std.api.components;

import com.panjiesw.std.api.ApiRouter;
import com.panjiesw.std.api.ApiRouter_MembersInjector;
import com.panjiesw.std.api.Server;
import com.panjiesw.std.api.Server_MembersInjector;
import com.panjiesw.std.api.modules.ApiModule;
import com.panjiesw.std.api.modules.ApiModule_AuthProviderFactory;
import com.panjiesw.std.api.modules.ApiModule_RouterFactory;
import com.panjiesw.std.api.modules.ApiModule_UserServiceFactory;
import com.panjiesw.std.api.modules.ApiModule_ValidatorFactory;
import com.panjiesw.std.api.modules.ApiModule_VerticleConfigFactory;
import com.panjiesw.std.api.modules.ApiModule_VertxFactory;
import com.panjiesw.std.service.user.UserService;
import dagger.MembersInjector;
import dagger.internal.ScopedProvider;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.Router;
import javax.annotation.Generated;
import javax.inject.Provider;
import javax.validation.Validator;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerApiComponent implements ApiComponent {
  private Provider<Vertx> vertxProvider;
  private Provider<Router> routerProvider;
  private Provider<JWTAuth> authProvider;
  private Provider<UserService> userServiceProvider;
  private Provider<Validator> validatorProvider;
  private MembersInjector<Server> serverMembersInjector;
  private Provider<JsonObject> verticleConfigProvider;
  private MembersInjector<ApiRouter> apiRouterMembersInjector;

  private DaggerApiComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.vertxProvider = ScopedProvider.create(ApiModule_VertxFactory.create(builder.apiModule));
    this.routerProvider = ScopedProvider.create(ApiModule_RouterFactory.create(builder.apiModule));
    this.authProvider = ScopedProvider.create(ApiModule_AuthProviderFactory.create(builder.apiModule));
    this.userServiceProvider = ScopedProvider.create(ApiModule_UserServiceFactory.create(builder.apiModule));
    this.validatorProvider = ScopedProvider.create(ApiModule_ValidatorFactory.create(builder.apiModule));
    this.serverMembersInjector = Server_MembersInjector.create(vertxProvider, routerProvider);
    this.verticleConfigProvider = ScopedProvider.create(ApiModule_VerticleConfigFactory.create(builder.apiModule));
    this.apiRouterMembersInjector = ApiRouter_MembersInjector.create(authProvider, routerProvider, vertxProvider, verticleConfigProvider);
  }

  @Override
  public Vertx vertx() {  
    return vertxProvider.get();
  }

  @Override
  public Router router() {  
    return routerProvider.get();
  }

  @Override
  public JWTAuth authProvider() {  
    return authProvider.get();
  }

  @Override
  public UserService userService() {  
    return userServiceProvider.get();
  }

  @Override
  public Validator validator() {  
    return validatorProvider.get();
  }

  @Override
  public void inject(Server server) {  
    serverMembersInjector.injectMembers(server);
  }

  @Override
  public void inject(ApiRouter router) {  
    apiRouterMembersInjector.injectMembers(router);
  }

  public static final class Builder {
    private ApiModule apiModule;
  
    private Builder() {  
    }
  
    public ApiComponent build() {  
      if (apiModule == null) {
        throw new IllegalStateException("apiModule must be set");
      }
      return new DaggerApiComponent(this);
    }
  
    public Builder apiModule(ApiModule apiModule) {  
      if (apiModule == null) {
        throw new NullPointerException("apiModule");
      }
      this.apiModule = apiModule;
      return this;
    }
  }
}

