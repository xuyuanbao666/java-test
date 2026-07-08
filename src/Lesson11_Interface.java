import java.util.List;

// ===== 接口 =====

// 接口的核心思想：定义规范，实现多态

// 1. 基础接口定义
interface Flyable {
    void fly();  // 抽象方法，无需实现
    default void land() {  // 默认方法（Java 8+）
        System.out.println("着陆");
    }
    static void showInfo() {  // 静态方法（Java 8+）
        System.out.println("Flyable接口：定义飞行能力");
    }
}

interface Swimmable {
    void swim();
    default void dive() {
        System.out.println("潜水");
    }
}

interface RunAction {
    void run();
    default void stop() {
        System.out.println("停止奔跑");
    }
}

// 2. 实现单个接口
class Bird implements Flyable {
    private String name;

    Bird(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + "在飞翔");
    }

    @Override
    public void land() {
        System.out.println(name + "降落在树枝上");
    }
}

class Fish implements Swimmable {
    private String name;

    Fish(String name) {
        this.name = name;
    }

    @Override
    public void swim() {
        System.out.println(name + "在游泳");
    }
}

// 3. 实现多个接口
class Duck implements Flyable, Swimmable, RunAction {
    private String name;

    Duck(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + "在飞");
    }

    @Override
    public void swim() {
        System.out.println(name + "在游泳");
    }

    @Override
    public void run() {
        System.out.println(name + "在奔跑");
    }
}

// 4. 接口继承
interface WaterBird extends Flyable, Swimmable {
    void fish();  // 捕鱼能力
}

class Eagle implements Flyable {
    @Override
    public void fly() {
        System.out.println("老鹰在翱翔");
    }
}

class Penguin implements Swimmable {
    @Override
    public void swim() {
        System.out.println("企鹅在游泳");
    }
}

// 5. 函数式接口（只有一个抽象方法）
@FunctionalInterface
interface MathOperation {
    double operate(double a, double b);
}

@FunctionalInterface
interface StringProcessor {
    String process(String input);
}

// 6. 常量接口
interface Constants {
    double PI = 3.14159;
    double E = 2.71828;
    String COMPANY = "Java学院";
    int MAX_SIZE = 100;
}

// 7. 接口作为策略模式
interface SortStrategy {
    void sort(int[] array);
}

class BubbleSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("使用冒泡排序");
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

class QuickSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("使用快速排序");
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}

class Sorter {
    private SortStrategy strategy;

    public Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void performSort(int[] array) {
        strategy.sort(array);
    }
}

// 8. 接口作为观察者模式
interface Observer {
    void update(String message);
}

interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

class NewsAgency implements Subject {
    private List<Observer> observers = new java.util.ArrayList<>();
    private String news;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }

    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
}

class NewsChannel implements Observer {
    private String name;

    public NewsChannel(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "收到新闻: " + message);
    }
}

