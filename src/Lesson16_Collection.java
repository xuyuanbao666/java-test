import java.util.*;

// ===== 集合框架 =====

// 集合框架的核心思想：存储和操作对象集合

// 1. 自定义类用于集合操作
class L16_Student implements Comparable<L16_Student> {
    private String name;
    private int age;
    private double score;

    public L16_Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getScore() { return score; }

    @Override
    public int compareTo(L16_Student other) {
        return Double.compare(this.score, other.score);  // 按成绩排序
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        L16_Student student = (L16_Student) obj;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "L16_Student{name='" + name + "', age=" + age + ", score=" + score + "}";
    }
}

public class Lesson16_Collection {
    public static void main(String[] args) {
        System.out.println("===== 集合框架详解 =====");

        // ===== 1. List：有序，可重复 =====
        System.out.println("\n----- 1. List -----");
        
        // ArrayList
        System.out.println("--- ArrayList ---");
        List<String> arrayList = new ArrayList<>();
        arrayList.add("苹果");
        arrayList.add("香蕉");
        arrayList.add("橘子");
        arrayList.add("苹果");  // 可重复
        System.out.println("列表: " + arrayList);
        System.out.println("大小: " + arrayList.size());
        System.out.println("索引1: " + arrayList.get(1));
        System.out.println("包含苹果: " + arrayList.contains("苹果"));
        System.out.println("苹果的索引: " + arrayList.indexOf("苹果"));
        
        arrayList.remove(0);
        System.out.println("删除后: " + arrayList);
        
        arrayList.add(1, "葡萄");
        System.out.println("插入后: " + arrayList);
        
        arrayList.set(0, "红苹果");
        System.out.println("修改后: " + arrayList);

        // LinkedList
        System.out.println("\n--- LinkedList ---");
        List<String> linkedList = new LinkedList<>();
        linkedList.add("第一");
        linkedList.add("第二");
        linkedList.add("第三");
        System.out.println("链表: " + linkedList);
        
        // LinkedList特有方法
        LinkedList<String> linked = new LinkedList<>(linkedList);
        linked.addFirst("开始");
        linked.addLast("结束");
        System.out.println("链表操作: " + linked);
        System.out.println("第一个: " + linked.getFirst());
        System.out.println("最后一个: " + linked.getLast());

        // 遍历方式
        System.out.println("\n--- 遍历方式 ---");
        // 方式1: for循环
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(i + ": " + arrayList.get(i));
        }
        
        // 方式2: 增强for
        for (String item : arrayList) {
            System.out.println(item);
        }
        
