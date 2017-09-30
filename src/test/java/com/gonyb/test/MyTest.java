package com.gonyb.test;

import com.gonyb.UserVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 
 * Created by Gonyb on 2017/9/28.
 */
@RunWith(VertxUnitRunner.class)
public class MyTest {
    
    private Vertx vertx;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        Handler<AsyncResult<String>> completionHandler = context.asyncAssertSuccess();
        vertx.deployVerticle(UserVerticle.class.getName(), completionHandler);
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testApplication(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().getNow(8080, "localhost", "/", response -> {
            response.handler(body -> {
                System.out.println(body);
                context.assertTrue(body.toString().contains("hello world666"));
                async.complete();
            });
        });
    }
}
