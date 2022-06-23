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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itmoclouddev.labf.filter.DragonFilter;
import com.itmoclouddev.labf.model.DragonCave;
import com.itmoclouddev.labf.model.DragonCharacter;
import com.itmoclouddev.labf.model.DragonType;
import com.itmoclouddev.labf.service.CaveService;
import com.itmoclouddev.labf.service.CaveServiceImpl;
import com.itmoclouddev.labf.service.DragonCaveService;
import com.itmoclouddev.labf.service.DragonCaveServiceImpl;

@RestController
@RequestMapping("/caves")
public class CaveController {

    private CaveService caveService;

    public CaveController() {
        super();

        caveService = new CaveServiceImpl();
    }

    @GetMapping
    public Collection<DragonCave> getAll(@RequestParam(name = "offset", defaultValue = "0") Long offset,
            @RequestParam(name = "limit", defaultValue = "10") Long limit,
            @RequestParam(name = "sort", required = false) String[] sortvalues,
            DragonFilter filter) {

        Collection<DragonCave> dragons = caveService.getAll();
        if (!(filter == null)) {
            dragons = caveService.getFiltered(dragons, filter);
        }
        if (!(sortvalues == null)) {
            dragons = caveService.getSorted(dragons, sortvalues);
        }
        return caveService.getPage(dragons, offset, limit);
    }

    @GetMapping("/test")
    public String getTestMessage() {
        return "This is test message from dragons";
    }

    @GetMapping("/{id}")
    public DragonCave getDragon(@PathVariable("id") long id) {
        return caveService.get(id);
    }

    @PostMapping
    public ResponseEntity<String> addDragon(@RequestBody DragonCave dragon) {
        long id = caveService.add(dragon);
        if (id > 0) {
            URI uri = URI.create("/dragons/" + id);
            // System.out.println(uri.toString());
            return ResponseEntity.accepted().location(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DragonCave> deleteDragon(@PathVariable("id") long id) {
        DragonCave deleted = caveService.delete(id);
        if (deleted != null) { // if there was dragon with such id
            return ResponseEntity.ok().body(deleted);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putDragon(@PathVariable("id") long id, @RequestBody DragonCave dragon) {
        DragonCave currentDragon = caveService.get(id);
        if (currentDragon == null) { // dragon not found
            return ResponseEntity.notFound().build();
        }
        // dragon exists -> update it
        caveService.update(id, dragon);
        return ResponseEntity.noContent().build();
    }

}
