package com.itmoclouddev.labf.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itmoclouddev.labf.filter.DragonFilter;
import com.itmoclouddev.labf.model.Dragon;
import com.itmoclouddev.labf.model.DragonCharacter;
import com.itmoclouddev.labf.model.DragonType;
import com.itmoclouddev.labf.service.DragonCaveService;
import com.itmoclouddev.labf.service.DragonCaveServiceImpl;

@RestController
@RequestMapping("/dragonscaves")
public class DragonAndCaveController {

    private DragonCaveService dragonCaveService;

    public DragonAndCaveController() {
        super();

        dragonCaveService = new DragonCaveServiceImpl();
    }

    @GetMapping("/count")
    public Object getCount(DragonFilter filter) {

        Collection<Dragon> dragons = dragonCaveService.getAll();

        if (!(filter == null)) {
            dragons = dragonCaveService.getFiltered(dragons, filter);
        }

        final long val = (long)dragons.size();

        return new Object() {
            public final long value = val;
        };
    }

    @GetMapping
    public Collection<Dragon> getAll(@RequestParam(name = "offset", defaultValue = "0") Long offset,
            @RequestParam(name = "limit", defaultValue = "-1") Long limit,
            @RequestParam(name = "sort", required = false) String[] sortvalues,
            DragonFilter filter) {

        Collection<Dragon> dragons = dragonCaveService.getAll();
        if (!(filter == null)) {
            dragons = dragonCaveService.getFiltered(dragons, filter);
        }
        if (!(sortvalues == null)) {
            dragons = dragonCaveService.getSorted(dragons, sortvalues);
        }
        if (limit == -1) {
            limit = (long)dragons.size();
        }
        return dragonCaveService.getPage(dragons, offset, limit);
    }

    @GetMapping("/{id}")
    public Dragon getDragon(@PathVariable("id") long id) {
        return dragonCaveService.get(id);
    }

    @PostMapping
    public ResponseEntity<String> addDragon(@RequestBody Dragon dragon) {
        long id = dragonCaveService.add(dragon);
        if (id > 0) {
            URI uri = URI.create("/dragons/" + id);
            return ResponseEntity.accepted().location(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dragon> deleteDragon(@PathVariable("id") long id) {
        Dragon deleted = dragonCaveService.delete(id);
        if (deleted != null) { // if there was dragon with such id
            return ResponseEntity.ok().body(deleted);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putDragon(@PathVariable("id") long id, @RequestBody Dragon dragon) {
        Dragon currentDragon = dragonCaveService.get(id);
        if (currentDragon == null) { // dragon not found
            return ResponseEntity.notFound().build();
        }
        // dragon exists -> update it
        dragonCaveService.update(id, dragon);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/typeless")
    public Object getTypeLessThan(@RequestParam(name = "type", required = true) DragonType type) {
        return new Object(){
            public final long value = dragonCaveService.countTypeLessThan(dragonCaveService.getAll(), type);
        };
    }

    @GetMapping("/charactermore")
    public Object getCharacterLessThan(@RequestParam(name = "character", required = true) DragonCharacter character) {
        return new Object(){
            public final long value = dragonCaveService.countCharacterMoreThan(dragonCaveService.getAll(), character);
        };
    }

    @GetMapping("/namestarts")
    public Collection<Dragon> getNameStartsWith(@RequestParam(name = "name", required = true) String name) {
        return dragonCaveService.nameStartsWith(dragonCaveService.getAll(), name);
    }

}