// 测试类
public class Lesson11_Interface {
    public static void main(String[] args) {
        System.out.println("===== 接口详解 =====");

        // 1. 基本接口使用
        System.out.println("\n----- 1. 基本接口 -----");
        Bird bird = new Bird("小鸟");
        Fish fish = new Fish("小鱼");
        Duck duck = new Duck("鸭子");

        bird.fly();
        bird.land();
        fish.swim();

        System.out.println("\n--- 鸭子会飞也会游泳也会跑 ---");
        duck.fly();
        duck.swim();
        duck.run();

        // 2. 接口多态
        System.out.println("\n----- 2. 接口多态 -----");
        showFlyable(bird);
        showFlyable(duck);
        showSwimmable(fish);
        showSwimmable(duck);

        // 3. 接口静态方法
        System.out.println("\n----- 3. 接口静态方法 -----");
        Flyable.showInfo();

        // 4. 函数式接口和Lambda
        System.out.println("\n----- 4. 函数式接口和Lambda -----");
        MathOperation add = (a, b) -> a + b;
        MathOperation subtract = (a, b) -> a - b;
        MathOperation multiply = (a, b) -> a * b;
        MathOperation divide = (a, b) -> b != 0 ? a / b : 0;

        System.out.println("10 + 5 = " + add.operate(10, 5));
        System.out.println("10 - 5 = " + subtract.operate(10, 5));
        System.out.println("10 * 5 = " + multiply.operate(10, 5));
        System.out.println("10 / 5 = " + divide.operate(10, 5));

        // 5. 字符串处理Lambda
        System.out.println("\n----- 5. 字符串处理 -----");
        StringProcessor toUpper = s -> s.toUpperCase();
        StringProcessor toLower = s -> s.toLowerCase();
        StringProcessor reverse = s -> new StringBuilder(s).reverse().toString();

        String testStr = "Hello World";
        System.out.println("原字符串: " + testStr);
        System.out.println("转大写: " + toUpper.process(testStr));
        System.out.println("转小写: " + toLower.process(testStr));
        System.out.println("反转: " + reverse.process(testStr));

        // 6. 常量接口
        System.out.println("\n----- 6. 常量接口 -----");
        System.out.println("PI = " + Constants.PI);
        System.out.println("公司: " + Constants.COMPANY);
        System.out.println("最大尺寸: " + Constants.MAX_SIZE);

        // 7. 策略模式
        System.out.println("\n----- 7. 策略模式 -----");
        int[] array1 = {64, 25, 12, 22, 11};
        int[] array2 = {64, 25, 12, 22, 11};

        Sorter bubbleSorter = new Sorter(new BubbleSortStrategy());
        bubbleSorter.performSort(array1);
        System.out.println("冒泡排序结果: " + java.util.Arrays.toString(array1));

        Sorter quickSorter = new Sorter(new QuickSortStrategy());
        quickSorter.performSort(array2);
        System.out.println("快速排序结果: " + java.util.Arrays.toString(array2));

        // 8. 观察者模式
        System.out.println("\n----- 8. 观察者模式 -----");
        NewsAgency agency = new NewsAgency();
        NewsChannel channel1 = new NewsChannel("央视新闻");
        NewsChannel channel2 = new NewsChannel("新华社");

        agency.addObserver(channel1);
        agency.addObserver(channel2);
        agency.setNews("Java 21正式发布！");

        // 9. 接口数组
        System.out.println("\n----- 9. 接口数组 -----");
        Flyable[] flyables = {bird, duck};
        for (Flyable f : flyables) {
            f.fly();
        }

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：创建可打印接口
        System.out.println("练习1 - 可打印接口:");
        Printable[] printables = {
            new PrintableDocument("Java教程.pdf"),
            new PrintableImage("logo.png"),
            new PrintableReport("季度报告")
        };
        for (Printable p : printables) {
            p.print();
        }
        
        // 练习2：创建可比较接口
        System.out.println("\n练习2 - 可比较接口:");
        InterfaceStudent[] students = {
            new InterfaceStudent("张三", 90),
            new InterfaceStudent("李四", 85),
            new InterfaceStudent("王五", 95)
        };
        java.util.Arrays.sort(students);
        for (InterfaceStudent s : students) {
            System.out.println(s);
        }
        
        // 练习3：创建可序列化接口
        System.out.println("\n练习3 - 可序列化接口:");
        SerializableUser user = new SerializableUser("张三", 25);
        String serialized = user.serialize();
        System.out.println("序列化结果: " + serialized);
        SerializableUser deserialized = SerializableUser.deserialize(serialized);
        System.out.println("反序列化结果: " + deserialized);
    }

    // 接口作为参数
    static void showFlyable(Flyable f) {
        f.fly();
    }

    static void showSwimmable(Swimmable s) {
        s.swim();
    }
}

// 可打印接口（练习1）
interface Printable {
    void print();
    default void showPreview() {
        System.out.println("显示预览...");
    }
}

class PrintableDocument implements Printable {
    private String filename;

    public PrintableDocument(String filename) {
        this.filename = filename;
    }

    @Override
    public void print() {
        System.out.println("打印文档: " + filename);
    }
}

class PrintableImage implements Printable {
    private String imageName;

    public PrintableImage(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public void print() {
        System.out.println("打印图片: " + imageName);
    }
}

class PrintableReport implements Printable {
    private String reportName;

    public PrintableReport(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public void print() {
        System.out.println("打印报告: " + reportName);
    }
}

// 可比较接口（练习2）
class InterfaceStudent implements Comparable<InterfaceStudent> {
    private String name;
    private int score;

    public InterfaceStudent(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(InterfaceStudent other) {
        return Integer.compare(this.score, other.score);  // 升序
        // return Integer.compare(other.score, this.score);  // 降序
    }

    @Override
    public String toString() {
        return "InterfaceStudent{name='" + name + "', score=" + score + "}";
    }
}

// 可序列化接口（练习3）
interface MySerializable {
    String serialize();
    static Object deserialize(String data) {
        return null;  // 由实现类提供具体实现
    }
}

class SerializableUser implements MySerializable {
    private String name;
    private int age;

    public SerializableUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String serialize() {
        return name + "," + age;
    }

    public static SerializableUser deserialize(String data) {
        String[] parts = data.split(",");
        return new SerializableUser(parts[0], Integer.parseInt(parts[1]));
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }
}
