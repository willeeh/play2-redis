package util;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtil
{
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static final String toJson(Object o)
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            return mapper.writeValueAsString(o);
        }
        catch (JsonProcessingException e)
        {
            logger.error("Json Processing was wrong: ", logger);
        }
        catch (IOException e)
        {
            logger.error("I/O exception: ", logger);
        }

        return null;
    }
}
