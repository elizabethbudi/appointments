// Created by persons listed below (name, UCI ID) following the rules of pair programming. All work in the project was done collaboratively.
// Timothy Lin 29663818
// Elizabeth Budi 83979146

package com.example.appointments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    // Event Listener that listens to each child in the database
    private ChildEventListener childEventListener;

    // Local data structure that will store all the values from the database
    private ArrayList<Appointment> appointmentList;
    private ArrayList<Appointment> searchResults;

    // ArrayAdapter allows the results to be displayed in a list on the app
    private AppointmentAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initializes the references to the database and contacts
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("appointments");

        // Initializes the local data structure to store the database
        appointmentList = new ArrayList<Appointment>();

        // Set up an array that will have the contents that you want to display
        searchResults = new ArrayList<Appointment>();

        // Sets up the event listener that will specify what happens when access of a node
        // occurs in the database
        childEventListener = new ChildEventListener(){
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Adds the Contact to the local data structure
                appointmentList.add(dataSnapshot.getValue(Appointment.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        // Need to add the event listener to the database
        myRef.addChildEventListener(childEventListener);

        // Sets up the list adapter to read from the results array
        listAdapter = new AppointmentAdapter( this, searchResults);
        ListView results = findViewById(R.id.listViewResults);
        results.setAdapter(listAdapter);
    }

    public void search(View view)
    {
        listAdapter.clear();    // clears any content
        boolean found = false;
        EditText text = (EditText)findViewById(R.id.editTextDate);
        String dateText = text.getText().toString();
        if(dateText.matches("([0-9]){2}/([0-9]){2}/([0-9]){4}")){
            for( Appointment a: appointmentList)
            {
                Date d = new Date(Integer.parseInt(dateText.substring(0, 2)), Integer.parseInt(dateText.substring(3, 5)), Integer.parseInt(dateText.substring(6, 10)));
                if (a.getDate().equals(d)) {
                    // If the contact name is a match, add the result to the listAdapter for display
                    listAdapter.add(a);
                    found = true;
                }
            }
            EditText search = (EditText)findViewById(R.id.editTextDate);
            if(!found) {
                Toast.makeText(this, search.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
            }
            search.setText("");

        }else{
            Toast.makeText(this, "Incorrect format of input.", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
