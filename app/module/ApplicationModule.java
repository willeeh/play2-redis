package module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import play.Play;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import services.Manager;
import services.ManagerImpl;

public class ApplicationModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(Manager.class).to(ManagerImpl.class);
    }

    @Provides
    public JedisPool provideRedisPool()
    {
        return new JedisPool(
            new JedisPoolConfig(),
            Play.application().configuration().getConfig("redis").getString("hostname"),
            Play.application().configuration().getConfig("redis").getInt("port") );
    }
}
