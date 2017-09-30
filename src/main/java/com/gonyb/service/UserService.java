package com.gonyb.service;

import com.gonyb.handle.ResponseLoggerHandler;
import com.gonyb.http.DoResult;
import com.gonyb.model.User;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by Gonyb on 2017/9/29.
 */
public class UserService {

    public static final UserService newInstance = new UserService();
    private UserService() {
    }

    public void registerUser(RoutingContext routingContext, User user){
        routingContext.put(ResponseLoggerHandler.RESULT_KEY, DoResult.success(user,"注册成功"));
    }
}
