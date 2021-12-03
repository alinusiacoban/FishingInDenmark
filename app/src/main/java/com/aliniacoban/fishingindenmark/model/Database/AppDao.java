package com.aliniacoban.fishingindenmark.model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aliniacoban.fishingindenmark.model.Fish;

import java.util.List;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setFish(Fish fish);

    @Insert
    void insertFish(Fish fish);

    @Query("SELECT * FROM fish_table")
    List<Fish> getFishes();

    @Delete
    void removeFish(Fish fish);
}
