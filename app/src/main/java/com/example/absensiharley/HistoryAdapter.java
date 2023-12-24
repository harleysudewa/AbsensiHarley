package com.example.absensiharley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList id, name, date, time, location, description;

    @Override
    public int getItemViewType(int position) {
        if (description.get(position).equals("Izin")) {
            return 1;
        } else if (description.get(position).equals("Absen Keluar")){
            return 2;
        } else {
            return 0;
        }
    }

    public HistoryAdapter(Context context, ArrayList id, ArrayList name, ArrayList date, ArrayList time, ArrayList location, ArrayList description) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.description = description;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.list_history_izin,parent,false);
            return new HistoryViewHolder(view);
        } else if (viewType == 2) {
            view = LayoutInflater.from(context).inflate(R.layout.list_history_keluar,parent,false);
            return new HistoryViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.list_history_absen,parent,false);
            return new HistoryViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HistoryViewHolder historyViewHolder = (HistoryViewHolder) holder;
        historyViewHolder.id.setText(String.valueOf(id.get(position)));
        historyViewHolder.name.setText(String.valueOf(name.get(position)));
        historyViewHolder.date.setText(String.valueOf(date.get(position)));
        historyViewHolder.description.setText(String.valueOf(description.get(position)));
        historyViewHolder.time.setText(String.valueOf(time.get(position)));
        historyViewHolder.location.setText((String.valueOf(location.get(position))));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }
    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, date, time, location, description;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvNomor);
            name = itemView.findViewById(R.id.tvNama);
            date = itemView.findViewById(R.id.tvAbsenDate);
            location = itemView.findViewById(R.id.tvLokasi);
            description = itemView.findViewById(R.id.tvStatusAbsen);
            time = itemView.findViewById(R.id.tvAbsenTime);
        }
    }
}









