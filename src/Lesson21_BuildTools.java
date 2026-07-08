// ===== Maven/Gradle 构建工具 =====

// 构建工具的核心思想：自动化项目构建、依赖管理和项目信息管理

// 1. Maven详解
class MavenGuide {
    // Maven项目结构
    public static void showProjectStructure() {
        System.out.println("Maven标准项目结构:");
        System.out.println("my-app/");
        System.out.println("├── pom.xml                    # 项目配置文件");
        System.out.println("├── src/");
        System.out.println("│   ├── main/");
        System.out.println("│   │   ├── java/              # Java源代码");
        System.out.println("│   │   ├── resources/         # 资源文件");
        System.out.println("│   │   └── webapp/            # Web应用文件");
        System.out.println("│   └── test/");
        System.out.println("│       ├── java/              # 测试代码");
        System.out.println("│       └── resources/         # 测试资源");
        System.out.println("└── target/                    # 构建输出目录");
    }
    
    // Maven生命周期
    public static void showLifecycle() {
        System.out.println("\nMaven生命周期:");
        System.out.println("1. clean    - 清理项目");
        System.out.println("2. validate - 验证项目结构");
        System.out.println("3. compile  - 编译源代码");
        System.out.println("4. test     - 运行测试");
        System.out.println("5. package  - 打包项目");
        System.out.println("6. verify   - 验证测试结果");
        System.out.println("7. install  - 安装到本地仓库");
        System.out.println("8. deploy   - 部署到远程仓库");
    }
    
    // Maven常用命令
    public static void showCommands() {
        System.out.println("\nMaven常用命令:");
        System.out.println("mvn clean                - 清理target目录");
        System.out.println("mvn compile              - 编译项目");
        System.out.println("mvn test                 - 运行测试");
        System.out.println("mvn package              - 打包项目");
        System.out.println("mvn clean package        - 清理并打包");
        System.out.println("mvn install              - 安装到本地仓库");
        System.out.println("mvn dependency:tree      - 显示依赖树");
        System.out.println("mvn site                 - 生成项目站点");
    }
}

// 2. Gradle详解
class GradleGuide {
    // Gradle项目结构
    public static void showProjectStructure() {
        System.out.println("Gradle标准项目结构:");
        System.out.println("my-app/");
        System.out.println("├── build.gradle               # 构建脚本");
        System.out.println("├── settings.gradle            # 设置文件");
        System.out.println("├── gradle/                    # Gradle包装器");
        System.out.println("├── gradlew                    # Linux/Mac包装器");
        System.out.println("├── gradlew.bat                # Windows包装器");
        System.out.println("├── src/");
        System.out.println("│   ├── main/");
        System.out.println("│   │   ├── java/");
        System.out.println("│   │   └── resources/");
        System.out.println("│   └── test/");
        System.out.println("│       ├── java/");
        System.out.println("│       └── resources/");
        System.out.println("└── build/                     # 构建输出目录");
    }
    
    // Gradle常用命令
    public static void showCommands() {
        System.out.println("\nGradle常用命令:");
        System.out.println("gradle build             - 构建项目");
        System.out.println("gradle clean             - 清理build目录");
        System.out.println("gradle test              - 运行测试");
        System.out.println("gradle run               - 运行应用");
        System.out.println("gradle dependencies      - 显示依赖");
        System.out.println("gradle tasks             - 显示所有任务");
        System.out.println("gradle wrapper           - 生成包装器");
    }
}

// 3. 构建工具对比
class BuildToolComparison {
    public static void compare() {
        System.out.println("Maven vs Gradle 对比:");
        System.out.println("+---------------------+---------------------+---------------------+");
        System.out.println("| 特性                | Maven               | Gradle              |");
        System.out.println("+---------------------+---------------------+---------------------+");
        System.out.println("| 配置语言            | XML                 | Groovy/Kotlin DSL   |");
        System.out.println("| 构建速度            | 较慢                | 较快（增量构建）    |");
        System.out.println("| 学习曲线            | 中等                | 较陡                |");
        System.out.println("| 灵活性              | 较低                | 较高                |");
        System.out.println("| 依赖管理            | 中央仓库            | 中央仓库+自定义     |");
        System.out.println("| 插件生态            | 丰富                | 丰富                |");
        System.out.println("| IDE支持            | 良好                | 良好                |");
        System.out.println("+---------------------+---------------------+---------------------+");
    }
}

