public class Lesson04_Loop {
    public static void main(String[] args) {
        // ===== 循环 =====

        // 1. for 循环：知道循环次数时用
        System.out.println("===== for 循环 =====");
        System.out.println("--- 打印 1~5 ---");
        for (int i = 1; i <= 5; i++) {
            System.out.println("第 " + i + " 次");
        }

        // for 循环变体
        System.out.println("\n--- for 循环变体 ---");
        // 倒序循环
        System.out.println("倒序打印 5~1:");
        for (int i = 5; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        // 步长为2
        System.out.println("偶数 2~10:");
        for (int i = 2; i <= 10; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        // 多个变量控制
        System.out.println("多变量控制:");
        for (int i = 0, j = 10; i < j; i++, j--) {
            System.out.println("i=" + i + ", j=" + j);
        }

        // 2. while 循环：不知道次数，根据条件循环
        System.out.println("\n===== while 循环 =====");
        System.out.println("--- 累加到 10 ---");
        int sum = 0;
        int n = 1;
        while (n <= 10) {
            sum += n;  // sum = sum + n
            n++;
        }
        System.out.println("1+2+...+10 = " + sum);  // 55
        
        // while 循环计算阶乘
        System.out.println("\n--- 计算 5 的阶乘 ---");
        int factorial = 1;
        int factNum = 5;
        int tempNum = factNum;
        while (tempNum > 1) {
            factorial *= tempNum;
            tempNum--;
        }
        System.out.println(factNum + "! = " + factorial);

        // 3. do-while 循环：至少执行一次
        System.out.println("\n===== do-while 循环 =====");
        System.out.println("--- 至少执行一次 ---");
        int num = 10;
        do {
            System.out.println("num = " + num);
            num--;
        } while (num > 7);
        
        // do-while 用于菜单系统
        System.out.println("\n--- 模拟菜单系统 ---");
        int choice = 0;
        do {
            System.out.println("1. 查看余额");
            System.out.println("2. 存款");
            System.out.println("3. 取款");
            System.out.println("0. 退出");
            // 模拟用户选择
            choice = (choice == 0) ? 2 : 0;  // 模拟选择2然后退出
            System.out.println("用户选择: " + choice);
            switch (choice) {
                case 1:
                    System.out.println("余额: 1000元");
                    break;
                case 2:
                    System.out.println("存款成功");
                    break;
                case 3:
                    System.out.println("取款成功");
                    break;
                case 0:
                    System.out.println("退出系统");
                    break;
            }
        } while (choice != 0);

        // 4. for-each 循环（增强 for 循环）
        System.out.println("\n===== for-each 循环 =====");
        int[] numbers = {10, 20, 30, 40, 50};
        System.out.println("遍历数组:");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
        
        // 遍历字符串数组
        String[] fruits = {"苹果", "香蕉", "橙子", "葡萄"};
        System.out.println("水果列表:");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }

        // 5. 嵌套循环
        System.out.println("\n===== 嵌套循环 =====");
        // 打印矩形
        System.out.println("--- 3x4 矩形 ---");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        
        // 打印直角三角形
        System.out.println("\n--- 直角三角形 ---");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        
        // 九九乘法表
        System.out.println("\n--- 九九乘法表 ---");
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%d×%d=%-4d", j, i, i * j);
            }
            System.out.println();
        }

        // 6. break 和 continue
        System.out.println("\n===== break 和 continue =====");
        System.out.println("--- break：遇到 3 就停 ---");
        for (int i = 1; i <= 5; i++) {
            if (i == 3) break;   // 跳出整个循环
            System.out.println(i);
        }

        System.out.println("\n--- continue：跳过 3 ---");
        for (int i = 1; i <= 5; i++) {
            if (i == 3) continue; // 跳过本次，继续下一次
            System.out.println(i);
        }
        
        // 带标签的 break（跳出多层循环）
        System.out.println("\n--- 带标签的 break ---");
        outer:
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 2) {
                    System.out.println("遇到 i=2, j=2，跳出外层循环");
                    break outer;
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }

        // 7. 无限循环
        System.out.println("\n===== 无限循环示例 =====");
        // 使用 while(true) 创建无限循环
        int counter = 0;
        while (true) {
            if (counter >= 3) {
                System.out.println("达到条件，退出无限循环");
                break;
            }
            System.out.println("循环次数: " + counter);
            counter++;
        }

        // 8. 循环中的常见模式
        System.out.println("\n===== 循环常见模式 =====");
        
        // 模式1：累加器
        System.out.println("--- 累加器模式 ---");
        int total = 0;
        for (int i = 1; i <= 100; i++) {
            total += i;
        }
        System.out.println("1到100的和: " + total);
        
        // 模式2：查找最大值/最小值
        System.out.println("\n--- 查找最值 ---");
        int[] scores = {85, 92, 78, 96, 88};
        int max = scores[0];
        int min = scores[0];
        for (int score : scores) {
            if (score > max) max = score;
            if (score < min) min = score;
        }
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        
        // 模式3：计数器
        System.out.println("\n--- 计数器模式 ---");
        int evenCount = 0;
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) evenCount++;
        }
        System.out.println("1-20中偶数的个数: " + evenCount);

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：计算斐波那契数列前10项
        System.out.println("练习1 - 斐波那契数列前10项:");
        int fib1 = 0, fib2 = 1;
        System.out.print(fib1 + " " + fib2 + " ");
        for (int i = 3; i <= 10; i++) {
            int fibNext = fib1 + fib2;
            System.out.print(fibNext + " ");
            fib1 = fib2;
            fib2 = fibNext;
        }
        System.out.println();
        
        // 练习2：判断一个数是否是质数
        System.out.println("\n练习2 - 判断质数:");
        int testPrime = 17;
        boolean isPrime = true;
        if (testPrime <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(testPrime); i++) {
                if (testPrime % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        System.out.println(testPrime + (isPrime ? "是" : "不是") + "质数");
        
        // 练习3：打印菱形
        System.out.println("\n练习3 - 打印菱形:");
        int diamondSize = 5;
        // 上半部分
        for (int i = 1; i <= diamondSize; i++) {
            for (int j = 1; j <= diamondSize - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        // 下半部分
        for (int i = diamondSize - 1; i >= 1; i--) {
            for (int j = 1; j <= diamondSize - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        // 练习4：猜数字游戏模拟
        System.out.println("\n练习4 - 猜数字游戏模拟:");
        int secretNumber = 42;
        int guess = 50;
        int attempts = 0;
        do {
            attempts++;
            if (guess == secretNumber) {
                System.out.println("恭喜！猜对了！用了" + attempts + "次");
                break;
            } else if (guess < secretNumber) {
                System.out.println("太小了！");
                guess += 5;  // 模拟猜大一点
            } else {
                System.out.println("太大了！");
                guess -= 3;  // 模拟猜小一点
            }
        } while (attempts < 10);
    }
}
