package com.itmoclouddev.labf.service;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.itmoclouddev.labf.dao.Dao;
import com.itmoclouddev.labf.dao.DaoException;
import com.itmoclouddev.labf.dao.DaoImpl;
import com.itmoclouddev.labf.dao.NotFoundException;
import com.itmoclouddev.labf.exception.InvalidValueException;
import com.itmoclouddev.labf.filter.DragonFilter;
import com.itmoclouddev.labf.model.Dragon;
import com.itmoclouddev.labf.model.DragonCharacter;
import com.itmoclouddev.labf.model.DragonType;

public class DragonServiceImpl implements DragonService {

    private Dao dragonDao;

    public DragonServiceImpl() {
        try {
            this.dragonDao = new DaoImpl();
        } catch (DaoException e) {
            e.printStackTrace();

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error in dao creating:\n", e.getMessage()));
        }
    }

    @Override
    public Dragon get(long id) {
        try {
            Dragon res = dragonDao.get(id);
            return res;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (InvalidValueException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Broken database row: %s", e.getMessage()));
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public long add(Dragon dragon) {
        try {
            dragon.setCreationDate(ZonedDateTime.now());
            long id = dragonDao.add(dragon);
            return id;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @Override
    public void update(long id, Dragon dragon) {
        try {
            dragonDao.update(id, dragon);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public Dragon delete(long id) {
        try {
            Dragon res = dragonDao.delete(id);
            return res;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (InvalidValueException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Broken database row: %s", e.getMessage()));
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public Collection<Dragon> getAll() {
        try {
            Collection<Dragon> res = dragonDao.getAll();
            return res;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (InvalidValueException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Broken database row: %s", e.getMessage()));
        }
    }

    @Override
    public Collection<Dragon> getFiltered(Collection<Dragon> collection, DragonFilter filter) {
        Predicate<Dragon> predicate = item -> true;
        
        Optional<DragonFilter> oFilter = Optional.ofNullable(filter);
        if (!oFilter.isPresent()) {
            return collection;
        }

        if (oFilter.map(DragonFilter::getAge).isPresent()){
            predicate.and(item -> item.getAge().equals(oFilter.map(DragonFilter::getAge).get()));
        }
        if (oFilter.map(DragonFilter::getCharacter).isPresent()){
            predicate.and(item -> item.getCharacter().equals(oFilter.map(DragonFilter::getCharacter).get()));
        }
        if (oFilter.map(DragonFilter::getCreationDate).isPresent()){
            predicate.and(item -> item.getCreationDate().equals(oFilter.map(DragonFilter::getCreationDate).get()));
        }
        if (oFilter.map(DragonFilter::getId).isPresent()){
            predicate.and(item -> item.getId().equals(oFilter.map(DragonFilter::getId).get()));
        }
        if (oFilter.map(DragonFilter::getName).isPresent()){
            predicate.and(item -> item.getName().equals(oFilter.map(DragonFilter::getName).get()));
        }
        if (oFilter.map(DragonFilter::getType).isPresent()){
            predicate.and(item -> item.getType().equals(oFilter.map(DragonFilter::getType).get()));
        }
        if (oFilter.map(DragonFilter::getWeight).isPresent()){
            predicate.and(item -> item.getWeight().equals(oFilter.map(DragonFilter::getWeight).get()));
        }
        // coords
        if (oFilter.map(DragonFilter::getCoordinateX).isPresent()){
            predicate.and(item -> item.getCoordinates().getX() == oFilter.map(DragonFilter::getCoordinateX).get());
        }
        if (oFilter.map(DragonFilter::getCoordinateY).isPresent()){
            predicate.and(item -> item.getCoordinates().getY() == oFilter.map(DragonFilter::getCoordinateY).get());
        }
        // cave
        if (oFilter.map(DragonFilter::getCaveDepth).isPresent()){
            predicate.and(item -> item.getCave().getDepth() == oFilter.map(DragonFilter::getCaveDepth).get());
        }
        if (oFilter.map(DragonFilter::getCaveNumberOfTreasures).isPresent()){
            predicate.and(item -> item.getCave().getNumberOfTreasures() == oFilter.map(DragonFilter::getCaveNumberOfTreasures).get());
        }

        
        // predicate = item -> (filter.getAge() == null || item.getAge().equals(filter.getAge()))
        //         && (filter.getCharacter() == null || item.getCharacter().equals(filter.getCharacter()))
        //         && (filter.getCreationDate() == null || item.getCreationDate().equals(filter.getCreationDate()))
        //         && (filter.getId() == null || item.getId().equals(filter.getId()))
        //         && (filter.getName() == null || item.getName().equals(filter.getName()))
        //         && (filter.getType() == null || item.getType().equals(filter.getType()))
        //         && (filter.getWeight() == null || item.getWeight().equals(filter.getWeight()))
        //         && (filter.getCoordinateX() == null || item.getCoordinates().getX() == (filter.getCoordinateX()))
        //         && (filter.getCoordinateY() == null || item.getCoordinates().getY() == (filter.getCoordinateY()))
        //         && (filter.getCaveDepth() == null || item.getCave().getDepth() == (filter.getCaveDepth()))
        //         && (filter.getCaveNumberOfTreasures() == null
        //                 || item.getCave().getNumberOfTreasures() == (filter.getCaveNumberOfTreasures()));
        

        collection = collection.stream().filter(predicate).collect(Collectors.toList());
        return collection;
    }

    @Override
    public long countTypeLessThan(Collection<Dragon> collection, DragonType type) {
        return collection.stream().filter(item -> item.getType().compareTo(type)<0).count();
    }

    @Override
    public long countCharacterMoreThan(Collection<Dragon> collection, DragonCharacter character) {
        return collection.stream().filter(item -> item.getCharacter().compareTo(character)>0).count();
    }

    @Override
    public Collection<Dragon> nameStartsWith(Collection<Dragon> collection, String substr) {
        return collection.stream().filter(item -> item.getName().startsWith(substr)).collect(Collectors.toList());
    }

    @Override
    public Collection<Dragon> getPage(Collection<Dragon> collection, long offset, long limit) {
        return collection.stream().skip(offset).limit(limit).collect(Collectors.toList());
    }

    @Override
    public Collection<Dragon> getSorted(Collection<Dragon> collection, String[] sortvalues) {
        Comparator<Dragon> comparator = Comparator.comparing(a -> -1);
        for (String val : sortvalues) {
            int sign = val.charAt(0) == '-' ? -1 : 1;
            String field = val.substring(1).toLowerCase();
            switch (field) {
                case "id": {
                    comparator = comparator.thenComparing(Dragon::getId, (o1, o2) -> o1.compareTo(o2)*sign);
                    break;
                }
                case "name": {
                    comparator = comparator.thenComparing(Dragon::getName, (o1, o2) -> o1.compareTo(o2)*sign);
                    break;
                }
                case "x": {
                    comparator = comparator.thenComparing(Dragon::getCoordinates, (o1, o2) -> (int)(o1.getX()-o2.getX())*sign);
                    break;
                }
                case "y": {
                    comparator = comparator.thenComparing(Dragon::getCoordinates, (o1, o2) -> (int)(o1.getY()-o2.getY())*sign);
                    break;
                }
                case "creationdate": {
                    comparator = comparator.thenComparing(Dragon::getCreationDate, (o1, o2) -> o1.compareTo(o2)*sign);
                    break;
                }
                case "age": {
                    comparator = comparator.thenComparing(Dragon::getAge, (o1, o2) -> o1.compareTo(o2)*sign);
                    break;
                }
                case "weight": {
                    comparator = comparator.thenComparing(Dragon::getWeight, (o1, o2) -> o1.compareTo(o2)*sign);
                    break;
                }
                case "type": {
                    comparator = comparator.thenComparing(Dragon::getType, (o1, o2) -> o1.compareTo(o2)*sign);
                    break;
                }
                case "character": {
                    comparator = comparator.thenComparing(Dragon::getCharacter, (o1, o2) -> o1.compareTo(o2)*sign);
                    break;
                }
                case "cavedepth": {
                    comparator = comparator.thenComparing(Dragon::getCave, (o1, o2) -> (int)(o1.getDepth()-o2.getDepth())*sign);
                    break;
                }
                case "cavenumberoftreasures": {
                    comparator = comparator.thenComparing(Dragon::getCave, (o1, o2) -> (int)(o1.getNumberOfTreasures()-o2.getNumberOfTreasures())*sign);
                    break;
                }
            }
        }

        collection = collection.stream().sorted(comparator).collect(Collectors.toList());
        return collection;
    }

}