public class Lesson21_BuildTools {
    public static void main(String[] args) {
        System.out.println("===== Maven/Gradle 构建工具详解 =====");

        // 1. Maven详解
        System.out.println("\n----- 1. Maven详解 -----");
        MavenGuide.showProjectStructure();
        MavenGuide.showLifecycle();
        MavenGuide.showCommands();

        // 2. Maven pom.xml详解
        System.out.println("\n----- 2. Maven pom.xml详解 -----");
        showPomXmlExample();

        // 3. Gradle详解
        System.out.println("\n----- 3. Gradle详解 -----");
        GradleGuide.showProjectStructure();
        GradleGuide.showCommands();

        // 4. Gradle build.gradle详解
        System.out.println("\n----- 4. Gradle build.gradle详解 -----");
        showBuildGradleExample();

        // 5. 构建工具对比
        System.out.println("\n----- 5. 构建工具对比 -----");
        BuildToolComparison.compare();

        // 6. 依赖管理
        System.out.println("\n----- 6. 依赖管理 -----");
        showDependencyManagement();

        // 7. 插件使用
        System.out.println("\n----- 7. 插件使用 -----");
        showPluginUsage();

        // 8. 最佳实践
        System.out.println("\n----- 8. 最佳实践 -----");
        showBestPractices();

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：创建Maven项目
        System.out.println("练习1 - 创建Maven项目:");
        showMavenProjectExercise();
        
        // 练习2：创建Gradle项目
        System.out.println("\n练习2 - 创建Gradle项目:");
        showGradleProjectExercise();
        
        // 练习3：多模块项目
        System.out.println("\n练习3 - 多模块项目:");
        showMultiModuleExercise();
        
        // 练习4：自定义构建
        System.out.println("\n练习4 - 自定义构建:");
        showCustomBuildExercise();
    }

    // Maven pom.xml示例
    static void showPomXmlExample() {
        System.out.println("pom.xml详细示例:");
        System.out.println("""
            <?xml version="1.0" encoding="UTF-8"?>
            <project xmlns="http://maven.apache.org/POM/4.0.0"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                     http://maven.apache.org/xsd/maven-4.0.0.xsd">
                <modelVersion>4.0.0</modelVersion>
                
                <!-- 项目坐标 -->
                <groupId>com.example</groupId>
                <artifactId>my-app</artifactId>
                <version>1.0-SNAPSHOT</version>
                <packaging>jar</packaging>
                
                <!-- 项目信息 -->
                <name>My Application</name>
                <description>示例项目</description>
                
                <!-- 属性 -->
                <properties>
                    <maven.compiler.source>11</maven.compiler.source>
                    <maven.compiler.target>11</maven.compiler.target>
                    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                </properties>
                
                <!-- 依赖 -->
                <dependencies>
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>8.0.33</version>
                    </dependency>
                    <dependency>
                        <groupId>junit</groupId>
                        <artifactId>junit</artifactId>
                        <version>4.13.2</version>
                        <scope>test</scope>
                    </dependency>
                </dependencies>
                
                <!-- 构建配置 -->
                <build>
                    <plugins>
                        <plugin>
                            <groupId>org.apache.maven.plugins</groupId>
                            <artifactId>maven-compiler-plugin</artifactId>
                            <version>3.11.0</version>
                            <configuration>
                                <source>11</source>
                                <target>11</target>
                            </configuration>
                        </plugin>
                    </plugins>
                </build>
            </project>
            """);
    }

    // Gradle build.gradle示例
    static void showBuildGradleExample() {
        System.out.println("build.gradle详细示例:");
        System.out.println("""
            plugins {
                id 'java'
                id 'application'
            }
            
            // 项目信息
            group = 'com.example'
            version = '1.0-SNAPSHOT'
            
            // Java版本
            java {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
            
            // 仓库
            repositories {
                mavenCentral()
            }
            
            // 依赖
            dependencies {
                implementation 'mysql:mysql-connector-java:8.0.33'
                implementation 'org.slf4j:slf4j-api:2.0.7'
                
                testImplementation 'junit:junit:4.13.2'
                testImplementation 'org.mockito:mockito-core:5.3.1'
            }
            
            // 应用插件配置
            application {
                mainClass = 'com.example.Main'
            }
            
            // 测试配置
            test {
                useJUnit()
            }
            """);
    }

    // 依赖管理
    static void showDependencyManagement() {
        System.out.println("依赖管理:");
        System.out.println("1. 依赖范围（Maven）:");
        System.out.println("   - compile: 默认，编译、测试、运行都有效");
        System.out.println("   - test: 只在测试时有效");
        System.out.println("   - provided: 编译和测试有效，运行时由容器提供");
        System.out.println("   - runtime: 测试和运行有效，编译时不需要");
        System.out.println("   - system: 本地系统路径");
        System.out.println("\n2. 依赖配置（Gradle）:");
        System.out.println("   - implementation: 编译和运行时有效");
        System.out.println("   - api: 编译和运行时有效，会传递依赖");
        System.out.println("   - testImplementation: 只在测试时有效");
        System.out.println("   - compileOnly: 只在编译时有效");
        System.out.println("   - runtimeOnly: 只在运行时有效");
        System.out.println("\n3. 依赖冲突解决:");
        System.out.println("   - Maven: 就近原则，声明顺序");
        System.out.println("   - Gradle: 最高版本原则，可配置");
    }

