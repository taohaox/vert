package com.gonyb.handle;

import com.gonyb.http.DoResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Gonyb on 2017/9/29.
 */
public class ExceptionHandler implements Handler<RoutingContext>{
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public void handle(RoutingContext routingContext) {
        Throwable failure = routingContext.failure();
        logger.error(failure.getMessage(),failure);
        routingContext.response().end(JsonObject.mapFrom(DoResult.failed(500,failure.getMessage())).toString());
    }

    public static ExceptionHandler create() {
        return new ExceptionHandler();
    }
}
