package com.example.mygymlogapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    ArrayList<String> nameData;
    ArrayList<String> typeData;
    Context context;

    public RecyclerViewAdapter(Context ct, ArrayList<String> rNames, ArrayList<String> descriptions) {
        context = ct;
        nameData = rNames;
        typeData = descriptions;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(nameData.get(position));
        holder.type.setText(typeData.get(position));
    }

    @Override
    public int getItemCount() {
        return nameData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);

        }
    }
}
