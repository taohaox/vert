package com.gonyb;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * vert.x的main
 * Created by Gonyb on 2017/9/28.
 */
public class Main {
    public static void main(String[] args){
        VertxOptions vo = new VertxOptions();
        vo.setEventLoopPoolSize(Runtime.getRuntime().availableProcessors() * 2);//EventLoop 线程池数量为2倍物理CPU数量
        Vertx vertx = Vertx.vertx(vo);
        DeploymentOptions options = new DeploymentOptions();
        options.setInstances(Runtime.getRuntime().availableProcessors() * 8);//8倍cpu的instance
        vertx.deployVerticle(UserVerticle.class.getName(), options);
    }
}
