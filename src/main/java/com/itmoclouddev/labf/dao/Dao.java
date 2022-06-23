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
    // public Collection<Dragon> getDragonsAndCavesFiltered(Dragon filter);

    // public long countTypeLessThan(DragonType type);
    // public long countCharacterMoreThan(DragonCharacter character);
    

    public Dragon getDragon(long id) throws DaoException, InvalidValueException, NotFoundException;
    public long addDragon(Dragon dragon) throws DaoException;
    public void updateDragon(long id, Dragon dragon) throws DaoException, NotFoundException;
    public Dragon deleteDragon(long id) throws DaoException, InvalidValueException, NotFoundException;
    public Collection<Dragon> getAllDragons() throws DaoException, InvalidValueException;
    // public Collection<Dragon> getDragonsFiltered(Dragon filter);
    
    public DragonCave getCave(long id) throws DaoException, InvalidValueException, NotFoundException;
    public long addCave(DragonCave cave) throws DaoException;
    public void updateCave(long id, DragonCave cave) throws DaoException, NotFoundException;
    public DragonCave deleteCave(long id) throws DaoException, InvalidValueException, NotFoundException;
    public Collection<DragonCave> getAllCaves() throws DaoException, InvalidValueException;
    // public Collection<DragonCave> getCavesFiltered(DragonCave filter);
}
