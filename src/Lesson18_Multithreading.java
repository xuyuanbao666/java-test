// ===== 多线程 =====

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.List;
import java.util.ArrayList;

// 多线程的核心思想：并发执行任务，提高程序效率

// 1. 线程状态
class ThreadStateDemo {
    public static void showThreadStates() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        System.out.println("新建状态: " + thread.getState());  // NEW
        thread.start();
        System.out.println("运行状态: " + thread.getState());  // RUNNABLE
        
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("终止状态: " + thread.getState());  // TERMINATED
    }
}

// 2. 线程同步
class SynchronizedDemo {
    private int count = 0;
    private final Object lock = new Object();
    
    // synchronized方法
    public synchronized void increment() {
        count++;
    }
    
    // synchronized代码块
    public void decrement() {
        synchronized (lock) {
            count--;
        }
    }
    
    public int getCount() {
        return count;
    }
}

// 3. 使用Lock接口
class LockDemo {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();
    
    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
    
    public int getCount() {
        return count;
    }
}

// 4. 生产者消费者模式
class ProducerConsumerDemo {
    private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    private volatile boolean running = true;
    
    public void start() throws InterruptedException {
        // 生产者线程
        Thread producer = new Thread(() -> {
            int value = 0;
            while (running) {
                try {
                    queue.put(value);
                    System.out.println("生产: " + value);
                    value++;
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        // 消费者线程
        Thread consumer = new Thread(() -> {
            while (running) {
                try {
                    int value = queue.take();
                    System.out.println("消费: " + value);
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        producer.start();
        consumer.start();
        
        Thread.sleep(1000);
        running = false;
        producer.join();
        consumer.join();
    }
}

// 5. 线程池示例
class ThreadPoolDemo {
    public static void demonstrate() throws ExecutionException, InterruptedException {
        // 固定大小线程池
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);
        
        // 提交任务
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            fixedPool.submit(() -> {
                System.out.println("任务" + taskId + "由" + 
                    Thread.currentThread().getName() + "执行");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // 关闭线程池
        fixedPool.shutdown();
        fixedPool.awaitTermination(1, TimeUnit.SECONDS);
        
        // 带返回值的任务
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(500);
            return 42;
        });
        
        System.out.println("任务结果: " + future.get());
        executor.shutdown();
    }
}

// 6. 原子操作
class AtomicDemo {
    private AtomicInteger count = new AtomicInteger(0);
    
    public void increment() {
        count.incrementAndGet();
    }
    
    public int getCount() {
        return count.get();
    }
}

public class Lesson18_Multithreading {
    public static void main(String[] args) throws Exception {
        System.out.println("===== 多线程详解 =====");

        // 1. 继承Thread类
        System.out.println("\n----- 1. 继承Thread -----");
        MyThread t1 = new MyThread("线程A");
        MyThread t2 = new MyThread("线程B");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // 2. 实现Runnable接口
        System.out.println("\n----- 2. 实现Runnable -----");
        Thread t3 = new Thread(new MyRunnable("线程C"));
        Thread t4 = new Thread(new MyRunnable("线程D"));
        t3.start();
        t4.start();
        t3.join();
        t4.join();

        // 3. Lambda表达式
        System.out.println("\n----- 3. Lambda表达式 -----");
        Thread t5 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "线程E");
        t5.start();
        t5.join();

        // 4. 线程状态
        System.out.println("\n----- 4. 线程状态 -----");
        ThreadStateDemo.showThreadStates();

        // 5. 线程同步
        System.out.println("\n----- 5. 线程同步 -----");
        SynchronizedDemo syncDemo = new SynchronizedDemo();
        Thread t6 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) syncDemo.increment();
        });
        Thread t7 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) syncDemo.increment();
        });
        t6.start();
        t7.start();
        t6.join();
        t7.join();
        System.out.println("同步计数器: " + syncDemo.getCount());

        // 6. Lock接口
        System.out.println("\n----- 6. Lock接口 -----");
        LockDemo lockDemo = new LockDemo();
        Thread t8 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) lockDemo.increment();
        });
        Thread t9 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) lockDemo.increment();
        });
        t8.start();
        t9.start();
        t8.join();
        t9.join();
        System.out.println("Lock计数器: " + lockDemo.getCount());

        // 7. 原子操作
        System.out.println("\n----- 7. 原子操作 -----");
        AtomicDemo atomicDemo = new AtomicDemo();
        Thread t10 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) atomicDemo.increment();
        });
        Thread t11 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) atomicDemo.increment();
        });
        t10.start();
        t11.start();
        t10.join();
        t11.join();
        System.out.println("原子计数器: " + atomicDemo.getCount());

        // 8. 生产者消费者
        System.out.println("\n----- 8. 生产者消费者 -----");
        ProducerConsumerDemo pcDemo = new ProducerConsumerDemo();
        pcDemo.start();

        // 9. 线程池
        System.out.println("\n----- 9. 线程池 -----");
        ThreadPoolDemo.demonstrate();

        // 10. Callable和Future
        System.out.println("\n----- 10. Callable和Future -----");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            final int taskId = i;
            Future<String> future = executor.submit(() -> {
                Thread.sleep(200);
                return "任务" + taskId + "完成";
            });
            futures.add(future);
        }
        
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
        executor.shutdown();

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：并发计数器
        System.out.println("练习1 - 并发计数器:");
        testConcurrentCounter();
        
        // 练习2：线程安全队列
        System.out.println("\n练习2 - 线程安全队列:");
        testThreadSafeQueue();
        
        // 练习3：线程池任务
        System.out.println("\n练习3 - 线程池任务:");
        testThreadPoolTasks();
        
        // 练习4：读写锁
        System.out.println("\n练习4 - 读写锁:");
        testReadWriteLock();
    }

    // 练习1：并发计数器
    static void testConcurrentCounter() throws InterruptedException {
        Counter counter = new Counter();
        List<Thread> threads = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads.add(thread);
            thread.start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("预期值: 10000");
        System.out.println("实际值: " + counter.getCount());
    }

    // 练习2：线程安全队列
    static void testThreadSafeQueue() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        
        // 生产者
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    queue.put("消息" + i);
                    System.out.println("生产: 消息" + i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // 消费者
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    String message = queue.take();
                    System.out.println("消费: " + message);
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    // 练习3：线程池任务
    static void testThreadPoolTasks() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Integer>> futures = new ArrayList<>();
        
        // 提交计算任务
        for (int i = 1; i <= 5; i++) {
            final int n = i;
            Future<Integer> future = executor.submit(() -> {
                int result = 1;
                for (int j = 1; j <= n; j++) {
                    result *= j;
                }
                return result;
            });
            futures.add(future);
        }
        
        // 获取结果
        for (int i = 0; i < futures.size(); i++) {
            System.out.println((i + 1) + "! = " + futures.get(i).get());
        }
        
        executor.shutdown();
    }

    // 练习4：读写锁
    static void testReadWriteLock() throws InterruptedException {
        ReadWriteLockDemo rwDemo = new ReadWriteLockDemo();
        
        // 写线程
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                rwDemo.write(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // 读线程
        List<Thread> readers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Thread reader = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    rwDemo.read();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            readers.add(reader);
        }
        
        writer.start();
        for (Thread reader : readers) {
            reader.start();
        }
        
        writer.join();
        for (Thread reader : readers) {
            reader.join();
        }
    }
}

// 线程类
class MyThread extends Thread {
    MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + ": " + i);
        }
    }
}

// Runnable实现
class MyRunnable implements Runnable {
    private String name;

    MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + ": " + i);
        }
    }
}

// 计数器
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

// 读写锁示例
class ReadWriteLockDemo {
    private int data = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    public void read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 读取: " + data);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    public void write(int value) {
        lock.writeLock().lock();
        try {
            data = value;
            System.out.println(Thread.currentThread().getName() + " 写入: " + value);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
