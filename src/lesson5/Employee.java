package lesson5;

public class Employee {
    private String fullName;
    private String sex;
    private double age;
    private String post;
    private String eMail;
    private int phoneNumber;
    private double salary;

    public Employee(String fullName, String sex, double age, String post, int phoneNumber, String eMail, double salary) {
        this.fullName = fullName;
        this.sex = sex;
        this.age = age;
        this.post = post;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }


    public void printInfo() {
        System.out.println("Сотрудник:");
        System.out.println("ФИО :" + fullName);
        System.out.println("пол :" + sex);
        System.out.println("возраст :" + age);
        System.out.println("должность :" + post);
        System.out.println("email :" + eMail);
        System.out.println("# телефона :" + phoneNumber);
        System.out.println("заработная плата :" + salary);
        System.out.println("-------------");
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String GetFullName() {
        return fullName;
    }

    public void setSex(String sex) {
        if (sex.equals("мужской") || sex.equals("женский")) {
            this.sex = sex;
        } else {
            System.out.println("Неверно указан пол сотрудника! Только мужской или женский");
        }
    }

    public String getSex() {
        return sex;
    }

    public void setAge(double age) {
        if (age >= 18 && age < 100) {
            this.age = age;
        } else {
            System.out.println("Неверно указан возраст сотрудника! Только от 18 до 100 лет");
        }
    }

    public double getAge() {
        return age;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }


    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }


}
