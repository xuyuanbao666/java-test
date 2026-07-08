// ===== 封装 =====

// 封装的核心思想：隐藏内部实现，只暴露必要接口

// 1. 访问修饰符详解
class AccessModifiersDemo {
    public String publicVar = "公开的";      // 任何地方都能访问
    protected String protectedVar = "受保护的"; // 同包和子类能访问
    String defaultVar = "默认的";           // 同包能访问
    private String privateVar = "私有的";    // 只有本类能访问

    public void showAccess() {
        System.out.println("publicVar: " + publicVar);
        System.out.println("protectedVar: " + protectedVar);
        System.out.println("defaultVar: " + defaultVar);
        System.out.println("privateVar: " + privateVar);
    }
}

// 2. 银行账户类（封装示例）
class L08_BankAccount {
    // private：只能在本类中访问
    private String owner;
    private double balance;
    private String accountNumber;

    // 构造方法
    public L08_BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
        this.accountNumber = generateAccountNumber();
    }

    // getter方法：获取属性
    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // setter方法：设置属性（可加验证）
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("存入: " + amount + "元");
        } else {
            System.out.println("存款金额必须大于0");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("取出: " + amount + "元");
            return true;
        } else {
            System.out.println("余额不足或金额无效");
            return false;
        }
    }

    public void showBalance() {
        System.out.println(owner + "的余额: " + balance + "元");
    }

    // 私有方法：内部使用
    private String generateAccountNumber() {
        return "ACC" + System.currentTimeMillis() % 10000;
    }

    // 公开方法：提供安全的访问方式
    public String getAccountInfo() {
        return "账号: " + accountNumber + ", 户主: " + owner + ", 余额: " + balance;
    }
}

// 3. 数据验证封装
class User {
    private String username;
    private String password;
    private int age;
    private String email;

    // 构造方法
    public User(String username, String password, int age, String email) {
        setUsername(username);
        setPassword(password);
        setAge(age);
        setEmail(email);
    }

    // getter和setter方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null && username.length() >= 3 && username.length() <= 20) {
            this.username = username;
        } else {
            System.out.println("用户名长度必须在3-20个字符之间");
        }
    }

    public String getPassword() {
        return "********";  // 不直接返回密码
    }

    public void setPassword(String password) {
        if (password != null && password.length() >= 6) {
            this.password = password;
        } else {
            System.out.println("密码长度不能少于6位");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("年龄必须在0-150之间");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            System.out.println("邮箱格式不正确");
        }
    }

    public void showInfo() {
        System.out.println("用户: " + username + ", 年龄: " + age + ", 邮箱: " + email);
    }
}

// 4. 不可变类（Immutable Class）
final class ImmutablePoint {
    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // 返回新对象而不是修改原对象
    public ImmutablePoint move(int dx, int dy) {
        return new ImmutablePoint(this.x + dx, this.y + dy);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

// 5. 封装的计算器类
class L08_Calculator {
    private double result;
    private String lastOperation;

    public L08_Calculator() {
        this.result = 0;
        this.lastOperation = "初始化";
    }

    public L08_Calculator add(double number) {
        result += number;
        lastOperation = "加 " + number;
        return this;  // 支持链式调用
    }

    public L08_Calculator subtract(double number) {
        result -= number;
        lastOperation = "减 " + number;
        return this;
    }

    public L08_Calculator multiply(double number) {
        result *= number;
        lastOperation = "乘 " + number;
        return this;
    }

    public L08_Calculator divide(double number) {
        if (number != 0) {
            result /= number;
            lastOperation = "除 " + number;
        } else {
            System.out.println("错误：除数不能为0");
        }
        return this;
    }

    public L08_Calculator clear() {
        result = 0;
        lastOperation = "清零";
        return this;
    }

    public double getResult() {
        return result;
    }

    public String getLastOperation() {
        return lastOperation;
    }

    public void showStatus() {
        System.out.printf("当前结果: %.2f (最近操作: %s)%n", result, lastOperation);
    }
}

// 6. 学生信息管理系统（封装综合示例）
class L08_StudentManager {
    private L08_Student[] students;
    private int size;
    private static final int MAX_CAPACITY = 100;

    public L08_StudentManager() {
        this.students = new L08_Student[MAX_CAPACITY];
        this.size = 0;
    }

    public boolean addStudent(L08_Student student) {
        if (size < MAX_CAPACITY) {
            students[size++] = student;
            System.out.println("添加学生成功: " + student.getName());
            return true;
        } else {
            System.out.println("错误：学生数量已达上限");
            return false;
        }
    }

    public L08_Student findStudent(String name) {
        for (int i = 0; i < size; i++) {
            if (students[i].getName().equals(name)) {
                return students[i];
            }
        }
        return null;
    }

    public void showAllStudents() {
        System.out.println("===== 学生列表 =====");
        for (int i = 0; i < size; i++) {
            students[i].showInfo();
        }
        System.out.println("总计: " + size + " 名学生");
    }
}

// 学生内部类（用于L08_StudentManager）
class L08_Student {
    private String name;
    private int age;
    private double score;

    public L08_Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        }
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        if (score >= 0 && score <= 100) {
            this.score = score;
        }
    }

    public void showInfo() {
        System.out.println("姓名: " + name + ", 年龄: " + age + ", 成绩: " + score);
    }
}

