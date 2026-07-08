// ===== 异常处理 =====

import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;

// 异常处理的核心思想：优雅地处理错误情况

// 1. 自定义异常类
class AgeException extends Exception {
    public AgeException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception {
    private double deficit;

    public InsufficientFundsException(double deficit) {
        super("余额不足，差额: " + deficit + "元");
        this.deficit = deficit;
    }

    public double getDeficit() {
        return deficit;
    }
}

class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String email) {
        super("无效的邮箱格式: " + email);
    }
}

// 2. 异常处理示例类
class ExceptionDemo {
    // 声明抛出异常
    public static int divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("除数不能为0");
        }
        return a / b;
    }

    // 声明抛出检查异常
    public static void readFile(String filename) throws FileNotFoundException, IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在: " + filename);
        }
        // 模拟读取文件
        System.out.println("读取文件: " + filename);
    }

    // try-with-resources 自动关闭资源
    public static String readWithResources(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        }
    }

    // 多个异常处理
    public static void processInput(String input) {
        try {
            int number = Integer.parseInt(input);
            if (number < 0) {
                throw new IllegalArgumentException("数字不能为负数");
            }
            System.out.println("处理数字: " + number);
        } catch (NumberFormatException e) {
            System.out.println("格式错误: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("参数错误: " + e.getMessage());
        } finally {
            System.out.println("输入处理完成");
        }
    }
}

public class Lesson14_Exception {
    public static void main(String[] args) {
        System.out.println("===== 异常处理详解 =====");

        // 1. 基本 try-catch
        System.out.println("\n----- 1. 基本 try-catch -----");
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("捕获异常: " + e.getMessage());
        } finally {
            System.out.println("finally: 无论是否异常都执行");
        }

