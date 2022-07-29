package com.example.gametime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VenueActivityAdapter extends RecyclerView.Adapter<VenueActivityAdapter.ActivityViewHolder> {
    public ArrayList<String> activities;
    private Context context;

    public VenueActivityAdapter(Context context, ArrayList<String> activities) {
        this.context = context;
        this.activities = activities;
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt;
        Button button;

        public ActivityViewHolder(final View view) {
            super(view);
            nameTxt = view.findViewById(R.id.holderActivityNameTxt);
            button = view.findViewById(R.id.activityDeleteBtn);

            System.out.println(button);

//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    activities.remove(nameTxt.getText());
//                    notifyDataSetChanged();
//                }
//            });
        }
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.venue_activity_info, parent, false);
        return new ActivityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        String activityName = activities.get(position);
        System.out.println(activityName);
        System.out.println(holder.nameTxt);

        holder.nameTxt.setText(activityName);
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }


}
