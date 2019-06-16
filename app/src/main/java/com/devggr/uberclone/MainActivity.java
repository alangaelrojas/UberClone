package com.devggr.uberclone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.devggr.uberclone.Adapters.AdapterDrivers;
import com.devggr.uberclone.Pojos.Driver;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements AdapterDrivers.OnClickEventsDriver{
    RecyclerView recyclerView;

    DatabaseReference drivers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        final AdapterDrivers adapter = new AdapterDrivers(this, this);
        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(adapter);


        drivers = FirebaseDatabase.getInstance().getReference("drivers");
        /*
        //SELECT * FROM drivers WHERE "n_asientos" < n_asientos
        final Query query_asientos = FirebaseDatabase.getInstance().getReference("drivers")
                    .orderByChild("asientos")
                    .endAt(n_asientos);
*/
        drivers.addChildEventListener(new ChildEventListener() {
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Driver driver = dataSnapshot.getValue(Driver.class);
                adapter.addDriver(driver);
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

        });
        drivers.keepSynced(true);
    }
    @Override
    public void onClick(final Driver driver) {
        String urlFoto = driver.getUrlFoto();
        String nombre = driver.getNombre();
        Intent i = new Intent(this, DriverActivity.class);
        i.putExtra("nombre", nombre);
        i.putExtra("urlFoto", urlFoto);
        startActivity(i);
        finish();
    }
}
