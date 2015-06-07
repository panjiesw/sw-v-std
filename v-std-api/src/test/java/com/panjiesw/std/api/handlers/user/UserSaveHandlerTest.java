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
public class UserSaveHandlerTest {

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
