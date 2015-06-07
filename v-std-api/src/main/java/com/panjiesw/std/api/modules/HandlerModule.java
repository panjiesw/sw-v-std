package com.panjiesw.std.api.modules;

import com.panjiesw.std.api.annotations.PerHandler;
import com.panjiesw.std.api.handlers.ApiHandler;
import dagger.Module;
import dagger.Provides;

/**
 * @author PanjieSW.
 */
@Module
public class HandlerModule {
  private final ApiHandler handler;

  public HandlerModule(ApiHandler handler) {
    this.handler = handler;
  }

  @PerHandler
  @Provides
  public ApiHandler handler() {
    return handler;
  }
}
