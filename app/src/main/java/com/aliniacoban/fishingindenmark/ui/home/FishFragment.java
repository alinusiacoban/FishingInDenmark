package com.aliniacoban.fishingindenmark.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aliniacoban.fishingindenmark.Adapters.FishAdapter;
import com.aliniacoban.fishingindenmark.R;

import java.util.concurrent.ExecutionException;

public class FishFragment extends Fragment {

    private FishesViewModel fishesViewModel;

    private RecyclerView recyclerView;
    private FishAdapter fishAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fishesViewModel = new ViewModelProvider(this).get(FishesViewModel.class);
        try {
            fishesViewModel.getFishes().observe(this, fishes -> {

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                fishAdapter = new FishAdapter(fishes);
                recyclerView.setAdapter(fishAdapter);

                fishAdapter.setOnClickListener(position ->
                {
                    fishesViewModel.removeFish(position);
                    Toast.makeText(getContext(), "Fish removed", Toast.LENGTH_SHORT).show();
                    fishAdapter.notifyDataSetChanged();
                });

            });
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fish_fragment, container, false);

        recyclerView = view.findViewById(R.id.rvFish);

        return view;
    }

}