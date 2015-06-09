package com.panjiesw.std.api.handlers.user;

import com.panjiesw.std.api.AbstractHandlerTest;
import com.panjiesw.std.api.UserServiceMockImpl;
import com.panjiesw.std.api.modules.ApiModule;
import com.panjiesw.std.service.user.UserService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.HttpURLConnection;

/**
 * @author PanjieSW.
 */
@RunWith(VertxUnitRunner.class)
public class UserSaveHandlerTest extends AbstractHandlerTest {

  @Override
  protected ApiModule apiModule() {
    UserService userService = new UserServiceMockImpl();
    return new ApiModule(vertx).userService(userService);
  }

  @Override
  protected void start(Handler<AsyncResult<JsonObject>> startFuture) {
    startFuture.handle(
      Future.succeededFuture(
        new JsonObject()
          .put("auth", new JsonObject()
            .put("enable", false))));
  }

  @Override
  protected void close(Handler<AsyncResult<Void>> closeFuture) {
    closeFuture.handle(Future.succeededFuture());
  }

  @Test
  public void testHandleSuccess(TestContext context) throws Exception {
    Async async = context.async();
    httpClient.post("/users", response -> {
      context.assertEquals(response.statusCode(), HttpURLConnection.HTTP_CREATED);
      async.complete();
    }).putHeader("content-type", "application/json")
      .setChunked(true)
      .write(new JsonObject().put("username", "something").put("email", "something@somewhere.com").encode())
      .end();
  }

  @Test
  public void testHandleInvalidRequest(TestContext context) throws Exception {
    Async async = context.async();
    httpClient.post("/users", response -> {
      context.assertEquals(response.statusCode(), HttpURLConnection.HTTP_BAD_REQUEST);
      response.bodyHandler(b -> {
        JsonObject obj = new JsonObject(new String(b.getBytes()));
        JsonObject errors = obj.getJsonObject("errors");
        context.assertEquals(errors.getString("email"), "may not be empty");
        async.complete();
      });
    }).putHeader("content-type", "application/json")
      .setChunked(true)
      .write(new JsonObject().put("username", "something").encode())
      .end();
  }
}
