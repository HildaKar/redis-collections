package org.example;

import org.example.redis.collection.RedisBackedMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/*
 * To run your tests you'll need to have Docker running in your environment.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RedissonTestConfig.class)
public class RedisBackedMapTest {

	private RedisBackedMap redisMap;

	@BeforeEach
	void setUp() {
		redisMap = new RedisBackedMap("testMap");
	}

	@AfterEach
	void tearDown() {
		redisMap.clear();
	}

	@Test
	void testIsEmptyInitially() {
		assertTrue(redisMap.isEmpty(), "Map should be empty at the start");
	}

	@Test
	void testPutAndGet() {
		Integer oldValue = (Integer) redisMap.put("apple", 10);
		assertNull(oldValue, "No previous value for 'apple'");
		assertEquals(10, redisMap.get("apple"));
	}

	@Test
	void testSize() {
		redisMap.put("banana", 20);
		redisMap.put("cherry", 30);
		assertEquals(2, redisMap.size(), "We added 2 entries");
	}

	@Test
	void testRemove() {
		redisMap.put("key1", 100);
		redisMap.put("key2", 200);

		Integer removedVal = (Integer) redisMap.remove("key1");
		assertEquals(100, removedVal);
		assertFalse(redisMap.containsKey("key1"));

		Integer missingVal = (Integer) redisMap.remove("notThere");
		assertNull(missingVal);
	}

	@Test
	void testContainsKeyAndValue() {
		redisMap.put("alpha", 1);
		redisMap.put("beta", 2);

		assertTrue(redisMap.containsKey("alpha"));
		assertFalse(redisMap.containsKey("gamma"));

		assertTrue(redisMap.containsValue(1));
		assertFalse(redisMap.containsValue(99));
	}

	@Test
	void testClear() {
		redisMap.put("one", 1);
		redisMap.put("two", 2);
		redisMap.put("three", 3);
		assertEquals(3, redisMap.size());
		redisMap.clear();
		assertTrue(redisMap.isEmpty());
		assertEquals(0, redisMap.size());
	}

	@Test
	void testKeySet() {
		redisMap.put("one", 1);
		redisMap.put("two", 2);
		redisMap.put("three", 3);
		Set<String> keys = redisMap.keySet();
		assertEquals(3, keys.size());
		assertTrue(keys.contains("one"));
		assertTrue(keys.contains("two"));
		assertTrue(keys.contains("three"));

		redisMap.remove("two");
		keys = redisMap.keySet();
		assertEquals(2, keys.size());
		assertFalse(keys.contains("two"));
	}
}
