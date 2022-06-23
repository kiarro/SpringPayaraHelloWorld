package com.itmoclouddev.labf.service;

import java.util.Collection;

import com.itmoclouddev.labf.filter.DragonFilter;
import com.itmoclouddev.labf.model.*;

public interface CaveService {
    public DragonCave get(long id);
    public long add(DragonCave cave);
    public void update(long id, DragonCave cave);
    public DragonCave delete(long id);
    public Collection<DragonCave> getAll();

    public Collection<DragonCave> getFiltered(Collection<DragonCave> collection, DragonFilter filter);
    public Collection<DragonCave> getSorted(Collection<DragonCave> collection, String[] sortvalues);

    public Collection<DragonCave> getPage(Collection<DragonCave> collection, long offest, long limit);
}
