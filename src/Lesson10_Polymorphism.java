// ===== 多态 =====

// 多态的核心思想：同一接口，不同实现

// 1. 基础形状类
class L10_Shape {
    String name;
    String color;

    L10_Shape(String name) {
        this.name = name;
        this.color = "黑色";
    }

    L10_Shape(String name, String color) {
        this.name = name;
        this.color = color;
    }

    double area() {
        return 0;
    }

    double perimeter() {
        return 0;
    }

    void show() {
        System.out.println(name + "的面积: " + String.format("%.2f", area()));
    }

    void showDetail() {
        System.out.println("形状: " + name + ", 颜色: " + color + 
                          ", 面积: " + String.format("%.2f", area()) + 
                          ", 周长: " + String.format("%.2f", perimeter()));
    }

    // 静态方法：计算总面积
    static double totalArea(L10_Shape[] shapes) {
        double total = 0;
        for (L10_Shape shape : shapes) {
            total += shape.area();
        }
        return total;
    }
}

// 2. 圆形类
class L10_Circle extends L10_Shape {
    double radius;

    L10_Circle(double radius) {
        super("圆形");
        this.radius = radius;
    }

    L10_Circle(double radius, String color) {
        super("圆形", color);
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }

    @Override
    double perimeter() {
        return 2 * Math.PI * radius;
    }
}

// 3. 矩形类
class L10_Rectangle extends L10_Shape {
    double width, height;

    L10_Rectangle(double width, double height) {
        super("矩形");
        this.width = width;
        this.height = height;
    }

    L10_Rectangle(double width, double height, String color) {
        super("矩形", color);
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }

    @Override
    double perimeter() {
        return 2 * (width + height);
    }
}

// 4. 三角形类
class L10_Triangle extends L10_Shape {
    double base, height;

    L10_Triangle(double base, double height) {
        super("三角形");
        this.base = base;
        this.height = height;
    }

    L10_Triangle(double base, double height, String color) {
        super("三角形", color);
        this.base = base;
        this.height = height;
    }

    @Override
    double area() {
        return 0.5 * base * height;
    }

    @Override
    double perimeter() {
        // 假设是等腰三角形
        double side = Math.sqrt((base/2) * (base/2) + height * height);
        return base + 2 * side;
    }
}

// 5. 正方形类（继承矩形）
class L10_Square extends L10_Rectangle {
    L10_Square(double side) {
        super(side, side);
        this.name = "正方形";
    }

    L10_Square(double side, String color) {
        super(side, side, color);
        this.name = "正方形";
    }
}

// 6. 动物多态示例
class L10_Animal {
    String name;

    L10_Animal(String name) {
        this.name = name;
    }

    void speak() {
        System.out.println(name + "发出声音");
    }

    void move() {
        System.out.println(name + "在移动");
    }
}

class L10_Dog extends L10_Animal {
    L10_Dog(String name) {
        super(name);
    }

    @Override
    void speak() {
        System.out.println(name + "汪汪叫");
    }

    @Override
    void move() {
        System.out.println(name + "在跑");
    }
}

class L10_Cat extends L10_Animal {
    L10_Cat(String name) {
        super(name);
    }

    @Override
    void speak() {
        System.out.println(name + "喵喵叫");
    }

    @Override
    void move() {
        System.out.println(name + "在走猫步");
    }
}

class L10_Bird extends L10_Animal {
    L10_Bird(String name) {
        super(name);
    }

    @Override
    void speak() {
        System.out.println(name + "在唱歌");
    }

    @Override
    void move() {
        System.out.println(name + "在飞");
    }
}

// 7. 接口多态示例
interface Drawable {
    void draw();
}

interface Resizable {
    void resize(double factor);
}

class GraphicCircle implements Drawable, Resizable {
    private double radius;

    GraphicCircle(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("绘制圆形，半径: " + radius);
    }

    @Override
    public void resize(double factor) {
        radius *= factor;
        System.out.println("调整大小后半径: " + radius);
    }
}

class GraphicRectangle implements Drawable, Resizable {
    private double width, height;

    GraphicRectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("绘制矩形，宽: " + width + ", 高: " + height);
    }

    @Override
    public void resize(double factor) {
        width *= factor;
        height *= factor;
        System.out.println("调整大小后: " + width + "x" + height);
    }
}