        // 方式3: Iterator
        System.out.println("\n--- Iterator ---");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
            if (item.equals("香蕉")) {
                iterator.remove();  // 安全删除
            }
        }
        System.out.println("删除后: " + arrayList);
        
        // 方式4: forEach (Java 8+)
        System.out.println("\n--- forEach ---");
        arrayList.forEach(item -> System.out.println(item));

        // ===== 2. Set：无序，不重复 =====
        System.out.println("\n----- 2. Set -----");
        
        // HashSet
        System.out.println("--- HashSet ---");
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(3);
        hashSet.add(1);
        hashSet.add(4);
        hashSet.add(1);  // 重复，不会添加
        hashSet.add(5);
        System.out.println("集合: " + hashSet);
        System.out.println("包含3: " + hashSet.contains(3));
        hashSet.remove(4);
        System.out.println("删除4后: " + hashSet);
        
        // TreeSet（有序）
        System.out.println("\n--- TreeSet ---");
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Banana");
        treeSet.add("Apple");
        treeSet.add("Cherry");
        treeSet.add("Date");
        System.out.println("有序集合: " + treeSet);
        
        // LinkedHashSet（保持插入顺序）
        System.out.println("\n--- LinkedHashSet ---");
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("First");
        linkedHashSet.add("Second");
        linkedHashSet.add("Third");
        System.out.println("有序集合: " + linkedHashSet);
        
        // 集合操作
        System.out.println("\n--- 集合操作 ---");
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
        
        // 交集
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("交集: " + intersection);
        
        // 并集
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("并集: " + union);
        
        // 差集
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("差集: " + difference);

        // ===== 3. Map：键值对 =====
        System.out.println("\n----- 3. Map -----");
        
        // HashMap
        System.out.println("--- HashMap ---");
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("小明", 90);
        hashMap.put("小红", 85);
        hashMap.put("小刚", 92);
        hashMap.put("小李", 88);
        System.out.println("Map: " + hashMap);
        System.out.println("小明的成绩: " + hashMap.get("小明"));
        System.out.println("包含小红: " + hashMap.containsKey("小红"));
        System.out.println("包含90分: " + hashMap.containsValue(90));
        System.out.println("大小: " + hashMap.size());
        
        // 遍历Map
        System.out.println("\n--- 遍历Map ---");
        // 方式1: entrySet
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // 方式2: keySet
        System.out.println("\n--- keySet遍历 ---");
        for (String key : hashMap.keySet()) {
            System.out.println(key + ": " + hashMap.get(key));
        }
        
        // 方式3: forEach (Java 8+)
        System.out.println("\n--- forEach遍历 ---");
        hashMap.forEach((key, value) -> System.out.println(key + ": " + value));
        
        // TreeMap（按键排序）
        System.out.println("\n--- TreeMap ---");
        Map<String, Integer> treeMap = new TreeMap<>(hashMap);
        System.out.println("有序Map: " + treeMap);
        
        // LinkedHashMap（保持插入顺序）
        System.out.println("\n--- LinkedHashMap ---");
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("First", 1);
        linkedHashMap.put("Second", 2);
        linkedHashMap.put("Third", 3);
        System.out.println("有序Map: " + linkedHashMap);

        // ===== 4. Queue和Deque =====
        System.out.println("\n----- 4. Queue和Deque -----");
        
        // Queue（队列：先进先出）
        System.out.println("--- Queue ---");
        Queue<String> queue = new LinkedList<>();
        queue.offer("第一");
        queue.offer("第二");
        queue.offer("第三");
        System.out.println("队列: " + queue);
        System.out.println("队首: " + queue.peek());
        System.out.println("出队: " + queue.poll());
        System.out.println("出队后: " + queue);
        
        // Deque（双端队列）
        System.out.println("\n--- Deque ---");
        Deque<String> deque = new LinkedList<>();
        deque.offerFirst("头部");
        deque.offerLast("尾部");
        deque.offerFirst("新头部");
        System.out.println("双端队列: " + deque);
        System.out.println("头部: " + deque.peekFirst());
        System.out.println("尾部: " + deque.peekLast());
        
        // Stack（栈：后进先出）
        System.out.println("\n--- Stack ---");
        Deque<String> stack = new LinkedList<>();
        stack.push("第一");
        stack.push("第二");
        stack.push("第三");
        System.out.println("栈: " + stack);
        System.out.println("栈顶: " + stack.peek());
        System.out.println("出栈: " + stack.pop());
        System.out.println("出栈后: " + stack);

        // ===== 5. Collections工具类 =====
        System.out.println("\n----- 5. Collections工具类 -----");
        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("原列表: " + nums);
        
        // 排序
        Collections.sort(nums);
        System.out.println("排序后: " + nums);
        
        // 反转
        Collections.reverse(nums);
        System.out.println("反转后: " + nums);
        
        // 随机打乱
        Collections.shuffle(nums);
        System.out.println("打乱后: " + nums);
        
        // 最大值、最小值
        System.out.println("最大值: " + Collections.max(nums));
        System.out.println("最小值: " + Collections.min(nums));
        
        // 频率
        List<String> items = Arrays.asList("苹果", "香蕉", "苹果", "橘子", "苹果");
        System.out.println("苹果出现次数: " + Collections.frequency(items, "苹果"));
        
        // 不可修改集合
        List<String> unmodifiable = Collections.unmodifiableList(items);
        System.out.println("不可修改列表: " + unmodifiable);
        // unmodifiable.add("新元素");  // 会抛出异常
        
        // 空集合
        List<Object> emptyList = Collections.emptyList();
        System.out.println("空列表: " + emptyList);
        
        // 单元素集合
        List<String> singletonList = Collections.singletonList("唯一元素");
        System.out.println("单元素列表: " + singletonList);

        // ===== 6. 集合转换 =====
        System.out.println("\n----- 6. 集合转换 -----");
        
        // List -> Set
        List<String> listWithDuplicates = Arrays.asList("A", "B", "A", "C", "B");
        Set<String> setFromList = new HashSet<>(listWithDuplicates);
        System.out.println("List: " + listWithDuplicates);
        System.out.println("去重后: " + setFromList);
        
        // Set -> List
        List<String> listFromSet = new ArrayList<>(setFromList);
        System.out.println("Set转List: " + listFromSet);
        
        // 数组 -> List
        String[] array = {"X", "Y", "Z"};
        List<String> listFromArray = Arrays.asList(array);
        System.out.println("数组转List: " + listFromArray);
        
        // List -> 数组
        String[] arrayFromList = listFromArray.toArray(new String[0]);
        System.out.println("List转数组: " + Arrays.toString(arrayFromList));
        
        // Map -> Set
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        System.out.println("Map转Set: " + entrySet);

        // ===== 7. 自定义对象集合 =====
        System.out.println("\n----- 7. 自定义对象集合 -----");
        
        // 学生列表
        List<L16_Student> students = new ArrayList<>();
        students.add(new L16_Student("张三", 20, 90));
        students.add(new L16_Student("李四", 21, 85));
        students.add(new L16_Student("王五", 19, 95));
        students.add(new L16_Student("赵六", 22, 88));
        
        System.out.println("学生列表: " + students);
        
        // 按成绩排序
        Collections.sort(students);
        System.out.println("按成绩排序: " + students);
        
        // 使用Comparator排序
        students.sort(Comparator.comparing(L16_Student::getName));
        System.out.println("按姓名排序: " + students);
        
        students.sort(Comparator.comparingInt(L16_Student::getAge).reversed());
        System.out.println("按年龄降序: " + students);
        
        // 学生成绩Map
        Map<String, L16_Student> studentMap = new HashMap<>();
        for (L16_Student student : students) {
            studentMap.put(student.getName(), student);
        }
        System.out.println("学生Map: " + studentMap);
        System.out.println("查找张三: " + studentMap.get("张三"));

        // ===== 8. 集合最佳实践 =====
        System.out.println("\n----- 8. 集合最佳实践 -----");
        
        // 选择合适的集合
        System.out.println("选择指南:");
        System.out.println("List - 有序，可重复，按索引访问");
        System.out.println("Set - 无序，不重复，快速查找");
        System.out.println("Map - 键值对，快速按键查找");
        System.out.println("Queue - 先进先出");
        System.out.println("Deque - 双端队列/栈");
        
        // 性能比较
        System.out.println("\n性能特点:");
        System.out.println("ArrayList - 随机访问快，插入删除慢");
        System.out.println("LinkedList - 插入删除快，随机访问慢");
        System.out.println("HashSet - 查找最快，无序");
        System.out.println("TreeSet - 有序，查找较快");
        System.out.println("HashMap - 查找最快，无序");
        System.out.println("TreeMap - 有序，查找较快");

        // ===== 练习题 =====
        System.out.println("\n===== 练习题 =====");
        
        // 练习1：学生成绩管理系统
        System.out.println("练习1 - 学生成绩管理系统:");
        testStudentGradeSystem();
        
        // 练习2：单词频率统计
        System.out.println("\n练习2 - 单词频率统计:");
        testWordFrequency();
        
        // 练习3：集合操作
        System.out.println("\n练习3 - 集合操作:");
        testSetOperations();
        
        // 练习4：LRU缓存
        System.out.println("\n练习4 - LRU缓存:");
        testLRUCache();
    }

    // 练习1：学生成绩管理系统
    static void testStudentGradeSystem() {
        Map<String, List<Integer>> gradeMap = new HashMap<>();
        
        // 添加成绩
        addGrade(gradeMap, "张三", 90);
        addGrade(gradeMap, "张三", 85);
        addGrade(gradeMap, "张三", 92);
        addGrade(gradeMap, "李四", 88);
        addGrade(gradeMap, "李四", 95);
        addGrade(gradeMap, "王五", 78);
        addGrade(gradeMap, "王五", 82);
        
        // 显示所有学生平均分
        for (Map.Entry<String, List<Integer>> entry : gradeMap.entrySet()) {
            String name = entry.getKey();
            List<Integer> grades = entry.getValue();
            double avg = grades.stream().mapToInt(Integer::intValue).average().orElse(0);
            System.out.printf("%s: 成绩%v, 平均分%.1f%n", name, grades, avg);
        }
        
        // 找出最高平均分学生
        String topStudent = "";
        double topAvg = 0;
        for (Map.Entry<String, List<Integer>> entry : gradeMap.entrySet()) {
            double avg = entry.getValue().stream().mapToInt(Integer::intValue).average().orElse(0);
            if (avg > topAvg) {
                topAvg = avg;
                topStudent = entry.getKey();
            }
        }
        System.out.println("最高平均分学生: " + topStudent + " (" + topAvg + "分)");
    }

    static void addGrade(Map<String, List<Integer>> gradeMap, String name, int grade) {
        gradeMap.computeIfAbsent(name, k -> new ArrayList<>()).add(grade);
    }

    // 练习2：单词频率统计
    static void testWordFrequency() {
        String text = "hello world hello java world hello java programming java";
        String[] words = text.split(" ");
        
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        
        System.out.println("文本: " + text);
        System.out.println("单词频率:");
        frequencyMap.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .forEach(entry -> System.out.println("  " + entry.getKey() + ": " + entry.getValue()));
    }

    // 练习3：集合操作
    static void testSetOperations() {
        Set<Integer> setA = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Set<Integer> setB = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8, 9));
        
        System.out.println("集合A: " + setA);
        System.out.println("集合B: " + setB);
        
        // 交集
        Set<Integer> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);
        System.out.println("交集: " + intersection);
        
        // 并集
        Set<Integer> union = new HashSet<>(setA);
        union.addAll(setB);
        System.out.println("并集: " + union);
        
        // 差集 A-B
        Set<Integer> differenceAB = new HashSet<>(setA);
        differenceAB.removeAll(setB);
        System.out.println("A-B: " + differenceAB);
        
        // 差集 B-A
        Set<Integer> differenceBA = new HashSet<>(setB);
        differenceBA.removeAll(setA);
        System.out.println("B-A: " + differenceBA);
    }

    // 练习4：LRU缓存
    static void testLRUCache() {
        // 使用LinkedHashMap实现LRU缓存
        int cacheSize = 3;
        Map<String, String> lruCache = new LinkedHashMap<String, String>(cacheSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > cacheSize;
            }
        };
        
        lruCache.put("key1", "value1");
        lruCache.put("key2", "value2");
        lruCache.put("key3", "value3");
        System.out.println("缓存: " + lruCache);
        
        lruCache.get("key1");  // 访问key1
        lruCache.put("key4", "value4");  // 添加新元素，会淘汰最久未使用的
        System.out.println("添加key4后: " + lruCache);
        
        lruCache.put("key5", "value5");
        System.out.println("添加key5后: " + lruCache);
    }
}
