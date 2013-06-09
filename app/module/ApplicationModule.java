package module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import play.Play;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import services.Manager;
import services.ManagerImpl;

import java.net.URI;
import java.net.URISyntaxException;

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
        try
        {
            URI redisURI = new URI(System.getenv("REDISTOGO_URL"));

            return new JedisPool(
                new JedisPoolConfig(),
                redisURI.getHost(),
                redisURI.getPort(),
                Protocol.DEFAULT_TIMEOUT,
                redisURI.getUserInfo().split(":",2)[1]);

            /*return new JedisPool(
                new JedisPoolConfig(),
                Play.application().configuration().getConfig("redis").getString("hostname"),
                Play.application().configuration().getConfig("redis").getInt("port") );*/

        }
        catch (URISyntaxException e)
        {
            //TODO
            // URI couldn't be parsed. Handle exception
        }

        return null;
    }
}