        // 2. 数组越界
        System.out.println("\n----- 2. 数组越界 -----");
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("数组下标越界: " + e.getMessage());
        }

        // 3. 空指针
        System.out.println("\n----- 3. 空指针 -----");
        try {
            String str = null;
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("空指针异常");
        }

        // 4. 多个catch
        System.out.println("\n----- 4. 多个catch -----");
        try {
            String s = "abc";
            int num = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("数字格式错误");
        } catch (Exception e) {
            System.out.println("其他异常");
        }

        // 5. 自定义异常
        System.out.println("\n----- 5. 自定义异常 -----");
        try {
            checkAge(15);
        } catch (AgeException e) {
            System.out.println("错误: " + e.getMessage());
        }

        // 6. throws 声明异常
        System.out.println("\n----- 6. throws 声明 -----");
        try {
            int result = ExceptionDemo.divide(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("计算错误: " + e.getMessage());
        }

        // 7. throw 抛出异常
        System.out.println("\n----- 7. throw 抛出 -----");
        try {
            validateEmail("invalid-email");
        } catch (InvalidEmailException e) {
            System.out.println("邮箱验证失败: " + e.getMessage());
        }

        // 8. try-with-resources
        System.out.println("\n----- 8. try-with-resources -----");
        try (Scanner scanner = new Scanner(System.in)) {
            // 模拟从字符串读取
            System.out.println("资源自动关闭演示");
        }

        // 9. 异常链
        System.out.println("\n----- 9. 异常链 -----");
        try {
            processTransaction(1000, 500);
        } catch (InsufficientFundsException e) {
            System.out.println("交易失败: " + e.getMessage());
            System.out.println("差额: " + e.getDeficit() + "元");
        }

        // 10. 嵌套 try-catch
        System.out.println("\n----- 10. 嵌套try-catch -----");
        try {
            try {
                int[] arr = new int[5];
                arr[10] = 100;  // 数组越界
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("内部catch: " + e.getMessage());
                throw new RuntimeException("包装异常", e);  // 异常链
            }
        } catch (RuntimeException e) {
            System.out.println("外部catch: " + e.getMessage());
            System.out.println("原始异常: " + e.getCause().getMessage());
        }

        // 11. 常见异常类型
        System.out.println("\n----- 11. 常见异常类型 -----");
        demonstrateCommonExceptions();

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：安全输入验证
        System.out.println("练习1 - 安全输入验证:");
        testSafeInput();
        
        // 练习2：文件操作异常处理
        System.out.println("\n练习2 - 文件操作异常处理:");
        testFileOperations();
        
        // 练习3：银行账户异常处理
        System.out.println("\n练习3 - 银行账户异常处理:");
        testBankAccount();
        
        // 练习4：计算器异常处理
        System.out.println("\n练习4 - 计算器异常处理:");
        testCalculator();
    }

    // 自定义异常使用
    static void checkAge(int age) throws AgeException {
        if (age < 18) {
            throw new AgeException("年龄不能小于18岁，当前年龄: " + age);
        }
        System.out.println("年龄合法: " + age);
    }

    // 邮箱验证
    static void validateEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new InvalidEmailException(email);
        }
        System.out.println("邮箱验证通过: " + email);
    }

    // 交易处理
    static void processTransaction(double balance, double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }
        System.out.println("交易成功，余额: " + (balance - amount));
    }

    // 常见异常演示
    static void demonstrateCommonExceptions() {
        // ClassCastException
        try {
            Object obj = "Hello";
            Integer num = (Integer) obj;  // 类转换异常
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: " + e.getMessage());
        }

        // StackOverflowError
        try {
            // 模拟递归过深
            System.out.println("递归演示（模拟栈溢出）");
            // recursiveMethod();  // 实际会栈溢出
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError: 递归过深");
        }

        // InputMismatchException
        try {
            // 模拟输入不匹配
            String input = "abc";
            // Scanner scanner = new Scanner(input);
            // int num = scanner.nextInt();  // 会抛出异常
            System.out.println("输入不匹配演示");
        } catch (InputMismatchException e) {
            System.out.println("InputMismatchException: " + e.getMessage());
        }
    }

    // 练习1：安全输入验证
    static void testSafeInput() {
        String[] inputs = {"123", "abc", "456", "xyz", "789"};
        for (String input : inputs) {
            try {
                int number = Integer.parseInt(input);
                if (number < 0) {
                    throw new IllegalArgumentException("负数不允许");
                }
                System.out.println("有效输入: " + number);
            } catch (NumberFormatException e) {
                System.out.println("无效输入: " + input + " (不是数字)");
            } catch (IllegalArgumentException e) {
                System.out.println("无效输入: " + input + " (" + e.getMessage() + ")");
            }
        }
    }

    // 练习2：文件操作异常处理
    static void testFileOperations() {
        String[] filenames = {"test.txt", "nonexistent.txt", "invalid/path.txt"};
        for (String filename : filenames) {
            try {
                ExceptionDemo.readFile(filename);
                System.out.println("文件读取成功: " + filename);
            } catch (FileNotFoundException e) {
                System.out.println("文件未找到: " + filename);
            } catch (IOException e) {
                System.out.println("IO错误: " + filename + " - " + e.getMessage());
            }
        }
    }

    // 练习3：银行账户异常处理
    static void testBankAccount() {
        double balance = 1000;
        double[] withdrawals = {500, 300, 800, 200, 1000};
        
        for (double amount : withdrawals) {
            try {
                if (amount > balance) {
                    throw new InsufficientFundsException(amount - balance);
                }
                balance -= amount;
                System.out.printf("取款 %.1f 元成功，余额: %.1f 元%n", amount, balance);
            } catch (InsufficientFundsException e) {
                System.out.printf("取款 %.1f 元失败: %s%n", amount, e.getMessage());
            }
        }
    }

    // 练习4：计算器异常处理
    static void testCalculator() {
        String[][] operations = {
            {"10", "+", "5"},
            {"10", "/", "0"},
            {"abc", "*", "5"},
            {"10", "-", "3"},
            {"10", "%", "0"}
        };
        
        for (String[] op : operations) {
            try {
                double num1 = Double.parseDouble(op[0]);
                double num2 = Double.parseDouble(op[2]);
                String operator = op[1];
                double result;
                
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("除数不能为0");
                        }
                        result = num1 / num2;
                        break;
                    case "%":
                        if (num2 == 0) {
                            throw new ArithmeticException("模数不能为0");
                        }
                        result = num1 % num2;
                        break;
                    default:
                        throw new IllegalArgumentException("未知运算符: " + operator);
                }
                System.out.printf("%.1f %s %.1f = %.1f%n", num1, operator, num2, result);
            } catch (NumberFormatException e) {
                System.out.println("数字格式错误: " + op[0] + " 或 " + op[2]);
            } catch (ArithmeticException e) {
                System.out.println("计算错误: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("参数错误: " + e.getMessage());
            }
        }
    }
}
