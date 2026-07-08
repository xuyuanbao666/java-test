import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

// ===== 网络编程 =====

// 网络编程的核心思想：实现计算机之间的通信

// 1. 网络工具类
class NetworkUtil {
    // 获取本机IP
    public static String getLocalIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "未知";
        }
    }
    
    // 获取本机主机名
    public static String getLocalHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "未知";
        }
    }
    
    // 检查主机是否可达
    public static boolean isHostReachable(String host, int timeout) {
        try {
            InetAddress address = InetAddress.getByName(host);
            return address.isReachable(timeout);
        } catch (IOException e) {
            return false;
        }
    }
    
    // 获取域名对应的IP
    public static String getIPByDomain(String domain) {
        try {
            InetAddress address = InetAddress.getByName(domain);
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            return "解析失败";
        }
    }
}

// 2. 简单HTTP客户端
class SimpleHttpClient {
    // 发送GET请求
    public static String sendGet(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }
            in.close();
            return response.toString();
        } else {
            return "请求失败，响应码: " + responseCode;
        }
    }
    
    // 发送POST请求
    public static String sendPost(String url, String data) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setDoOutput(true);
        
        try (OutputStream os = conn.getOutputStream()) {
            os.write(data.getBytes());
            os.flush();
        }
        
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }
            in.close();
            return response.toString();
        } else {
            return "请求失败，响应码: " + responseCode;
        }
    }
}

// 3. TCP服务器（支持多客户端）
class MultiClientServer {
    private int port;
    private ExecutorService threadPool;
    private volatile boolean running = false;
    
    public MultiClientServer(int port) {
        this.port = port;
        this.threadPool = Executors.newFixedThreadPool(10);
    }
    
