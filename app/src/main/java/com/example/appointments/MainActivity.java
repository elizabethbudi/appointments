// Timothy Lin 29663818
// Elizabeth Budi 83979146


package com.example.appointments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private ChildEventListener childEventListener;
    private AppointmentAdapter listAdapter;
    private ArrayList<Appointment> appointmentList;
    private ListView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("appointments");

        appointmentList = new ArrayList<Appointment>();

        childEventListener = new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listAdapter.add( dataSnapshot.getValue(Appointment.class));
                listAdapter.sort(new Comparator<Appointment>() {
                    @Override
                    public int compare(Appointment lhs, Appointment rhs) {
                        return rhs.compareTo(lhs);
                    }
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                listAdapter.sort(new Comparator<Appointment>() {
                    @Override
                    public int compare(Appointment lhs, Appointment rhs) {
                        return rhs.compareTo(lhs);
                    }
                });
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        myRef.addChildEventListener(childEventListener);

        listAdapter = new AppointmentAdapter(this, appointmentList);


        results = findViewById(R.id.listViewResults);
        results.setAdapter(listAdapter);
    }

    public void addAppointment(View view){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void searchAppointment(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
