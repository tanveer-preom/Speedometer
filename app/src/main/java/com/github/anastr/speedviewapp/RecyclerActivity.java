package com.github.anastr.speedviewapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.anastr.speedviewlib.Speedometer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        List<Integer> speeds = new ArrayList<>();
        for (int i=0; i<100; i++)
            speeds.add(new Random().nextInt(99)+1);
        recyclerView.setAdapter(new RVAdapter(speeds));
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {

        List<Integer> speeds;

        RVAdapter(List<Integer> speeds) {
            this.speeds = speeds;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            // set speed at 0 without animation (to start from this position).
            holder.speedometer.setSpeedAt(0);
            holder.speedometer.speedTo(speeds.get(position));
        }

        @Override
        public int getItemCount() {
            return speeds.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            Speedometer speedometer;

            MyViewHolder(View itemView) {
                super(itemView);
                speedometer = itemView.findViewById(R.id.speedometer);
            }
        }
    }
}
