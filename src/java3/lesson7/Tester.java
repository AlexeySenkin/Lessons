package java3.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Tester {

    public static void start(Class<?> aClass) {
        try {
            Object testObject = aClass.newInstance();
            Method[] methods = aClass.getDeclaredMethods();
            int beforeSuiteCount = 0;
            int afterSuiteCount = 0;
            for (Method m : methods) {
                if(m.getAnnotation(BeforeSuite.class) != null) {
                    beforeSuiteCount++;
                }
                if(m.getAnnotation(AfterSuite.class) != null) {
                    afterSuiteCount++;
                }
            }
            if (beforeSuiteCount > 1 || afterSuiteCount > 1) {
                throw new RuntimeException("Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать " +
                        "в единственном экземпляре");
            }
            for (Method m : methods) {
                if(m.getAnnotation(BeforeSuite.class) != null) {
                    m.invoke(testObject);
                }
            }
            int maxPriority = 1;
            int minPriority = 1;
            for (Method m : methods) {
                if(m.getAnnotation(Test.class) != null) {
                    maxPriority = Math.max(maxPriority, m.getAnnotation(Test.class).priority());
                    minPriority = Math.min(minPriority, m.getAnnotation(Test.class).priority());
                }
            }
            for (int i = minPriority; i <= maxPriority; i++) {
                for (Method m : methods)
                    if ((m.getAnnotation(Test.class) != null) && (m.getAnnotation(Test.class).priority() == i)) {
                        m.invoke(testObject);
                    }
            }
            for (Method m : methods) {
                if(m.getAnnotation(AfterSuite.class) != null) {
                    m.invoke(testObject);
                }
            }
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
