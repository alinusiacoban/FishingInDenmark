package com.aliniacoban.fishingindenmark.model.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.aliniacoban.fishingindenmark.model.Fish;

@Database(entities = {Fish.class}, version = 6, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract AppDao appDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "LocalDatabase").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
