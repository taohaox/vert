package com.gonyb;

import io.vertx.core.AbstractVerticle;

/**
 * 在pom文件里面配置了   启动的时候 会调用这个start方法
 * Created by Gonyb on 2017/9/28.
 */
public class MainVerticle extends AbstractVerticle {
    @Override
    public void start(){
        vertx.deployVerticle(UserVerticle.class.getName());
    }
}
