package com.example.gametime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    String[] up_events, date;
    Context context;

    public EventAdapter(Context context, String[] up_events, String[] date){
        this.context = context;
        this.up_events = up_events;
        this.date = date;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.event, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.event.setText(up_events[position]);
        holder.start.setText(date[position]);
    }

    @Override
    public int getItemCount() {
        return up_events.length;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        TextView event, start;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.eventview);
            start = itemView.findViewById(R.id.startview);
        }
    }
}
