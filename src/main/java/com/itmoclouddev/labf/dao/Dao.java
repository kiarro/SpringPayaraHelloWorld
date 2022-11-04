package com.itmoclouddev.labf.dao;

import java.util.Collection;

import com.itmoclouddev.labf.exception.InvalidValueException;
import com.itmoclouddev.labf.model.Dragon;
import com.itmoclouddev.labf.model.DragonCave;
import com.itmoclouddev.labf.model.DragonCharacter;
import com.itmoclouddev.labf.model.DragonType;

public interface Dao {
    
    public Dragon getDragonAndCave(long id) throws DaoException, InvalidValueException, NotFoundException;
    public long addDragonAndCave(Dragon dragon) throws DaoException;
    public void updateDragonWithCave(long id, Dragon dragon) throws DaoException, NotFoundException;
    public Dragon deleteDragonAndCave(long id) throws DaoException, InvalidValueException, NotFoundException;
    public Collection<Dragon> getAllDragonsAndCaves() throws DaoException, InvalidValueException;
    
}
