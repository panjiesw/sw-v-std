package com.panjiesw.std.api;

import com.panjiesw.std.api.components.ApiComponent;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.handler.LoggerHandler.Format;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

/**
 * @author PanjieSW.
 */
@Slf4j
public final class Server {
  @Inject
  Vertx vertx;

  @Inject
  Router router;

  private Server() {
  }

  public void start() {
    vertx.createHttpServer().requestHandler(router::accept).listen(7777);
  }

  private void setupRootHandlers() {
    router.route().handler(LoggerHandler.create(Format.SHORT));
  }

  public static Server init(ApiComponent app) {
    log.info("Initializing Server");

    Server server = new Server();
    app.inject(server);
    server.setupRootHandlers();

    ApiRouter.create(app);

    return server;
  }
}
