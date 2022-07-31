package com.example.gametime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gametime.model.Event;

import java.util.ArrayList;

public class JoinedAdapter extends RecyclerView.Adapter<JoinedAdapter.JoinedViewHolder> {
    ArrayList<Event> list;
    Context context;

    public JoinedAdapter(Context context, ArrayList<Event> list){
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public JoinedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.event, parent, false);
        return new JoinedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JoinedViewHolder holder, int position) {
        Event event = list.get(position);
        holder.name.setText(event.getName());
        holder.start_time.setText(event.getStart_time());
        holder.end_time.setText(event.getEnd_time());
        holder.num_players.setText(Integer.toString(event.getNum_players()));
        holder.venue.setText(event.getVenue());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class JoinedViewHolder extends RecyclerView.ViewHolder{
        TextView name, start_time, end_time, num_players, venue;

        public JoinedViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.eventview);
            start_time = itemView.findViewById(R.id.startview);
            end_time = itemView.findViewById(R.id.endview);
            num_players = itemView.findViewById(R.id.playersview);
            venue = itemView.findViewById(R.id.venueview);
        }

    }

}
