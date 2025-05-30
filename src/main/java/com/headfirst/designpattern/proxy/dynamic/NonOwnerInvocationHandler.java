package com.headfirst.designpattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class NonOwnerInvocationHandler implements InvocationHandler {
    private Person person;

    public NonOwnerInvocationHandler(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (methodName.startsWith("get")) {
            // Allow all get methods
            return method.invoke(person, args);
        } else if (methodName.startsWith("set")) {
            // Only allow set methods for certain properties
            if ("setInterests".equals(methodName) || "setGeekRating".equals(methodName)) {
                return method.invoke(person, args);
            } else {
                throw new IllegalAccessException("You cannot set this property");
            }
        }
        return null;
    }
    
}
