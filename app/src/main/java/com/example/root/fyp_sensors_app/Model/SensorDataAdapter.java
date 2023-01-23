package com.example.root.fyp_sensors_app.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.root.fyp_sensors_app.R;

import java.util.List;

public class SensorDataAdapter extends RecyclerView.Adapter<SensorDataAdapter.MyViewHolder> {

    private final List<Sensor> sensors;

    public SensorDataAdapter(List<Sensor> data) {
        this.sensors = data;
    }


    public int getItemCount() {
        return sensors.size();
    }

    public void onBindViewHolder(MyViewHolder paramMyViewHolder, int paramInt) {
        Sensor sensor = sensors.get(paramInt);
        paramMyViewHolder.sensor_name_view.setText(sensor.getName());
        paramMyViewHolder.sensor_data_view.setText(sensor.getData());
    }

    public MyViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {

         return new MyViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.list_item, paramViewGroup, false));
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sensor_data_view, sensor_name_view;

MyViewHolder(View param1View) {
            super(param1View);
            sensor_name_view = param1View.findViewById(R.id.tv_name);
            sensor_data_view = param1View.findViewById(R.id.tv_data);
        }
    }
}