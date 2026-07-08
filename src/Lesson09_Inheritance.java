// ===== 继承 =====

// 继承的核心思想：代码复用，建立类之间的层次关系

// 1. 父类：动物
class L09_Animal {
    String name;
    int age;
    static int animalCount = 0;

    L09_Animal(String name) {
        this.name = name;
        this.age = 0;
        animalCount++;
        System.out.println("创建动物: " + name);
    }

    L09_Animal(String name, int age) {
        this.name = name;
        this.age = age;
        animalCount++;
        System.out.println("创建动物: " + name + ", 年龄: " + age);
    }

    void eat() {
        System.out.println(name + "在吃东西");
    }

    void sleep() {
        System.out.println(name + "在睡觉");
    }

    void showInfo() {
        System.out.println("动物: " + name + ", 年龄: " + age);
    }

    // final方法：子类不能重写
    final String getName() {
        return name;
    }

    static int getAnimalCount() {
        return animalCount;
    }
}

// 2. 子类：狗
class L09_Dog extends L09_Animal {
    String breed;  // 品种

    L09_Dog(String name) {
        super(name);  // 调用父类构造方法
        this.breed = "未知";
    }

    L09_Dog(String name, int age, String breed) {
        super(name, age);  // 调用父类构造方法
        this.breed = breed;
    }

    // 子类特有方法
    void bark() {
        System.out.println(name + "在汪汪叫");
    }

    // 重写父类方法
    @Override
    void eat() {
        System.out.println(name + "在啃骨头");
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "L09_Dog{name='" + name + "', age=" + age + ", breed='" + breed + "'}";
    }

    // 重写showInfo方法
    @Override
    void showInfo() {
        super.showInfo();  // 调用父类方法
        System.out.println("品种: " + breed);
    }
}

// 3. 子类：猫
class L09_Cat extends L09_Animal {
    String color;  // 颜色

    L09_Cat(String name) {
        super(name);
        this.color = "未知";
    }

    L09_Cat(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

    void meow() {
        System.out.println(name + "在喵喵叫");
    }

    @Override
    void eat() {
        System.out.println(name + "在吃鱼");
    }

    @Override
    void showInfo() {
        super.showInfo();
        System.out.println("颜色: " + color);
    }
}

// 4. 多层继承：动物 -> 狗 -> 宠物狗
class L09_PetDog extends L09_Dog {
    String owner;  // 主人

    L09_PetDog(String name, String owner) {
        super(name);  // 调用L09_Dog的构造方法
        this.owner = owner;
    }

    void showOwner() {
        System.out.println(name + "的主人是: " + owner);
    }

    @Override
    void showInfo() {
        super.showInfo();
        System.out.println("主人: " + owner);
    }
}

// 5. final类：不能被继承
final class FinalClass {
    void doSomething() {
        System.out.println("这是final类，不能被继承");
    }
}

// 6. 抽象类（将在下一课详细讲解）
abstract class L09_Shape {
    String color;

    L09_Shape(String color) {
        this.color = color;
    }

    abstract double getArea();
    abstract double getPerimeter();

    void showColor() {
        System.out.println("颜色: " + color);
    }
}

class L09_Circle extends L09_Shape {
    double radius;

    L09_Circle(String color, double radius) {
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
}

class L09_Rectangle extends L09_Shape {
    double width, height;

