package com.panjiesw.std.service.user;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.jooq.conf.ParamType;
import org.jooq.util.postgres.PostgresDSL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.panjiesw.std.service.user.sql.Tables.STD_USER;

/**
 * @author PanjieSW.
 */
@RunWith(VertxUnitRunner.class)
public class UserServiceTest {
  private Vertx vertx;
  private UserService userService;
  private AsyncSQLClient postgreService;
  private SQLConnection connection;

  @Before
  public void setUp(TestContext context) throws Exception {
    Async async = context.async();
    vertx = Vertx.vertx();

    JsonObject config = new JsonObject()
      .put("host", "localhost")
      .put("database", "blangszut-test")
      .put("username", "blangszut")
      .put("password", "qwe123");
    userService = UserService.create(vertx, config);
    userService.start(res -> {
      try {
        URL url = getClass().getResource("/schema/core.sql");
        String core = new String(Files.readAllBytes(Paths.get(url.toURI())));
        postgreService = PostgreSQLClient.createShared(vertx, config);
        postgreService.getConnection(conn -> {
          connection = conn.result();
          connection.execute(core, rr -> async.complete());
        });
      } catch (URISyntaxException | IOException e) {
        context.fail(e);
        async.complete();
      }
    });
  }

  @After
  public void tearDown(TestContext context) throws Exception {
    connection.execute(
      "drop table std_user cascade; drop table std_role cascade;",
      context.asyncAssertSuccess(res -> vertx.close(context.asyncAssertSuccess())));
  }

  @Test
  public void testVerticle(TestContext context) throws Exception {
    vertx.deployVerticle(
      UserServiceVerticle.class.getName(),
      context.asyncAssertSuccess(deploymentID ->
        vertx.undeploy(deploymentID, context.asyncAssertSuccess())));
  }

  @Test
  public void testProxy(TestContext context) throws Exception {
    vertx.deployVerticle(
      UserServiceVerticle.class.getName(),
      context.asyncAssertSuccess(deploymentID -> {
        UserService proxyService = UserService.createEventBusProxy(vertx, "blangszut.service-user");
        context.assertNotNull(proxyService);
        vertx.undeploy(deploymentID, context.asyncAssertSuccess());
      }));
  }

  @Test
  public void testSave(TestContext context) throws Exception {
    JsonObject payload = new JsonObject()
      .put("username", "anuanu")
      .put("email", "anuanu")
      .put("password", "anuanu")
      .put("fullname", "anuanu")
      .put("roleId", 1);
    userService.save(payload, context.asyncAssertSuccess(res -> {
      String sql = PostgresDSL.select(STD_USER.USERNAME)
        .from(STD_USER)
        .where(STD_USER.USERNAME.equal("anuanu"))
        .getSQL(ParamType.INLINED);
      connection.query(sql, context.asyncAssertSuccess(q ->
        context.assertEquals(q.getResults().get(0).getString(0), "anuanu")));
    }));
  }

  @Test
  public void testQuery(TestContext context) throws Exception {
    String sql = "SELECT * FROM std_user";
    userService.query(sql, context.asyncAssertSuccess(res -> {
      context.assertNotNull(res);
      context.assertEquals(res.size(), 1);
    }));
  }

  @Test
  public void testQueryOne(TestContext context) throws Exception {
    String sql = "SELECT * FROM std_user WHERE username='admin'";
    userService.query(sql, context.asyncAssertSuccess(context::assertNotNull));
  }

  @Test
  public void testOne(TestContext context) throws Exception {
    userService.one(1L, context.asyncAssertSuccess(res -> {
      context.assertNotNull(res);
      context.assertEquals(res.getString("username"), "admin");
    }));
  }

  @Test
  public void testFindByUsername(TestContext context) throws Exception {
    userService.findByUsername("admin", context.asyncAssertSuccess(res -> {
      context.assertNotNull(res);
      context.assertEquals(res.getString("username"), "admin");
    }));
  }

  @Test
  public void testFindByEmail(TestContext context) throws Exception {
    userService.findByEmail("panjie@panjiesw.com", context.asyncAssertSuccess(res -> {
      context.assertNotNull(res);
      context.assertEquals(res.getString("username"), "admin");
    }));
  }
}
