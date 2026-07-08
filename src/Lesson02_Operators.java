public class Lesson02_Operators {
    public static void main(String[] args) {
        // ===== 运算符 =====

        // 1. 算术运算符
        int a = 10, b = 3;
        System.out.println("===== 算术运算符 =====");
        System.out.println("加: " + (a + b));   // 13
        System.out.println("减: " + (a - b));   // 7
        System.out.println("乘: " + (a * b));   // 30
        System.out.println("除: " + (a / b));   // 3（整数除法，小数部分丢弃）
        System.out.println("余: " + (a % b));   // 1（取余数）
        
        // 整数除法 vs 浮点数除法
        System.out.println("整数除法: 10/3 = " + (10/3));      // 3
        System.out.println("浮点除法: 10.0/3 = " + (10.0/3));  // 3.333...
        
        // 2. 赋值运算符
        System.out.println("\n===== 赋值运算符 =====");
        int num = 10;
        System.out.println("初始值: " + num);  // 10
        num += 5;  // 等价于 num = num + 5
        System.out.println("+=5 后: " + num);  // 15
        num -= 3;  // 等价于 num = num - 3
        System.out.println("-=3 后: " + num);  // 12
        num *= 2;  // 等价于 num = num * 2
        System.out.println("*=2 后: " + num);  // 24
        num /= 4;  // 等价于 num = num / 4
        System.out.println("/=4 后: " + num);  // 6
        num %= 4;  // 等价于 num = num % 4
        System.out.println("%=4 后: " + num);  // 2

        // 3. 自增自减
        System.out.println("\n===== 自增自减 =====");
        int count = 0;
        System.out.println("初始count: " + count);  // 0
        count++;  // 后缀自增：先使用后加1
        System.out.println("count++ 后: " + count);  // 1
        ++count;  // 前缀自增：先加1后使用
        System.out.println("++count 后: " + count);  // 2
        
        // 自增自减的区别
        int i = 5;
        int j = i++;  // j=5, i=6（先赋值后自增）
        System.out.println("i++ 后: i=" + i + ", j=" + j);
        int k = ++i;  // k=7, i=7（先自增后赋值）
        System.out.println("++i 后: i=" + i + ", k=" + k);

        // 4. 比较运算符（结果是 boolean）
        System.out.println("\n===== 比较运算符 =====");
        System.out.println("10 > 3  → " + (10 > 3));   // true
        System.out.println("10 < 3  → " + (10 < 3));   // false
        System.out.println("10 >= 10 → " + (10 >= 10)); // true
        System.out.println("10 <= 9 → " + (10 <= 9));   // false
        System.out.println("10 == 3 → " + (10 == 3));  // false
        System.out.println("10 != 3 → " + (10 != 3));  // true
        
        // 注意：== 比较基本类型比较值，比较对象比较引用
        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");
        System.out.println("s1 == s2: " + (s1 == s2));      // true（字符串常量池）
        System.out.println("s1 == s3: " + (s1 == s3));      // false（不同对象）
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true（内容相同）

        // 5. 逻辑运算符
        System.out.println("\n===== 逻辑运算符 =====");
        boolean x = true, y = false;
        System.out.println("true && false → " + (x && y));  // 与：两个都true才true
        System.out.println("true || false → " + (x || y));  // 或：有一个true就true
        System.out.println("!true → " + (!x));               // 非：取反
        
        // 短路特性
        int val = 5;
        boolean result = (val > 10) && (val++ > 0);
        System.out.println("短路与: val=" + val + ", result=" + result);  // val仍为5
        
        // 6. 位运算符（了解即可）
        System.out.println("\n===== 位运算符 =====");
        int bitA = 0b1010;  // 二进制1010 = 十进制10
        int bitB = 0b1100;  // 二进制1100 = 十进制12
        System.out.println("A & B = " + (bitA & bitB));   // 按位与：8 (1000)
        System.out.println("A | B = " + (bitA | bitB));   // 按位或：14 (1110)
        System.out.println("A ^ B = " + (bitA ^ bitB));   // 按位异或：6 (0110)
        System.out.println("~A = " + (~bitA));             // 按位取反：-11
        System.out.println("A << 2 = " + (bitA << 2));    // 左移2位：40
        System.out.println("A >> 1 = " + (bitA >> 1));    // 右移1位：5

        // 7. 三元运算符
        System.out.println("\n===== 三元运算符 =====");
        int score = 85;
        String grade = (score >= 60) ? "及格" : "不及格";
        System.out.println("成绩" + score + "分: " + grade);
        
        // 嵌套三元运算符
        String level = (score >= 90) ? "优秀" : 
                      (score >= 80) ? "良好" : 
                      (score >= 60) ? "及格" : "不及格";
        System.out.println("等级: " + level);

        // 8. 运算符优先级（从高到低）
        System.out.println("\n===== 运算符优先级 =====");
        // () → ++/-- → * / % → + - → 比较 → 逻辑 → 赋值
        int priority = 2 + 3 * 4;      // 14，不是20
        System.out.println("2 + 3 * 4 = " + priority);
        int withParen = (2 + 3) * 4;   // 20
        System.out.println("(2 + 3) * 4 = " + withParen);
        
        // 复杂表达式
        int complex = 10 + 5 * 2 > 15 && true ? 1 : 0;
        System.out.println("复杂表达式结果: " + complex);

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        // 练习1：计算两个数的和、差、积、商、余
        int num1 = 15, num2 = 4;
        System.out.println("练习1 - 两数运算:");
        System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
        System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
        System.out.println(num1 + " * " + num2 + " = " + (num1 * num2));
        System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
        System.out.println(num1 + " % " + num2 + " = " + (num1 % num2));
        
        // 练习2：判断一个数是否为偶数
        int testNum = 7;
        boolean isEven = (testNum % 2 == 0);
        System.out.println("\n练习2 - " + testNum + "是偶数吗？" + isEven);
        
        // 练习3：交换两个变量的值
        int swapA = 10, swapB = 20;
        System.out.println("\n练习3 - 交换前: a=" + swapA + ", b=" + swapB);
        int temp = swapA;
        swapA = swapB;
        swapB = temp;
        System.out.println("交换后: a=" + swapA + ", b=" + swapB);
        
        // 练习4：计算表达式 (a + b) * c / d 的结果
        int exA = 5, exB = 3, exC = 4, exD = 2;
        int expression = (exA + exB) * exC / exD;
        System.out.println("\n练习4 - (5+3)*4/2 = " + expression);
    }
}
