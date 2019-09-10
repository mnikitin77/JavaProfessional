package com.mvnikitin.lesson7;

import java.lang.reflect.Method;

public class Tests {

    @BeforeSuite
    public void init() {
        System.out.println("Initializing tests...");
    }

    @Test
    public void Test1 () throws NoSuchMethodException {
        System.out.println("Performing Test1 (prioroty: " +
                this.getPriority(this.getClass().getMethod("Test1")) + ").");
    }

    @Test(priority = 3)
    public void Test2 () throws NoSuchMethodException {
        System.out.println("Performing Test2 (prioroty: " +
                this.getPriority(this.getClass().getMethod("Test2")) + ").");
    }

    @Test(priority = 3)
    public void Test3 () throws NoSuchMethodException {
        System.out.println("Performing Test3 (prioroty: " +
                this.getPriority(this.getClass().getMethod("Test3")) + ").");
    }

    @Test(priority = 10)
    public void Test4 () throws NoSuchMethodException {
        System.out.println("Performing Test4 (prioroty: " +
                this.getPriority(this.getClass().getMethod("Test4")) + ").");
    }

    @AfterSuite
    public void finish() {
        System.out.println("Finishing tests...");
    }

    private int getPriority(Method method) {
        return method.getAnnotation(Test.class) != null ?
                method.getAnnotation(Test.class).priority() : -1;
    }
}
