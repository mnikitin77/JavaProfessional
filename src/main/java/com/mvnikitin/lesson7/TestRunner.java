package com.mvnikitin.lesson7;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRunner {

    public static final Class PRETEST_ANNOTATION = BeforeSuite.class;
    public static final Class POSTTEST_ANNOTATION = AfterSuite.class;
    public static final Class TEST_ANNOTATION = Test.class;

    public static void start(Class cls) {
        Object testClass;

        try {
            testClass = cls.newInstance();

        // Execute preconditions (@BeforeSuite)
            executeAuxilliaries(testClass,
                    getMarkedMethods(
                            testClass.getClass().getMethods(),
                            PRETEST_ANNOTATION),
                    PRETEST_ANNOTATION);

        // Run tests (@Test) by priority
            Method[] tests = getMarkedMethods(
                    testClass.getClass().getMethods(),
                    TEST_ANNOTATION);

            // Sorting test methods by priority
            Arrays.sort(tests, (t1, t2) ->
                t2.getAnnotation(Test.class).priority() -
                        t1.getAnnotation(Test.class).priority());

            for (Method m: tests) {
                m.invoke(testClass);
            }

        // Execute post-conditions (@AfterSuite)
            executeAuxilliaries(testClass,
                    getMarkedMethods(
                            testClass.getClass().getMethods(),
                            POSTTEST_ANNOTATION),
                    POSTTEST_ANNOTATION);

        } catch (InstantiationException |
                IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void start(String className) throws ClassNotFoundException {
        start(Class.forName(className));
    }

    private static Method[] getMarkedMethods(Method[] methods,
                                             Class annotation) {
        List<Method> list = new ArrayList<>();

        for (Method m: methods) {
            Annotation a = m.getAnnotation(annotation);
            if(a != null)
                list.add(m);
        }

        return list.toArray(new Method[list.size()]);
    }

    private static void executeAuxilliaries(
            Object obj, Method[] methods, Class marker)
            throws InvocationTargetException, IllegalAccessException {
        if(methods.length > 1) {
            throw new RuntimeException("Class " +
                    obj.getClass().getSimpleName() +
                    " has more than 1 method marked @" +
                    marker.getSimpleName() + ".");
        } else if (methods != null) {
            methods[0].invoke(obj);
        }
    }
}
