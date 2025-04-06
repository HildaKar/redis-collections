package org.example.redis.collection;

import org.example.util.SpringContextHolder;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * RedisBackedMap is a Map implementation that delegates all calls to a Redisson RMap.
 */
public class RedisBackedMap
		implements Map<String, Integer> {

	private final RMap<String, Integer> rmap;

	public RedisBackedMap(String name) {
		RedissonClient redissonClient = SpringContextHolder.getBean(RedissonClient.class);
		rmap = redissonClient.getMap(name);
	}

	@Override
	public int size() {
		return rmap.size();
	}

	@Override
	public boolean isEmpty() {
		return rmap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return rmap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return rmap.containsValue(value);
	}

	@Override
	public Integer get(Object key) {
		return rmap.get(key);
	}

	@Override
	public Integer put(String key,
			Integer value) {
		return rmap.put(key, value);
	}

	@Override
	public Integer remove(Object key) {
		return rmap.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Integer> m) {
		rmap.putAll(m);
	}

	@Override
	public void clear() {
		rmap.clear();
	}

	@Override
	public Set<String> keySet() {
		return rmap.keySet();
	}

	@Override
	public Collection<Integer> values() {
		return rmap.values();
	}

	@Override
	public Set<Entry<String, Integer>> entrySet() {
		return rmap.entrySet();
	}
}
