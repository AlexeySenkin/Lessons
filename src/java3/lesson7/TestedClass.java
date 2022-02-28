package java3.lesson7;

public class TestedClass {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Выполнение BeforeSuite");
    }
    @AfterSuite
    public void AfterSuite() {
        System.out.println("Выполнение AfterSuite");
    }
   @Test
    public void test1() {
        System.out.println("Тест 1 - ОК");
    }
    @Test(priority = 2)
    public void test2() {
        System.out.println("Тест 2 - ОК");
    }
    @Test(priority = 3)
    public void test3() {
        System.out.println("Тест 3 - ОК");
    }
    @Test(priority = 4)
    public void test4() {
        System.out.println("Тест 4 - ОК");
    }
    @Test(priority = 5)
    public void test5() {
        System.out.println("Тест 5 - ОК");
    }
    @Test(priority = 6)
    public void test6() {
        System.out.println("Тест 6 - ОК");
    }
    @Test(priority = 7)
    public void test7() {
        System.out.println("Тест 7 - ОК");
    }
    @Test(priority = 8)
    public void test8() {
        System.out.println("Тест 8 - ОК");
    }
    @Test(priority = 9)
    public void test9() {
        System.out.println("Тест 9 - ОК");
    }
    @Test(priority = 10)
    public void test10() {
        System.out.println("Тест 10 - ОК");
    }


}
