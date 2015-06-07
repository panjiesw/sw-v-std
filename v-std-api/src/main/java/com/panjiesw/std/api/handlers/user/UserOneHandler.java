package com.panjiesw.std.api.handlers.user;

import com.panjiesw.std.api.components.ApiComponent;
import com.panjiesw.std.api.handlers.ApiHandler;
import com.panjiesw.std.api.handlers.user.impl.DaggerUserOneHandlerImpl_HandlerComponent;
import com.panjiesw.std.api.handlers.user.impl.UserOneHandlerImpl;
import com.panjiesw.std.api.handlers.user.impl.UserOneHandlerImpl.HandlerComponent;
import com.panjiesw.std.api.modules.HandlerModule;

/**
 * @author PanjieSW.
 */
public interface UserOneHandler extends ApiHandler {
  static UserOneHandler init(ApiComponent app) {
    UserOneHandlerImpl userOneHandler = new UserOneHandlerImpl();
    HandlerComponent component = DaggerUserOneHandlerImpl_HandlerComponent.builder()
      .apiComponent(app)
      .handlerModule(new HandlerModule(userOneHandler))
      .build();
    return component.inject(userOneHandler);
  }
}
