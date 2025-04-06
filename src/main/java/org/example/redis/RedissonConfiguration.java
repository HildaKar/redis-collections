package org.example.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:redisson.properties")
public class RedissonConfiguration {

	@Value("${redis.host}")
	private String redisHost;

	@Value("${redis.port}")
	private String redisPort;

	@Value("${redis.password:}")
	private String password;

	@Value("${redis.retryAttempts:3}")
	private int retryAttempts;

	@Value("${redis.retryInterval:1500}")
	private int retryInterval;

	@Value("${redis.connectTimeout:10000}")
	private int connectTimeout;

	@Value("${redis.idleConnectionTimeout:10000}")
	private int idleConnectionTimeout;

	@Value("${redis.requestTimeout:3000}")
	private int requestTimeout;
	
	@Value("${redis.maxPoolSize}")
	private int maxPoolSize;

	@Value("${redis.minIdleSize}")
	private int minIdleSize;

	@Bean
	public RedissonClient redissonClient() {
		var config = new Config();

		var address = "redis://" + redisHost + ":" + redisPort;

		config.useSingleServer()
				.setAddress(address)
				.setPassword(password)
				.setRetryAttempts(retryAttempts)
				.setRetryInterval(retryInterval)
				.setConnectTimeout(connectTimeout)
				.setIdleConnectionTimeout(idleConnectionTimeout)
				.setTimeout(requestTimeout)
				.setConnectionPoolSize(maxPoolSize)
				.setConnectionMinimumIdleSize(minIdleSize);

		return Redisson.create(config);
	}
}
