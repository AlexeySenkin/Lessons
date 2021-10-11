package lesson2;

public class HomeWorkApp2 {

    public static void main(String[] args) {
        int a = 2024;
        int b = -2014;
        String s = "test string";
        System.out.print("1:");
        System.out.println(isSumRange(a,b));
        System.out.print("2:");
        printSign(a);
        System.out.print("3:");
        System.out.println(isSignNegative(a));
        System.out.print("4:");
        printStrings(s,a + b);
        System.out.print("5:");
        System.out.println(isLeapYear(a));
    }

    public static boolean isSumRange(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    public static void printSign(int a) {
        if (isSignNegative(a)) {
            System.out.println("Отрицательное");
        } else {
            System.out.println("Положительное");
        }
    }

    public static boolean isSignNegative(int a) {
        return a < 0;
    }

    public static void printStrings(String str, int printCount) {
        for (int i = 0; i < printCount; i++) {
            System.out.println(str);
        }
    }

    public static boolean isLeapYear(int year) {
        //System.out.println((year % 400) + ", " + (year % 4) + ", " + (year % 100));
        return year % 400 == 0 || ( year % 4 == 0 && year % 100 != 0 );
    }


}
