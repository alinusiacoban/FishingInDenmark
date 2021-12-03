package com.aliniacoban.fishingindenmark.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliniacoban.fishingindenmark.R;
import com.aliniacoban.fishingindenmark.model.Fish;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FishAdapter extends RecyclerView.Adapter<FishAdapter.ViewHolder> {

    private List<Fish> fishes;
    private FishAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public FishAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycleview_notif_item, parent, false);
        return new FishAdapter.ViewHolder(view, listener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (fishes != null && position <= fishes.size()) {
            holder.fishType.setText(fishes.get(position).getFishType() + " ");
            holder.fishWeight.setText(fishes.get(position).getFishWeight() + " ");
            holder.fishLength.setText(fishes.get(position).getFishLength() + " ");
            switch (fishes.get(position).getFishType()) {
                case "Salmon":
                    holder.imageFish.setBackgroundResource(R.drawable.salmon);
                    break;
                case "Pike":
                    holder.imageFish.setBackgroundResource(R.drawable.pike);
                    break;
                case "Perch":
                    holder.imageFish.setBackgroundResource(R.drawable.perch);
                    break;
                case "Carp":
                    holder.imageFish.setBackgroundResource(R.drawable.carp);
                    break;
                case "Eel":
                    holder.imageFish.setBackgroundResource(R.drawable.eel);
                    break;
                case "Trout":
                    holder.imageFish.setBackgroundResource(R.drawable.trout);
                    break;
                case "Zander":
                    holder.imageFish.setBackgroundResource(R.drawable.zander);
                    break;
                case "Shark":
                    holder.imageFish.setBackgroundResource(R.drawable.shark);
                    break;
                case "Catfish":
                    holder.imageFish.setBackgroundResource(R.drawable.catfish);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (fishes == null) {
            fishes = new ArrayList<>();
            return 0;
        }
        return fishes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView fishType;
        private final TextView fishWeight;
        private final TextView fishLength;
        private final ImageView imageFish;

        ViewHolder(@NonNull View itemView, FishAdapter.OnItemClickListener listener) {
            super(itemView);
            fishType = itemView.findViewById(R.id.textViewFishType1);
            fishWeight = itemView.findViewById(R.id.textViewFishWeight1);
            fishLength = itemView.findViewById(R.id.text1);
            imageFish = itemView.findViewById(R.id.imageViewFish);
        }

    }

    public interface OnItemClickListener {
        void onRemoveClickListener(int position) throws ExecutionException, InterruptedException;
    }

    public void setOnClickListener(FishAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public FishAdapter(List<Fish> fishes) {
        this.fishes = new ArrayList<>();
        this.fishes = fishes;
    }
}


