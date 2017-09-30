package com.gonyb;

import com.gonyb.handle.BodyAsJsonHandler;
import com.gonyb.handle.ExceptionHandler;
import com.gonyb.handle.RequestLoggerHandler;
import com.gonyb.handle.ResponseLoggerHandler;
import com.gonyb.model.User;
import com.gonyb.service.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * 用户路由表
 * Created by Gonyb on 2017/9/28.
 */
public class UserVerticle extends AbstractVerticle {
    @Override
    public void start(){
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create()); //设置之后  才能获取请求体
        router.route().failureHandler(ExceptionHandler.create()); //异常处理类
        router.route().handler(RequestLoggerHandler.create());
        router.post("/user").handler(BodyAsJsonHandler.create(User.class, UserService.newInstance::registerUser));
        router.route().handler(ResponseLoggerHandler.create());
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router::accept).listen(8080);
    }
}
