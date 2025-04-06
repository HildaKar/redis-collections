package org.example;

import org.example.redis.collection.RedisBackedMap;
import org.example.redis.collection.RedisBackedQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Queue;

@SpringBootApplication
public class RedisCollectionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisCollectionDemoApplication.class, args);

		redisMap();
		redisQueue();
	}

	private static void redisMap() {
		Map<String, Integer> myMap = new RedisBackedMap("myMap");

		System.out.println("Inserting keys: apple->10, banana->20, cherry->30...");
		myMap.put("apple", 10);
		myMap.put("banana", 20);
		myMap.put("cherry", 30);

		System.out.println("myMap size = " + myMap.size());
		System.out.println("myMap.get('banana') = " + myMap.get("banana"));

		System.out.println("Removing 'banana'...");
		myMap.remove("banana");
		System.out.println("Contains 'banana'? " + myMap.containsKey("banana"));

		System.out.println("Final keySet: " + myMap.keySet());
		System.out.println("Final values: " + myMap.values());

		System.out.println("Clearing map...");
		myMap.clear();
		System.out.println("myMap is now empty? " + myMap.isEmpty());
	}

	private static void redisQueue() {
		Queue<Integer> myQueue = new RedisBackedQueue("myQueue");

		System.out.println("Inserting elements: 10, 20, 30...");
		myQueue.add(10);
		myQueue.add(20);
		myQueue.add(30);

		System.out.println("myQueue size = " + myQueue.size());
		System.out.println("myQueue.peek() = " + myQueue.peek());

		System.out.println("Removing element...");
		myQueue.poll();
		System.out.println("Contains 10? " + myQueue.contains(10));

		System.out.println("Final elements: " + myQueue);

		System.out.println("Clearing queue...");
		myQueue.clear();
		System.out.println("myQueue is now empty? " + myQueue.isEmpty());
	}

}
