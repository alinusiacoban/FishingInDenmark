package com.aliniacoban.fishingindenmark.ui.achievement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aliniacoban.fishingindenmark.R;
import com.aliniacoban.fishingindenmark.model.Fish;

import java.util.concurrent.ExecutionException;

public class CreateFishFragment extends Fragment {

    private CreateFishViewModel createFishViewModel;
    private Button createBtn;
    private EditText fishType;
    private EditText fish_weight;
    private EditText fish_length;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createFishViewModel = new ViewModelProvider(this).get(CreateFishViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fish_creator_fragment, container, false);
        fishType = view.findViewById(R.id.editTextFishType);
        fish_weight = view.findViewById(R.id.editTextFishWeight);
        fish_length = view.findViewById(R.id.editTextFishLength);
        createBtn = view.findViewById(R.id.buttonCreateFish);

        createBtn.setOnClickListener(v -> {
            String fish_type = " ";
            String fish_weight = " ";
            String fish_length = " ";


            fish_type = this.fishType.getText().toString();
            fish_weight = this.fish_weight.getText().toString();
            fish_length = this.fish_length.getText().toString();


            try {
                createFishViewModel.insertCharacter(new Fish(
                        fish_type,
                        fish_length,
                        fish_weight

                ));
                Toast.makeText(getContext(), "Achievement Created", Toast.LENGTH_SHORT).show();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        return view;

    }
}
