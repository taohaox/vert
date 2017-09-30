package com.gonyb.handle;

import com.gonyb.http.DoResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 返回日志打印
 * Created by Gonyb on 2017/9/29.
 */
public class ResponseLoggerHandler implements Handler<RoutingContext> {
    private static final Logger logger = LoggerFactory.getLogger(ResponseLoggerHandler.class);
    public static final String RESULT_KEY="result";
    @Override
    public void handle(RoutingContext routingContext) {
        // 所有的请求都会调用这个处理器处理
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=utf-8");
        if(!response.ended()){
            logger.info("=========响应结果========");
            DoResult doResult = routingContext.get(RESULT_KEY);
            if (doResult == null) {
                response.setStatusCode(404);
                response.setStatusMessage("请求方法错误或请求路径不存在");
                String msg = JsonObject.mapFrom(DoResult.failed(response.getStatusCode(), response.getStatusMessage())).toString();
                logger.info(msg);
                response.end(msg);
            } else {
                String result = JsonObject.mapFrom(doResult).toString();
                logger.info(result);
                // 写入响应并结束处理
                response.end(result);
            }
        }
    }
    public static ResponseLoggerHandler create() {
        return new ResponseLoggerHandler();
    }
}
