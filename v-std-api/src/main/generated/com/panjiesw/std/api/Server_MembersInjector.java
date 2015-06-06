package com.panjiesw.std.api;

import dagger.MembersInjector;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class Server_MembersInjector implements MembersInjector<Server> {
  private final Provider<Vertx> vertxProvider;
  private final Provider<Router> routerProvider;

  public Server_MembersInjector(Provider<Vertx> vertxProvider, Provider<Router> routerProvider) {  
    assert vertxProvider != null;
    this.vertxProvider = vertxProvider;
    assert routerProvider != null;
    this.routerProvider = routerProvider;
  }

  @Override
  public void injectMembers(Server instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.vertx = vertxProvider.get();
    instance.router = routerProvider.get();
  }

  public static MembersInjector<Server> create(Provider<Vertx> vertxProvider, Provider<Router> routerProvider) {  
      return new Server_MembersInjector(vertxProvider, routerProvider);
  }
}