    public void start() {
        running = true;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("服务器启动，端口: " + port);
            while (running) {
                Socket client = server.accept();
                System.out.println("客户端连接: " + client.getInetAddress());
                threadPool.submit(new ClientHandler(client));
            }
        } catch (IOException e) {
            if (running) {
                System.out.println("服务器错误: " + e.getMessage());
            }
        } finally {
            stop();
        }
    }
    
    public void stop() {
        running = false;
        threadPool.shutdown();
    }
    
    private static class ClientHandler implements Runnable {
        private Socket client;
        
        public ClientHandler(Socket client) {
            this.client = client;
        }
        
        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
                 PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
                
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("收到: " + message);
                    out.println("服务器回复: " + message);
                    if ("bye".equalsIgnoreCase(message)) {
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("客户端处理错误: " + e.getMessage());
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

// 4. UDP示例
class UDPDemo {
    // UDP发送
    public static void send(String message, String host, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] data = message.getBytes();
        InetAddress address = InetAddress.getByName(host);
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        socket.send(packet);
        socket.close();
    }
    
    // UDP接收
    public static String receive(int port) throws IOException {
        DatagramSocket socket = new DatagramSocket(port);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String message = new String(packet.getData(), 0, packet.getLength());
        socket.close();
        return message;
    }
}

// 5. URL解析器
class URLParser {
    public static void parseURL(String urlStr) throws MalformedURLException {
        URL url = new URL(urlStr);
        System.out.println("协议: " + url.getProtocol());
        System.out.println("主机: " + url.getHost());
        System.out.println("端口: " + url.getPort());
        System.out.println("路径: " + url.getPath());
        System.out.println("查询: " + url.getQuery());
        System.out.println("完整URL: " + url.toString());
    }
}

public class Lesson19_Network {
    public static void main(String[] args) throws Exception {
        System.out.println("===== 网络编程详解 =====");

        // 1. InetAddress
        System.out.println("\n----- 1. InetAddress -----");
        try {
            InetAddress local = InetAddress.getLocalHost();
            System.out.println("本机: " + local);
            System.out.println("主机名: " + local.getHostName());
            System.out.println("IP: " + local.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("获取失败: " + e.getMessage());
        }

        // 2. 网络工具类
        System.out.println("\n----- 2. 网络工具类 -----");
        System.out.println("本机IP: " + NetworkUtil.getLocalIP());
        System.out.println("本机主机名: " + NetworkUtil.getLocalHostName());
        System.out.println("百度可达: " + NetworkUtil.isHostReachable("www.baidu.com", 3000));
        System.out.println("百度IP: " + NetworkUtil.getIPByDomain("www.baidu.com"));

        // 3. URL解析
        System.out.println("\n----- 3. URL解析 -----");
        try {
            URLParser.parseURL("https://www.example.com:8080/path?param=value");
        } catch (MalformedURLException e) {
            System.out.println("URL解析错误: " + e.getMessage());
        }

        // 4. HTTP客户端示例
        System.out.println("\n----- 4. HTTP客户端 -----");
        System.out.println("HTTP请求示例代码:");
        System.out.println("String response = SimpleHttpClient.sendGet(\"https://api.example.com/data\");");
        System.out.println("String response = SimpleHttpClient.sendPost(\"https://api.example.com/data\", \"key=value\");");

        // 5. TCP服务器示例
        System.out.println("\n----- 5. TCP服务器 -----");
        System.out.println("TCP服务器示例代码:");
        System.out.println("MultiClientServer server = new MultiClientServer(8888);");
        System.out.println("server.start();  // 启动服务器");
        System.out.println("// 客户端连接后可发送消息");

        // 6. TCP客户端示例
        System.out.println("\n----- 6. TCP客户端 -----");
        System.out.println("TCP客户端示例代码:");
        System.out.println("Socket socket = new Socket(\"localhost\", 8888);");
        System.out.println("PrintWriter out = new PrintWriter(socket.getOutputStream(), true);");
        System.out.println("out.println(\"Hello Server!\");");

        // 7. UDP示例
        System.out.println("\n----- 7. UDP示例 -----");
        System.out.println("UDP是无连接的，适合发送小数据包");
        System.out.println("UDP发送: UDPDemo.send(\"消息\", \"localhost\", 9999);");
        System.out.println("UDP接收: String msg = UDPDemo.receive(9999);");

        // 8. Socket编程详解
        System.out.println("\n----- 8. Socket编程详解 -----");
        demonstrateSocketProgramming();

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：简单的聊天室
        System.out.println("练习1 - 简单聊天室:");
        testChatRoom();
        
        // 练习2：文件传输
        System.out.println("\n练习2 - 文件传输:");
        testFileTransfer();
        
        // 练习3：端口扫描器
        System.out.println("\n练习3 - 端口扫描器:");
        testPortScanner();
        
        // 练习4：简单的Web服务器
        System.out.println("\n练习4 - 简单Web服务器:");
        testSimpleWebServer();
    }

    // Socket编程演示
    static void demonstrateSocketProgramming() {
        System.out.println("Socket编程步骤:");
        System.out.println("1. 服务器端:");
        System.out.println("   - 创建ServerSocket");
        System.out.println("   - 调用accept()等待客户端连接");
        System.out.println("   - 获取输入/输出流");
        System.out.println("   - 读写数据");
        System.out.println("   - 关闭连接");
        System.out.println("2. 客户端:");
        System.out.println("   - 创建Socket连接服务器");
        System.out.println("   - 获取输入/输出流");
        System.out.println("   - 读写数据");
        System.out.println("   - 关闭连接");
    }

    // 练习1：简单聊天室
    static void testChatRoom() {
        System.out.println("聊天室功能:");
        System.out.println("- 支持多客户端连接");
        System.out.println("- 广播消息给所有客户端");
        System.out.println("- 私聊功能");
        System.out.println("- 用户上下线通知");
        System.out.println("实现代码在注释中...");
    }

    // 练习2：文件传输
    static void testFileTransfer() {
        System.out.println("文件传输功能:");
        System.out.println("- 客户端发送文件到服务器");
        System.out.println("- 服务器接收并保存文件");
        System.out.println("- 支持大文件分块传输");
        System.out.println("- 传输进度显示");
        System.out.println("实现代码在注释中...");
    }

    // 练习3：端口扫描器
    static void testPortScanner() {
        System.out.println("端口扫描器功能:");
        System.out.println("- 扫描指定IP的端口范围");
        System.out.println("- 检测端口是否开放");
        System.out.println("- 多线程加速扫描");
        System.out.println("- 结果统计");
        System.out.println("实现代码在注释中...");
    }

    // 练习4：简单Web服务器
    static void testSimpleWebServer() {
        System.out.println("简单Web服务器功能:");
        System.out.println("- 监听HTTP请求");
        System.out.println("- 返回静态HTML页面");
        System.out.println("- 支持GET请求");
        System.out.println("- 处理404错误");
        System.out.println("实现代码在注释中...");
    }
}
