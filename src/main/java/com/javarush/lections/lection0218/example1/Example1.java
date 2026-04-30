package com.javarush.lections.lection0218.example1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;

public class Example1 {
    public static void main(String[] args) throws Exception {
        ThreadLocal<Credentials> securityContext = new ThreadLocal<>();
        securityContext.set(new Credentials(Set.of(Role.USER, Role.ADMIN)));

        Method method = Example1.class.getDeclaredMethod("printSecret");
        executeWithSecurity(securityContext, method);
    }

    // just for static methods
    public static void executeWithSecurity(ThreadLocal<Credentials> securityContext, Method method, Object...args) throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);

        Set<Role> userRoles = Optional.ofNullable(securityContext.get())
                .map(Credentials::getRoles)
                .orElse(Set.of());

        if(method.isAnnotationPresent(Security.class)){
            Security annotation = method.getAnnotation(Security.class);
            Role roleFromAnnotation = annotation.role();

            if(userRoles.contains(roleFromAnnotation)) {
                method.invoke(null, args);
            } else throw new RuntimeException();
        } else {
            throw new RuntimeException();
        }
    }

    @Security(role = Role.ADMIN)
    public static void printSecret(){
        System.out.println("Password: ....");
    }
    @Security
    public static void print(){}
}

