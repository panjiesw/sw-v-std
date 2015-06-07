package com.panjiesw.std.api;

import com.panjiesw.std.api.components.ApiComponent;
import com.panjiesw.std.api.handlers.user.UserOneHandler;
import com.panjiesw.std.api.handlers.user.UserSaveHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author PanjieSW.
 */
@Slf4j
public final class ApiRouter {
  public static void create(ApiComponent app) {
    log.info("Registering ApiRouter");
    UserOneHandler.init(app).create();
    UserSaveHandler.init(app).create();
  }
}
