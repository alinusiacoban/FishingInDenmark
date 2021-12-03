package com.aliniacoban.fishingindenmark.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "fish_table")

public class Fish {

    @PrimaryKey(autoGenerate = true)

    private int fishId;
    private String fishType;
    private String fishWeight;
    private String fishLength;


    public Fish(int fishId, String fishType, String fishWeight, String fishLength) {
        this.fishId = fishId;
        this.fishType = fishType;
        this.fishWeight = fishWeight;
        this.fishLength = fishLength;
    }

    @Ignore
    public Fish() {
    }

    @Ignore
    public Fish(String fishType, String fishWeight, String fishLength) {
        this.fishType = fishType;
        this.fishWeight = fishWeight;
        this.fishLength = fishLength;
    }


    public int getFishId() {
        return fishId;
    }

    public void setFishId(int fishId) {
        this.fishId = fishId;
    }

    public String getFishType() {
        return fishType;
    }

    public void setFishType(String fishType) {
        this.fishType = fishType;
    }

    public String getFishWeight() {
        return fishWeight;
    }

    public void setFishWeight(String fishWeight) {
        this.fishWeight = fishWeight;
    }

    public String getFishLength() {
        return fishLength;
    }

    public void setFishLength(String fishLength) {
        this.fishLength = fishLength;
    }
}
