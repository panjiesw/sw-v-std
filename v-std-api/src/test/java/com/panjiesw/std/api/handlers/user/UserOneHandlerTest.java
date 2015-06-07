package com.panjiesw.std.api.handlers.user;

import com.panjiesw.std.api.ApiTestVerticle;
import com.panjiesw.std.api.UserServiceMockImpl;
import com.panjiesw.std.api.modules.ApiModule;
import com.panjiesw.std.service.user.UserService;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.HttpURLConnection;

/**
 * @author PanjieSW.
 */
@RunWith(VertxUnitRunner.class)
public class UserOneHandlerTest {
  private Vertx vertx;

  private HttpClient httpClient;

  @Before
  public void setUp(TestContext context) throws Exception {
    vertx = Vertx.vertx();
    httpClient = vertx
      .createHttpClient(
        new HttpClientOptions().setDefaultHost("localhost").setDefaultPort(7777));

    UserService userService = new UserServiceMockImpl();
    ApiModule apiModule = new ApiModule(vertx).userService(userService);
    ApiTestVerticle apiTestVerticle = new ApiTestVerticle(apiModule);
    vertx.deployVerticle(apiTestVerticle, context.asyncAssertSuccess());
  }

  @After
  public void tearDown() throws Exception {
    vertx.close();
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
