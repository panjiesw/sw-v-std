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
public class UserOneHandlerTest extends AbstractHandlerTest {

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
    httpClient.getNow("/users/1", res -> {
      context.assertEquals(res.statusCode(), HttpURLConnection.HTTP_OK);
      res.bodyHandler(b -> {
        JsonObject obj = new JsonObject(new String(b.getBytes()));
        context.assertEquals(obj.getLong("id"), 1L);
        async.complete();
      });
    });
  }

  @Test
  public void testHandleNotFound(TestContext context) throws Exception {
    Async async = context.async();
    httpClient.getNow("/users/6", res -> {
      context.assertEquals(res.statusCode(), HttpURLConnection.HTTP_NOT_FOUND);
      async.complete();
    });
  }

  @Test
  public void testHandleException(TestContext context) throws Exception {
    Async async = context.async();
    httpClient.getNow("/users/2", res -> {
      context.assertEquals(res.statusCode(), HttpURLConnection.HTTP_INTERNAL_ERROR);
      async.complete();
    });
  }
}
