package com.panjiesw.std.api.components;

import com.panjiesw.std.api.annotations.PerHandler;
import com.panjiesw.std.api.handlers.ApiHandler;
import com.panjiesw.std.api.modules.HandlerModule;
import dagger.Component;

/**
 * @author PanjieSW.
 */
@PerHandler
@Component(modules = HandlerModule.class, dependencies = ApiComponent.class)
public interface AbstractHandlerComponent {
  ApiHandler apiHandler();
}
