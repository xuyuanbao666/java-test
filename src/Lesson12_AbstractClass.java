// ===== 抽象类 =====

// 抽象类的核心思想：定义模板，强制实现

// 1. 抽象类基础
abstract class L12_Vehicle {
    protected String brand;
    protected int year;

    L12_Vehicle(String brand) {
        this.brand = brand;
        this.year = 2024;
    }

    L12_Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    // 抽象方法：子类必须实现
    abstract void start();
    abstract void stop();
    abstract String getType();

    // 普通方法：子类可以直接用
    void showInfo() {
        System.out.println("品牌: " + brand + ", 年份: " + year + ", 类型: " + getType());
    }

    // final方法：子类不能重写
    final String getBrand() {
        return brand;
    }
}

// 2. 具体子类
class L12_Car extends L12_Vehicle {
    private int doors;

    L12_Car(String brand) {
        super(brand);
        this.doors = 4;
    }

    L12_Car(String brand, int year, int doors) {
        super(brand, year);
        this.doors = doors;
    }

    @Override
    void start() {
        System.out.println(brand + " 汽车启动，踩油门");
    }

    @Override
    void stop() {
        System.out.println(brand + " 汽车停止，踩刹车");
    }

    @Override
    String getType() {
        return "汽车";
    }

    @Override
    void showInfo() {
        super.showInfo();
        System.out.println("车门数: " + doors);
    }
}

class L12_ElectricBike extends L12_Vehicle {
    private int batteryLevel;

    L12_ElectricBike(String brand) {
        super(brand);
        this.batteryLevel = 100;
    }

    L12_ElectricBike(String brand, int year, int batteryLevel) {
        super(brand, year);
        this.batteryLevel = batteryLevel;
    }

    @Override
    void start() {
        System.out.println(brand + " 电动车启动，拧把手");
    }

    @Override
    void stop() {
        System.out.println(brand + " 电动车停止，松把手");
    }

    @Override
    String getType() {
        return "电动车";
    }

    void charge() {
        batteryLevel = 100;
        System.out.println(brand + " 充电完成，电量: " + batteryLevel + "%");
    }
}

class L12_Truck extends L12_Vehicle {
    private double loadCapacity;

    L12_Truck(String brand, double loadCapacity) {
        super(brand);
        this.loadCapacity = loadCapacity;
    }

    L12_Truck(String brand, int year, double loadCapacity) {
        super(brand, year);
        this.loadCapacity = loadCapacity;
    }

    @Override
    void start() {
        System.out.println(brand + " 卡车启动，柴油发动机");
    }

    @Override
    void stop() {
        System.out.println(brand + " 卡车停止，气刹制动");
    }

    @Override
    String getType() {
        return "卡车";
    }

    @Override
    void showInfo() {
        super.showInfo();
        System.out.println("载重量: " + loadCapacity + "吨");
    }
}

// 3. 模板方法模式
abstract class L12_Game {
    protected String name;

    L12_Game(String name) {
        this.name = name;
    }

    // 模板方法：定义游戏流程
    public final void play() {
        System.out.println("===== 开始游戏: " + name + " =====");
        initialize();
        startGame();
        endGame();
        System.out.println("===== 游戏结束 =====\n");
    }

    // 抽象方法：子类实现具体步骤
    protected abstract void initialize();
    protected abstract void startGame();
    protected abstract void endGame();
}

class L12_Chess extends L12_Game {
    L12_Chess() {
        super("国际象棋");
    }

    @Override
    protected void initialize() {
        System.out.println("摆放棋子...");
    }

    @Override
    protected void startGame() {
        System.out.println("白棋先行...");
    }

    @Override
    protected void endGame() {
        System.out.println("将军！游戏结束");
    }
}

class L12_Football extends L12_Game {
    L12_Football() {
        super("足球");
    }

    @Override
    protected void initialize() {
        System.out.println("球员入场...");
    }

