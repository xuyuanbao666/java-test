import java.util.Scanner;

/**
 * Java 学习课程主程序
 * 包含从基础到高级的 Java 学习内容
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║           Java 学习课程 - 主菜单                  ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("欢迎来到 Java 学习课程！本课程包含以下内容：");
        System.out.println();
        
        // 显示课程列表
        showCourseMenu();
        
        System.out.println("请选择要运行的课程 (1-21)，或输入 0 退出：");
        System.out.print("> ");
        
        // 模拟用户选择
        int choice = 1;  // 默认选择课程1
        
        switch (choice) {
            case 0:
                System.out.println("感谢使用 Java 学习课程，再见！");
                break;
            case 1:
                System.out.println("\n正在运行: Lesson01 - 变量和数据类型");
                System.out.println("请直接运行 Lesson01_Variables.java");
                break;
            case 2:
                System.out.println("\n正在运行: Lesson02 - 运算符");
                System.out.println("请直接运行 Lesson02_Operators.java");
                break;
            case 3:
                System.out.println("\n正在运行: Lesson03 - 条件语句");
                System.out.println("请直接运行 Lesson03_IfElse.java");
                break;
            case 4:
                System.out.println("\n正在运行: Lesson04 - 循环");
                System.out.println("请直接运行 Lesson04_Loop.java");
                break;
            case 5:
                System.out.println("\n正在运行: Lesson05 - 数组");
                System.out.println("请直接运行 Lesson05_Array.java");
                break;
            case 6:
                System.out.println("\n正在运行: Lesson06 - 方法");
                System.out.println("请直接运行 Lesson06_Method.java");
                break;
            case 7:
                System.out.println("\n正在运行: Lesson07 - 类和对象");
                System.out.println("请直接运行 Lesson07_ClassAndObject.java");
                break;
            case 8:
                System.out.println("\n正在运行: Lesson08 - 封装");
                System.out.println("请直接运行 Lesson08_Encapsulation.java");
                break;
            case 9:
                System.out.println("\n正在运行: Lesson09 - 继承");
                System.out.println("请直接运行 Lesson09_Inheritance.java");
                break;
            case 10:
                System.out.println("\n正在运行: Lesson10 - 多态");
                System.out.println("请直接运行 Lesson10_Polymorphism.java");
                break;
            case 11:
                System.out.println("\n正在运行: Lesson11 - 接口");
                System.out.println("请直接运行 Lesson11_Interface.java");
                break;
            case 12:
                System.out.println("\n正在运行: Lesson12 - 抽象类");
                System.out.println("请直接运行 Lesson12_AbstractClass.java");
                break;
            case 13:
                System.out.println("\n正在运行: Lesson13 - 常用类");
                System.out.println("请直接运行 Lesson13_CommonClasses.java");
                break;
            case 14:
                System.out.println("\n正在运行: Lesson14 - 异常处理");
                System.out.println("请直接运行 Lesson14_Exception.java");
                break;
            case 15:
                System.out.println("\n正在运行: Lesson15 - 文件IO");
                System.out.println("请直接运行 Lesson15_FileIO.java");
                break;
            case 16:
                System.out.println("\n正在运行: Lesson16 - 集合框架");
                System.out.println("请直接运行 Lesson16_Collection.java");
                break;
            case 17:
                System.out.println("\n正在运行: Lesson17 - 泛型");
                System.out.println("请直接运行 Lesson17_Generic.java");
                break;
            case 18:
                System.out.println("\n正在运行: Lesson18 - 多线程");
                System.out.println("请直接运行 Lesson18_Multithreading.java");
                break;
            case 19:
                System.out.println("\n正在运行: Lesson19 - 网络编程");
                System.out.println("请直接运行 Lesson19_Network.java");
                break;
            case 20:
                System.out.println("\n正在运行: Lesson20 - JDBC数据库");
                System.out.println("请直接运行 Lesson20_JDBC.java");
                break;
            case 21:
                System.out.println("\n正在运行: Lesson21 - 构建工具");
                System.out.println("请直接运行 Lesson21_BuildTools.java");
                break;
            default:
                System.out.println("无效选择，请输入 1-21 之间的数字");
        }
        
        scanner.close();
    }
    
    /**
     * 显示课程菜单
     */
    private static void showCourseMenu() {
        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│ 第一阶段：Java 基础                               │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│  1. 变量和数据类型 (Lesson01)                     │");
        System.out.println("│  2. 运算符 (Lesson02)                             │");
        System.out.println("│  3. 条件语句 (Lesson03)                           │");
        System.out.println("│  4. 循环 (Lesson04)                               │");
        System.out.println("│  5. 数组 (Lesson05)                               │");
        System.out.println("│  6. 方法 (Lesson06)                               │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ 第二阶段：面向对象编程                             │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│  7. 类和对象 (Lesson07)                           │");
        System.out.println("│  8. 封装 (Lesson08)                               │");
        System.out.println("│  9. 继承 (Lesson09)                               │");
        System.out.println("│ 10. 多态 (Lesson10)                               │");
        System.out.println("│ 11. 接口 (Lesson11)                               │");
        System.out.println("│ 12. 抽象类 (Lesson12)                             │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ 第三阶段：Java 高级特性                           │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ 13. 常用类 (Lesson13)                             │");
        System.out.println("│ 14. 异常处理 (Lesson14)                           │");
        System.out.println("│ 15. 文件IO (Lesson15)                             │");
        System.out.println("│ 16. 集合框架 (Lesson16)                           │");
        System.out.println("│ 17. 泛型 (Lesson17)                               │");
        System.out.println("│ 18. 多线程 (Lesson18)                             │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ 第四阶段：Java 应用开发                           │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ 19. 网络编程 (Lesson19)                           │");
        System.out.println("│ 20. JDBC数据库 (Lesson20)                         │");
        System.out.println("│ 21. 构建工具 (Lesson21)                           │");
        System.out.println("└────────────────────────────────────────────────────┘");
        System.out.println();
    }
    
    /**
     * 显示课程详情
     */
    private static void showCourseDetail(int courseNumber) {
        String[] courseNames = {
            "变量和数据类型", "运算符", "条件语句", "循环", "数组", "方法",
            "类和对象", "封装", "继承", "多态", "接口", "抽象类",
            "常用类", "异常处理", "文件IO", "集合框架", "泛型", "多线程",
            "网络编程", "JDBC数据库", "构建工具"
        };
        
        if (courseNumber >= 1 && courseNumber <= 21) {
            System.out.println("课程 " + courseNumber + ": " + courseNames[courseNumber - 1]);
            System.out.println("文件: Lesson" + String.format("%02d", courseNumber) + "_*.java");
        }
    }
}
