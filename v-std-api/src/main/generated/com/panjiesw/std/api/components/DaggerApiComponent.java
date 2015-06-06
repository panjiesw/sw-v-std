package com.panjiesw.std.api.components;

import com.panjiesw.std.api.Server;
import com.panjiesw.std.api.Server_MembersInjector;
import com.panjiesw.std.api.modules.ApiModule;
import com.panjiesw.std.api.modules.ApiModule_RouterFactory;
import com.panjiesw.std.api.modules.ApiModule_UserServiceFactory;
import com.panjiesw.std.api.modules.ApiModule_VertxFactory;
import com.panjiesw.std.service.user.UserService;
import dagger.MembersInjector;
import dagger.internal.ScopedProvider;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerApiComponent implements ApiComponent {
  private Provider<Vertx> vertxProvider;
  private Provider<Router> routerProvider;
  private Provider<UserService> userServiceProvider;
  private MembersInjector<Server> serverMembersInjector;

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
    this.userServiceProvider = ScopedProvider.create(ApiModule_UserServiceFactory.create(builder.apiModule));
    this.serverMembersInjector = Server_MembersInjector.create(vertxProvider, routerProvider);
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
  public UserService userService() {  
    return userServiceProvider.get();
  }

  @Override
  public void inject(Server server) {  
    serverMembersInjector.injectMembers(server);
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

