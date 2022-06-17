package com.itmoclouddev.labf.dao;

import java.util.Collection;

import com.itmoclouddev.labf.exception.InvalidValueException;
import com.itmoclouddev.labf.model.Dragon;
import com.itmoclouddev.labf.model.DragonCharacter;
import com.itmoclouddev.labf.model.DragonType;

public interface Dao {
    
    public Dragon get(long id) throws DaoException, InvalidValueException, NotFoundException;
    public long add(Dragon dragon) throws DaoException;
    public void update(long id, Dragon dragon) throws DaoException, NotFoundException;
    public Dragon delete(long id) throws DaoException, InvalidValueException, NotFoundException;
    public Collection<Dragon> getAll() throws DaoException, InvalidValueException;
    public Collection<Dragon> getFiltered(Dragon filter);

    public long countTypeLessThan(DragonType type);
    public long countCharacterMoreThan(DragonCharacter character);
    
}
