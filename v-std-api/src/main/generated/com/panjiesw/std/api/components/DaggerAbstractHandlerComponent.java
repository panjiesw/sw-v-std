package com.panjiesw.std.api.components;

import com.panjiesw.std.api.handlers.ApiHandler;
import com.panjiesw.std.api.modules.HandlerModule;
import com.panjiesw.std.api.modules.HandlerModule_HandlerFactory;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerAbstractHandlerComponent implements AbstractHandlerComponent {
  private Provider<ApiHandler> handlerProvider;

  private DaggerAbstractHandlerComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.handlerProvider = ScopedProvider.create(HandlerModule_HandlerFactory.create(builder.handlerModule));
  }

  @Override
  public ApiHandler apiHandler() {  
    return handlerProvider.get();
  }

  public static final class Builder {
    private HandlerModule handlerModule;
    private ApiComponent apiComponent;
  
    private Builder() {  
    }
  
    public AbstractHandlerComponent build() {  
      if (handlerModule == null) {
        throw new IllegalStateException("handlerModule must be set");
      }
      if (apiComponent == null) {
        throw new IllegalStateException("apiComponent must be set");
      }
      return new DaggerAbstractHandlerComponent(this);
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

