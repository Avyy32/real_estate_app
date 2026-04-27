package com.example.realestateapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private final List<Project> projects;
    private final Context context;

    public ProjectAdapter(Context context, List<Project> projects) {
        this.context = context;
        this.projects = projects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_launch_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.tvName.setText(project.getName());
        holder.tvLocation.setText(project.getLocation());
        holder.tvPrice.setText(project.getPriceRange());

        holder.btnViewNumber.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShareDetailsActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation, tvPrice;
        Button btnViewNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvProjectName);
            tvLocation = itemView.findViewById(R.id.tvProjectLocation);
            tvPrice = itemView.findViewById(R.id.tvProjectPriceRange);
            btnViewNumber = itemView.findViewById(R.id.btnViewNumber);
        }
    }
}