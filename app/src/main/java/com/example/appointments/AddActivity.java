// Created by persons listed below (name, UCI ID) following the rules of pair programming. All work in the project was done collaboratively.
// Timothy Lin 29663818
// Elizabeth Budi 83979146

package com.example.appointments;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class AddActivity extends AppCompatActivity{
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("appointments");

    }

    public void add(View view){
        EditText editDescription = findViewById(R.id.editTextDescription);
        String description = editDescription.getText().toString();
        EditText editDate = findViewById(R.id.editTextDate);
        String dateText = editDate.getText().toString();
        EditText editStartTime = findViewById(R.id.editTextStartTime);
        String startTimeText = editStartTime.getText().toString();
        EditText editEndTime = findViewById(R.id.editTextEndTime);
        String endTimeText = editEndTime.getText().toString();

        if(description.length() > 0 && dateText.length() > 0 && startTimeText.length() > 0 && endTimeText.length() > 0)
        {
            if(dateText.matches("((0[1-9])|1[0-2])/(0[1-9]|[12][0-9]|3[01])/([0-9]){4}") && startTimeText.matches("(([01][0-9]|2[0-3]):([0-5][0-9]))")
                    && endTimeText.matches("(([01][0-9]|2[0-3]):([0-5][0-9]))")) {

                Date date = new Date(Integer.parseInt(dateText.substring(0, 2)), Integer.parseInt(dateText.substring(3, 5)), Integer.parseInt(dateText.substring(6, 10)));
                Time startTime = new Time(Integer.parseInt(startTimeText.substring(0,2)), Integer.parseInt(startTimeText.substring(3,5)));
                Time endTime = new Time(Integer.parseInt(endTimeText.substring(0,2)), Integer.parseInt(endTimeText.substring(3,5)));

                String key = myRef.push().getKey();

                Appointment a = new Appointment(description, date, startTime, endTime, key);

                myRef.child(key).setValue(a);
                Toast.makeText(this, "Successfully added.", Toast.LENGTH_LONG).show();
                editDescription.setText("");
                editDate.setText("");
                editStartTime.setText("");
                editEndTime.setText("");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Incorrect format of input.", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this,"Please fill out all the boxes before posting.", Toast.LENGTH_LONG).show();
        }


    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

