package java3.lesson7;

public class HomeWorkApp7 {
    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("java3.lesson7.TestedClass");
            Tester.start(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
