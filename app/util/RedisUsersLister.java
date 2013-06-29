package util;

import model.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Simple program to list the registered users.
 * Execute it with an argument: the Redis password.
 */
public class RedisUsersLister
{
	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.err.println("Missing arguments: REDIS_PASSWORD");
			System.exit(0);
		}

		final String password = args[0];

		final JedisPool jedisPool = new JedisPool(
				new JedisPoolConfig(),
				"cod.redistogo.com", 10376,
				5000,
				password);

		final Jedis jedis = jedisPool.getResource();

		final User dummyUser = new User();

		final int usersCount = Integer.parseInt(
				jedis.get(dummyUser.getCounterRepositoryName()));

		System.out.println(usersCount + " users");

		for (int i = 1; i <= usersCount; i++)
		{
			final String user = jedis.get(dummyUser.getRepositoryName() + i);
			System.out.println(user);
		}

		jedisPool.returnResource(jedis);
	}
}
