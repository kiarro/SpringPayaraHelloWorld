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
import com.itmoclouddev.labf.model.DragonCave;
import com.itmoclouddev.labf.model.DragonCharacter;
import com.itmoclouddev.labf.model.DragonType;

public class CaveServiceImpl implements CaveService {

    private Dao dragonDao;

    public CaveServiceImpl() {
        try {
            this.dragonDao = new DaoImpl();
        } catch (DaoException e) {
            e.printStackTrace();

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error in dao creating:\n", e.getMessage()));
        }
    }

    @Override
    public DragonCave get(long id) {
        try {
            DragonCave res = dragonDao.getCave(id);
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
    public long add(DragonCave cave) {
        try {
            long id = dragonDao.addCave(cave);
            return id;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @Override
    public void update(long id, DragonCave cave) {
        try {
            dragonDao.updateCave(id, cave);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public DragonCave delete(long id) {
        try {
            DragonCave res = dragonDao.deleteCave(id);
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
    public Collection<DragonCave> getAll() {
        try {
            Collection<DragonCave> res = dragonDao.getAllCaves();
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
    public Collection<DragonCave> getFiltered(Collection<DragonCave> collection, DragonFilter filter) {
        Predicate<DragonCave> predicate = item -> true;
        
        Optional<DragonFilter> oFilter = Optional.ofNullable(filter);
        if (!oFilter.isPresent()) {
            return collection;
        }

        if (oFilter.map(DragonFilter::getCaveDepth).isPresent()){
            predicate.and(item -> item.getDepth() == oFilter.map(DragonFilter::getCaveDepth).get());
        }
        if (oFilter.map(DragonFilter::getCaveNumberOfTreasures).isPresent()){
            predicate.and(item -> item.getNumberOfTreasures() == oFilter.map(DragonFilter::getCaveNumberOfTreasures).get());
        }
        if (oFilter.map(DragonFilter::getCaveId).isPresent()){
            predicate.and(item -> item.getCaveId() == oFilter.map(DragonFilter::getCaveId).get());
        }

        collection = collection.stream().filter(predicate).collect(Collectors.toList());
        return collection;
    }

    @Override
    public Collection<DragonCave> getPage(Collection<DragonCave> collection, long offset, long limit) {
        return collection.stream().skip(offset).limit(limit).collect(Collectors.toList());
    }

    @Override
    public Collection<DragonCave> getSorted(Collection<DragonCave> collection, String[] sortvalues) {
        Comparator<DragonCave> comparator = Comparator.comparing(a -> -1);
        for (String val : sortvalues) {
            int sign = val.charAt(0) == '-' ? -1 : 1;
            String field = val.substring(1).toLowerCase();
            switch (field) {
                case "cavedepth": {
                    comparator = comparator.thenComparing(DragonCave::getDepth, (o1, o2) -> o1.compareTo(o2)*sign);
                    break;
                }
                case "cavenumberoftreasures": {
                    comparator = comparator.thenComparing(DragonCave::getNumberOfTreasures, (o1, o2) -> o1.compareTo(o2)*sign);
                    break;
                }
                case "caveid": {
                    comparator = comparator.thenComparing(DragonCave::getCaveId, (o1, o2) -> o1.compareTo(o2)*sign);
                    break;
                }
            }
        }

        collection = collection.stream().sorted(comparator).collect(Collectors.toList());
        return collection;
    }

}
