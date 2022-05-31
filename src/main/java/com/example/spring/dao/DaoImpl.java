package com.example.spring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.inject.Named;

import com.example.spring.core.Coordinates;
import com.example.spring.core.Dragon;
import com.example.spring.core.DragonCave;
import com.example.spring.core.DragonCharacter;
import com.example.spring.core.DragonType;
import com.example.spring.core.InvalidValueException;

@Named
public class DaoImpl implements Dao {
    private Connection connection = null;

    public DaoImpl() throws DaoException {

        
    }

    @Override
    public Dragon get(long id) throws DaoException, InvalidValueException, NotFoundException {
        return new Dragon(1l, "name", new Coordinates(0.1f, 0.2f), java.time.ZonedDateTime.now(), 100l, 101l, DragonType.AIR, DragonCharacter.CHAOTIC_EVIL, new DragonCave(250.33f, 200));
    }

    @Override
    public long add(Dragon dragon) throws DaoException {
        return 1;
    }

    @Override
    public void update(long id, Dragon dragon) throws DaoException, NotFoundException {
        return;
    }

    @Override
    public Dragon delete(long id) throws DaoException, InvalidValueException, NotFoundException {
        return new Dragon(1l, "name", new Coordinates(0.1f, 0.2f), java.time.ZonedDateTime.now(), 100l, 101l, DragonType.AIR, DragonCharacter.CHAOTIC_EVIL, new DragonCave(250.33f, 200));
    }

    @Override
    public Collection<Dragon> getAll() throws DaoException, InvalidValueException {
        ArrayList<Dragon> collection = new ArrayList<Dragon>();

        collection.add(new Dragon(1l, "name", new Coordinates(0.1f, 0.2f), java.time.ZonedDateTime.now(), 100l, 101l, DragonType.AIR, DragonCharacter.CHAOTIC_EVIL, new DragonCave(250.33f, 200)));
        return collection;
    }

    @Override
    public Collection<Dragon> getFiltered(Dragon filter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long countTypeLessThan(DragonType type) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long countCharacterMoreThan(DragonCharacter character) {
        // TODO Auto-generated method stub
        return 0;
    }

}
