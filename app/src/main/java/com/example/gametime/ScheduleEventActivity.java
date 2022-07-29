package com.example.gametime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.gametime.firebase.FirebaseConfig;
import com.example.gametime.firebase.GTFirebaseException;
import com.example.gametime.model.Event;
import com.example.gametime.model.User;

import java.util.ArrayList;

public class ScheduleEventActivity extends AppCompatActivity{

    String venue;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_event);
        Intent intent = getIntent();
        venue = intent.getStringExtra("venue_name");
        ArrayList<String> activities = intent.getStringArrayListExtra("activities");
        Spinner spinner = (Spinner) findViewById(R.id.activity_type_spinner);
        ScheduleEventActivity t = this;
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, activities);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);

        String eventName = ((EditText) findViewById(R.id.EventName)).getText().toString();
    }

    public void scheduleEvent(View view){
        String event_name = ((EditText)findViewById(R.id.EventName)).getText().toString();
        String date = ((TextView)findViewById(R.id.selected_date)).getText().toString();
        String start_time = date +
                "-" + ((TextView)findViewById(R.id.start_time)).getText().toString();
        String end_time = date +
                "-" +  ((TextView)findViewById(R.id.end_time)).getText().toString();
        int num_players = Integer.parseInt(
                ((EditText)findViewById(R.id.num_players_input)).getText().toString());
        String activity_type = ((Spinner)findViewById(
                R.id.activity_type_spinner)).getSelectedItem().toString();
        Event event = new Event(event_name, start_time, end_time, venue, num_players, activity_type, null);
        try {
            FirebaseConfig.getInstance().customerBehavior.scheduleEvent(null, event);
            Intent back = new Intent(ScheduleEventActivity.this, SelectVenueActivity.class);
            startActivity(back);
            finish();
        } catch (GTFirebaseException e) {
            e.printStackTrace();
        }
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
