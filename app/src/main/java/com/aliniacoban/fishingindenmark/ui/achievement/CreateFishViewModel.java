package com.aliniacoban.fishingindenmark.ui.achievement;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.aliniacoban.fishingindenmark.Repositories.FishRepository;
import com.aliniacoban.fishingindenmark.model.Fish;

import java.util.concurrent.ExecutionException;

public class CreateFishViewModel extends AndroidViewModel {

    private final FishRepository fishRepository;

    public CreateFishViewModel(Application application) {
        super(application);
        fishRepository = FishRepository.getInstance(application);
    }

    public void insertCharacter(Fish fish) throws ExecutionException, InterruptedException {
        fishRepository.insert(fish);
    }
}