package com.panjiesw.std.api.handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * @author PanjieSW.
 */
public interface ApiHandler extends Handler<RoutingContext> {
  void create();
}