    // 插件使用
    static void showPluginUsage() {
        System.out.println("常用插件:");
        System.out.println("Maven插件:");
        System.out.println("  - maven-compiler-plugin: 编译配置");
        System.out.println("  - maven-surefire-plugin: 测试运行");
        System.out.println("  - maven-jar-plugin: JAR打包");
        System.out.println("  - maven-war-plugin: WAR打包");
        System.out.println("  - maven-shade-plugin: 可执行JAR");
        System.out.println("\nGradle插件:");
        System.out.println("  - java: Java项目支持");
        System.out.println("  - application: 应用程序支持");
        System.out.println("  - spring-boot: Spring Boot支持");
        System.out.println("  - war: WAR打包");
        System.out.println("  - shadow: 可执行JAR");
    }

    // 最佳实践
    static void showBestPractices() {
        System.out.println("构建工具最佳实践:");
        System.out.println("1. 使用依赖管理:");
        System.out.println("   - Maven: dependencyManagement");
        System.out.println("   - Gradle: platform/BOM");
        System.out.println("\n2. 版本管理:");
        System.out.println("   - 使用属性定义版本号");
        System.out.println("   - 统一管理依赖版本");
        System.out.println("\n3. 多模块项目:");
        System.out.println("   - 合理划分模块");
        System.out.println("   - 明确模块依赖关系");
        System.out.println("\n4. 构建优化:");
        System.out.println("   - 使用本地缓存");
        System.out.println("   - 并行构建");
        System.out.println("   - 增量构建");
        System.out.println("\n5. 持续集成:");
        System.out.println("   - 自动化构建");
        System.out.println("   - 自动化测试");
        System.out.println("   - 自动化部署");
    }

    // 练习1：创建Maven项目
    static void showMavenProjectExercise() {
        System.out.println("练习步骤:");
        System.out.println("1. 创建Maven项目:");
        System.out.println("   mvn archetype:generate -DgroupId=com.example -DartifactId=my-app");
        System.out.println("\n2. 添加依赖:");
        System.out.println("   编辑pom.xml，添加MySQL驱动依赖");
        System.out.println("\n3. 编写代码:");
        System.out.println("   创建数据库连接工具类");
        System.out.println("\n4. 构建项目:");
        System.out.println("   mvn clean package");
        System.out.println("\n5. 运行测试:");
        System.out.println("   mvn test");
    }

    // 练习2：创建Gradle项目
    static void showGradleProjectExercise() {
        System.out.println("练习步骤:");
        System.out.println("1. 创建Gradle项目:");
        System.out.println("   gradle init --type java-application");
        System.out.println("\n2. 配置build.gradle:");
        System.out.println("   添加MySQL驱动依赖");
        System.out.println("\n3. 编写代码:");
        System.out.println("   创建数据库连接工具类");
        System.out.println("\n4. 构建项目:");
        System.out.println("   gradle build");
        System.out.println("\n5. 运行应用:");
        System.out.println("   gradle run");
    }

    // 练习3：多模块项目
    static void showMultiModuleExercise() {
        System.out.println("多模块项目结构:");
        System.out.println("parent-project/");
        System.out.println("├── pom.xml                    # 父POM");
        System.out.println("├── module-common/             # 公共模块");
        System.out.println("│   └── pom.xml");
        System.out.println("├── module-dao/                # 数据访问模块");
        System.out.println("│   └── pom.xml");
        System.out.println("├── module-service/            # 业务逻辑模块");
        System.out.println("│   └── pom.xml");
        System.out.println("└── module-web/                # Web模块");
        System.out.println("    └── pom.xml");
        System.out.println("\n模块依赖关系:");
        System.out.println("web → service → dao → common");
    }

    // 练习4：自定义构建
    static void showCustomBuildExercise() {
        System.out.println("自定义构建任务:");
        System.out.println("Maven自定义插件:");
        System.out.println("1. 创建Maven插件项目");
        System.out.println("2. 实现Mojo接口");
        System.out.println("3. 配置插件参数");
        System.out.println("4. 打包并使用");
        System.out.println("\nGradle自定义任务:");
        System.out.println("1. 创建build.gradle");
        System.out.println("2. 定义自定义任务");
        System.out.println("3. 配置任务依赖");
        System.out.println("4. 执行任务");
    }
}
