package services;

import model.Persistable;

public interface Manager<T extends Persistable>
{
    void set(T entity) throws Exception;
}
