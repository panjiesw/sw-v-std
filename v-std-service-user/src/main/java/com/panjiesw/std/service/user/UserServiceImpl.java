package com.panjiesw.std.service.user;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import lombok.extern.slf4j.Slf4j;
import org.jooq.conf.ParamType;
import org.jooq.util.postgres.PostgresDSL;

import java.util.List;

import static com.panjiesw.std.service.user.sql.Tables.STD_USER;

/**
 * @author PanjieSW.
 */
@Slf4j
public class UserServiceImpl implements UserService {
  private final Vertx vertx;
  private final JsonObject config;

  protected AsyncSQLClient postgreService;
  protected SQLConnection connection;

  public UserServiceImpl(Vertx vertx, JsonObject config) {
    this.vertx = vertx;
    this.config = config;
  }

  @Override
  public void start(Handler<AsyncResult<Void>> whenDone) {
    JsonObject dbConfig = new JsonObject()
      .put("host", "localhost")
      .put("database", config.getString("database", "blangszut"))
      .put("username", "blangszut")
      .put("password", "qwe123");

    postgreService = PostgreSQLClient.createShared(vertx, dbConfig);
    postgreService.getConnection(res -> {
      if (res.succeeded()) {
        log.info("PostgreSQL connection is successfully created");
        connection = res.result();
        whenDone.handle(Future.succeededFuture());
        log.info("User Service started");
      } else {
        log.error("Failed to get PostgreSQL connection", res.cause());
        whenDone.handle(Future.failedFuture(res.cause()));
      }
    });
  }

  @Override
  public void stop(Handler<AsyncResult<Void>> whenDone) {
    postgreService.close(whenDone);
  }

  @Override
  public UserService save(JsonObject payload, Handler<AsyncResult<JsonArray>> resultHandler) {
    String sql = PostgresDSL
      .insertInto(STD_USER,
        STD_USER.USERNAME, STD_USER.EMAIL, STD_USER.PASSWORD,
        STD_USER.FULLNAME, STD_USER.STATUS, STD_USER.ROLE_ID)
      .values(payload.getString("username"), payload.getString("email"), payload.getString("password"),
        payload.getString("fullname"), payload.getInteger("status", -1).shortValue(), payload.getLong("roleId"))
      .getSQL(ParamType.INLINED);
    log.trace("Payload: {}, Generated SQL: {}", payload.toString(), sql);
    connection.update(sql, res -> {
      if (res.succeeded()) {
        vertx.runOnContext(v -> resultHandler.handle(Future.succeededFuture(res.result().getKeys())));
      } else {
        vertx.runOnContext(v -> resultHandler.handle(Future.failedFuture(res.cause())));
      }
    });
    return this;
  }

  @Override
  public UserService query(String sql, Handler<AsyncResult<List<JsonObject>>> resultHandler) {
    connection.query(sql, res -> {
      if (res.succeeded()) {
        ResultSet result = res.result();
        vertx.runOnContext(v -> resultHandler.handle(Future.succeededFuture(result.getRows())));
      } else {
        vertx.runOnContext(v -> resultHandler.handle(Future.failedFuture(res.cause())));
      }
    });
    return this;
  }

  @Override
  public UserService queryOne(String sql, Handler<AsyncResult<JsonObject>> resultHandler) {
    connection.query(sql, res -> {
      if (res.succeeded()) {
        ResultSet result = res.result();
        if (result.getNumRows() > 0) {
          vertx.runOnContext(v -> resultHandler.handle(Future.succeededFuture(result.getRows().get(0))));
        } else {
          vertx.runOnContext(v -> resultHandler.handle(Future.failedFuture("")));
        }
      } else {
        vertx.runOnContext(v -> resultHandler.handle(Future.failedFuture(res.cause())));
      }
    });
    return this;
  }

  @Override
  public UserService one(Long id, Handler<AsyncResult<JsonObject>> resultHandler) {
    String sql = PostgresDSL
      .select()
      .from(STD_USER)
      .where(STD_USER.ID.eq(id))
      .getSQL(ParamType.INLINED);
    log.trace("Id: {}, Generated SQL: {}", id, sql);
    queryOne(sql, resultHandler);
    return this;
  }

  @Override
  public UserService findByUsername(String username, Handler<AsyncResult<JsonObject>> resultHandler) {
    String sql = PostgresDSL
      .select()
      .from(STD_USER)
      .where(STD_USER.USERNAME.eq(username))
      .getSQL(ParamType.INLINED);
    log.trace("Username: {}, Generated SQL: {}", username, sql);
    queryOne(sql, resultHandler);
    return this;
  }

  @Override
  public UserService findByEmail(String email, Handler<AsyncResult<JsonObject>> resultHandler) {
    String sql = PostgresDSL
      .select()
      .from(STD_USER)
      .where(STD_USER.EMAIL.eq(email))
      .getSQL(ParamType.INLINED);
    log.trace("Username: {}, Generated SQL: {}", email, sql);
    queryOne(sql, resultHandler);
    return this;
  }
}
