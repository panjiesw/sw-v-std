package com.panjiesw.std.api;

import com.panjiesw.std.common.exceptions.NotFoundException;
import com.panjiesw.std.common.exceptions.ServiceException;
import com.panjiesw.std.service.user.UserService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.*;

/**
 * @author PanjieSW.
 */
public class UserServiceMockImpl implements UserService {

  private final Map<Long, JsonObject> users = new LinkedHashMap<>(5);
  private final List<Long> ids = new LinkedList<>();

  public UserServiceMockImpl() {
    users.put(1L, new JsonObject().put("id", 1L).put("username", "someone"));
    users.put(2L, new JsonObject().put("id", 2L));
    ids.add(1L);
    ids.add(2L);
  }

  @Override
  public UserService save(JsonObject payload, Handler<AsyncResult<JsonArray>> resultHandler) {
    if (!Objects.equals(payload.getString("username"), "error")) {
      payload.put("id", ids.get(ids.size() - 1));
      users.put(payload.getLong("id"), payload);
      resultHandler.handle(Future.succeededFuture(new JsonArray()));
    } else {
      resultHandler.handle(Future.failedFuture(new Exception("dummy")));
    }
    return this;
  }

  @Override
  public UserService query(String sql, Handler<AsyncResult<List<JsonObject>>> resultHandler) {
    return this;
  }

  @Override
  public UserService queryOne(String sql, Handler<AsyncResult<JsonObject>> resultHandler) {
    return this;
  }

  @Override
  public UserService one(Long id, Handler<AsyncResult<JsonObject>> resultHandler) {
    if (id.equals(2L)) {
      resultHandler.handle(Future.failedFuture(new ServiceException("Unknown Exception", new Exception("Dummy"))));
    } else {
      if (users.containsKey(id)) {
        resultHandler.handle(Future.succeededFuture(users.get(id)));
      } else {
        resultHandler.handle(Future.failedFuture(new NotFoundException("id")));
      }
    }
    return this;
  }

  @Override
  public UserService findByUsername(String username, Handler<AsyncResult<JsonObject>> resultHandler) {
    return this;
  }

  @Override
  public UserService findByEmail(String email, Handler<AsyncResult<JsonObject>> resultHandler) {
    return this;
  }

  @Override
  public void start(Handler<AsyncResult<Void>> whenDone) {
    whenDone.handle(Future.succeededFuture());
  }

  @Override
  public void stop(Handler<AsyncResult<Void>> whenDone) {
    whenDone.handle(Future.succeededFuture());
  }
}
