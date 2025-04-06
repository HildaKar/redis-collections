package org.example;

import jakarta.annotation.PreDestroy;
import org.example.util.SpringContextHolder;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.redisson.config.Config;
import org.testcontainers.containers.GenericContainer;

@TestConfiguration
public class RedissonTestConfig {

	// Start the Redis container (you could also start this in your test class)
	private static final GenericContainer<?> redisContainer = new GenericContainer<>("redis:latest").withExposedPorts(
			6379);

	static {
		redisContainer.start();
	}

	@Bean
	public SpringContextHolder springContextHolder() {
		return new SpringContextHolder();
	}

	@Bean(destroyMethod = "shutdown")
	public RedissonClient redissonClient() {
		String redisHost = redisContainer.getHost();
		Integer redisPort = redisContainer.getMappedPort(6379);
		String address = "redis://" + redisHost + ":" + redisPort;

		Config config = new Config();
		config.useSingleServer().setAddress(address);
		return Redisson.create(config);
	}

	@PreDestroy
	public void stopRedisContainer() {
		if (redisContainer != null && redisContainer.isRunning()) {
			redisContainer.stop();
		}
	}
}
