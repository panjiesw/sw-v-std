package com.panjiesw.std.service.user;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.ProxyIgnore;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

import java.util.List;

/**
 * @author PanjieSW.
 */
@ProxyGen
public interface UserService {

  static UserService create(Vertx vertx, JsonObject config) {
    return new UserServiceImpl(vertx, config);
  }

  static UserService createEventBusProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(UserService.class, vertx, address);
  }

  @Fluent
  UserService save(JsonObject payload, Handler<AsyncResult<JsonArray>> resultHandler);

  @Fluent
  UserService query(String sql, Handler<AsyncResult<List<JsonObject>>> resultHandler);

  @Fluent
  UserService queryOne(String sql, Handler<AsyncResult<JsonObject>> resultHandler);

  @Fluent
  UserService one(Long id, Handler<AsyncResult<JsonObject>> resultHandler);

  @Fluent
  UserService findByUsername(String username, Handler<AsyncResult<JsonObject>> resultHandler);

  @Fluent
  UserService findByEmail(String email, Handler<AsyncResult<JsonObject>> resultHandler);

  /**
   * Start the service
   */
  @ProxyIgnore
  void start(Handler<AsyncResult<Void>> whenDone);

  /**
   * Stop the service
   */
  @ProxyClose
  void stop(Handler<AsyncResult<Void>> whenDone);
}
