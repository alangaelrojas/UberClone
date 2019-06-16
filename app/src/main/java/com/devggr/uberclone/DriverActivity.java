package com.devggr.uberclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DriverActivity extends AppCompatActivity {

    ImageView imgCar;
    TextView txtNombre;
    Button btnReservar;


    String urlFoto, nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        imgCar = findViewById(R.id.imgDriver);
        txtNombre = findViewById(R.id.txtNombreDriver);
        btnReservar = findViewById(R.id.btnReservar);

        urlFoto = getIntent().getExtras().getString("urlFoto");
        nombre = getIntent().getExtras().getString("nombre");

        Glide.with(this).load(urlFoto).into(imgCar);
        txtNombre.setText(nombre);
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Reservado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
