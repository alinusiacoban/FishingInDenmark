package com.aliniacoban.fishingindenmark.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aliniacoban.fishingindenmark.Repositories.FishRepository;
import com.aliniacoban.fishingindenmark.model.Fish;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FishesViewModel extends AndroidViewModel {

    private final FishRepository fishRepository;

    public FishesViewModel(Application application) {
        super(application);
        fishRepository = FishRepository.getInstance(application);
    }

    public LiveData<List<Fish>> getFishes() throws ExecutionException, InterruptedException {
        return fishRepository.getFishes();
    }

    public void removeFish(int pos) throws ExecutionException, InterruptedException {
        fishRepository.removeFish(pos);
    }

}