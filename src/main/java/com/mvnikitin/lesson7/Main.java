package com.mvnikitin.lesson7;

public class Main {
    public static void main(String[] args) {
        // вызывем start() с объектом Class
        TestRunner.start(Tests.class);

        // вызываем start() с названием класса
        try {
            TestRunner.start("com.mvnikitin.lesson7.Tests");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


//    Initializing tests...
//    Performing Test4 (prioroty: 10).
//    Performing Test2 (prioroty: 3).
//    Performing Test3 (prioroty: 3).
//    Performing Test1 (prioroty: 1).
//    Finishing tests...
//    Initializing tests...
//    Performing Test4 (prioroty: 10).
//    Performing Test2 (prioroty: 3).
//    Performing Test3 (prioroty: 3).
//    Performing Test1 (prioroty: 1).
//    Finishing tests...
