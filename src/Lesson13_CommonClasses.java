import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// ===== 常用类 =====

public class Lesson13_CommonClasses {
    public static void main(String[] args) {
        // ===== 1. String 字符串详解 =====
        System.out.println("===== 1. String 字符串 =====");
        String s1 = "Hello World";
        String s2 = new String("Hello World");
        String s3 = "hello world";

        System.out.println("长度: " + s1.length());
        System.out.println("charAt(0): " + s1.charAt(0));
        System.out.println("substring(0,5): " + s1.substring(0, 5));
        System.out.println("toUpperCase: " + s1.toUpperCase());
        System.out.println("toLowerCase: " + s1.toLowerCase());
        System.out.println("contains: " + s1.contains("World"));
        System.out.println("equals: " + s1.equals(s2));
        System.out.println("equalsIgnoreCase: " + s1.equalsIgnoreCase(s3));
        System.out.println("indexOf: " + s1.indexOf("World"));
        System.out.println("lastIndexOf: " + s1.lastIndexOf("l"));
        System.out.println("startsWith: " + s1.startsWith("Hello"));
        System.out.println("endsWith: " + s1.endsWith("World"));
        System.out.println("trim: " + "  Hello  ".trim());
        System.out.println("replace: " + s1.replace("World", "Java"));
        System.out.println("isEmpty: " + "".isEmpty());
        System.out.println("isBlank: " + "  ".isBlank());  // Java 11+

        // 字符串分割
        System.out.println("\n--- 字符串分割 ---");
        String csv = "苹果,香蕉,橘子,葡萄";
        String[] fruits = csv.split(",");
        System.out.println("分割结果: " + Arrays.toString(fruits));

        // 字符串拼接
        System.out.println("\n--- 字符串拼接 ---");
        String name = "小明";
        int age = 18;
        String info = "我叫" + name + "，今年" + age + "岁";
        System.out.println(info);

        // 格式化字符串
        System.out.println("\n--- 格式化字符串 ---");
        String formatted = String.format("姓名: %s, 年龄: %d, 成绩: %.1f", name, age, 95.5);
        System.out.println(formatted);

        // 字符串与数组转换
        System.out.println("\n--- 字符串与数组转换 ---");
        char[] chars = s1.toCharArray();
        System.out.println("字符数组: " + Arrays.toString(chars));
        String fromChars = new String(chars);
        System.out.println("从字符数组创建: " + fromChars);

        // ===== 2. StringBuilder 可变字符串 =====
        System.out.println("\n===== 2. StringBuilder =====");
        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        sb.append(" ");
        sb.append("World");
        System.out.println("初始: " + sb.toString());
        
        sb.insert(5, ",");
        System.out.println("插入后: " + sb.toString());
        
        sb.delete(5, 6);
        System.out.println("删除后: " + sb.toString());
        
        sb.replace(6, 11, "Java");
        System.out.println("替换后: " + sb.toString());
        
        sb.reverse();
        System.out.println("反转后: " + sb.toString());
        
        sb.reverse();  // 再次反转回来
        System.out.println("容量: " + sb.capacity());
        System.out.println("长度: " + sb.length());

        // StringBuilder 高效拼接
        System.out.println("\n--- 高效拼接 ---");
        StringBuilder efficient = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            efficient.append(i).append(" ");
        }
        System.out.println("拼接结果: " + efficient.toString().trim());

        // ===== 3. ArrayList 动态数组 =====
        System.out.println("\n===== 3. ArrayList =====");
        ArrayList<String> list = new ArrayList<>();
        list.add("苹果");
        list.add("香蕉");
        list.add("橘子");
        list.add(1, "葡萄");  // 在指定位置插入
        System.out.println("列表: " + list);
        System.out.println("大小: " + list.size());
        System.out.println("第一个: " + list.get(0));
        System.out.println("包含苹果: " + list.contains("苹果"));
        System.out.println("香蕉的索引: " + list.indexOf("香蕉"));

        list.remove(1);  // 删除葡萄
        System.out.println("删除后: " + list);
        
        list.set(0, "红苹果");  // 修改元素
        System.out.println("修改后: " + list);

