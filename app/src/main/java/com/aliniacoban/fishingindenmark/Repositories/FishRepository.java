package com.aliniacoban.fishingindenmark.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aliniacoban.fishingindenmark.model.Database.AppDao;
import com.aliniacoban.fishingindenmark.model.Database.AppDatabase;
import com.aliniacoban.fishingindenmark.model.Fish;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FishRepository {
    private final AppDao dao;
    private static FishRepository instance;

    private final MutableLiveData<List<Fish>> fishes;

    public FishRepository(Application app) {
        AppDatabase appDatabase = AppDatabase.getInstance(app);
        dao = appDatabase.appDao();
        fishes = new MutableLiveData<>();
    }

    public static synchronized FishRepository getInstance(Application app) {
        if (instance == null) {
            instance = new FishRepository(app);
        }
        return instance;
    }

    public void insert(Fish fish) throws ExecutionException, InterruptedException {
        new FishRepository.InsertAsyncTask(dao).execute(fish);

        List<Fish> fishes = getFishesFromDB();
        this.fishes.postValue(fishes);
    }

    public List<Fish> getFishesFromDB() throws ExecutionException, InterruptedException {
        return new GetFishes(dao).execute().get();
    }

    public LiveData<List<Fish>> getFishes() {
        return fishes;
    }

    public void removeFish(int pos) throws ExecutionException, InterruptedException {
        List<Fish> list = getFishesFromDB();
        Fish temp = list.get(pos);
        new RemoveItemAsync(dao).execute(temp);
    }

    public static class RemoveItemAsync extends AsyncTask<Fish, Void, Void> {
        private final AppDao dao;

        private RemoveItemAsync(AppDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Fish... fishes) {
            dao.removeFish(fishes[0]);
            return null;
        }
    }


    public static class GetFishes extends AsyncTask<Void, Void, List<Fish>> {

        private final AppDao dao;

        private GetFishes(AppDao dao) {
            this.dao = dao;
        }

        @Override
        protected List<Fish> doInBackground(Void... voids) {
            return dao.getFishes();
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Fish, Void, Void> {

        private final AppDao dao;

        private InsertAsyncTask(AppDao appDao) {
            this.dao = appDao;
        }


        @Override
        protected Void doInBackground(Fish... fishes) {
            dao.insertFish(fishes[0]);
            return null;
        }
    }
}