// 测试类
public class Lesson10_Polymorphism {
    // 多态方法：父类引用指向子类对象
    static void printArea(L10_Shape shape) {
        shape.show();
    }

    // 多态方法：处理不同动物
    static void makeAnimalPerform(L10_Animal animal) {
        animal.speak();
        animal.move();
    }

    // 多态方法：处理可绘制对象
    static void drawShape(Drawable drawable) {
        drawable.draw();
    }

    public static void main(String[] args) {
        System.out.println("===== 多态详解 =====");

        // 1. 基本多态示例
        System.out.println("\n----- 1. 基本多态 -----");
        L10_Shape s1 = new L10_Circle(5);
        L10_Shape s2 = new L10_Rectangle(4, 6);
        L10_Shape s3 = new L10_Triangle(3, 8);

        // 同一方法，不同行为
        printArea(s1);
        printArea(s2);
        printArea(s3);

        // 2. 形状数组多态
        System.out.println("\n----- 2. 形状数组 -----");
        L10_Shape[] shapes = {s1, s2, s3, new L10_Square(4)};
        for (L10_Shape s : shapes) {
            s.show();
        }
        System.out.println("总面积: " + String.format("%.2f", L10_Shape.totalArea(shapes)));

        // 3. 带颜色的形状
        System.out.println("\n----- 3. 带颜色的形状 -----");
        L10_Shape coloredCircle = new L10_Circle(3, "红色");
        L10_Shape coloredRect = new L10_Rectangle(5, 4, "蓝色");
        coloredCircle.showDetail();
        coloredRect.showDetail();

        // 4. 动物多态示例
        System.out.println("\n----- 4. 动物多态 -----");
        L10_Animal dog = new L10_Dog("旺财");
        L10_Animal cat = new L10_Cat("咪咪");
        L10_Animal bird = new L10_Bird("小黄");

        makeAnimalPerform(dog);
        makeAnimalPerform(cat);
        makeAnimalPerform(bird);

        // 5. 动物数组多态
        System.out.println("\n----- 5. 动物数组 -----");
        L10_Animal[] animals = {dog, cat, bird};
        for (L10_Animal animal : animals) {
            animal.speak();
        }

        // 6. 接口多态
        System.out.println("\n----- 6. 接口多态 -----");
        Drawable drawable1 = new GraphicCircle(5);
        Drawable drawable2 = new GraphicRectangle(4, 6);
        
        drawShape(drawable1);
        drawShape(drawable2);

        // 7. 向上转型和向下转型
        System.out.println("\n----- 7. 类型转换 -----");
        // 向上转型（自动）
        L10_Shape shape = new L10_Circle(10);
        shape.show();  // 调用Circle的area方法
        
        // 向下转型（强制）
        if (shape instanceof L10_Circle) {
            L10_Circle circle = (L10_Circle) shape;
            System.out.println("圆形半径: " + circle.radius);
        }

        // 8. 多态的实际应用：简单计算器
        System.out.println("\n----- 8. 计算器多态 -----");
        L10_Calculator calc = new L10_Calculator();
        calc.calculate("add", 10, 5);
        calc.calculate("subtract", 10, 5);
        calc.calculate("multiply", 10, 5);
        calc.calculate("divide", 10, 5);

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：创建支付方式多态
        System.out.println("练习1 - 支付方式多态:");
        Payment[] payments = {
            new CreditCardPayment("1234-5678", 1000),
            new AlipayPayment("user@example.com", 500),
            new WechatPayment("wxid_123", 300)
        };
        for (Payment payment : payments) {
            payment.pay();
            payment.showReceipt();
        }
        
        // 练习2：创建游戏角色多态
        System.out.println("\n练习2 - 游戏角色多态:");
        GameCharacter[] characters = {
            new Warrior("战士", 100, 20),
            new Mage("法师", 80, 50),
            new Archer("弓箭手", 90, 30)
        };
        for (GameCharacter character : characters) {
            character.attack();
            character.useSkill();
        }
        
        // 练习3：创建通知系统多态
        System.out.println("\n练习3 - 通知系统多态:");
        Notification[] notifications = {
            new EmailNotification("user@example.com"),
            new SMSNotification("13800138000"),
            new PushNotification("device_token_123")
        };
        for (Notification notification : notifications) {
            notification.send("这是一条测试消息");
        }
    }
}