    @Override
    protected void startGame() {
        System.out.println("开球！");
    }

    @Override
    protected void endGame() {
        System.out.println("终场哨响！");
    }
}

// 4. 抽象类实现接口
interface Drawable {
    void draw();
    default void erase() {
        System.out.println("擦除图形");
    }
}

interface Resizable {
    void resize(double factor);
}

abstract class AbstractShape implements Drawable, Resizable {
    protected String color;
    protected double x, y;

    AbstractShape(String color, double x, double y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    // 实现接口方法
    @Override
    public void draw() {
        System.out.println("绘制" + color + "形状在(" + x + ", " + y + ")");
    }

    // 抽象方法
    abstract double getArea();
    abstract double getPerimeter();

    void showPosition() {
        System.out.println("位置: (" + x + ", " + y + ")");
    }
}

class AbstractCircle extends AbstractShape {
    private double radius;

    AbstractCircle(String color, double x, double y, double radius) {
        super(color, x, y);
        this.radius = radius;
    }

    @Override
    public void resize(double factor) {
        radius *= factor;
        System.out.println("圆形调整大小，新半径: " + radius);
    }

    @Override
    public void draw() {
        super.draw();
        System.out.println("绘制圆形，半径: " + radius);
    }

    @Override
    double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

class AbstractRectangle extends AbstractShape {
    private double width, height;

