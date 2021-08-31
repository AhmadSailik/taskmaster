package com.example.firstandroidapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class vickAdapter extends RecyclerView.Adapter<vickAdapter.TaskViewHolder> {

    List<Task>allTask=new ArrayList<>();



    public vickAdapter(List<Task>allTask){
        this.allTask=allTask;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        public Task task;
        View itemView;
        Context context;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.context=itemView.getContext();
            this.itemView=itemView;

        }

    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_vicky,parent,false);
        TaskViewHolder taskViewHolder=new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  vickAdapter.TaskViewHolder holder, int position) {
        holder.task=allTask.get(position);
        Button button=holder.itemView.findViewById(R.id.buttonOdetails);
        button.setText(holder.task.title);
        button.setOnClickListener((v)-> {
            String name=button.getText().toString();


            Intent oneTask =new Intent(holder.context,DetailPage.class);
            oneTask.putExtra("title",holder.task.title);
            oneTask.putExtra("body",holder.task.body);
            oneTask.putExtra("state",holder.task.state);
            holder.context.startActivity(oneTask);
        });
//        TextView title=holder.itemView.findViewById(R.id.textView2);
//        TextView body=holder.itemView.findViewById(R.id.textView3);
//        TextView state=holder.itemView.findViewById(R.id.textView4);
//        title.setText(holder.task.title);
//        body.setText(holder.task.body);
//        state.setText(holder.task.state);

    }

    @Override
    public int getItemCount() {
        return allTask.size();
    }
}
