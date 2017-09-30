package com.gonyb.handle;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.net.SocketAddress;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求日志记录
 * Created by Gonyb on 2017/9/29.
 */
public class RequestLoggerHandler implements Handler<RoutingContext> {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggerHandler.class);
    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        logger.info("==========开始请求=========");
        SocketAddress socketAddress = request.localAddress();
        String uri = request.uri();
        logger.info("request path:{}{}", socketAddress , uri);
        logger.info("request method:{}", request.method());
        if (!request.method().equals(HttpMethod.GET)) {
            String arg = routingContext.getBodyAsJson().toString();
            logger.info("request body:{}", arg);
        }
        // 所有的请求都会调用这个处理器处理
        routingContext.next();
    }
    public static RequestLoggerHandler create() {
        return new RequestLoggerHandler();
    }
}