    AbstractRectangle(String color, double x, double y, double width, double height) {
        super(color, x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void resize(double factor) {
        width *= factor;
        height *= factor;
        System.out.println("矩形调整大小，新尺寸: " + width + "x" + height);
    }

    @Override
    public void draw() {
        super.draw();
        System.out.println("绘制矩形，宽: " + width + ", 高: " + height);
    }

    @Override
    double getArea() {
        return width * height;
    }

    @Override
    double getPerimeter() {
        return 2 * (width + height);
    }
}

// 5. 抽象工厂模式
abstract class DatabaseFactory {
    abstract DBConnection createDBConnection();
    abstract DBStatement createDBStatement();

    public void executeQuery(String sql) {
        DBConnection conn = createDBConnection();
        DBStatement stmt = createDBStatement();
        conn.connect();
        stmt.execute(sql);
        conn.disconnect();
    }
}

interface DBConnection {
    void connect();
    void disconnect();
}

interface DBStatement {
    void execute(String sql);
}

class MySQLFactory extends DatabaseFactory {
    @Override
    DBConnection createDBConnection() {
        return new MySQLDBConnection();
    }

    @Override
    DBStatement createDBStatement() {
        return new MySQLStatement();
    }
}

class PostgreSQLFactory extends DatabaseFactory {
    @Override
    DBConnection createDBConnection() {
        return new PostgreSQLDBConnection();
    }

    @Override
    DBStatement createDBStatement() {
        return new PostgreSQLStatement();
    }
}

class MySQLDBConnection implements DBConnection {
    @Override
    public void connect() {
        System.out.println("连接MySQL数据库");
    }

    @Override
    public void disconnect() {
        System.out.println("断开MySQL连接");
    }
}

class MySQLStatement implements DBStatement {
    @Override
    public void execute(String sql) {
        System.out.println("MySQL执行SQL: " + sql);
    }
}

class PostgreSQLDBConnection implements DBConnection {
    @Override
    public void connect() {
        System.out.println("连接PostgreSQL数据库");
    }

    @Override
    public void disconnect() {
        System.out.println("断开PostgreSQL连接");
    }
}

class PostgreSQLStatement implements DBStatement {
    @Override
    public void execute(String sql) {
        System.out.println("PostgreSQL执行SQL: " + sql);
    }
}

// 测试类
public class Lesson12_AbstractClass {
    public static void main(String[] args) {
        System.out.println("===== 抽象类详解 =====");

        // 1. 基本抽象类使用
        System.out.println("\n----- 1. 基本抽象类 -----");
        L12_Car car = new L12_Car("宝马", 2023, 4);
        L12_ElectricBike bike = new L12_ElectricBike("雅迪", 2024, 80);
        L12_Truck truck = new L12_Truck("沃尔沃", 2022, 20);

        car.showInfo();
        car.start();
        car.stop();

        System.out.println();
        bike.showInfo();
        bike.start();
        bike.charge();

        System.out.println();
        truck.showInfo();
        truck.start();
        truck.stop();

        // 2. 抽象类多态
        System.out.println("\n----- 2. 抽象类多态 -----");
        L12_Vehicle v1 = new L12_Car("奔驰");
        L12_Vehicle v2 = new L12_ElectricBike("小牛");
        L12_Vehicle v3 = new L12_Truck("东风", 15);

        v1.start();
        v2.start();
        v3.start();

        // 3. 模板方法模式
        System.out.println("\n----- 3. 模板方法模式 -----");
        L12_Game chess = new L12_Chess();
        L12_Game football = new L12_Football();

        chess.play();
        football.play();

        // 4. 抽象类实现接口
        System.out.println("\n----- 4. 抽象类实现接口 -----");
        AbstractShape circle = new AbstractCircle("红色", 10, 20, 5);
        AbstractShape rect = new AbstractRectangle("蓝色", 30, 40, 8, 6);

        circle.draw();
        circle.showPosition();
        System.out.printf("圆形面积: %.2f, 周长: %.2f%n", circle.getArea(), circle.getPerimeter());
        circle.resize(2);

        System.out.println();
        rect.draw();
        rect.showPosition();
        System.out.printf("矩形面积: %.2f, 周长: %.2f%n", rect.getArea(), rect.getPerimeter());
        rect.resize(0.5);

        // 5. 抽象工厂模式
        System.out.println("\n----- 5. 抽象工厂模式 -----");
        DatabaseFactory mysqlFactory = new MySQLFactory();
        DatabaseFactory postgresFactory = new PostgreSQLFactory();

        mysqlFactory.executeQuery("SELECT * FROM users");
        System.out.println();
        postgresFactory.executeQuery("SELECT * FROM orders");

        // 6. 抽象类数组
        System.out.println("\n----- 6. 抽象类数组 -----");
        L12_Vehicle[] vehicles = {car, bike, truck};
        for (L12_Vehicle v : vehicles) {
            v.showInfo();
            v.start();
            System.out.println();
        }

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：创建员工抽象类
        System.out.println("练习1 - 员工抽象类:");
        L12_Employee[] employees = {
            new L12_FullTimeEmployee("张三", 8000),
            new L12_PartTimeEmployee("李四", 50, 120),
            new L12_Contractor("王五", 500, 20)
        };
        for (L12_Employee emp : employees) {
            emp.showInfo();
            System.out.printf("月薪: %.1f元%n", emp.calculateSalary());
        }
        
        // 练习2：创建图形抽象类
        System.out.println("\n练习2 - 图形抽象类:");
        L12_Shape[] shapes = {
            new L12_ShapeCircle("红色", 5),
            new L12_ShapeRectangle("蓝色", 4, 6),
            new L12_ShapeTriangle("绿色", 3, 4, 5)
        };
        for (L12_Shape shape : shapes) {
            shape.draw();
            System.out.printf("面积: %.2f, 周长: %.2f%n", shape.getArea(), shape.getPerimeter());
        }
        
        // 练习3：创建银行账户抽象类
        System.out.println("\n练习3 - 银行账户抽象类:");
        L12_BankAccount savings = new L12_SavingsAccount("1001", "张三", 10000, 0.05);
        L12_BankAccount checking = new L12_CheckingAccount("1002", "李四", 5000, 2000);
        
        savings.showInfo();
        savings.deposit(5000);
        savings.withdraw(3000);
        ((L12_SavingsAccount) savings).addInterest();
        savings.showInfo();
        
        System.out.println();
        checking.showInfo();
        checking.deposit(1000);
        checking.withdraw(8000);
        checking.showInfo();
    }
}

// 员工抽象类（练习1）
abstract class L12_Employee {
    protected String name;

    L12_Employee(String name) {
        this.name = name;
    }

    abstract double calculateSalary();
    abstract String getEmployeeType();

    void showInfo() {
        System.out.println("员工: " + name + ", 类型: " + getEmployeeType());
    }
}

class L12_FullTimeEmployee extends L12_Employee {
    private double monthlySalary;

    L12_FullTimeEmployee(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    double calculateSalary() {
        return monthlySalary;
    }

    @Override
    String getEmployeeType() {
        return "全职员工";
    }
}

class L12_PartTimeEmployee extends L12_Employee {
    private double hourlyRate;
    private int hoursWorked;

    L12_PartTimeEmployee(String name, double hourlyRate, int hoursWorked) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    String getEmployeeType() {
        return "兼职员工";
    }
}

class L12_Contractor extends L12_Employee {
    private double dailyRate;
    private int daysWorked;

    L12_Contractor(String name, double dailyRate, int daysWorked) {
        super(name);
        this.dailyRate = dailyRate;
        this.daysWorked = daysWorked;
    }

    @Override
    double calculateSalary() {
        return dailyRate * daysWorked;
    }

    @Override
    String getEmployeeType() {
        return "合同工";
    }
}

// 图形抽象类（练习2）
abstract class L12_Shape {
    protected String color;

    L12_Shape(String color) {
        this.color = color;
    }

    abstract double getArea();
    abstract double getPerimeter();
    abstract void draw();
}

class L12_ShapeCircle extends L12_Shape {
    private double radius;

    L12_ShapeCircle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    void draw() {
        System.out.println("绘制" + color + "圆形，半径: " + radius);
    }
}

class L12_ShapeRectangle extends L12_Shape {
    private double width, height;

    L12_ShapeRectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    double getArea() {
        return width * height;
    }

    @Override
    double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    void draw() {
        System.out.println("绘制" + color + "矩形，宽: " + width + ", 高: " + height);
    }
}

class L12_ShapeTriangle extends L12_Shape {
    private double side1, side2, side3;

    L12_ShapeTriangle(String color, double side1, double side2, double side3) {
        super(color);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    double getArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    void draw() {
        System.out.println("绘制" + color + "三角形，边长: " + side1 + ", " + side2 + ", " + side3);
    }
}

// 银行账户抽象类（练习3）
abstract class L12_BankAccount {
    protected String accountNumber;
    protected String ownerName;
    protected double balance;

    L12_BankAccount(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    abstract void deposit(double amount);
    abstract boolean withdraw(double amount);

    void showInfo() {
        System.out.printf("账号: %s, 户主: %s, 余额: %.1f元%n", 
                         accountNumber, ownerName, balance);
    }
}

class L12_SavingsAccount extends L12_BankAccount {
    private double interestRate;

    L12_SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
        super(accountNumber, ownerName, balance);
        this.interestRate = interestRate;
    }

    @Override
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("存款: " + amount + "元");
        }
    }

    @Override
    boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("取款: " + amount + "元");
            return true;
        }
        System.out.println("余额不足");
        return false;
    }

    void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.printf("利息: %.1f元，新余额: %.1f元%n", interest, balance);
    }
}

class L12_CheckingAccount extends L12_BankAccount {
    private double overdraftLimit;

    L12_CheckingAccount(String accountNumber, String ownerName, double balance, double overdraftLimit) {
        super(accountNumber, ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("存款: " + amount + "元");
        }
    }

    @Override
    boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance + overdraftLimit) {
            balance -= amount;
            System.out.println("取款: " + amount + "元");
            return true;
        }
        System.out.println("超出透支限额");
        return false;
    }
}
