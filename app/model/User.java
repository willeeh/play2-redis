package model;

import java.util.Date;

import static play.data.validation.Constraints.Required;

public class User implements Persistable
{
    public static String COUNTER_KEY = "counter:users";
    public static String USERS_KEY = "users:";

    @Required
    public String email;

    public Date created;

    public User()
    {
        created = new Date();
    }

    @Override
    public String getCounterRepositoryName()
    {
        return COUNTER_KEY;
    }

    @Override
    public String getRepositoryName()
    {
        return USERS_KEY;
    }
}
