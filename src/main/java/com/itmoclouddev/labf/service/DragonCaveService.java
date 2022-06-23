package com.itmoclouddev.labf.service;

import java.util.Collection;

import com.itmoclouddev.labf.filter.DragonFilter;
import com.itmoclouddev.labf.model.*;

public interface DragonCaveService {
    public Dragon get(long id);
    public long add(Dragon dragon);
    public void update(long id, Dragon dragon);
    public Dragon delete(long id);
    public Collection<Dragon> getAll();

    public Collection<Dragon> getFiltered(Collection<Dragon> collection, DragonFilter filter);
    public Collection<Dragon> getSorted(Collection<Dragon> collection, String[] sortvalues);

    public long countTypeLessThan(Collection<Dragon> collection, DragonType type);
    public long countCharacterMoreThan(Collection<Dragon> collection, DragonCharacter character);

    public Collection<Dragon> nameStartsWith(Collection<Dragon> collection, String substr);

    public Collection<Dragon> getPage(Collection<Dragon> collection, long offest, long limit);
}
