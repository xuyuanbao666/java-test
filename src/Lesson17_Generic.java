import java.util.*;

// ===== 泛型 =====

// 泛型的核心思想：类型参数化，提高代码复用性和安全性

// 1. 泛型类
class Box<T> {
    private T content;

    public void put(T item) {
        this.content = item;
    }

    public T get() {
        return content;
    }

    public boolean isEmpty() {
        return content == null;
    }

    @Override
    public String toString() {
        return "Box{content=" + content + "}";
    }
}

// 2. 多类型参数泛型类
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }

    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }

    @Override
    public String toString() {
        return "Pair{key=" + key + ", value=" + value + "}";
    }
}

// 3. 泛型接口
interface Repository<T> {
    void add(T item);
    T get(int index);
    List<T> getAll();
    int size();
}

// 4. 泛型接口实现
class ArrayListRepository<T> implements Repository<T> {
    private List<T> items = new ArrayList<>();

    @Override
    public void add(T item) {
        items.add(item);
    }

    @Override
    public T get(int index) {
        return items.get(index);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    @Override
    public int size() {
        return items.size();
    }
}

// 5. 泛型方法类
class GenericUtils {
    // 打印数组
    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // 查找最大值
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        T max = array[0];
        for (T item : array) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    // 查找最小值
    public static <T extends Comparable<T>> T findMin(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        T min = array[0];
        for (T item : array) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }
        return min;
    }

    // 数组转集合
    public static <T> List<T> arrayToList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    // 集合转数组
    @SuppressWarnings("unchecked")
    public static <T> T[] listToArray(List<T> list, Class<T> clazz) {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, list.size());
        return list.toArray(array);
    }

    // 安全的类型转换
    @SuppressWarnings("unchecked")
    public static <T> T safeCast(Object obj, Class<T> clazz) {
        if (clazz.isInstance(obj)) {
            return clazz.cast(obj);
        }
        return null;
    }

    // 创建空列表
    public static <T> List<T> emptyList() {
        return new ArrayList<>();
    }

    // 创建单元素列表
    public static <T> List<T> singletonList(T item) {
        List<T> list = new ArrayList<>();
        list.add(item);
        return list;
    }
}

// 6. 泛型继承
class Container<T> {
    protected T data;

    public Container(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}

class NamedContainer<T> extends Container<T> {
    private String name;

    public NamedContainer(String name, T data) {
        super(data);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "NamedContainer{name='" + name + "', data=" + data + "}";
    }
}

// 7. 泛型方法重载
class MethodOverload {
    // 泛型方法
    public static <T> void process(T item) {
        System.out.println("处理泛型: " + item);
    }

    // 具体类型方法（重载）
    public static void process(String item) {
        System.out.println("处理字符串: " + item);
    }

    // 泛型方法，带约束
    public static <T extends Number> void processNumber(T item) {
        System.out.println("处理数字: " + item.doubleValue());
    }
}

public class Lesson17_Generic {
    public static void main(String[] args) {
        System.out.println("===== 泛型详解 =====");

        // 1. 泛型类
        System.out.println("\n----- 1. 泛型类 -----");
        Box<String> stringBox = new Box<>();
        stringBox.put("Hello");
        System.out.println("String Box: " + stringBox.get());
        System.out.println("是否为空: " + stringBox.isEmpty());

        Box<Integer> intBox = new Box<>();
        intBox.put(100);
        System.out.println("Integer Box: " + intBox.get());

        Box<Double> doubleBox = new Box<>();
        doubleBox.put(3.14);
        System.out.println("Double Box: " + doubleBox.get());

        // 2. 多类型参数
        System.out.println("\n----- 2. 多类型参数 -----");
        Pair<String, Integer> nameAge = new Pair<>("张三", 25);
        System.out.println("姓名年龄: " + nameAge);

        Pair<Integer, Boolean> idStatus = new Pair<>(1001, true);
        System.out.println("ID状态: " + idStatus);

        // 3. 泛型接口
        System.out.println("\n----- 3. 泛型接口 -----");
        Repository<String> stringRepo = new ArrayListRepository<>();
        stringRepo.add("苹果");
        stringRepo.add("香蕉");
        stringRepo.add("橘子");
        System.out.println("仓库大小: " + stringRepo.size());
        System.out.println("第一个: " + stringRepo.get(0));
        System.out.println("所有: " + stringRepo.getAll());

        Repository<Integer> intRepo = new ArrayListRepository<>();
        intRepo.add(10);
        intRepo.add(20);
        intRepo.add(30);
        System.out.println("数字仓库: " + intRepo.getAll());

        // 4. 泛型方法
        System.out.println("\n----- 4. 泛型方法 -----");
        String[] names = {"小明", "小红", "小刚"};
        Integer[] nums = {3, 1, 4, 1, 5, 9};
        Double[] doubles = {3.14, 2.71, 1.41};

        System.out.print("字符串数组: ");
        GenericUtils.printArray(names);
        System.out.print("整数数组: ");
        GenericUtils.printArray(nums);
        System.out.print("浮点数组: ");
        GenericUtils.printArray(doubles);

        // 5. 泛型约束
        System.out.println("\n----- 5. 泛型约束 -----");
        System.out.println("整数最大值: " + GenericUtils.findMax(nums));
        System.out.println("字符串最大值: " + GenericUtils.findMax(names));
        System.out.println("浮点最小值: " + GenericUtils.findMin(doubles));

        // 6. 泛型继承
        System.out.println("\n----- 6. 泛型继承 -----");
        Container<String> container = new Container<>("数据");
        System.out.println("容器: " + container.getData());

        NamedContainer<Integer> namedContainer = new NamedContainer<>("年龄", 25);
        System.out.println("命名容器: " + namedContainer);
        System.out.println("名称: " + namedContainer.getName());
        System.out.println("数据: " + namedContainer.getData());

        // 7. 泛型通配符
        System.out.println("\n----- 7. 泛型通配符 -----");
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<String> strList = Arrays.asList("a", "b", "c");
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);