// 测试类
public class Lesson08_Encapsulation {
    public static void main(String[] args) {
        System.out.println("===== 封装详解 =====");

        // 1. 访问修饰符示例
        System.out.println("\n----- 1. 访问修饰符 -----");
        AccessModifiersDemo demo = new AccessModifiersDemo();
        demo.showAccess();
        // System.out.println(demo.privateVar);  // 错误！无法访问私有变量

        // 2. 银行账户封装示例
        System.out.println("\n----- 2. 银行账户封装 -----");
        L08_BankAccount account = new L08_BankAccount("小明", 1000);
        account.showBalance();

        account.deposit(500);
        account.showBalance();

        account.withdraw(200);
        account.showBalance();

        account.withdraw(2000);  // 余额不足
        System.out.println("账户信息: " + account.getAccountInfo());

        // 3. 数据验证封装
        System.out.println("\n----- 3. 数据验证封装 -----");
        User user1 = new User("zhangsan", "123456", 25, "zhangsan@example.com");
        user1.showInfo();
        
        User user2 = new User("li", "123", 200, "invalid-email");  // 验证失败
        user2.showInfo();

        // 4. 不可变类示例
        System.out.println("\n----- 4. 不可变类 -----");
        ImmutablePoint point1 = new ImmutablePoint(10, 20);
        ImmutablePoint point2 = point1.move(5, 3);
        
        System.out.println("原点: " + point1);
        System.out.println("移动后: " + point2);
        System.out.println("原点不变: " + point1);

        // 5. 计算器封装示例
        System.out.println("\n----- 5. 计算器封装 -----");
        L08_Calculator calc = new L08_Calculator();
        calc.add(10).subtract(3).multiply(2).divide(4);
        calc.showStatus();
        System.out.println("最终结果: " + calc.getResult());

        // 6. 学生管理系统
        System.out.println("\n----- 6. 学生管理系统 -----");
        L08_StudentManager manager = new L08_StudentManager();
        manager.addStudent(new L08_Student("张三", 20, 90));
        manager.addStudent(new L08_Student("李四", 21, 85));
        manager.addStudent(new L08_Student("王五", 19, 92));
        manager.showAllStudents();

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：创建温度类
        System.out.println("练习1 - 温度类:");
        Temperature temp1 = new Temperature(100, 'C');
        temp1.showInfo();
        System.out.println("华氏温度: " + temp1.toFahrenheit() + "°F");
        System.out.println("开尔文温度: " + temp1.toKelvin() + "K");
        
        // 练习2：创建购物车类
        System.out.println("\n练习2 - 购物车类:");
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("苹果", 5.5, 3);
        cart.addItem("香蕉", 3.0, 5);
        cart.addItem("橙子", 4.0, 2);
        cart.showCart();
        System.out.println("总价: " + cart.getTotalPrice() + "元");
        
        // 练习3：创建密码验证器
        System.out.println("\n练习3 - 密码验证器:");
        PasswordValidator validator = new PasswordValidator();
        System.out.println("密码'123'是否有效: " + validator.isValid("123"));
        System.out.println("密码'Abc123'是否有效: " + validator.isValid("Abc123"));
        System.out.println("密码'Abc123!@#'是否有效: " + validator.isValid("Abc123!@#"));
    }
}

// 温度类（练习1）
class Temperature {
    private double value;
    private char unit;  // 'C', 'F', 'K'

    public Temperature(double value, char unit) {
        this.value = value;
        this.unit = Character.toUpperCase(unit);
    }

    public double toCelsius() {
        switch (unit) {
            case 'C': return value;
            case 'F': return (value - 32) * 5 / 9;
            case 'K': return value - 273.15;
            default: return value;
        }
    }

    public double toFahrenheit() {
        return toCelsius() * 9 / 5 + 32;
    }

    public double toKelvin() {
        return toCelsius() + 273.15;
    }

    public void showInfo() {
        System.out.printf("温度: %.1f°%c%n", value, unit);
    }
}

// 购物车类（练习2）
class ShoppingCart {
    private String[] itemNames;
    private double[] itemPrices;
    private int[] itemQuantities;
    private int itemCount;
    private static final int MAX_ITEMS = 50;

    public ShoppingCart() {
        this.itemNames = new String[MAX_ITEMS];
        this.itemPrices = new double[MAX_ITEMS];
        this.itemQuantities = new int[MAX_ITEMS];
        this.itemCount = 0;
    }

    public boolean addItem(String name, double price, int quantity) {
        if (itemCount < MAX_ITEMS && quantity > 0) {
            itemNames[itemCount] = name;
            itemPrices[itemCount] = price;
            itemQuantities[itemCount] = quantity;
            itemCount++;
            System.out.println("添加商品: " + name + " x" + quantity);
            return true;
        }
        return false;
    }

    public double getTotalPrice() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += itemPrices[i] * itemQuantities[i];
        }
        return total;
    }

    public void showCart() {
        System.out.println("===== 购物车 =====");
        for (int i = 0; i < itemCount; i++) {
            System.out.printf("%s: %.1f元 x%d = %.1f元%n", 
                itemNames[i], itemPrices[i], itemQuantities[i], 
                itemPrices[i] * itemQuantities[i]);
        }
    }
}

// 密码验证器（练习3）
class PasswordValidator {
    public boolean isValid(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
        }
        
        return hasUpper && hasLower && hasDigit;
    }
}
