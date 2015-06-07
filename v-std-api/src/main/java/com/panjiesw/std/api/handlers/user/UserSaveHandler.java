package com.panjiesw.std.api.handlers.user;

import com.panjiesw.std.api.components.ApiComponent;
import com.panjiesw.std.api.handlers.ApiHandler;
import com.panjiesw.std.api.handlers.user.impl.DaggerUserSaveHandlerImpl_HandlerComponent;
import com.panjiesw.std.api.handlers.user.impl.UserSaveHandlerImpl;
import com.panjiesw.std.api.handlers.user.impl.UserSaveHandlerImpl.HandlerComponent;
import com.panjiesw.std.api.modules.HandlerModule;

/**
 * @author PanjieSW.
 */
public interface UserSaveHandler extends ApiHandler {
  static UserSaveHandler init(ApiComponent app) {
    UserSaveHandlerImpl userSaveHandler = new UserSaveHandlerImpl();
    HandlerComponent component = DaggerUserSaveHandlerImpl_HandlerComponent.builder()
      .apiComponent(app)
      .handlerModule(new HandlerModule(userSaveHandler))
      .build();
    return component.inject(userSaveHandler);
  }
}
