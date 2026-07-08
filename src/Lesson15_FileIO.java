import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Arrays;

// ===== 文件IO =====

// 文件IO的核心思想：读写外部数据

// 1. 文件工具类
class FileUtil {
    // 创建文件
    public static boolean createFile(String filename) {
        try {
            File file = new File(filename);
            if (file.exists()) {
                System.out.println("文件已存在: " + filename);
                return true;
            }
            return file.createNewFile();
        } catch (IOException e) {
            System.out.println("创建文件失败: " + e.getMessage());
            return false;
        }
    }

    // 创建目录
    public static boolean createDirectory(String dirname) {
        File dir = new File(dirname);
        return dir.mkdirs();
    }

    // 删除文件或目录
    public static boolean delete(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    delete(f.getAbsolutePath());
                }
            }
        }
        return file.delete();
    }

    // 列出目录内容
    public static void listDirectory(String dirname) {
        File dir = new File(dirname);
        if (!dir.exists()) {
            System.out.println("目录不存在: " + dirname);
            return;
        }
        if (!dir.isDirectory()) {
            System.out.println("不是目录: " + dirname);
            return;
        }
        File[] files = dir.listFiles();
        if (files != null) {
            System.out.println("目录内容: " + dirname);
            for (File file : files) {
                String type = file.isDirectory() ? "[目录]" : "[文件]";
                System.out.println(type + " " + file.getName());
            }
        }
    }

    // 获取文件信息
    public static void showFileInfo(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("文件不存在: " + filename);
            return;
        }
        System.out.println("文件名: " + file.getName());
        System.out.println("绝对路径: " + file.getAbsolutePath());
        System.out.println("文件大小: " + file.length() + " 字节");
        System.out.println("是否可读: " + file.canRead());
        System.out.println("是否可写: " + file.canWrite());
        System.out.println("最后修改: " + new java.util.Date(file.lastModified()));
    }
}

// 2. 文本文件读写类
class TextFileHandler {
    // 写入文本文件
    public static void writeText(String filename, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        }
    }

    // 追加文本
    public static void appendText(String filename, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(content);
        }
    }

    // 读取所有文本
    public static String readAllText(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        }
    }

    // 读取所有行
    public static List<String> readAllLines(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<String> lines = new java.util.ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        }
    }

    // 复制文件
    public static void copyFile(String source, String dest) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(dest))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}

// 3. 二进制文件处理类
class BinaryFileHandler {
    // 写入字节
    public static void writeBytes(String filename, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(data);
        }
    }

    // 读取字节
    public static byte[] readBytes(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            byte[] data = new byte[fis.available()];
            fis.read(data);
            return data;
        }
    }

    // 复制二进制文件
    public static void copyBinaryFile(String source, String dest) throws IOException {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }
}

// 4. 对象序列化类
class SerializationUtil {
    // 序列化对象
    public static void serialize(Object obj, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(obj);
        }
    }

    // 反序列化对象
    public static Object deserialize(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        }
    }
}

// 5. 可序列化的用户类
class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private transient String password;  // transient字段不序列化

    public User(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + ", password='" + password + "'}";
    }
}

