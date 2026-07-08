// ===== 类与对象 =====

// 定义一个学生类
class Student {
    // 属性（成员变量）
    String name;
    int age;
    double score;
    static int studentCount = 0;  // 静态变量，统计学生数量

    // 构造方法
    Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.score = 0;
        studentCount++;  // 每创建一个学生，计数加1
    }

    // 构造方法重载
    Student(String name, int age, double score) {
        this(name, age);  // 调用另一个构造方法
        this.score = score;
    }

    // 方法
    void study(String subject) {
        System.out.println(name + "正在学习" + subject);
    }

    void showInfo() {
        System.out.println("姓名: " + name + ", 年龄: " + age + ", 成绩: " + score);
    }

    // 静态方法
    static int getStudentCount() {
        return studentCount;
    }

    // 实例方法：比较成绩
    boolean hasHigherScore(Student other) {
        return this.score > other.score;
    }

    // toString 方法
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", score=" + score + "}";
    }
}

// 定义一个银行账户类
class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;

    // 构造方法
    BankAccount(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0;
    }

    // 存款
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("存款成功: " + amount + "元");
        } else {
            System.out.println("存款金额必须大于0");
        }
    }

    // 取款
    boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("取款成功: " + amount + "元");
            return true;
        } else {
            System.out.println("取款失败: 余额不足或金额无效");
            return false;
        }
    }

    // 查询余额
    double getBalance() {
        return balance;
    }

    // 显示账户信息
    void showAccountInfo() {
        System.out.println("账号: " + accountNumber + ", 户主: " + ownerName + ", 余额: " + balance + "元");
    }
}

// 测试类
public class Lesson07_ClassAndObject {
    public static void main(String[] args) {
        System.out.println("===== 类与对象基础 =====");

        // 1. 创建对象
        System.out.println("\n----- 1. 创建对象 -----");
        Student s1 = new Student("小明", 18);
        Student s2 = new Student("小红", 19, 95.5);

        // 2. 访问属性
        System.out.println("\n----- 2. 访问属性 -----");
        s1.score = 95.5;

        // 3. 调用方法
        System.out.println("\n----- 3. 调用方法 -----");
        s1.study("Java");
        s2.study("Python");

        // 4. 打印信息
        System.out.println("\n----- 4. 打印信息 -----");
        s1.showInfo();
        s2.showInfo();

        // 5. 对象比较
        System.out.println("\n----- 5. 对象比较 -----");
        System.out.println("s1的成绩比s2高吗？" + s1.hasHigherScore(s2));
        System.out.println("s1 == s2: " + (s1 == s2));  // 引用比较
        System.out.println("s1.toString(): " + s1.toString());

        // 6. 静态成员
        System.out.println("\n----- 6. 静态成员 -----");
        System.out.println("学生总数: " + Student.getStudentCount());
        Student s3 = new Student("小刚", 20, 88.0);
        System.out.println("创建新学生后总数: " + Student.getStudentCount());

        // 7. 对象数组
        System.out.println("\n----- 7. 对象数组 -----");
        Student[] students = new Student[3];
        students[0] = new Student("张三", 20, 90);
        students[1] = new Student("李四", 21, 85);
        students[2] = new Student("王五", 19, 92);
        
        System.out.println("学生列表:");
        for (Student student : students) {
            student.showInfo();
        }

        // 8. 银行账户示例
        System.out.println("\n----- 8. 银行账户示例 -----");
        BankAccount account1 = new BankAccount("1001", "张三");
        BankAccount account2 = new BankAccount("1002", "李四");
        
        account1.deposit(1000);
        account1.withdraw(300);
        account1.showAccountInfo();
        
        account2.deposit(2000);
        account2.withdraw(500);
        account2.showAccountInfo();

        // 9. 对象作为方法参数
        System.out.println("\n----- 9. 对象作为方法参数 -----");
        printStudentInfo(s1);
        printStudentInfo(s2);

        // 10. 对象作为返回值
        System.out.println("\n----- 10. 对象作为返回值 -----");
        Student topStudent = getTopStudent(students);
        System.out.println("成绩最好的学生: " + topStudent.name + ", 成绩: " + topStudent.score);

        // 11. 匿名对象
        System.out.println("\n----- 11. 匿名对象 -----");
        new Student("临时学生", 22).showInfo();  // 匿名对象，只能使用一次

        // 12. 代码块
        System.out.println("\n----- 12. 代码块 -----");
        new Student("代码块测试", 23);

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：创建矩形类
        System.out.println("练习1 - 矩形类:");
        Rectangle rect1 = new Rectangle(5.0, 3.0);
        Rectangle rect2 = new Rectangle(4.0, 4.0);
        rect1.showInfo();
        rect2.showInfo();
        System.out.println("矩形1的面积: " + rect1.getArea());
        System.out.println("矩形1是正方形吗？" + rect1.isSquare());
        
        // 练习2：创建图书类
        System.out.println("\n练习2 - 图书类:");
        Book book1 = new Book("Java编程思想", "Bruce Eckel", 108.0);
        Book book2 = new Book("Effective Java", "Joshua Bloch", 79.0);
        book1.showInfo();
        book2.showInfo();
        
        // 练习3：创建日期类
        System.out.println("\n练习3 - 日期类:");
        MyDate date1 = new MyDate(2024, 7, 3);
        MyDate date2 = new MyDate(2024, 12, 25);
        date1.showInfo();
        date2.showInfo();
        System.out.println("date1是否在date2之前？" + date1.isBefore(date2));
    }

    // 对象作为方法参数
    static void printStudentInfo(Student student) {
        System.out.println("学生信息 - 姓名: " + student.name + ", 年龄: " + student.age);
    }

    // 对象作为返回值
    static Student getTopStudent(Student[] students) {
        if (students == null || students.length == 0) {
            return null;
        }
        Student top = students[0];
        for (Student student : students) {
            if (student.score > top.score) {
                top = student;
            }
        }
        return top;
    }
}

// 矩形类（练习1）
class Rectangle {
    double width;
    double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    double getArea() {
        return width * height;
    }

    double getPerimeter() {
        return 2 * (width + height);
    }

    boolean isSquare() {
        return width == height;
    }

    void showInfo() {
        System.out.println("矩形: 宽=" + width + ", 高=" + height + ", 面积=" + getArea() + ", 周长=" + getPerimeter());
    }
}

// 图书类（练习2）
class Book {
    String title;
    String author;
    double price;

    Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    void showInfo() {
        System.out.println("图书: 《" + title + "》, 作者: " + author + ", 价格: " + price + "元");
    }
}

// 日期类（练习3）
class MyDate {
    int year;
    int month;
    int day;

    MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    boolean isBefore(MyDate other) {
        if (this.year != other.year) {
            return this.year < other.year;
        }
        if (this.month != other.month) {
            return this.month < other.month;
        }
        return this.day < other.day;
    }

    void showInfo() {
        System.out.printf("日期: %04d-%02d-%02d%n", year, month, day);
    }
}
