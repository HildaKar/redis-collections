package org.example.redis.collection;

import org.example.util.SpringContextHolder;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * RedisBackedQueue is a Queue implementation that delegates all calls to a Redisson RQueue.
 */
public class RedisBackedQueue
		implements Queue<Integer> {

	private final RQueue<Integer> rqueue;

	public RedisBackedQueue(String name) {
		RedissonClient redissonClient = SpringContextHolder.getBean(RedissonClient.class);
		rqueue = redissonClient.getQueue(name);
	}

	@Override
	public boolean add(Integer e) {
		return rqueue.add(e);
	}

	@Override
	public boolean offer(Integer e) {
		return rqueue.offer(e);
	}

	@Override
	public Integer remove() {
		return rqueue.remove();
	}

	@Override
	public Integer poll() {
		return rqueue.poll();
	}

	@Override
	public Integer element() {
		return rqueue.peek();
	}

	@Override
	public Integer peek() {
		return rqueue.peek();
	}

	@Override
	public int size() {
		return rqueue.size();
	}

	@Override
	public boolean isEmpty() {
		return rqueue.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return rqueue.contains(o);
	}

	@Override
	public Iterator<Integer> iterator() {
		return rqueue.iterator();
	}

	@Override
	public Object[] toArray() {
		return rqueue.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return rqueue.toArray(a);
	}

	@Override
	public boolean remove(Object o) {
		return rqueue.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return rqueue.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		return rqueue.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return rqueue.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return rqueue.retainAll(c);
	}

	@Override
	public void clear() {
		rqueue.clear();
	}
}