        System.out.println("整数列表:");
        printList(intList);
        System.out.println("字符串列表:");
        printList(strList);
        System.out.println("浮点列表:");
        printList(doubleList);

        // 8. 上界通配符
        System.out.println("\n----- 8. 上界通配符 -----");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("数字总和: " + sum(numbers));

        // 9. 下界通配符
        System.out.println("\n----- 9. 下界通配符 -----");
        List<Number> numberList = new ArrayList<>();
        addNumbers(numberList);
        System.out.println("数字列表: " + numberList);

        // 10. 类型擦除
        System.out.println("\n----- 10. 类型擦除 -----");
        Box<String> stringBox2 = new Box<>();
        Box<Integer> intBox2 = new Box<>();
        System.out.println("String Box类: " + stringBox2.getClass().getName());
        System.out.println("Integer Box类: " + intBox2.getClass().getName());
        System.out.println("类型相同: " + (stringBox2.getClass() == intBox2.getClass()));

        // 11. 泛型数组
        System.out.println("\n----- 11. 泛型数组 -----");
        List<String> stringList = GenericUtils.arrayToList(names);
        System.out.println("数组转列表: " + stringList);

        // 12. 方法重载
        System.out.println("\n----- 12. 方法重载 -----");
        MethodOverload.process("Hello");
        MethodOverload.process(100);
        MethodOverload.processNumber(3.14);
        MethodOverload.processNumber(100);

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：泛型栈
        System.out.println("练习1 - 泛型栈:");
        testGenericStack();
        
        // 练习2：泛型队列
        System.out.println("\n练习2 - 泛型队列:");
        testGenericQueue();
        
        // 练习3：泛型缓存
        System.out.println("\n练习3 - 泛型缓存:");
        testGenericCache();
        
        // 练习4：泛型工具方法
        System.out.println("\n练习4 - 泛型工具方法:");
        testGenericUtility();
    }

    // 通配符方法
    static void printList(List<?> list) {
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // 上界通配符：只能读取，不能写入
    static double sum(List<? extends Number> list) {
        double total = 0;
        for (Number num : list) {
            total += num.doubleValue();
        }
        return total;
    }

    // 下界通配符：可以写入
    static void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    // 练习1：泛型栈
    static void testGenericStack() {
        GenericStack<String> stack = new GenericStack<>();
        stack.push("第一");
        stack.push("第二");
        stack.push("第三");
        System.out.println("栈大小: " + stack.size());
        System.out.println("栈顶: " + stack.peek());
        System.out.println("出栈: " + stack.pop());
        System.out.println("出栈后大小: " + stack.size());
        System.out.println("是否为空: " + stack.isEmpty());
    }

    // 练习2：泛型队列
    static void testGenericQueue() {
        GenericQueue<Integer> queue = new GenericQueue<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("队列大小: " + queue.size());
        System.out.println("队首: " + queue.peek());
        System.out.println("出队: " + queue.dequeue());
        System.out.println("出队后大小: " + queue.size());
    }

    // 练习3：泛型缓存
    static void testGenericCache() {
        GenericCache<String, Integer> cache = new GenericCache<>(3);
        cache.put("数学", 90);
        cache.put("语文", 85);
        cache.put("英语", 95);
        System.out.println("缓存: " + cache);
        System.out.println("数学成绩: " + cache.get("数学"));
        
        cache.put("物理", 88);  // 会淘汰最早的数据
        System.out.println("添加物理后: " + cache);
    }

    // 练习4：泛型工具方法
    static void testGenericUtility() {
        // 安全类型转换
        Object obj = "Hello";
        String str = GenericUtils.safeCast(obj, String.class);
        System.out.println("安全转换: " + str);
        
        Integer num = GenericUtils.safeCast(obj, Integer.class);
        System.out.println("转换失败: " + num);
        
        // 创建列表
        List<String> list = GenericUtils.singletonList("唯一元素");
        System.out.println("单元素列表: " + list);
        
        List<Object> empty = GenericUtils.emptyList();
        System.out.println("空列表: " + empty);
    }
}

// 泛型栈（练习1）
class GenericStack<T> {
    private List<T> elements = new ArrayList<>();

    public void push(T item) {
        elements.add(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return elements.remove(elements.size() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return elements.get(elements.size() - 1);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }
}

// 泛型队列（练习2）
class GenericQueue<T> {
    private List<T> elements = new ArrayList<>();

    public void enqueue(T item) {
        elements.add(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return elements.remove(0);
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return elements.get(0);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }
}

// 泛型缓存（练习3）
class GenericCache<K, V> {
    private int capacity;
    private Map<K, V> cache;

    public GenericCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.get(key);
    }

    public int size() {
        return cache.size();
    }

    @Override
    public String toString() {
        return cache.toString();
    }
}
