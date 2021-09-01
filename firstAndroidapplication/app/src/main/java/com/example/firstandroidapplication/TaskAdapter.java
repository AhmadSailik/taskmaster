package com.example.firstandroidapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    List<Task>allTask=new ArrayList<Task>();
    public TaskAdapter(List<Task>allTask){

        this.allTask=allTask;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        public Task task;
        View itemView;

        public TaskViewHolder(@NonNull  View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewHolder taskViewHolder=new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        holder.task=allTask.get(position);

        TextView titleOfTask=holder.itemView.findViewById(R.id.titleOfTask);
        TextView bodyOfTask=holder.itemView.findViewById(R.id.bodyOfTask);
        TextView stateOfTask=holder.itemView.findViewById(R.id.stateOfTask);
        titleOfTask.setText(holder.task.title);
        bodyOfTask.setText(holder.task.body);
        stateOfTask.setText(holder.task.state);
    }

    @Override
    public int getItemCount() {
        return allTask.size();
    }
}
