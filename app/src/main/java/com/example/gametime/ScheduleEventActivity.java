package com.example.gametime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.gametime.firebase.FirebaseConfig;
import com.example.gametime.firebase.GTFirebaseException;
import com.example.gametime.model.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class ScheduleEventActivity extends AppCompatActivity{

    String venue;
    int num_events;
    boolean done;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_event);
        Intent intent = getIntent();
        venue = intent.getStringExtra("venue_name");
        num_events = intent.getIntExtra("num_events", 0);
        done = false;
        ArrayList<String> activities = intent.getStringArrayListExtra("activities");
        Spinner spinner = (Spinner) findViewById(R.id.activity_type_spinner);
        ScheduleEventActivity t = this;
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, activities);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);

        String eventName = ((EditText) findViewById(R.id.EventName)).getText().toString();
    }

    public void toastMessage(String msg){
        Toast.makeText(ScheduleEventActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void clearErrors(){
        ((TextView)findViewById(R.id.selected_date)).setError(null);
        ((TextView)findViewById(R.id.start_time)).setError(null);
        ((TextView)findViewById(R.id.end_time)).setError(null);
        ((EditText)findViewById(R.id.num_players_input)).setError(null);
        ((EditText)findViewById(R.id.EventName)).setError(null);
    }

    public void scheduleEvent(View view){

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        Date curr_date = new Date(System.currentTimeMillis());

        String date = ((TextView)findViewById(R.id.selected_date)).getText().toString();
        Pattern date_pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Pattern time_pattern = Pattern.compile("\\d{2}:\\d{2}");
        Pattern event_pattern = Pattern.compile("\\w+.*");
        String event_name = ((EditText)findViewById(R.id.EventName)).getText().toString();;
        if(!event_pattern.matcher(event_name).matches()){
            toastMessage("Invalid event name");
            clearErrors();
            ((EditText)findViewById(R.id.EventName)).setError("Invalid name");
            return;
        }

        if(!date_pattern.matcher(date).matches()){
            toastMessage("Please enter event date");
            clearErrors();
            ((TextView)findViewById(R.id.selected_date)).setError("Enter date");
            return;
        }

        String start_time = ((TextView)findViewById(R.id.start_time)).getText().toString();
        if(!time_pattern.matcher(start_time).matches()){
            toastMessage("Please enter event start time");
            clearErrors();
            ((TextView)findViewById(R.id.start_time)).setError("Enter time");
            return;
        }
        start_time = date + "-" + start_time;
        if(start_time.compareTo(formatter.format(curr_date)) < 0){
            clearErrors();
            toastMessage("Event start time cannot be in the past");
            return;
        }
        String end_time = ((TextView)findViewById(R.id.end_time)).getText().toString();
        if(!time_pattern.matcher(end_time).matches()){
            toastMessage("Please enter event end time");
            clearErrors();
            ((TextView)findViewById(R.id.end_time)).setError("Enter time");
            return;
        }
        end_time = date + "-" +  end_time;
        if(end_time.compareTo(start_time)< 0){
            clearErrors();
            toastMessage("End time cannot be before start time");
            return;
        }
        String num_players_input = ((EditText)findViewById(R.id.num_players_input)).getText().toString();

        if(!num_players_input.matches("\\d+")){
            toastMessage("Please enter the number of players");
            clearErrors();
            ((EditText)findViewById(R.id.num_players_input)).setError("Enter number of players");
            return;
        }
        int num_players = Integer.parseInt(num_players_input);

        String activity_type = ((Spinner)findViewById(
                R.id.activity_type_spinner)).getSelectedItem().toString();
        Event event = new Event(event_name, start_time, end_time, venue, num_players, activity_type);

        FirebaseConfig.getInstance().customerBehavior.listenForEvents(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(event.toUIDString())){
                    if(!done)
                        toastMessage("This event already exists");
                }
                else{
                    try {
                        done = true;
                        FirebaseConfig.getInstance().customerBehavior.scheduleEvent(venue, event, num_events);
                        toastMessage("Event successfully created");
                        Intent back = new Intent(ScheduleEventActivity.this, SelectVenueActivity.class);
                        startActivity(back);
                        finish();
                    } catch (GTFirebaseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void displayTimePicker(View view){
        TextView display = findViewById(R.id.end_time);
        Log.i("time_id", view.getResources().getResourceName(view.getId()));
        if(view.getResources().getResourceName(view.getId()).equals("com.example.gametime:id/select_start_time_button"))
            display = findViewById(R.id.start_time);
        DialogFragment frag = new TimeFragmentPicker(display);
        frag.show(getSupportFragmentManager(), "timePicker");
    }

    public void displayDatePicker(View view){
        DialogFragment newFragment = new DatePickerFragment(findViewById(R.id.selected_date));
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
