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

public class DragonCaveServiceImpl implements DragonCaveService {

    private Dao dragonDao;

    public DragonCaveServiceImpl() {
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
            Dragon res = dragonDao.getDragonAndCave(id);
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
            long id = dragonDao.addDragonAndCave(dragon);
            return id;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @Override
    public void update(long id, Dragon dragon) {
        try {
            dragonDao.updateDragonWithCave(id, dragon);
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
            Dragon res = dragonDao.deleteDragonAndCave(id);
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
            Collection<Dragon> res = dragonDao.getAllDragonsAndCaves();
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

        if (oFilter.map(DragonFilter::getAge).isPresent() && oFilter.map(DragonFilter::getOp_age).isPresent()) {
            switch (filter.getOp_age()) {
                case eq:
                    predicate = predicate.and(item -> item.getAge().compareTo(filter.getAge()) == 0);
                    break;
                case ge:
                    predicate = predicate.and(item -> item.getAge().compareTo(filter.getAge()) >= 0);
                    break;
                case gt:
                    predicate = predicate.and(item -> item.getAge().compareTo(filter.getAge()) > 0);
                    break;
                case le:
                    predicate = predicate.and(item -> item.getAge().compareTo(filter.getAge()) <= 0);
                    break;
                case lt:
                    predicate = predicate.and(item -> item.getAge().compareTo(filter.getAge()) < 0);
                    break;
                case ne:
                    predicate = predicate.and(item -> item.getAge().compareTo(filter.getAge()) != 0);
                    break;
            }
        }
        if (oFilter.map(DragonFilter::getCharacter).isPresent()
                && oFilter.map(DragonFilter::getOp_character).isPresent()) {
            switch (filter.getOp_character()) {
                case eq:
                    predicate = predicate.and(item -> item.getCharacter().compareTo(filter.getCharacter()) == 0);
                    break;
                case ge:
                    predicate = predicate.and(item -> item.getCharacter().compareTo(filter.getCharacter()) >= 0);
                    break;
                case gt:
                    predicate = predicate.and(item -> item.getCharacter().compareTo(filter.getCharacter()) > 0);
                    break;
                case le:
                    predicate = predicate.and(item -> item.getCharacter().compareTo(filter.getCharacter()) <= 0);
                    break;
                case lt:
                    predicate = predicate.and(item -> item.getCharacter().compareTo(filter.getCharacter()) < 0);
                    break;
                case ne:
                    predicate = predicate.and(item -> item.getCharacter().compareTo(filter.getCharacter()) != 0);
                    break;
            }
        }
        if (oFilter.map(DragonFilter::getCreationDate).isPresent()
                && oFilter.map(DragonFilter::getOp_creationDate).isPresent()) {
            switch (filter.getOp_creationDate()) {
                case eq:
                    predicate = predicate.and(item -> item.getCreationDate().compareTo(filter.getCreationDate()) == 0);
                    break;
                case ge:
                    predicate = predicate.and(item -> item.getCreationDate().compareTo(filter.getCreationDate()) >= 0);
                    break;
                case gt:
                    predicate = predicate.and(item -> item.getCreationDate().compareTo(filter.getCreationDate()) > 0);
                    break;
                case le:
                    predicate = predicate.and(item -> item.getCreationDate().compareTo(filter.getCreationDate()) <= 0);
                    break;
                case lt:
                    predicate = predicate.and(item -> item.getCreationDate().compareTo(filter.getCreationDate()) < 0);
                    break;
                case ne:
                    predicate = predicate.and(item -> item.getCreationDate().compareTo(filter.getCreationDate()) != 0);
                    break;
            }
        }
        if (oFilter.map(DragonFilter::getId).isPresent()
                && oFilter.map(DragonFilter::getOp_id).isPresent()) {
            switch (filter.getOp_id()) {
                case eq:
                    predicate = predicate.and(item -> item.getId().compareTo(filter.getId()) == 0);
                    break;
                case ge:
                    predicate = predicate.and(item -> item.getId().compareTo(filter.getId()) >= 0);
                    break;
                case gt:
                    predicate = predicate.and(item -> item.getId().compareTo(filter.getId()) > 0);
                    break;
                case le:
                    predicate = predicate.and(item -> item.getId().compareTo(filter.getId()) <= 0);
                    break;
                case lt:
                    predicate = predicate.and(item -> item.getId().compareTo(filter.getId()) < 0);
                    break;
                case ne:
                    predicate = predicate.and(item -> item.getId().compareTo(filter.getId()) != 0);
                    break;
            }
        }
        if (oFilter.map(DragonFilter::getName).isPresent()
                && oFilter.map(DragonFilter::getOp_name).isPresent()) {
            switch (filter.getOp_name()) {
                case eq:
                    // predicate = predicate.and(item -> item.getName().compareTo(filter.getName()) == 0);
                    predicate = predicate.and(item -> item.getName().contains(filter.getName()));
                    break;
                case ge:
                    predicate = predicate.and(item -> item.getName().compareTo(filter.getName()) >= 0);
                    break;
                case gt:
                    predicate = predicate.and(item -> item.getName().compareTo(filter.getName()) > 0);
                    break;
                case le:
                    predicate = predicate.and(item -> item.getName().compareTo(filter.getName()) <= 0);
                    break;
                case lt:
                    predicate = predicate.and(item -> item.getName().compareTo(filter.getName()) < 0);
                    break;
                case ne:
                    predicate = predicate.and(item -> item.getName().compareTo(filter.getName()) != 0);
                    break;
            }
        }
        if (oFilter.map(DragonFilter::getType).isPresent()
                && oFilter.map(DragonFilter::getOp_type).isPresent()) {
            switch (filter.getOp_type()) {
                case eq:
                    predicate = predicate.and(item -> item.getType().compareTo(filter.getType()) == 0);
                    break;
                case ge:
                    predicate = predicate.and(item -> item.getType().compareTo(filter.getType()) >= 0);
                    break;
                case gt:
                    predicate = predicate.and(item -> item.getType().compareTo(filter.getType()) > 0);
                    break;
                case le:
                    predicate = predicate.and(item -> item.getType().compareTo(filter.getType()) <= 0);
                    break;
                case lt:
                    predicate = predicate.and(item -> item.getType().compareTo(filter.getType()) < 0);
                    break;
                case ne:
                    predicate = predicate.and(item -> item.getType().compareTo(filter.getType()) != 0);
                    break;
            }
        }
        if (oFilter.map(DragonFilter::getWeight).isPresent()
                && oFilter.map(DragonFilter::getOp_weight).isPresent()) {
            switch (filter.getOp_weight()) {
                case eq:
                    predicate = predicate.and(item -> item.getWeight().compareTo(filter.getWeight()) == 0);
                    break;
                case ge:
                    predicate = predicate.and(item -> item.getWeight().compareTo(filter.getWeight()) >= 0);
                    break;
                case gt:
                    predicate = predicate.and(item -> item.getWeight().compareTo(filter.getWeight()) > 0);
                    break;
                case le:
                    predicate = predicate.and(item -> item.getWeight().compareTo(filter.getWeight()) <= 0);
                    break;
                case lt:
                    predicate = predicate.and(item -> item.getWeight().compareTo(filter.getWeight()) < 0);
                    break;
                case ne:
                    predicate = predicate.and(item -> item.getWeight().compareTo(filter.getWeight()) != 0);
                    break;
            }
        }
        if (oFilter.map(DragonFilter::getCoordinateX).isPresent()
                && oFilter.map(DragonFilter::getOp_coordinateX).isPresent()) {
            switch (filter.getOp_coordinateX()) {
                case eq:
                    predicate = predicate.and(item -> item.getCoordinates().getX() == filter.getCoordinateX());
                    break;
                case ge:
                    predicate = predicate.and(item -> item.getCoordinates().getX() >= filter.getCoordinateX());
                    break;
                case gt:
                    predicate = predicate.and(item -> item.getCoordinates().getX() > filter.getCoordinateX());
                    break;
                case le:
                    predicate = predicate.and(item -> item.getCoordinates().getX() <= filter.getCoordinateX());
                    break;
                case lt:
                    predicate = predicate.and(item -> item.getCoordinates().getX() < filter.getCoordinateX());
                    break;
                case ne:
                    predicate = predicate.and(item -> item.getCoordinates().getX() != filter.getCoordinateX());
                    break;
            }
        }
        if (oFilter.map(DragonFilter::getCoordinateY).isPresent()
                && oFilter.map(DragonFilter::getOp_coordinateY).isPresent()) {
            switch (filter.getOp_coordinateY()) {
                case eq:
                    predicate = predicate.and(item -> item.getCoordinates().getY() == filter.getCoordinateY());
                    break;
                case ge:
                    predicate = predicate.and(item -> item.getCoordinates().getY() >= filter.getCoordinateY());
                    break;
                case gt:
                    predicate = predicate.and(item -> item.getCoordinates().getY() > filter.getCoordinateY());
                    break;
                case le:
                    predicate = predicate.and(item -> item.getCoordinates().getY() <= filter.getCoordinateY());
                    break;
                case lt:
                    predicate = predicate.and(item -> item.getCoordinates().getY() < filter.getCoordinateY());
                    break;
                case ne:
                    predicate = predicate.and(item -> item.getCoordinates().getY() != filter.getCoordinateY());
                    break;
            }
        }
        if (oFilter.map(DragonFilter::getCaveDepth).isPresent()
                && oFilter.map(DragonFilter::getOp_caveDepth).isPresent()) {
            switch (filter.getOp_caveDepth()) {
                case eq:
                    predicate = predicate.and(item -> item.getCave().getDepth() == filter.getCaveDepth());
                    break;
                case ge:
                    predicate = predicate.and(item -> item.getCave().getDepth() >= filter.getCaveDepth());
                    break;
                case gt:
                    predicate = predicate.and(item -> item.getCave().getDepth() > filter.getCaveDepth());
                    break;
                case le:
                    predicate = predicate.and(item -> item.getCave().getDepth() <= filter.getCaveDepth());
                    break;
                case lt:
                    predicate = predicate.and(item -> item.getCave().getDepth() < filter.getCaveDepth());
                    break;
                case ne:
                    predicate = predicate.and(item -> item.getCave().getDepth() != filter.getCaveDepth());
                    break;
            }
        }
        if (oFilter.map(DragonFilter::getCaveNumberOfTreasures).isPresent()
                && oFilter.map(DragonFilter::getOp_caveNumberOfTreasures).isPresent()) {
            switch (filter.getOp_caveNumberOfTreasures()) {
                case eq:
                    predicate = predicate.and(item -> item.getCave().getNumberOfTreasures() == filter.getCaveNumberOfTreasures());
                    break;
                case ge:
                    predicate = predicate.and(item -> item.getCave().getNumberOfTreasures() >= filter.getCaveNumberOfTreasures());
                    break;
                case gt:
                    predicate = predicate.and(item -> item.getCave().getNumberOfTreasures() > filter.getCaveNumberOfTreasures());
                    break;
                case le:
                    predicate = predicate.and(item -> item.getCave().getNumberOfTreasures() <= filter.getCaveNumberOfTreasures());
                    break;
                case lt:
                    predicate = predicate.and(item -> item.getCave().getNumberOfTreasures() < filter.getCaveNumberOfTreasures());
                    break;
                case ne:
                    predicate = predicate.and(item -> item.getCave().getNumberOfTreasures() != filter.getCaveNumberOfTreasures());
                    break;
            }
        }

        collection = collection.stream().filter(predicate).collect(Collectors.toList());
        return collection;
    }

    @Override
    public long countTypeLessThan(Collection<Dragon> collection, DragonType type) {
        return collection.stream().filter(item -> item.getType().compareTo(type) < 0).count();
    }

    @Override
    public long countCharacterMoreThan(Collection<Dragon> collection, DragonCharacter character) {
        return collection.stream().filter(item -> item.getCharacter().compareTo(character) > 0).count();
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
                    comparator = comparator.thenComparing(Dragon::getId, (o1, o2) -> o1.compareTo(o2) * sign);
                    break;
                }
                case "name": {
                    comparator = comparator.thenComparing(Dragon::getName, (o1, o2) -> o1.compareTo(o2) * sign);
                    break;
                }
                case "coordinatex": {
                    comparator = comparator.thenComparing(Dragon::getCoordinates,
                            (o1, o2) -> Float.compare(o1.getX(), o2.getX()) * sign);
                    break;
                }
                case "coordinatey": {
                    comparator = comparator.thenComparing(Dragon::getCoordinates,
                            (o1, o2) -> Float.compare(o1.getY(), o2.getY()) * sign);
                    break;
                }
                case "creationdate": {
                    comparator = comparator.thenComparing(Dragon::getCreationDate, (o1, o2) -> o1.compareTo(o2) * sign);
                    break;
                }
                case "age": {
                    comparator = comparator.thenComparing(Dragon::getAge, (o1, o2) -> o1.compareTo(o2) * sign);
                    break;
                }
                case "weight": {
                    comparator = comparator.thenComparing(Dragon::getWeight, (o1, o2) -> o1.compareTo(o2) * sign);
                    break;
                }
                case "type": {
                    comparator = comparator.thenComparing(Dragon::getType, (o1, o2) -> o1.compareTo(o2) * sign);
                    break;
                }
                case "character": {
                    comparator = comparator.thenComparing(Dragon::getCharacter, (o1, o2) -> o1.compareTo(o2) * sign);
                    break;
                }
                case "cavedepth": {
                    comparator = comparator.thenComparing(Dragon::getCave,
                            (o1, o2) -> Float.compare(o1.getDepth(), o2.getDepth()) * sign);
                    break;
                }
                case "cavenumberoftreasures": {
                    comparator = comparator.thenComparing(Dragon::getCave,
                            (o1, o2) -> Double.compare(o1.getNumberOfTreasures(), o2.getNumberOfTreasures()) * sign);
                    break;
                }
                case "caveid": {
                    comparator = comparator.thenComparing(Dragon::getCave,
                            (o1, o2) -> (int) (o1.getCaveId() - o2.getCaveId()) * sign);
                    break;
                }
            }
        }

        collection = collection.stream().sorted(comparator).collect(Collectors.toList());
        return collection;
    }

}