public class Lesson15_FileIO {
    public static void main(String[] args) {
        System.out.println("===== 文件IO详解 =====");

        String filename = "test.txt";
        String binaryFile = "test.bin";
        String objectFile = "user.ser";

        // 1. 基本文件操作
        System.out.println("\n----- 1. 基本文件操作 -----");
        FileUtil.createFile(filename);
        FileUtil.showFileInfo(filename);

        // 2. 写入文件
        System.out.println("\n----- 2. 写入文件 -----");
        try {
            TextFileHandler.writeText(filename, "Hello Java!\n文件操作练习\n第三行内容");
            System.out.println("写入成功");
        } catch (IOException e) {
            System.out.println("写入失败: " + e.getMessage());
        }

        // 3. 读取文件
        System.out.println("\n----- 3. 读取文件 -----");
        try {
            String content = TextFileHandler.readAllText(filename);
            System.out.println("文件内容:\n" + content);
        } catch (IOException e) {
            System.out.println("读取失败: " + e.getMessage());
        }

        // 4. 追加内容
        System.out.println("\n----- 4. 追加内容 -----");
        try {
            TextFileHandler.appendText(filename, "\n追加的内容");
            System.out.println("追加成功");
            // 重新读取
            String newContent = TextFileHandler.readAllText(filename);
            System.out.println("新内容:\n" + newContent);
        } catch (IOException e) {
            System.out.println("追加失败: " + e.getMessage());
        }

        // 5. Files工具类（Java 7+）
        System.out.println("\n----- 5. Files工具类 -----");
        try {
            Path path = Paths.get(filename);
            System.out.println("文件大小: " + Files.size(path) + " 字节");
            System.out.println("所有行:");
            Files.readAllLines(path).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("读取失败: " + e.getMessage());
        }

        // 6. File类详细信息
        System.out.println("\n----- 6. File类详细信息 -----");
        File file = new File(filename);
        System.out.println("文件名: " + file.getName());
        System.out.println("绝对路径: " + file.getAbsolutePath());
        System.out.println("是否存在: " + file.exists());
        System.out.println("是否文件: " + file.isFile());
        System.out.println("是否目录: " + file.isDirectory());
        System.out.println("文件大小: " + file.length() + " 字节");
        System.out.println("是否可读: " + file.canRead());
        System.out.println("是否可写: " + file.canWrite());

        // 7. 目录操作
        System.out.println("\n----- 7. 目录操作 -----");
        String dirname = "test_dir";
        FileUtil.createDirectory(dirname);
        System.out.println("创建目录: " + dirname);
        
        // 在目录中创建文件
        for (int i = 1; i <= 3; i++) {
            String subFile = dirname + "/file" + i + ".txt";
            try {
                TextFileHandler.writeText(subFile, "文件" + i + "的内容");
            } catch (IOException e) {
                System.out.println("创建文件失败: " + subFile);
            }
        }
        FileUtil.listDirectory(dirname);

        // 8. 二进制文件操作
        System.out.println("\n----- 8. 二进制文件操作 -----");
        try {
            byte[] data = {72, 101, 108, 108, 111};  // "Hello"
            BinaryFileHandler.writeBytes(binaryFile, data);
            System.out.println("写入二进制文件成功");
            
            byte[] readData = BinaryFileHandler.readBytes(binaryFile);
            System.out.println("读取的字节: " + Arrays.toString(readData));
            System.out.println("转换为字符串: " + new String(readData));
        } catch (IOException e) {
            System.out.println("二进制操作失败: " + e.getMessage());
        }

        // 9. 对象序列化
        System.out.println("\n----- 9. 对象序列化 -----");
        try {
            User user = new User("张三", 25, "password123");
            System.out.println("原对象: " + user);
            
            SerializationUtil.serialize(user, objectFile);
            System.out.println("序列化成功");
            
            User deserialized = (User) SerializationUtil.deserialize(objectFile);
            System.out.println("反序列化对象: " + deserialized);
            System.out.println("注意: password字段为null（transient）");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("序列化失败: " + e.getMessage());
        }

        // 10. 文件复制
        System.out.println("\n----- 10. 文件复制 -----");
        String copyFile = "copy_test.txt";
        try {
            TextFileHandler.copyFile(filename, copyFile);
            System.out.println("复制成功");
            System.out.println("复制内容:");
            System.out.println(TextFileHandler.readAllText(copyFile));
        } catch (IOException e) {
            System.out.println("复制失败: " + e.getMessage());
        }

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：统计文件行数和字符数
        System.out.println("练习1 - 统计文件信息:");
        testFileStatistics();
        
        // 练习2：文件内容搜索
        System.out.println("\n练习2 - 文件内容搜索:");
        testFileSearch();
        
        // 练习3：配置文件读取
        System.out.println("\n练习3 - 配置文件读取:");
        testConfigFile();
        
        // 练习4：日志文件处理
        System.out.println("\n练习4 - 日志文件处理:");
        testLogFile();
        
        // 清理临时文件
        cleanup();
    }