    L09_Rectangle(String color, double width, double height) {
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
}

// 测试类
public class Lesson09_Inheritance {
    public static void main(String[] args) {
        System.out.println("===== 继承详解 =====");

        // 1. 基本继承示例
        System.out.println("\n----- 1. 基本继承 -----");
        L09_Dog dog = new L09_Dog("旺财");
        L09_Cat cat = new L09_Cat("咪咪");

        dog.eat();     // 调用重写后的方法
        dog.sleep();   // 继承父类方法
        dog.bark();    // 子类特有方法

        System.out.println();
        cat.eat();
        cat.meow();

        // 2. 带参数的构造方法
        System.out.println("\n----- 2. 带参数构造方法 -----");
        L09_Dog dog2 = new L09_Dog("大黄", 3, "金毛");
        L09_Cat cat2 = new L09_Cat("小白", 2, "白色");
        dog2.showInfo();
        System.out.println();
        cat2.showInfo();

        // 3. 多层继承
        System.out.println("\n----- 3. 多层继承 -----");
        L09_PetDog petDog = new L09_PetDog("小白", "小明");
        petDog.eat();  // 继承自L09_Animal
        petDog.bark();  // 继承自L09_Dog？不，L09_PetDog没有继承L09_Dog
        petDog.showOwner();
        petDog.showInfo();

        // 4. 静态成员继承
        System.out.println("\n----- 4. 静态成员 -----");
        System.out.println("动物总数: " + L09_Animal.getAnimalCount());
        L09_Dog dog3 = new L09_Dog("小黑");
        System.out.println("创建新动物后总数: " + L09_Animal.getAnimalCount());

        // 5. 抽象类继承
        System.out.println("\n----- 5. 抽象类继承 -----");
        L09_Circle circle = new L09_Circle("红色", 5.0);
        L09_Rectangle rect = new L09_Rectangle("蓝色", 4.0, 6.0);
        
        circle.showColor();
        System.out.printf("圆形面积: %.2f, 周长: %.2f%n", circle.getArea(), circle.getPerimeter());
        
        rect.showColor();
        System.out.printf("矩形面积: %.2f, 周长: %.2f%n", rect.getArea(), rect.getPerimeter());

        // 6. toString方法重写
        System.out.println("\n----- 6. toString方法 -----");
        System.out.println("dog2.toString(): " + dog2.toString());
        System.out.println("直接输出dog2: " + dog2);  // 自动调用toString

        // 7. instanceof 操作符
        System.out.println("\n----- 7. instanceof操作符 -----");
        L09_Animal animal = new L09_Dog("测试狗");
        System.out.println("animal instanceof L09_Animal: " + (animal instanceof L09_Animal));
        System.out.println("animal instanceof L09_Dog: " + (animal instanceof L09_Dog));
        System.out.println("animal instanceof L09_Cat: " + (animal instanceof L09_Cat));
        System.out.println("animal instanceof Object: " + (animal instanceof Object));

        // 8. 向上转型和向下转型
        System.out.println("\n----- 8. 类型转换 -----");
        // 向上转型（自动）
        L09_Animal animalDog = new L09_Dog("转型狗");
        animalDog.eat();  // 调用L09_Dog的eat方法
        // animalDog.bark();  // 错误！L09_Animal类型没有bark方法
        
        // 向下转型（强制）
        if (animalDog instanceof L09_Dog) {
            L09_Dog dogCast = (L09_Dog) animalDog;
            dogCast.bark();  // 现在可以调用bark方法
        }

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：创建员工继承体系
        System.out.println("练习1 - 员工继承体系:");
        L09_Employee emp1 = new L09_Employee("张三", 25, 8000);
        L09_Manager mgr1 = new L09_Manager("李四", 35, 15000, 5000);
        emp1.showInfo();
        System.out.println("年薪: " + emp1.getAnnualSalary());
        System.out.println();
        mgr1.showInfo();
        System.out.println("年薪: " + mgr1.getAnnualSalary());
        
        // 练习2：创建图形继承体系
        System.out.println("\n练习2 - 图形继承体系:");
        L09_Triangle triangle = new L09_Triangle("绿色", 3.0, 4.0, 5.0);
        triangle.showInfo();
        System.out.printf("面积: %.2f, 周长: %.2f%n", triangle.getArea(), triangle.getPerimeter());
        
        // 练习3：创建交通工具继承体系
        System.out.println("\n练习3 - 交通工具继承体系:");
        L09_Car car = new L09_Car("丰田", "卡罗拉", 2020);
        L09_ElectricCar eCar = new L09_ElectricCar("特斯拉", "Model 3", 2022, 500);
        car.showInfo();
        car.start();
        System.out.println();
        eCar.showInfo();
        eCar.start();
        eCar.charge();
    }
}

// 员工类（练习1）
class L09_Employee {
    protected String name;
    protected int age;
    protected double salary;

    public L09_Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public double getAnnualSalary() {
        return salary * 12;
    }

    public void showInfo() {
        System.out.println("员工: " + name + ", 年龄: " + age + ", 月薪: " + salary);
    }
}

// 经理类（继承员工）
class L09_Manager extends L09_Employee {
    private double bonus;

    public L09_Manager(String name, int age, double salary, double bonus) {
        super(name, age, salary);
        this.bonus = bonus;
    }

    @Override
    public double getAnnualSalary() {
        return super.getAnnualSalary() + bonus;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("职位: 经理, 奖金: " + bonus);
    }
}

// 三角形类（练习2）
class L09_Triangle extends L09_Shape {
    private double side1, side2, side3;

    public L09_Triangle(String color, double side1, double side2, double side3) {
        super(color);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    double getArea() {
        // 海伦公式
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    double getPerimeter() {
        return side1 + side2 + side3;
    }

    public void showInfo() {
        System.out.printf("三角形: 边长=%.1f, %.1f, %.1f%n", side1, side2, side3);
    }
}

// 交通工具类（练习3）
class L09_Vehicle {
    protected String brand;
    protected String model;
    protected int year;

    public L09_Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public void start() {
        System.out.println(brand + " " + model + " 启动");
    }

    public void stop() {
        System.out.println(brand + " " + model + " 停止");
    }

    public void showInfo() {
        System.out.println("车辆: " + brand + " " + model + ", 年份: " + year);
    }
}

// 汽车类（继承交通工具）
class L09_Car extends L09_Vehicle {
    public L09_Car(String brand, String model, int year) {
        super(brand, model, year);
    }

    @Override
    public void start() {
        System.out.println("汽车 " + brand + " " + model + " 引擎启动");
    }
}

// 电动汽车类（继承汽车）
class L09_ElectricCar extends L09_Car {
    private int batteryCapacity;  // 电池容量（km）

    public L09_ElectricCar(String brand, String model, int year, int batteryCapacity) {
        super(brand, model, year);
        this.batteryCapacity = batteryCapacity;
    }

    public void charge() {
        System.out.println("电动汽车 " + brand + " " + model + " 正在充电");
    }

    @Override
    public void start() {
        System.out.println("电动汽车 " + brand + " " + model + " 静音启动");
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("续航里程: " + batteryCapacity + "km");
    }
}
