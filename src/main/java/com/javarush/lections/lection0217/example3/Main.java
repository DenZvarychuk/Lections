package com.javarush.lections.lection0217.example3;

import java.lang.reflect.Proxy;

public class Main {
    static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        System.out.println("Static Proxy");
        UserServiceLogger userServiceLogger = new UserServiceLogger(userService);
        User byId = userServiceLogger.getById(123);
        System.out.println(byId);

        System.out.println("Dynamic Proxy");
        UserService proxy = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),
                new Class<?>[]{UserService.class},
                new GeneralLoggerHandler(userService));

        proxy.getById(byId.id());
        System.out.println(byId.id());

    }
}


