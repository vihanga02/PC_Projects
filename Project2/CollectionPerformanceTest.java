package PC_Projects.PC_Projects;

import java.util.*;

public class CollectionPerformanceTest {
    public static void main(String[] args) {
        int numElements = 100000;
        int numTests = 100;

        // Create collections
        Map<String, Collection<Integer>> collections = new LinkedHashMap<>();
        collections.put("HashSet", new HashSet<>());
        collections.put("TreeSet", new TreeSet<>());
        collections.put("LinkedHashSet", new LinkedHashSet<>());
        collections.put("ArrayList", new ArrayList<>());
        collections.put("LinkedList", new LinkedList<>());
        collections.put("ArrayDeque", new ArrayDeque<>());
        collections.put("PriorityQueue", new PriorityQueue<>());

        // Create maps
        Map<String, Map<Integer, Integer>> maps = new LinkedHashMap<>();
        maps.put("HashMap", new HashMap<>());
        maps.put("TreeMap", new TreeMap<>());
        maps.put("LinkedHashMap", new LinkedHashMap<>());

        // Fill collections and maps with random items
        Random rand = new Random();
        loadItems(collections, maps, numElements, rand);

        // Perform tests for collections
        Map<String, Map<String, Float>> collectionOperationTimes = performTests(collections, numTests, rand);

        // Perform tests for maps
        Map<String, Map<String, Float>> mapOperationTimes = performTests(maps, numTests, rand);

        // Print the average times for each operation for each collection
        System.out.println("Average times for collection operations:");
        for (Map.Entry<String, Map<String, Float>> entry : collectionOperationTimes.entrySet()) {
            System.out.println("Collection: " + entry.getKey());
            System.out.println(entry.getValue());
        }

        // Print the average times for each operation for each map
        System.out.println("\nAverage times for map operations:");
        for (Map.Entry<String, Map<String, Float>> entry : mapOperationTimes.entrySet()) {
            System.out.println("Map: " + entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    private static void loadItems(Map<String, Collection<Integer>> collections, Map<String, Map<Integer, Integer>> maps, int numElements, Random rand) {
        // Load items into collections
        for (Collection<Integer> collection : collections.values()) {
            while (collection.size() < numElements) {
                collection.add(rand.nextInt(100000));
            }
        }

        // Load items into maps
        for (Map<Integer, Integer> map : maps.values()) {
            while (map.size() < numElements) {
                int key = rand.nextInt(100000);
                int value = rand.nextInt(100000);
                map.put(key, value);
            }
        }
    }

    private static Map<String, Map<String, Float>> performTests(Map<String, ? extends Map> mapCollection, int numTests, Random rand) {
        Map<String, Map<String, Float>> operationTimes = new LinkedHashMap<>();

        // Iterate over the map collection
        for (Map.Entry<String, ? extends Map> entry : mapCollection.entrySet()) {
            String collectionName = entry.getKey();
            Map collection = entry.getValue();

            Map<String, Float> times = new LinkedHashMap<>();
            operationTimes.put(collectionName, times);

            for (int i = 0; i < numTests; i++) {
                long startTime, endTime, totalTime;

                // Create a backup copy of the collection/map
                Map backupCollection = new HashMap(collection);

                // Test operations on the backup collection
                // Test "add" method
                startTime = System.nanoTime();
                backupCollection.put(rand.nextInt(100000), rand.nextInt(100000));
                endTime = System.nanoTime();
                totalTime = endTime - startTime;
                times.put("add", times.getOrDefault("add", 0f) + totalTime);

                // Test "contains" method
                startTime = System.nanoTime();
                backupCollection.containsValue(rand.nextInt(100000));
                endTime = System.nanoTime();
                totalTime = endTime - startTime;
                times.put("contains", times.getOrDefault("contains", 0f) + totalTime);

                // Test "remove" method
                startTime = System.nanoTime();
                backupCollection.remove(rand.nextInt(100000));
                endTime = System.nanoTime();
                totalTime = endTime - startTime;
                times.put("remove", times.getOrDefault("remove", 0f) + totalTime);

                // Test "clear" method
                startTime = System.nanoTime();
                backupCollection.clear();
                endTime = System.nanoTime();
                totalTime = endTime - startTime;
                times.put("clear", times.getOrDefault("clear", 0f) + totalTime);

                // Restore the original collection/map
                collection.clear();
                collection.putAll(backupCollection);
            }

            // Calculate average times for each operation
            for (Map.Entry<String, Float> entryTime : times.entrySet()) {
                times.put(entryTime.getKey(), entryTime.getValue() / numTests);
            }
        }
        return operationTimes;
    }
}