        // 遍历方式
        System.out.println("\n--- 遍历方式 ---");
        // 方式1: for循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ": " + list.get(i));
        }
        
        // 方式2: 增强for循环
        for (String item : list) {
            System.out.println(item);
        }
        
        // 方式3: forEach (Java 8+)
        list.forEach(item -> System.out.println(item));

        // ArrayList 存储数字（自动装箱）
        System.out.println("\n--- 数字列表 ---");
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        System.out.println("数字列表: " + numbers);
        
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("总和: " + sum);

        // ===== 4. Math 数学类 =====
        System.out.println("\n===== 4. Math 数学类 =====");
        System.out.println("abs(-10): " + Math.abs(-10));
        System.out.println("max(10,20): " + Math.max(10, 20));
        System.out.println("min(10,20): " + Math.min(10, 20));
        System.out.println("sqrt(16): " + Math.sqrt(16));
        System.out.println("pow(2,3): " + Math.pow(2, 3));
        System.out.println("cbrt(27): " + Math.cbrt(27));
        System.out.println("ceil(3.2): " + Math.ceil(3.2));
        System.out.println("floor(3.8): " + Math.floor(3.8));
        System.out.println("round(3.5): " + Math.round(3.5));
        System.out.println("random(): " + Math.random());
        System.out.println("PI: " + Math.PI);
        System.out.println("E: " + Math.E);
        System.out.println("log(10): " + Math.log(10));
        System.out.println("log10(100): " + Math.log10(100));
        System.out.println("sin(PI/2): " + Math.sin(Math.PI / 2));

        // 生成随机数
        System.out.println("\n--- 随机数 ---");
        // 0-99的随机整数
        int randomInt = (int) (Math.random() * 100);
        System.out.println("0-99随机数: " + randomInt);
        
        // 1-6的骰子
        int dice = (int) (Math.random() * 6) + 1;
        System.out.println("骰子: " + dice);

        // ===== 5. 包装类 =====
        System.out.println("\n===== 5. 包装类 =====");
        // 基本类型 -> 包装类
        Integer intObj = Integer.valueOf(100);
        Double doubleObj = Double.valueOf(3.14);
        Boolean boolObj = Boolean.valueOf(true);
        Character charObj = Character.valueOf('A');
        
        // 包装类 -> 基本类型
        int intVal = intObj.intValue();
        double doubleVal = doubleObj.doubleValue();
        boolean boolVal = boolObj.booleanValue();
        char charVal = charObj.charValue();
        
        // 字符串 -> 基本类型
        int parsedInt = Integer.parseInt("123");
        double parsedDouble = Double.parseDouble("3.14");
        boolean parsedBool = Boolean.parseBoolean("true");
        
        // 基本类型 -> 字符串
        String intStr = String.valueOf(100);
        String doubleStr = String.valueOf(3.14);
        String boolStr = String.valueOf(true);
        
        System.out.println("parseInt: " + parsedInt);
        System.out.println("parseDouble: " + parsedDouble);
        System.out.println("valueOf: " + intStr);
        
        // 自动装箱和拆箱
        System.out.println("\n--- 自动装箱拆箱 ---");
        Integer autoBoxed = 100;  // 自动装箱
        int autoUnboxed = autoBoxed;  // 自动拆箱
        System.out.println("自动装箱: " + autoBoxed);
        System.out.println("自动拆箱: " + autoUnboxed);
        
        // 缓存机制
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        System.out.println("127 == 127: " + (a == b));  // true (缓存)
        System.out.println("128 == 128: " + (c == d));  // false (新对象)
        System.out.println("127 equals 127: " + a.equals(b));

        // ===== 6. 日期时间类 =====
        System.out.println("\n===== 6. 日期时间类 =====");
        
        // 旧版Date类
        System.out.println("--- Date类 ---");
        Date now = new Date();
        System.out.println("当前时间: " + now);
        System.out.println("时间戳: " + now.getTime());
        
        // SimpleDateFormat 格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(now);
        System.out.println("格式化: " + formattedDate);
        
        // Calendar类
        System.out.println("\n--- Calendar类 ---");
        Calendar cal = Calendar.getInstance();
        System.out.println("年: " + cal.get(Calendar.YEAR));
        System.out.println("月: " + (cal.get(Calendar.MONTH) + 1));
        System.out.println("日: " + cal.get(Calendar.DAY_OF_MONTH));
        System.out.println("时: " + cal.get(Calendar.HOUR_OF_DAY));
        System.out.println("分: " + cal.get(Calendar.MINUTE));
        System.out.println("秒: " + cal.get(Calendar.SECOND));
        
        // Java 8+ 新版日期时间API
        System.out.println("\n--- Java 8+ 日期时间 ---");
        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();
        
        System.out.println("日期: " + today);
        System.out.println("时间: " + currentTime);
        System.out.println("日期时间: " + dateTime);
        
        // 格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String formattedDateTime = dateTime.format(dtf);
        System.out.println("格式化: " + formattedDateTime);
        
        // 日期计算
        LocalDate tomorrow = today.plusDays(1);
        LocalDate yesterday = today.minusDays(1);
        System.out.println("明天: " + tomorrow);
        System.out.println("昨天: " + yesterday);
        
        // 创建指定日期
        LocalDate birthday = LocalDate.of(2000, 6, 15);
        System.out.println("生日: " + birthday);
        System.out.println("年龄: " + (today.getYear() - birthday.getYear()));

        // ===== 7. Arrays 工具类 =====
        System.out.println("\n===== 7. Arrays 工具类 =====");
        int[] numbersArray = {5, 2, 8, 1, 9, 3};
        System.out.println("原数组: " + Arrays.toString(numbersArray));
        
        // 排序
        Arrays.sort(numbersArray);
        System.out.println("排序后: " + Arrays.toString(numbersArray));
        
        // 二分查找
        int index = Arrays.binarySearch(numbersArray, 5);
        System.out.println("5的索引: " + index);
        
        // 填充
        int[] filled = new int[5];
        Arrays.fill(filled, 7);
        System.out.println("填充7: " + Arrays.toString(filled));
        
        // 比较
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        System.out.println("数组相等: " + Arrays.equals(arr1, arr2));
        
        // 复制
        int[] copied = Arrays.copyOf(arr1, 5);
        System.out.println("复制: " + Arrays.toString(copied));
        
        // 多维数组转字符串
        int[][] matrix = {{1, 2}, {3, 4}};
        System.out.println("矩阵: " + Arrays.deepToString(matrix));

        // ===== 8. Objects 工具类 =====
        System.out.println("\n===== 8. Objects 工具类 =====");
        String str1 = "Hello";
        String str2 = null;
        
        System.out.println("Objects.isNull(str1): " + java.util.Objects.isNull(str1));
        System.out.println("Objects.isNull(str2): " + java.util.Objects.isNull(str2));
        System.out.println("Objects.nonNull(str1): " + java.util.Objects.nonNull(str1));
        
        // 安全的toString
        System.out.println("Objects.toString(str1): " + java.util.Objects.toString(str1));
        System.out.println("Objects.toString(str2, \"默认值\"): " + java.util.Objects.toString(str2, "默认值"));
        
        // hashCode
        System.out.println("Objects.hashCode(str1): " + java.util.Objects.hashCode(str1));

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：字符串处理
        System.out.println("练习1 - 字符串处理:");
        String sentence = "Hello World Java Programming";
        System.out.println("原字符串: " + sentence);
        System.out.println("单词数: " + sentence.split(" ").length);
        System.out.println("首字母大写: " + capitalizeFirstLetter(sentence));
        System.out.println("反转单词: " + reverseWords(sentence));
        
        // 练习2：ArrayList操作
        System.out.println("\n练习2 - ArrayList操作:");
        ArrayList<L13_Student> students = new ArrayList<>();
        students.add(new L13_Student("张三", 90));
        students.add(new L13_Student("李四", 85));
        students.add(new L13_Student("王五", 95));
        students.add(new L13_Student("赵六", 88));
        
        System.out.println("学生列表: " + students);
        System.out.println("平均分: " + calculateAverage(students));
        System.out.println("最高分学生: " + findTopStudent(students));
        
        // 练习3：日期计算
        System.out.println("\n练习3 - 日期计算:");
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("2024年天数: " + daysBetween);
        
        // 练习4：随机密码生成
        System.out.println("\n练习4 - 随机密码生成:");
        String password = generatePassword(12);
        System.out.println("随机密码: " + password);
        System.out.println("密码强度: " + checkPasswordStrength(password));
    }

    // 练习1：首字母大写
    static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        String[] words = str.split(" ");
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1).toLowerCase())
                      .append(" ");
            }
        }
        return result.toString().trim();
    }

    // 练习1：反转单词
    static String reverseWords(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    // 练习2：计算平均分
    static double calculateAverage(ArrayList<L13_Student> students) {
        if (students == null || students.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (L13_Student student : students) {
            sum += student.score;
        }
        return (double) sum / students.size();
    }

    // 练习2：查找最高分学生
    static L13_Student findTopStudent(ArrayList<L13_Student> students) {
        if (students == null || students.isEmpty()) {
            return null;
        }
        L13_Student top = students.get(0);
        for (L13_Student student : students) {
            if (student.score > top.score) {
                top = student;
            }
        }
        return top;
    }

    // 练习4：生成随机密码
    static String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }

    // 练习4：检查密码强度
    static String checkPasswordStrength(String password) {
        if (password == null || password.length() < 8) {
            return "弱";
        }
        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }
        int strength = (hasUpper ? 1 : 0) + (hasLower ? 1 : 0) + (hasDigit ? 1 : 0) + (hasSpecial ? 1 : 0);
        if (strength >= 4) return "强";
        if (strength >= 3) return "中";
        return "弱";
    }
}

// 学生类（用于练习）
class L13_Student {
    String name;
    int score;

    L13_Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "L13_Student{name='" + name + "', score=" + score + "}";
    }
}
