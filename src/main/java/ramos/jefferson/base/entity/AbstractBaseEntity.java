package ramos.jefferson.base.entity;

import java.io.Serializable;

public abstract class AbstractBaseEntity implements Serializable {
    
    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
    
}
