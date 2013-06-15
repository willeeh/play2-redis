package module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import play.Configuration;
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
            /*Configuration configuration = Play.application().configuration().getConfig("redis");

            String uri = "redis://";

            if ( configuration.getString("hostname") != null )
            {
                uri += "{username}:{password}@";

                uri = uri.replace("{username}",  configuration.getString("hostname"))
                         .replace("{password}",  configuration.getString("password"));
            }

            uri += "{host}:{port}";

            uri = uri.replace("{host}",      configuration.getString("host"))
                     .replace("{port}",      configuration.getString("port"));

            URI redisURI = new URI(uri);

            return new JedisPool(
                new JedisPoolConfig(),
                redisURI.getHost(),
                redisURI.getPort(),
                Protocol.DEFAULT_TIMEOUT,
                redisURI.getUserInfo().split(":",2)[1]);*/

            return new JedisPool(
                new JedisPoolConfig(),
                Play.application().configuration().getConfig("redis").getString("hostname"),
                Play.application().configuration().getConfig("redis").getInt("port") );

        }
        catch (Exception e)
        {
            //TODO
            // URI couldn't be parsed. Handle exception
        }

        return null;
    }
}
