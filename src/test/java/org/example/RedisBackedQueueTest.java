package org.example;

import org.example.redis.collection.RedisBackedQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/*
 * To run your tests you'll need to have Docker running in your environment.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RedissonTestConfig.class)
public class RedisBackedQueueTest {

	private RedisBackedQueue redisQueue;

	@BeforeEach
	void setUp() {
		redisQueue = new RedisBackedQueue("testQueue");
	}

	@AfterEach
	void tearDown() {
		redisQueue.clear();
	}

	@Test
	void testIsEmptyInitially() {
		assertTrue(redisQueue.isEmpty(), "Queue should be empty at start");
	}

	@Test
	void testOfferAndPoll() {
		assertTrue(redisQueue.offer(10));
		assertTrue(redisQueue.offer(20));

		assertEquals(10, redisQueue.poll());
		assertEquals(20, redisQueue.poll());
		assertNull(redisQueue.poll(), "No more elements left");
	}

	@Test
	void testAddAndRemove() {
		redisQueue.add(100);
		redisQueue.add(200);

		assertEquals(100, redisQueue.remove());
		assertEquals(200, redisQueue.remove());

		assertThrows(NoSuchElementException.class, () -> redisQueue.remove());
	}

	@Test
	void testPeek() {
		redisQueue.add(1);
		redisQueue.add(2);

		assertEquals(1, redisQueue.peek());
		assertEquals(1, redisQueue.peek());

		redisQueue.poll();
		assertEquals(2, redisQueue.peek());
	}

	@Test
	void testContains() {
		redisQueue.add(11);
		redisQueue.add(22);

		assertTrue(redisQueue.contains(11));
		assertFalse(redisQueue.contains(99));
	}
}
