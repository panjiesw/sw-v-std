package com.panjiesw.std.api;

import com.panjiesw.std.api.modules.ApiModule;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import org.junit.After;
import org.junit.Before;

/**
 * @author PanjieSW.
 */
public abstract class AbstractHandlerTest {
  protected Vertx vertx;

  protected HttpClient httpClient;

  @Before
  public void setUp(TestContext context) throws Exception {
    vertx = Vertx.vertx();
    httpClient = vertx
      .createHttpClient(
        new HttpClientOptions().setDefaultHost("localhost").setDefaultPort(7777));

//    UserService userService = new UserServiceMockImpl();
//    ApiModule apiModule = new ApiModule(vertx).userService(userService);
    ApiTestVerticle apiTestVerticle = new ApiTestVerticle(apiModule());
    this.start(r ->
      vertx.deployVerticle(
        apiTestVerticle, new DeploymentOptions().setConfig(r.result()), context.asyncAssertSuccess()));
  }

  @After
  public void tearDown(TestContext context) throws Exception {
    this.close(context.asyncAssertSuccess(res -> vertx.close(context.asyncAssertSuccess())));
  }

  protected abstract ApiModule apiModule();

  protected abstract void start(Handler<AsyncResult<JsonObject>> startFuture);

  protected abstract void close(Handler<AsyncResult<Void>> closeFuture);
}
