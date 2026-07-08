public class Lesson05_Array {
    public static void main(String[] args) {
        // ===== 数组基础 =====

        // 1. 声明和创建数组
        System.out.println("===== 1. 声明和创建数组 =====");
        int[] nums = new int[5];        // 创建长度为5的数组
        nums[0] = 10;                   // 给第一个元素赋值
        nums[1] = 20;
        nums[2] = 30;
        nums[3] = 40;
        nums[4] = 50;
        // nums[5] = 60;  // 错误！下标越界

        // 2. 声明并初始化
        System.out.println("\n===== 2. 声明并初始化 =====");
        String[] fruits = {"苹果", "香蕉", "橘子"};
        double[] scores = {90.5, 85.0, 92.5, 88.0};

        // 3. 访问数组
        System.out.println("\n===== 3. 访问数组 =====");
        System.out.println("第一个水果: " + fruits[0]);
        System.out.println("数组长度: " + scores.length);
        System.out.println("最后一个水果: " + fruits[fruits.length - 1]);

        // 4. 遍历数组 - for循环
        System.out.println("\n===== 4. for循环遍历 =====");
        for (int i = 0; i < nums.length; i++) {
            System.out.println("nums[" + i + "] = " + nums[i]);
        }

        // 5. 遍历数组 - 增强for循环
        System.out.println("\n===== 5. 增强for循环 =====");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // 6. 求平均值练习
        System.out.println("\n===== 6. 求平均值 =====");
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        double avg = (double) sum / nums.length;
        System.out.println("平均值: " + avg);

        // ===== 数组常见操作 =====

        // 7. 数组排序
        System.out.println("\n===== 7. 数组排序 =====");
        int[] unsorted = {64, 25, 12, 22, 11};
        System.out.println("排序前: " + java.util.Arrays.toString(unsorted));
        
        // 冒泡排序
        int[] bubbleSorted = unsorted.clone();
        for (int i = 0; i < bubbleSorted.length - 1; i++) {
            for (int j = 0; j < bubbleSorted.length - i - 1; j++) {
                if (bubbleSorted[j] > bubbleSorted[j + 1]) {
                    int temp = bubbleSorted[j];
                    bubbleSorted[j] = bubbleSorted[j + 1];
                    bubbleSorted[j + 1] = temp;
                }
            }
        }
        System.out.println("冒泡排序后: " + java.util.Arrays.toString(bubbleSorted));
        
        // 使用Arrays.sort()
        int[] arraySorted = unsorted.clone();
        java.util.Arrays.sort(arraySorted);
        System.out.println("Arrays排序后: " + java.util.Arrays.toString(arraySorted));

        // 8. 数组查找
        System.out.println("\n===== 8. 数组查找 =====");
        int[] searchArray = {10, 20, 30, 40, 50};
        int searchTarget = 30;
        int foundIndex = -1;
        
        // 线性查找
        for (int i = 0; i < searchArray.length; i++) {
            if (searchArray[i] == searchTarget) {
                foundIndex = i;
                break;
            }
        }
        System.out.println("元素 " + searchTarget + " 在索引: " + foundIndex);
        
        // 使用Arrays.binarySearch()（需要先排序）
        int[] sortedArray = {10, 20, 30, 40, 50};
        int binaryIndex = java.util.Arrays.binarySearch(sortedArray, 30);
        System.out.println("二分查找索引: " + binaryIndex);

        // 9. 数组复制
        System.out.println("\n===== 9. 数组复制 =====");
        int[] original = {1, 2, 3, 4, 5};
        
        // 方法1：clone()
        int[] cloned = original.clone();
        System.out.println("克隆数组: " + java.util.Arrays.toString(cloned));
        
        // 方法2：Arrays.copyOf()
        int[] copied = java.util.Arrays.copyOf(original, 3);  // 复制前3个
        System.out.println("复制前3个: " + java.util.Arrays.toString(copied));
        
        // 方法3：System.arraycopy()
        int[] destination = new int[5];
        System.arraycopy(original, 0, destination, 0, original.length);
        System.out.println("System复制: " + java.util.Arrays.toString(destination));

        // 10. 数组填充
        System.out.println("\n===== 10. 数组填充 =====");
        int[] filled = new int[5];
        java.util.Arrays.fill(filled, 7);
        System.out.println("填充7: " + java.util.Arrays.toString(filled));
        
        int[] rangeFilled = {1, 2, 3, 4, 5};
        java.util.Arrays.fill(rangeFilled, 1, 3, 9);  // 索引1到2填充9
        System.out.println("部分填充9: " + java.util.Arrays.toString(rangeFilled));

        // 11. 数组比较
        System.out.println("\n===== 11. 数组比较 =====");
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {1, 2, 4};
        
        System.out.println("arr1 == arr2: " + (arr1 == arr2));  // false（引用比较）
        System.out.println("Arrays.equals(arr1, arr2): " + java.util.Arrays.equals(arr1, arr2));  // true
        System.out.println("Arrays.equals(arr1, arr3): " + java.util.Arrays.equals(arr1, arr3));  // false

        // ===== 二维数组 =====

        // 12. 二维数组基础
        System.out.println("\n===== 12. 二维数组 =====");
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("二维数组遍历:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        
        // 13. 不规则二维数组
        System.out.println("\n===== 13. 不规则二维数组 =====");
        int[][] jagged = new int[3][];
        jagged[0] = new int[] {1, 2};
        jagged[1] = new int[] {3, 4, 5};
        jagged[2] = new int[] {6, 7, 8, 9};
        
        System.out.println("不规则数组:");
        for (int i = 0; i < jagged.length; i++) {
            for (int j = 0; j < jagged[i].length; j++) {
                System.out.print(jagged[i][j] + " ");
            }
            System.out.println();
        }

        // 14. 二维数组应用：矩阵转置
        System.out.println("\n===== 14. 矩阵转置 =====");
        int[][] originalMatrix = {
            {1, 2, 3},
            {4, 5, 6}
        };
        int rows = originalMatrix.length;
        int cols = originalMatrix[0].length;
        int[][] transposed = new int[cols][rows];
        
        // 转置
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = originalMatrix[i][j];
            }
        }
        
        System.out.println("原矩阵:");
        for (int[] row : originalMatrix) {
            System.out.println(java.util.Arrays.toString(row));
        }
        System.out.println("转置后:");
        for (int[] row : transposed) {
            System.out.println(java.util.Arrays.toString(row));
        }

        // ===== Arrays工具类常用方法 =====

        // 15. Arrays.toString() 和 Arrays.deepToString()
        System.out.println("\n===== 15. Arrays工具类 =====");
        int[] demoArray = {5, 2, 8, 1, 9};
        System.out.println("数组: " + java.util.Arrays.toString(demoArray));
        
        // 排序
        java.util.Arrays.sort(demoArray);
        System.out.println("排序后: " + java.util.Arrays.toString(demoArray));
        
        // 填充
        int[] fillArray = new int[5];
        java.util.Arrays.fill(fillArray, 3);
        System.out.println("填充3: " + java.util.Arrays.toString(fillArray));
        
        // 比较
        int[] compare1 = {1, 2, 3};
        int[] compare2 = {1, 2, 3};
        System.out.println("数组相等: " + java.util.Arrays.equals(compare1, compare2));
        
        // 哈希值
        System.out.println("数组哈希: " + java.util.Arrays.hashCode(compare1));

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：找出数组中的最大值和最小值
        System.out.println("练习1 - 找最值:");
        int[] findMaxMin = {34, 15, 88, 2, 56, 42};
        int max = findMaxMin[0];
        int min = findMaxMin[0];
        for (int num : findMaxMin) {
            if (num > max) max = num;
            if (num < min) min = num;
        }
        System.out.println("数组: " + java.util.Arrays.toString(findMaxMin));
        System.out.println("最大值: " + max + ", 最小值: " + min);
        
        // 练习2：数组反转
        System.out.println("\n练习2 - 数组反转:");
        int[] toReverse = {1, 2, 3, 4, 5};
        System.out.println("反转前: " + java.util.Arrays.toString(toReverse));
        for (int i = 0; i < toReverse.length / 2; i++) {
            int temp = toReverse[i];
            toReverse[i] = toReverse[toReverse.length - 1 - i];
            toReverse[toReverse.length - 1 - i] = temp;
        }
        System.out.println("反转后: " + java.util.Arrays.toString(toReverse));
        
        // 练习3：合并两个有序数组
        System.out.println("\n练习3 - 合并有序数组:");
        int[] arrA = {1, 3, 5, 7};
        int[] arrB = {2, 4, 6, 8};
        int[] merged = new int[arrA.length + arrB.length];
        int i = 0, j = 0, k = 0;
        while (i < arrA.length && j < arrB.length) {
            if (arrA[i] <= arrB[j]) {
                merged[k++] = arrA[i++];
            } else {
                merged[k++] = arrB[j++];
            }
        }
        while (i < arrA.length) merged[k++] = arrA[i++];
        while (j < arrB.length) merged[k++] = arrB[j++];
        System.out.println("合并结果: " + java.util.Arrays.toString(merged));
        
        // 练习4：统计字符出现次数
        System.out.println("\n练习4 - 统计字符出现次数:");
        String text = "hello world";
        int[] charCount = new int[26];  // 26个字母
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                charCount[Character.toLowerCase(c) - 'a']++;
            }
        }
        System.out.println("字符串: \"" + text + "\"");
        for (int idx = 0; idx < charCount.length; idx++) {
            if (charCount[idx] > 0) {
                System.out.println((char)('a' + idx) + ": " + charCount[idx] + "次");
            }
        }
        
        // 练习5：杨辉三角
        System.out.println("\n练习5 - 杨辉三角（前6行）:");
        int numRows = 6;
        int[][] triangle = new int[numRows][];
        for (int row = 0; row < numRows; row++) {
            triangle[row] = new int[row + 1];
            triangle[row][0] = 1;
            triangle[row][row] = 1;
            for (int col = 1; col < row; col++) {
                triangle[row][col] = triangle[row-1][col-1] + triangle[row-1][col];
            }
        }
        for (int row = 0; row < numRows; row++) {
            // 打印空格
            for (int s = 0; s < numRows - row - 1; s++) {
                System.out.print("  ");
            }
            for (int num : triangle[row]) {
                System.out.printf("%4d", num);
            }
            System.out.println();
        }
    }
}
