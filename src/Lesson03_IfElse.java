public class Lesson03_IfElse {
    public static void main(String[] args) {
        // ===== 条件判断 =====

        int score = 85;

        // 1. 基本 if-else
        System.out.println("===== 基本 if-else =====");
        if (score >= 60) {
            System.out.println("及格了！");
        } else {
            System.out.println("不及格...");
        }

        // 2. 多条件 if-else if-else
        System.out.println("\n===== 多条件判断 =====");
        if (score >= 90) {
            System.out.println("优秀 A");
        } else if (score >= 80) {
            System.out.println("良好 B");
        } else if (score >= 60) {
            System.out.println("及格 C");
        } else {
            System.out.println("不及格 D");
        }

        // 3. 嵌套 if
        System.out.println("\n===== 嵌套 if =====");
        int age = 20;
        boolean hasID = true;
        if (age >= 18) {
            if (hasID) {
                System.out.println("允许进入");
            } else {
                System.out.println("请出示身份证");
            }
        } else {
            System.out.println("未成年人禁止进入");
        }

        // 4. switch 语句
        System.out.println("\n===== switch 语句 =====");
        int dayOfWeek = 3;
        String dayName;
        switch (dayOfWeek) {
            case 1:
                dayName = "星期一";
                break;
            case 2:
                dayName = "星期二";
                break;
            case 3:
                dayName = "星期三";
                break;
            case 4:
                dayName = "星期四";
                break;
            case 5:
                dayName = "星期五";
                break;
            case 6:
                dayName = "星期六";
                break;
            case 7:
                dayName = "星期日";
                break;
            default:
                dayName = "无效日期";
                break;
        }
        System.out.println("今天是: " + dayName);

        // 5. switch 表达式（Java 14+）
        System.out.println("\n===== switch 表达式 =====");
        String season = "夏";
        String weather = switch (season) {
            case "春" -> "温暖多风";
            case "夏" -> "炎热多雨";
            case "秋" -> "凉爽干燥";
            case "冬" -> "寒冷多雪";
            default -> "未知季节";
        };
        System.out.println(season + "天的天气: " + weather);

        // 6. 条件运算符的复杂应用
        System.out.println("\n===== 复杂条件判断 =====");
        int year = 2024;
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        System.out.println(year + "年是闰年吗？" + isLeapYear);

        // 7. 多条件组合
        System.out.println("\n===== 多条件组合 =====");
        int temperature = 28;
        boolean isRaining = false;
        String activity;
        if (temperature > 30) {
            activity = "游泳";
        } else if (temperature > 20) {
            if (isRaining) {
                activity = "室内运动";
            } else {
                activity = "户外野餐";
            }
        } else if (temperature > 10) {
            activity = "散步";
        } else {
            activity = "待在家里";
        }
        System.out.println("温度" + temperature + "°C，下雨：" + isRaining + "，建议活动：" + activity);

        // 8. 输入验证示例
        System.out.println("\n===== 输入验证示例 =====");
        String username = "admin";
        String password = "123456";
        boolean isValidUser = username.equals("admin");
        boolean isValidPass = password.length() >= 6;
        
        if (isValidUser && isValidPass) {
            System.out.println("登录成功！");
        } else {
            if (!isValidUser) {
                System.out.println("用户名错误");
            }
            if (!isValidPass) {
                System.out.println("密码长度不足6位");
            }
        }

        // 9. 成绩等级判定（综合示例）
        System.out.println("\n===== 成绩等级判定系统 =====");
        int finalScore = 78;
        String letterGrade;
        String comment;
        
        if (finalScore >= 90) {
            letterGrade = "A";
            comment = "优秀！继续保持！";
        } else if (finalScore >= 80) {
            letterGrade = "B";
            comment = "良好，还有提升空间。";
        } else if (finalScore >= 70) {
            letterGrade = "C";
            comment = "中等，需要更加努力。";
        } else if (finalScore >= 60) {
            letterGrade = "D";
            comment = "及格，但成绩不理想。";
        } else {
            letterGrade = "F";
            comment = "不及格，需要重修。";
        }
        System.out.println("成绩: " + finalScore + "分");
        System.out.println("等级: " + letterGrade);
        System.out.println("评语: " + comment);

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：判断一个年份是否是闰年
        int testYear = 2000;
        boolean isLeap = (testYear % 4 == 0 && testYear % 100 != 0) || (testYear % 400 == 0);
        System.out.println("练习1 - " + testYear + "年是闰年吗？" + isLeap);
        
        // 练习2：根据BMI判断体重状况
        double weight = 70.0;  // 体重（公斤）
        double heightM = 1.75; // 身高（米）
        double bmi = weight / (heightM * heightM);
        String bmiCategory;
        if (bmi < 18.5) {
            bmiCategory = "偏瘦";
        } else if (bmi < 24) {
            bmiCategory = "正常";
        } else if (bmi < 28) {
            bmiCategory = "偏胖";
        } else {
            bmiCategory = "肥胖";
        }
        System.out.printf("练习2 - BMI: %.1f，体重状况: %s%n", bmi, bmiCategory);
        
        // 练习3：计算出租车费
        double distance = 15.0;  // 行驶距离（公里）
        double fare;
        if (distance <= 3) {
            fare = 10;  // 起步价
        } else if (distance <= 10) {
            fare = 10 + (distance - 3) * 2;  // 3-10公里每公里2元
        } else {
            fare = 10 + 7 * 2 + (distance - 10) * 3;  // 超过10公里每公里3元
        }
        System.out.printf("练习3 - 行驶%.1f公里，车费: %.1f元%n", distance, fare);
        
        // 练习4：判断一个字符是字母、数字还是其他
        char testChar = 'A';
        String charType;
        if (Character.isLetter(testChar)) {
            charType = "字母";
        } else if (Character.isDigit(testChar)) {
            charType = "数字";
        } else {
            charType = "其他字符";
        }
        System.out.println("练习4 - 字符'" + testChar + "'是" + charType);
    }
}
