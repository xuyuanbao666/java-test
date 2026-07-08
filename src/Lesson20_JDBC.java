// ===== JDBC 数据库操作 =====
// 注意：需要添加 MySQL 驱动 jar 包

/*
 * 使用步骤：
 * 1. 下载 mysql-connector-java.jar
 * 2. 添加到项目 classpath
 * 3. 创建数据库和表
 */

import java.sql.*;
import java.util.*;

// JDBC工具类
class JDBCUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    // 获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    // 关闭资源
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 执行更新
    public static int executeUpdate(String sql, Object... params) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeUpdate();
        }
    }
    
    // 执行查询
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnLabel(i), rs.getObject(i));
                    }
                    results.add(row);
                }
            }
        }
        return results;
    }
}

// 数据库操作类
class UserDAO {
    // 创建用户表
    public static void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                age INT,
                email VARCHAR(100),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
            """;
        JDBCUtil.executeUpdate(sql);
        System.out.println("用户表创建成功");
    }
    
    // 插入用户
    public static int insertUser(String name, int age, String email) throws SQLException {
        String sql = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)";
        return JDBCUtil.executeUpdate(sql, name, age, email);
    }
    
    // 查询所有用户
    public static List<Map<String, Object>> findAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        return JDBCUtil.executeQuery(sql);
    }
    
    // 根据ID查询
    public static Map<String, Object> findUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<Map<String, Object>> results = JDBCUtil.executeQuery(sql, id);
        return results.isEmpty() ? null : results.get(0);
    }
    
    // 更新用户
    public static int updateUser(int id, String name, int age, String email) throws SQLException {
        String sql = "UPDATE users SET name = ?, age = ?, email = ? WHERE id = ?";
        return JDBCUtil.executeUpdate(sql, name, age, email, id);
    }
    
    // 删除用户
    public static int deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        return JDBCUtil.executeUpdate(sql, id);
    }
}

// 事务处理示例
class TransactionDemo {
    public static void transferMoney(int fromId, int toId, double amount) throws SQLException {
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);  // 开启事务
            
            // 扣减转出账户
            String debitSql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(debitSql)) {
                pstmt.setDouble(1, amount);
                pstmt.setInt(2, fromId);
                pstmt.executeUpdate();
            }
            
            // 增加转入账户
            String creditSql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(creditSql)) {
                pstmt.setDouble(1, amount);
                pstmt.setInt(2, toId);
                pstmt.executeUpdate();
            }
            
            conn.commit();  // 提交事务
            System.out.println("转账成功");
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();  // 回滚事务
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}

public class Lesson20_JDBC {
    public static void main(String[] args) {
        System.out.println("===== JDBC 数据库操作详解 =====");

        // 1. JDBC 代码结构
        System.out.println("\n----- 1. JDBC 代码结构 -----");
        showJDBCStructure();

        // 2. JDBC 工具类
        System.out.println("\n----- 2. JDBC 工具类 -----");
        showJDBCUtilExample();

        // 3. CRUD 操作
        System.out.println("\n----- 3. CRUD 操作 -----");
        showCRUDExample();

        // 4. 事务处理
        System.out.println("\n----- 4. 事务处理 -----");
        showTransactionExample();

        // 5. 批量操作
        System.out.println("\n----- 5. 批量操作 -----");
        showBatchExample();

        // 6. 连接池
        System.out.println("\n----- 6. 连接池 -----");
        showConnectionPoolExample();

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：学生管理系统
        System.out.println("练习1 - 学生管理系统:");
        showStudentSystemExample();
        
        // 练习2：商品管理系统
        System.out.println("\n练习2 - 商品管理系统:");
        showProductSystemExample();
        
        // 练习3：日志系统
        System.out.println("\n练习3 - 日志系统:");
        showLogSystemExample();
        
        // 练习4：数据导入导出
        System.out.println("\n练习4 - 数据导入导出:");
        showImportExportExample();
    }

    // JDBC 代码结构
    static void showJDBCStructure() {
        System.out.println("JDBC基本步骤:");
        System.out.println("1. 加载驱动");
        System.out.println("   Class.forName(\"com.mysql.cj.jdbc.Driver\");");
        System.out.println("2. 获取连接");
        System.out.println("   Connection conn = DriverManager.getConnection(url, user, password);");
        System.out.println("3. 创建Statement");
        System.out.println("   PreparedStatement ps = conn.prepareStatement(sql);");
        System.out.println("4. 执行SQL");
        System.out.println("   ResultSet rs = ps.executeQuery();  // 查询");
        System.out.println("   int rows = ps.executeUpdate();    // 增删改");
        System.out.println("5. 处理结果");
        System.out.println("   while (rs.next()) { ... }");
        System.out.println("6. 关闭资源");
        System.out.println("   rs.close(); ps.close(); conn.close();");
    }

    // JDBC工具类示例
    static void showJDBCUtilExample() {
        System.out.println("JDBCUtil工具类功能:");
        System.out.println("1. 获取连接: JDBCUtil.getConnection()");
        System.out.println("2. 关闭资源: JDBCUtil.close(conn, stmt, rs)");
        System.out.println("3. 执行更新: JDBCUtil.executeUpdate(sql, params...)");
        System.out.println("4. 执行查询: JDBCUtil.executeQuery(sql, params...)");
    }

    // CRUD示例
    static void showCRUDExample() {
        System.out.println("CRUD操作示例:");
        System.out.println("// 创建");
        System.out.println("UserDAO.insertUser(\"张三\", 25, \"zhangsan@example.com\");");
        System.out.println("// 读取");
        System.out.println("List<Map<String, Object>> users = UserDAO.findAllUsers();");
        System.out.println("// 更新");
        System.out.println("UserDAO.updateUser(1, \"李四\", 30, \"lisi@example.com\");");
        System.out.println("// 删除");
        System.out.println("UserDAO.deleteUser(1);");
    }

    // 事务处理示例
    static void showTransactionExample() {
        System.out.println("事务处理示例:");
        System.out.println("Connection conn = null;");
        System.out.println("try {");
        System.out.println("    conn = JDBCUtil.getConnection();");
        System.out.println("    conn.setAutoCommit(false);  // 开启事务");
        System.out.println("    // 执行多个SQL操作");
        System.out.println("    conn.commit();  // 提交事务");
        System.out.println("} catch (SQLException e) {");
        System.out.println("    if (conn != null) conn.rollback();  // 回滚事务");
        System.out.println("} finally {");
        System.out.println("    if (conn != null) {");
        System.out.println("        conn.setAutoCommit(true);");
        System.out.println("        conn.close();");
        System.out.println("    }");
        System.out.println("}");
    }

    // 批量操作示例
    static void showBatchExample() {
        System.out.println("批量操作示例:");
        System.out.println("Connection conn = JDBCUtil.getConnection();");
        System.out.println("PreparedStatement ps = conn.prepareStatement(\"INSERT INTO users (name, age) VALUES (?, ?)\");");
        System.out.println("for (User user : userList) {");
        System.out.println("    ps.setString(1, user.getName());");
        System.out.println("    ps.setInt(2, user.getAge());");
        System.out.println("    ps.addBatch();");
        System.out.println("}");
        System.out.println("ps.executeBatch();");
    }

    // 连接池示例
    static void showConnectionPoolExample() {
        System.out.println("连接池示例（使用HikariCP）:");
        System.out.println("HikariConfig config = new HikariConfig();");
        System.out.println("config.setJdbcUrl(\"jdbc:mysql://localhost:3306/test\");");
        System.out.println("config.setUsername(\"root\");");
        System.out.println("config.setPassword(\"password\");");
        System.out.println("config.setMaximumPoolSize(10);");
        System.out.println("HikariDataSource dataSource = new HikariDataSource(config);");
        System.out.println("Connection conn = dataSource.getConnection();");
    }

    // 练习1：学生管理系统
    static void showStudentSystemExample() {
        System.out.println("学生管理系统功能:");
        System.out.println("- 创建学生表");
        System.out.println("- 添加学生信息");
        System.out.println("- 查询学生列表");
        System.out.println("- 按条件查询");
        System.out.println("- 更新学生信息");
        System.out.println("- 删除学生");
    }

    // 练习2：商品管理系统
    static void showProductSystemExample() {
        System.out.println("商品管理系统功能:");
        System.out.println("- 商品分类管理");
        System.out.println("- 商品信息管理");
        System.out.println("- 库存管理");
        System.out.println("- 价格查询");
        System.out.println("- 销售统计");
    }

    // 练习3：日志系统
    static void showLogSystemExample() {
        System.out.println("日志系统功能:");
        System.out.println("- 记录操作日志");
        System.out.println("- 按时间查询日志");
        System.out.println("- 按操作类型查询");
        System.out.println("- 日志统计分析");
        System.out.println("- 日志清理");
    }

    // 练习4：数据导入导出
    static void showImportExportExample() {
        System.out.println("数据导入导出功能:");
        System.out.println("- 从CSV导入数据");
        System.out.println("- 导出数据到CSV");
        System.out.println("- 数据格式转换");
        System.out.println("- 批量导入优化");
        System.out.println("- 错误处理");
    }
}
