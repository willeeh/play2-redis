package services;

import com.google.inject.Inject;
import model.Persistable;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.JsonUtil;

public class ManagerImpl implements Manager
{
    private final JedisPool jedisPool;

    @Inject
    public ManagerImpl(JedisPool jedisPool)
    {
        this.jedisPool = jedisPool;
    }

    public void set(Persistable entity) throws Exception
    {
        Jedis jedis = jedisPool.getResource();

        try
        {
            jedis.set(
                    entity.getRepositoryName() + jedis.incr(entity.getCounterRepositoryName()),
                    JsonUtil.toJson(entity)
            );
        }
        finally
        {
            jedisPool.returnResource(jedis);
        }

    }
}