// 计算器类（多态应用）
class L10_Calculator {
    interface Operation {
        double execute(double a, double b);
    }

    private Operation addOp = (a, b) -> a + b;
    private Operation subtractOp = (a, b) -> a - b;
    private Operation multiplyOp = (a, b) -> a * b;
    private Operation divideOp = (a, b) -> b != 0 ? a / b : 0;

    void calculate(String operation, double a, double b) {
        double result;
        switch (operation) {
            case "add":
                result = addOp.execute(a, b);
                break;
            case "subtract":
                result = subtractOp.execute(a, b);
                break;
            case "multiply":
                result = multiplyOp.execute(a, b);
                break;
            case "divide":
                result = divideOp.execute(a, b);
                break;
            default:
                System.out.println("未知操作");
                return;
        }
        System.out.printf("%.1f %s %.1f = %.1f%n", a, operation, b, result);
    }
}

// 支付方式多态（练习1）
interface Payment {
    void pay();
    void showReceipt();
}

class CreditCardPayment implements Payment {
    private String cardNumber;
    private double amount;

    public CreditCardPayment(String cardNumber, double amount) {
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    @Override
    public void pay() {
        System.out.println("信用卡支付: " + amount + "元");
    }

    @Override
    public void showReceipt() {
        System.out.println("收据 - 信用卡尾号: " + cardNumber.substring(cardNumber.length()-4) + 
                          ", 金额: " + amount + "元");
    }
}

class AlipayPayment implements Payment {
    private String email;
    private double amount;

    public AlipayPayment(String email, double amount) {
        this.email = email;
        this.amount = amount;
    }

    @Override
    public void pay() {
        System.out.println("支付宝支付: " + amount + "元");
    }

    @Override
    public void showReceipt() {
        System.out.println("收据 - 支付宝: " + email + ", 金额: " + amount + "元");
    }
}

class WechatPayment implements Payment {
    private String openId;
    private double amount;

    public WechatPayment(String openId, double amount) {
        this.openId = openId;
        this.amount = amount;
    }

    @Override
    public void pay() {
        System.out.println("微信支付: " + amount + "元");
    }

    @Override
    public void showReceipt() {
        System.out.println("收据 - 微信: " + openId + ", 金额: " + amount + "元");
    }
}

// 游戏角色多态（练习2）
abstract class GameCharacter {
    protected String name;
    protected int health;
    protected int attackPower;

    public GameCharacter(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    abstract void attack();
    abstract void useSkill();
}

class Warrior extends GameCharacter {
    public Warrior(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    void attack() {
        System.out.println(name + "挥剑攻击，造成" + attackPower + "点伤害");
    }

    @Override
    void useSkill() {
        System.out.println(name + "使用旋风斩，造成" + (attackPower * 2) + "点伤害");
    }
}

class Mage extends GameCharacter {
    public Mage(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    void attack() {
        System.out.println(name + "发射魔法弹，造成" + attackPower + "点伤害");
    }

    @Override
    void useSkill() {
        System.out.println(name + "释放暴风雪，造成" + (attackPower * 3) + "点伤害");
    }
}

class Archer extends GameCharacter {
    public Archer(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    void attack() {
        System.out.println(name + "射箭攻击，造成" + attackPower + "点伤害");
    }

    @Override
    void useSkill() {
        System.out.println(name + "使用多重射击，造成" + (attackPower * 2) + "点伤害");
    }
}

// 通知系统多态（练习3）
interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    private String emailAddress;

    public EmailNotification(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void send(String message) {
        System.out.println("发送邮件到 " + emailAddress + ": " + message);
    }
}

class SMSNotification implements Notification {
    private String phoneNumber;

    public SMSNotification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String message) {
        System.out.println("发送短信到 " + phoneNumber + ": " + message);
    }
}

class PushNotification implements Notification {
    private String deviceToken;

    public PushNotification(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Override
    public void send(String message) {
        System.out.println("发送推送通知到设备 " + deviceToken + ": " + message);
    }
}
