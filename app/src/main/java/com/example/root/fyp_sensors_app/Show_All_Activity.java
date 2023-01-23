package com.example.root.fyp_sensors_app;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.root.fyp_sensors_app.Model.Sensor;
import com.example.root.fyp_sensors_app.Model.SensorDataAdapter;

import java.util.ArrayList;
import java.util.List;

public class Show_All_Activity extends AppCompatActivity {

    public String MQ131, MQ135, MQ2, MQ7, NO2, heart_rate, humidity, oxygen_rate, temp_c, temp_f , temp_room;
    public List<Sensor> sensors = new ArrayList<>();
    public SensorDataAdapter mAdapter;


    protected void onCreate(Bundle paramBundle) {

        super.onCreate(paramBundle);
        setContentView(R.layout.show_all_activity);


         RecyclerView recyclerView;
         recyclerView = findViewById(R.id.rv_sensors);


        Intent intent = getIntent();
        temp_c = intent.getStringExtra("temp_c");
        temp_f = intent.getStringExtra("temp_f");
        humidity = intent.getStringExtra("humidity");
        MQ131 = intent.getStringExtra("MQ131");
        MQ135 = intent.getStringExtra("MQ135");
        MQ2 = intent.getStringExtra("MQ2");
        MQ7 = intent.getStringExtra("MQ7");
        NO2 = intent.getStringExtra("NO2");
        temp_room = intent.getStringExtra("temp_room");
        heart_rate = intent.getStringExtra("heart_rate");
        oxygen_rate = intent.getStringExtra("oxygen_rate");



        mAdapter = new SensorDataAdapter(sensors);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration((Context)this, 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        preparesensordata();
    }




    private void preparesensordata() {
        Sensor sensor = new Sensor(getResources().getStringArray(R.array.sensors)[1], temp_c);
        sensors.add(sensor);
        sensor = new Sensor(getResources().getStringArray(R.array.sensors)[2], temp_f);
        sensors.add(sensor);
        sensor = new Sensor(getResources().getStringArray(R.array.sensors)[3], humidity);
        sensors.add(sensor);
        sensor = new Sensor(getResources().getStringArray(R.array.sensors)[4], MQ131);
        sensors.add(sensor);
        sensor = new Sensor(getResources().getStringArray(R.array.sensors)[5], MQ135);
        sensors.add(sensor);
        sensor = new Sensor(getResources().getStringArray(R.array.sensors)[6], MQ2);
        sensors.add(sensor);
        sensor = new Sensor(getResources().getStringArray(R.array.sensors)[7], MQ7);
        sensors.add(sensor);
        sensor = new Sensor(getResources().getStringArray(R.array.sensors)[8], NO2);
        sensors.add(sensor);
        sensor = new Sensor(getResources().getStringArray(R.array.sensors)[9], temp_room);
        sensors.add(sensor);
        sensor = new Sensor(getResources().getStringArray(R.array.sensors)[10], heart_rate);
        sensors.add(sensor);
        sensor = new Sensor(getResources().getStringArray(R.array.sensors)[11], oxygen_rate);
        sensors.add(sensor);
        mAdapter.notifyDataSetChanged();
    }




}
