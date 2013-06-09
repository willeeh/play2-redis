package model;

import org.codehaus.jackson.annotate.JsonIgnore;

public interface Persistable
{
    @JsonIgnore
    String getCounterRepositoryName();

    @JsonIgnore
    String getRepositoryName();
}
