package com.gonyb.handle;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;

/**
 * 将请求body 转为实体对象
 * Created by Gonyb on 2017/9/29.
 */
public class BodyAsJsonHandler<T> implements Handler<RoutingContext> {
    private static final Logger logger = LoggerFactory.getLogger(BodyAsJsonHandler.class);
    private Class<T> type;
    private BiConsumer<RoutingContext,T> biConsumer;

    private BodyAsJsonHandler(Class<T> type, BiConsumer<RoutingContext, T> biConsumer) {
        this.type = type;
        this.biConsumer = biConsumer;
    }

    @Override
    public void handle(RoutingContext event) {
        handlerBodyAsJson(event,type,biConsumer);
    }

    private void handlerBodyAsJson(RoutingContext routingContext, Class<T> type, BiConsumer<RoutingContext,T> biConsumer){
        if (!routingContext.request().method().equals(HttpMethod.GET)) {
            String arg = routingContext.getBodyAsJson().toString();
            logger.info("request body:{}", arg);
        }
        JsonObject bodyAsJson = routingContext.getBodyAsJson();
        T t = bodyAsJson.mapTo(type);
        biConsumer.accept(routingContext,t);
        routingContext.next();
    }

    public static <T>BodyAsJsonHandler<T> create(Class<T> type, BiConsumer<RoutingContext,T> biConsumer) {
        return new BodyAsJsonHandler<>(type,biConsumer);
    }
}
