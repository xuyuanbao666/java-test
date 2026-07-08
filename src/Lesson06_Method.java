public class Lesson06_Method {
    public static void main(String[] args) {
        // ===== 方法基础 =====

        // 1. 调用无参无返回值方法
        System.out.println("===== 1. 无参无返回值方法 =====");
        sayHello();

        // 2. 调用有参方法
        System.out.println("\n===== 2. 有参方法 =====");
        greet("小明");
        greet("小红");

        // 3. 调用有返回值方法
        System.out.println("\n===== 3. 有返回值方法 =====");
        int result = add(10, 20);
        System.out.println("10 + 20 = " + result);

        // 4. 方法重载
        System.out.println("\n===== 4. 方法重载 =====");
        System.out.println("add(1,2) = " + add(1, 2));
        System.out.println("add(1,2,3) = " + add(1, 2, 3));
        System.out.println("add(1.5,2.5) = " + add(1.5, 2.5));

        // ===== 方法参数传递 =====

        // 5. 值传递（基本类型）
        System.out.println("\n===== 5. 值传递 =====");
        int num = 10;
        System.out.println("调用前: num = " + num);
        modifyValue(num);
        System.out.println("调用后: num = " + num);  // 仍然是10

        // 6. 引用传递（对象类型）
        System.out.println("\n===== 6. 引用传递 =====");
        int[] array = {1, 2, 3};
        System.out.println("调用前: array[0] = " + array[0]);
        modifyArray(array);
        System.out.println("调用后: array[0] = " + array[0]);  // 变为100

        // 7. 可变参数
        System.out.println("\n===== 7. 可变参数 =====");
        System.out.println("求和(1,2,3): " + sum(1, 2, 3));
        System.out.println("求和(1,2,3,4,5): " + sum(1, 2, 3, 4, 5));
        System.out.println("求和(): " + sum());

        // 8. 递归方法
        System.out.println("\n===== 8. 递归方法 =====");
        System.out.println("5的阶乘: " + factorial(5));
        System.out.println("斐波那契第10项: " + fibonacci(10));

        // 9. 方法引用（Java 8+）
        System.out.println("\n===== 9. 方法引用 =====");
        // 静态方法引用
        java.util.function.BinaryOperator<Integer> addOp = Lesson06_Method::addStatic;
        System.out.println("方法引用加法: " + addOp.apply(5, 3));
        
        // 实例方法引用
        Lesson06_Method instance = new Lesson06_Method();
        java.util.function.UnaryOperator<String> toUpper = instance::toUpperCase;
        System.out.println("转大写: " + toUpper.apply("hello"));

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：判断素数
        System.out.println("练习1 - 素数判断:");
        for (int i = 2; i <= 20; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        
        // 练习2：数组求最大值
        System.out.println("\n练习2 - 数组最大值:");
        int[] arr = {23, 45, 12, 67, 34, 89, 56};
        System.out.println("数组: " + java.util.Arrays.toString(arr));
        System.out.println("最大值: " + max(arr));
        
        // 练习3：字符串反转
        System.out.println("\n练习3 - 字符串反转:");
        String original = "Hello World";
        String reversed = reverseString(original);
        System.out.println("原字符串: " + original);
        System.out.println("反转后: " + reversed);
        
        // 练习4：计算组合数 C(n,k)
        System.out.println("\n练习4 - 组合数计算:");
        int n = 5, k = 2;
        System.out.println("C(" + n + "," + k + ") = " + combination(n, k));
        
        // 练习5：汉诺塔问题
        System.out.println("\n练习5 - 汉诺塔（3个盘子）:");
        hanoi(3, 'A', 'B', 'C');
    }

    // ===== 方法定义 =====

    // 无参无返回值
    static void sayHello() {
        System.out.println("Hello!");
    }

    // 有参无返回值
    static void greet(String name) {
        System.out.println("你好, " + name + "!");
    }

    // 有参有返回值
    static int add(int a, int b) {
        return a + b;
    }

    // 方法重载：同名不同参数
    static int add(int a, int b, int c) {
        return a + b + c;
    }

    // 方法重载：不同类型参数
    static double add(double a, double b) {
        return a + b;
    }

    // 值传递示例
    static void modifyValue(int x) {
        x = 100;  // 只修改局部变量
        System.out.println("方法内: x = " + x);
    }

    // 引用传递示例
    static void modifyArray(int[] arr) {
        arr[0] = 100;  // 修改数组内容
        System.out.println("方法内: arr[0] = " + arr[0]);
    }

    // 可变参数
    static int sum(int... numbers) {
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }

    // 递归：阶乘
    static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    // 递归：斐波那契数列
    static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 静态方法（用于方法引用）
    static int addStatic(int a, int b) {
        return a + b;
    }

    // 实例方法（用于方法引用）
    String toUpperCase(String str) {
        return str.toUpperCase();
    }

    // 判断素数
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // 数组最大值
    static int max(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    // 字符串反转
    static String reverseString(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return new StringBuilder(str).reverse().toString();
    }

    // 组合数计算 C(n,k) = n! / (k! * (n-k)!)
    static long combination(int n, int k) {
        if (k > n) return 0;
        if (k == 0 || k == n) return 1;
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    // 汉诺塔递归
    static void hanoi(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("移动盘子 1 从 " + from + " 到 " + to);
            return;
        }
        hanoi(n - 1, from, aux, to);
        System.out.println("移动盘子 " + n + " 从 " + from + " 到 " + to);
        hanoi(n - 1, aux, to, from);
    }

    // 方法重载：判断素数（重载版本）
    static boolean isPrime(int n, boolean detailed) {
        if (n < 2) return false;
        if (detailed) {
            System.out.print(n + " 的因数: ");
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }
        return isPrime(n);
    }
}
