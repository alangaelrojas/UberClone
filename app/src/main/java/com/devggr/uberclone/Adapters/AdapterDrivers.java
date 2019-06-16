package com.devggr.uberclone.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.devggr.uberclone.Pojos.Driver;
import com.devggr.uberclone.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterDrivers extends RecyclerView.Adapter<AdapterDrivers.HolderDrivers> {

    private List<Driver> driverList = new ArrayList<>();
    private Context c;
    public OnClickEventsDriver onClickEventsDriver;

    public AdapterDrivers (Context c, OnClickEventsDriver onClickEventsDriver){
        this.c = c;
        this.onClickEventsDriver = onClickEventsDriver;
    }
    public void addDriver(Driver driver){
        driverList.add(driver);
        notifyItemInserted(driverList.size());
    }
    @Override
    public HolderDrivers onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.cardview_coches, viewGroup, false);
        return new HolderDrivers(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDrivers holderDrivers, int position) {
        holderDrivers.nombre.setText(driverList.get(position).getNombre());
        holderDrivers.asientos.setText(driverList.get(position).getAsientos());
        holderDrivers.cal.setText(driverList.get(position).getCalif());
        Glide.with(c).load(driverList.get(position).getUrlFoto()).into(holderDrivers.fotoUrl);
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    //Interface que se implementa en la actividad
    public interface OnClickEventsDriver{
        void onClick(Driver driver);
    }
    public class HolderDrivers extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nombre, cal, asientos;
        private ImageView fotoUrl;
        private CardView cardView;
        public HolderDrivers(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtNombreContacto);
            cal = itemView.findViewById(R.id.txtCal);
            asientos = itemView.findViewById(R.id.txtAsientos);
            fotoUrl = itemView.findViewById(R.id.cimgFotoContacto);
            cardView = itemView.findViewById(R.id.cardview_drivers);
            cardView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cardview_drivers:
                    onClickEventsDriver.onClick(driverList.get(getAdapterPosition()));
                    break;
            }
        }
    }
}
