public class Lesson01_Variables {
    public static void main(String[] args) {
        // ===== Java 的基本数据类型 =====

        // 1. 整数
        int age = 20;              // int：最常用的整数类型
        long bigNumber = 1000000L; // long：更大的整数，加 L 后缀

        // 2. 小数（浮点数）
        double price = 9.99;       // double：常用的小数
        float height = 1.75f;      // float：单精度小数，加 f 后缀

        // 3. 字符
        char grade = 'A';          // char：单个字符，用单引号
        char chinese = '中';       // 也可以存储中文字符

        // 4. 布尔值
        boolean isStudent = true;  // boolean：只有 true 或 false
        int score = 85;            // 先声明一个成绩变量
        boolean isPass = score >= 60;  // 可以存储比较结果

        // 5. 字符串（不是基本类型，但很常用）
        String name = "小明";      // String：文本，用双引号
        String greeting = "你好，" + name;  // 字符串拼接

        // ===== 变量声明的多种方式 =====
        // 方式1：声明并初始化
        int x = 10;
        
        // 方式2：先声明后初始化
        int y;
        y = 20;
        
        // 方式3：同时声明多个同类型变量
        int a = 1, b = 2, c = 3;
        
        // 方式4：使用final定义常量（不可修改）
        final double PI = 3.14159;
        final String SCHOOL_NAME = "Java学院";

        // ===== 类型转换 =====
        // 自动类型转换（小范围 → 大范围）
        int intVal = 100;
        long longVal = intVal;      // int → long，自动转换
        float floatVal = longVal;   // long → float，自动转换
        double doubleVal = floatVal; // float → double，自动转换
        
        // 强制类型转换（大范围 → 小范围，可能丢失数据）
        double pi = 3.14159;
        int rounded = (int) pi;     // 强制转换为int，小数部分丢失
        System.out.println("圆周率取整: " + rounded);  // 3
        
        // 字符串与其他类型的转换
        String numStr = "123";
        int number = Integer.parseInt(numStr);  // 字符串 → 整数
        String backToStr = String.valueOf(number);  // 整数 → 字符串

        // ===== 变量作用域示例 =====
        int scopeVar = 100;  // 方法内变量
        {
            int blockVar = 200;  // 代码块内变量
            System.out.println("代码块内可以访问: " + scopeVar);
            System.out.println("代码块内变量: " + blockVar);
        }
        // System.out.println(blockVar);  // 错误！代码块外无法访问

        // ===== 打印输出 =====
        System.out.println("===== 基本信息 =====");
        System.out.println("名字: " + name);
        System.out.println("年龄: " + age);
        System.out.println("身高: " + height + "米");
        System.out.println("成绩: " + grade);
        System.out.println("是学生: " + isStudent);
        System.out.println("价格: " + price + "元");
        System.out.println("问候语: " + greeting);
        System.out.println("学校: " + SCHOOL_NAME);
        
        // 格式化输出
        System.out.printf("圆周率: %.2f%n", PI);  // 保留两位小数
        System.out.printf("姓名: %s, 年龄: %d%n", name, age);

        // ===== 练习题 =====
        // 练习1：声明变量存储你的个人信息
        // 要求：姓名、年龄、身高、是否喜欢编程
        // 练习2：实现华氏温度转摄氏温度
        // 公式：摄氏 = (华氏 - 32) * 5/9
        double fahrenheit = 98.6;
        double celsius = (fahrenheit - 32) * 5 / 9;
        System.out.printf("%.1f°F = %.1f°C%n", fahrenheit, celsius);
        
        // 练习3：计算圆的面积和周长
        // 半径 = 5.0
        double radius = 5.0;
        double area = PI * radius * radius;
        double circumference = 2 * PI * radius;
        System.out.printf("半径%.1f的圆：面积=%.2f, 周长=%.2f%n", 
                         radius, area, circumference);
    }
}