    // 练习1：统计文件信息
    static void testFileStatistics() {
        String filename = "test.txt";
        try {
            List<String> lines = TextFileHandler.readAllLines(filename);
            int lineCount = lines.size();
            int charCount = 0;
            int wordCount = 0;
            
            for (String line : lines) {
                charCount += line.length();
                wordCount += line.split("\\s+").length;
            }
            
            System.out.println("文件: " + filename);
            System.out.println("行数: " + lineCount);
            System.out.println("字符数: " + charCount);
            System.out.println("单词数: " + wordCount);
        } catch (IOException e) {
            System.out.println("统计失败: " + e.getMessage());
        }
    }

    // 练习2：文件内容搜索
    static void testFileSearch() {
        String filename = "test.txt";
        String keyword = "Java";
        try {
            List<String> lines = TextFileHandler.readAllLines(filename);
            System.out.println("搜索关键词: " + keyword);
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(keyword)) {
                    System.out.println("第" + (i + 1) + "行: " + lines.get(i));
                }
            }
        } catch (IOException e) {
            System.out.println("搜索失败: " + e.getMessage());
        }
    }

    // 练习3：配置文件读取
    static void testConfigFile() {
        String configFile = "config.properties";
        try {
            // 创建配置文件
            StringBuilder config = new StringBuilder();
            config.append("# 应用配置\n");
            config.append("app.name=Java学习系统\n");
            config.append("app.version=1.0\n");
            config.append("app.author=小明\n");
            config.append("database.url=localhost:3306\n");
            config.append("database.user=root\n");
            
            TextFileHandler.writeText(configFile, config.toString());
            System.out.println("配置文件创建成功");
            
            // 读取配置
            List<String> lines = TextFileHandler.readAllLines(configFile);
            System.out.println("配置内容:");
            for (String line : lines) {
                if (!line.startsWith("#") && line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    System.out.println(parts[0].trim() + ": " + parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("配置文件处理失败: " + e.getMessage());
        }
    }

    // 练习4：日志文件处理
    static void testLogFile() {
        String logFile = "app.log";
        try {
            // 创建日志文件
            StringBuilder log = new StringBuilder();
            log.append("2024-01-15 10:30:00 INFO 应用启动\n");
            log.append("2024-01-15 10:30:05 INFO 用户登录: admin\n");
            log.append("2024-01-15 10:31:00 WARN 内存使用率过高\n");
            log.append("2024-01-15 10:32:00 ERROR 数据库连接失败\n");
            log.append("2024-01-15 10:33:00 INFO 应用关闭\n");
            
            TextFileHandler.writeText(logFile, log.toString());
            System.out.println("日志文件创建成功");
            
            // 分析日志
            List<String> lines = TextFileHandler.readAllLines(logFile);
            int infoCount = 0, warnCount = 0, errorCount = 0;
            
            for (String line : lines) {
                if (line.contains("INFO")) infoCount++;
                else if (line.contains("WARN")) warnCount++;
                else if (line.contains("ERROR")) errorCount++;
            }
            
            System.out.println("日志统计:");
            System.out.println("INFO: " + infoCount + "条");
            System.out.println("WARN: " + warnCount + "条");
            System.out.println("ERROR: " + errorCount + "条");
        } catch (IOException e) {
            System.out.println("日志处理失败: " + e.getMessage());
        }
    }

    // 清理临时文件
    static void cleanup() {
        String[] files = {"test.txt", "test.bin", "user.ser", "copy_test.txt", 
                         "config.properties", "app.log"};
        String[] dirs = {"test_dir"};
        
        System.out.println("\n----- 清理临时文件 -----");
        for (String file : files) {
            File f = new File(file);
            if (f.exists()) {
                f.delete();
                System.out.println("删除文件: " + file);
            }
        }
        for (String dir : dirs) {
            FileUtil.delete(dir);
            System.out.println("删除目录: " + dir);
        }
    }
}
