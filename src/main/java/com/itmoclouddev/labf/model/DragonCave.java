package com.itmoclouddev.labf.model;

import com.itmoclouddev.labf.exception.InvalidValueException;

public class DragonCave {
    private Long caveId;

    private float depth; //Поле не может быть null
    private double numberOfTreasures; //Значение поля должно быть больше 0
    
    public DragonCave(){
        
    }

    public DragonCave(float depth, double numberOfTreasures) throws InvalidValueException {
        this.setDepth(depth);
        this.setNumberOfTreasures(numberOfTreasures);
    }

    public DragonCave(Long id, float depth, double numberOfTreasures) throws InvalidValueException {
        this(depth, numberOfTreasures);
        setCaveId(id);
    }

    public double getNumberOfTreasures() {
        return numberOfTreasures;
    }

    public void setNumberOfTreasures(double numberOfTreasures) throws InvalidValueException {
        if (numberOfTreasures <=0) {
            throw new InvalidValueException("Field 'numberOfTreasures' should be positive");
        }
        this.numberOfTreasures = numberOfTreasures;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public Long getCaveId() {
        return caveId;
    }

    public void setCaveId(Long cave_id) {
        this.caveId = cave_id;
    }
}
