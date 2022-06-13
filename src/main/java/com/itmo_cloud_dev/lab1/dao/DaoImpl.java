package com.itmo_cloud_dev.lab1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.itmo_cloud_dev.lab1.exception.InvalidValueException;
import com.itmo_cloud_dev.lab1.model.Coordinates;
import com.itmo_cloud_dev.lab1.model.Dragon;
import com.itmo_cloud_dev.lab1.model.DragonCave;
import com.itmo_cloud_dev.lab1.model.DragonCharacter;
import com.itmo_cloud_dev.lab1.model.DragonType;

public class DaoImpl implements Dao {

    private Connection connection = null;

    public DaoImpl() throws DaoException {

        try {

            InitialContext ctx = new InitialContext();

            DataSource ds = (DataSource) ctx.lookup("jdbc/postgrespool");
            connection = ds.getConnection();

            // Logger log = Logger.getLogger(DaoImpl.class.getName());
            // log.info("--------\n----------\n------------\n\n" + (ds == null)
            //         + "\n\n--------------\n------------\n-----------\n---------");

        } catch (SQLException e) {
            throw new DaoException("Error in establishing database connection", e);
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public Dragon get(long id) throws DaoException, InvalidValueException, NotFoundException {
        try {
            PreparedStatement psGet = connection.prepareStatement(
                    "SELECT * FROM" +
                            " dragons JOIN caves ON dragons.cave_id = caves.cave_id" +
                            " WHERE dragon_id=?");

            psGet.setLong(1, id);
            ResultSet rs = psGet.executeQuery();

            if (rs.next()) {
                return new Dragon(
                        rs.getLong("dragon_id"),
                        rs.getString("name"),
                        new Coordinates(rs.getFloat("coordinate_x"),
                                rs.getFloat("coordinate_y")),
                        rs.getObject("creation_date", OffsetDateTime.class).toZonedDateTime()
                                .withZoneSameInstant(ZoneId.of(rs.getString("creation_date_zone"))),
                        rs.getLong("age"),
                        rs.getLong("weight"),
                        DragonType.valueOf(rs.getString("type")),
                        DragonCharacter.valueOf(rs.getString("character")),
                        new DragonCave(
                                rs.getFloat("depth"),
                                rs.getDouble("number_of_treasures")));
            } else {
                throw new NotFoundException(String.format("Dragon with id=%d not found", id));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in database", e);
        }
    }

    @Override
    public long add(Dragon dragon) throws DaoException {
        try {
            PreparedStatement psAdd = connection.prepareStatement(
                    "WITH ins1 AS (" +
                            " INSERT INTO caves(depth, number_of_treasures)" +
                            " values (?, ?)" +
                            " RETURNING cave_id AS new_cave_id" +
                            " )" +
                            " INSERT INTO dragons (name, coordinate_x, coordinate_y, creation_date, creation_date_zone, age, weight, type, character, cave_id)"+
                            " SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, new_cave_id FROM ins1" +
                            " RETURNING dragon_id");
            // cave params
            psAdd.setFloat(1, dragon.getCave().getDepth());
            psAdd.setDouble(2, dragon.getCave().getNumberOfTreasures());
            // dragon params
            psAdd.setString(3, dragon.getName());
            psAdd.setFloat(4, dragon.getCoordinates().getX());
            psAdd.setFloat(5, dragon.getCoordinates().getY());
            psAdd.setObject(6, dragon.getCreationDate().toOffsetDateTime());
            psAdd.setString(7, dragon.getCreationDate().getZone().getId());
            psAdd.setLong(8, dragon.getAge());
            psAdd.setLong(9, dragon.getWeight());
            psAdd.setString(10, dragon.getType().name());
            psAdd.setString(11, dragon.getCharacter().name());

            // execute
            ResultSet rs = psAdd.executeQuery();
            rs.next();
            return rs.getLong("dragon_id");
        } catch (SQLException e) {
            throw new DaoException("Error in database", e);
        }
    }

    @Override
    public void update(long id, Dragon dragon) throws DaoException, NotFoundException {
        try {
            PreparedStatement psUpdate = connection.prepareStatement(
                    "WITH upd1 AS (" +
                            " update dragons" +
                            " SET name=?, coordinate_x=?, coordinate_y=?," +
                            " age=?, weight=?, type=?, character=?" +
                            " WHERE dragon_id=?" +
                            " RETURNING cave_id AS cave_id" +
                            " )" +
                            " UPDATE caves" +
                            " SET depth=?, number_of_treasures=?" +
                            " WHERE cave_id IN (SELECT cave_id FROM upd1)");

            psUpdate.setString(1, dragon.getName());
            psUpdate.setFloat(2, dragon.getCoordinates().getX());
            psUpdate.setFloat(3, dragon.getCoordinates().getY());
            psUpdate.setLong(4, dragon.getAge());
            psUpdate.setLong(5, dragon.getWeight());
            psUpdate.setString(6, dragon.getType().name());
            psUpdate.setString(7, dragon.getCharacter().name());
            psUpdate.setLong(8, id);
            psUpdate.setFloat(9, dragon.getCave().getDepth());
            psUpdate.setDouble(10, dragon.getCave().getNumberOfTreasures());

            int changedRows = psUpdate.executeUpdate();
            if (changedRows == 0) {
                throw new NotFoundException(String.format("Dragon with id=%d not found", id));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in database", e);
        }
    }

    @Override
    public Dragon delete(long id) throws DaoException, InvalidValueException, NotFoundException {
        try {
            PreparedStatement psDelete = connection.prepareStatement(
                    
                            " WITH del1 AS ("+
                                " DELETE from dragons"+
                                " WHERE dragon_id=?"+
                                " RETURNING *"+
                            " )"+
                            " , del2 AS ("+
                                " DELETE FROM caves"+
                                " WHERE cave_id IN (select cave_id from del1)"+
                                " RETURNING *"+
                            " )"+
                            " SELECT * FROM del1 JOIN del2"+
                            " ON del1.cave_id = del2.cave_id"
                            );

            psDelete.setLong(1, id);

            ResultSet rs = psDelete.executeQuery();

            if (!rs.next()) {
                throw new NotFoundException(String.format("Dragon with id=%d not found",id));
            }

            return new Dragon(
                    rs.getLong("dragon_id"),
                    rs.getString("name"),
                    new Coordinates(rs.getFloat("coordinate_x"),
                            rs.getFloat("coordinate_y")),
                    rs.getObject("creation_date", OffsetDateTime.class).toZonedDateTime()
                            .withZoneSameInstant(ZoneId.of(rs.getString("creation_date_zone"))),
                    rs.getLong("age"),
                    rs.getLong("weight"),
                    DragonType.valueOf(rs.getString("type")),
                    DragonCharacter.valueOf(rs.getString("character")),
                    new DragonCave(
                            rs.getFloat("depth"),
                            rs.getDouble("number_of_treasures")));
        } catch (SQLException e) {
            throw new DaoException("Error in database", e);
        }
    }

    @Override
    public Collection<Dragon> getAll() throws DaoException, InvalidValueException {
        ArrayList<Dragon> collection = new ArrayList<Dragon>();

        try {
            PreparedStatement psGetAll = connection.prepareStatement(
                            "SELECT * FROM"+
                            " dragons JOIN caves ON dragons.cave_id = caves.cave_id"
                            );

            ResultSet rs = psGetAll.executeQuery();
            while (rs.next()) {
                collection.add(
                        new Dragon(rs.getLong("dragon_id"),
                                rs.getString("name"),
                                new Coordinates(rs.getFloat("coordinate_x"),
                                        rs.getFloat("coordinate_y")),
                                rs.getObject("creation_date", OffsetDateTime.class).toZonedDateTime()
                                        .withZoneSameInstant(ZoneId.of(rs.getString("creation_date_zone"))),
                                rs.getLong("age"),
                                rs.getLong("weight"),
                                DragonType.valueOf(rs.getString("type")),
                                DragonCharacter.valueOf(rs.getString("character")),
                                new DragonCave(
                                        rs.getFloat("depth"),
                                        rs.getDouble("number_of_treasures"))));
            }

            return collection;
        } catch (SQLException e) {
            throw new DaoException("Error in database", e);
        }
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
