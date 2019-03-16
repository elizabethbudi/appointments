package com.example.appointments;

import android.content.Intent;
import android.view.View;
import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivityForResult;
import static android.support.v4.content.ContextCompat.startActivity;

public class AppointmentAdapter extends ArrayAdapter<Appointment> {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Context context;
    private List<Appointment> appointmentList = new ArrayList<Appointment>();

    public AppointmentAdapter(Context context, ArrayList<Appointment> list){
        super(context, 0, list);
        this.context = context;
        appointmentList = list;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("appointments");
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;

        if(listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.appointment_view, parent, false);
        }

        final Appointment currentAppointment = appointmentList.get(position);

        TextView description = (TextView) listItem.findViewById(R.id.editTextDescription);
        description.setText(currentAppointment.getDescription());

        TextView date = (TextView) listItem.findViewById(R.id.editTextDate);
        date.setText("Date: " + currentAppointment.getDate().toString());

        TextView time = (TextView) listItem.findViewById(R.id.editTextTime);
        time.setText("Time: " + currentAppointment.getStart() + " - " + currentAppointment.getEnd());


        Button delete = listItem.findViewById(R.id.buttonDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = currentAppointment.getUid();
                myRef.child(key).removeValue();
                refreshDelete(currentAppointment);
            }
        });

        Button edit = listItem.findViewById(R.id.buttonEdit);
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("uid", currentAppointment);
                context.startActivity(intent);
            }
        });

        return listItem;
    }


    public void refreshDelete(Appointment toDelete){
        List<Appointment> temp = new ArrayList<Appointment>();
        temp.addAll(appointmentList);
        for (Appointment a : temp){
            if (a.getUid() == toDelete.getUid()){
                this.remove(a);
            }
        }
    }

}
