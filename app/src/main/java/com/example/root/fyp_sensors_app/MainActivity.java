package com.example.root.fyp_sensors_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.root.fyp_sensors_app.Ubidot_Chart.Chart_view;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //Button on;
    TextView sensor_name;
    TextView sensor_data;
    DatabaseReference dref;

    public String MQ131, MQ135, MQ2, MQ7, NO2, heart_rate, humidity, oxygen_rate, temp_c, temp_f, temp_room;

    private Button ecg_data;

    String[] items;

    private Spinner spinner;
    private int spinner_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensor_name = findViewById(R.id.sensor_name);
        sensor_data = findViewById(R.id.sensor_data);
        ecg_data = findViewById(R.id.ecg_data);
        spinner = findViewById(R.id.spinner);
        dref= FirebaseDatabase.getInstance().getReference();

        if (!isNetworkAvailable())
            Showtoast();

       ecg_data.setOnClickListener(view -> {

           Intent intent = new Intent(MainActivity.this, Chart_view.class);
           startActivity(intent);


       });

       items = getResources().getStringArray(R.array.sensors);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("FirebaseIOT");
        FirebaseAuth.getInstance();

        databaseReference.addValueEventListener(new ValueEventListener() {
          //  final MainActivity this$0;

            public void onCancelled(DatabaseError param1DatabaseError) {
                Toast.makeText((Context)MainActivity.this, "Failed to get data.", Toast.LENGTH_SHORT).show();
            }

            public void onDataChange(DataSnapshot param1DataSnapshot) {
                Log.d("getdata", "onDataChange: ");

                temp_c = Objects.requireNonNull(param1DataSnapshot.child("Body Temperature (Celsius)").getValue()).toString();
                temp_c = temp_c.concat("°C");


                temp_f = Objects.requireNonNull(param1DataSnapshot.child("Body Temperature (Fahrenheit)").getValue()).toString();
                temp_f = temp_f.concat("°F");


               humidity = Objects.requireNonNull(param1DataSnapshot.child("Humidity").getValue()).toString();
               humidity = humidity.concat("%");


                MQ131 = Objects.requireNonNull(param1DataSnapshot.child("MQ-131_Data").getValue()).toString();


                MQ135 = Objects.requireNonNull(param1DataSnapshot.child("MQ-135_Data").getValue()).toString();

                MQ2 = Objects.requireNonNull(param1DataSnapshot.child("MQ-2_Data").getValue()).toString();

                MQ7 = Objects.requireNonNull(param1DataSnapshot.child("MQ-7_Data").getValue()).toString();

                NO2 = Objects.requireNonNull(param1DataSnapshot.child("NO2").getValue()).toString();

                temp_room = Objects.requireNonNull(param1DataSnapshot.child("Room Temperature").getValue()).toString();
                temp_room = temp_room.concat("°C");

                heart_rate = Objects.requireNonNull(param1DataSnapshot.child("Heart Rate").getValue()).toString();


               oxygen_rate = Objects.requireNonNull(param1DataSnapshot.child("Oxygen").getValue()).toString();

                oxygen_rate = oxygen_rate.concat("%");
                Log.d("getdata", "onDataChange: end");



                Log.d("getdata", " " + temp_c + " " + oxygen_rate);
            }
        });



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, items);
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_item);


        arrayAdapter.notifyDataSetChanged();
        spinner.setAdapter(arrayAdapter);
        int spinnerValue = getSharedPreferences("sensor", 0).getInt("user_choice", 0);
        spinner.setSelection(0);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> paramAdapterView, View view, int paramInt, long l) {


                if (!isNetworkAvailable())
                    Showtoast();


//                if (paramAdapterView.getId() == paramInt) {

                    String str = paramAdapterView.getItemAtPosition(paramInt).toString();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(" selected");
                    Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();

                    Log.d("onitem", " " + str + " " + paramInt + " " + paramAdapterView.getId());

                    if (str.equals("none")) {
                        sensor_name.setVisibility(View.GONE);
                        sensor_data.setVisibility(View.GONE);
                    } else if (str.equals(getResources().getStringArray(R.array.sensors)[1])) {
                        sensor_name.setVisibility(View.VISIBLE);
                        sensor_data.setVisibility(View.VISIBLE);
                        sensor_name.setText(str);
                        sensor_data.setText(temp_c);
                    } else if (str.equals(getResources().getStringArray(R.array.sensors)[2])) {
                        sensor_name.setVisibility(View.VISIBLE);
                        sensor_data.setVisibility(View.VISIBLE);
                        sensor_name.setText(str);
                        sensor_data.setText(temp_f);
                    } else if (str.equals(getResources().getStringArray(R.array.sensors)[3])) {
                        sensor_name.setVisibility(View.VISIBLE);
                        sensor_data.setVisibility(View.VISIBLE);
                        sensor_name.setText(str);
                        sensor_data.setText(humidity);
                    } else if (str.equals(getResources().getStringArray(R.array.sensors)[4])) {
                        sensor_name.setVisibility(View.VISIBLE);
                        sensor_data.setVisibility(View.VISIBLE);
                        sensor_name.setText(str);
                        sensor_data.setText(MQ131);
                    } else if (str.equals(getResources().getStringArray(R.array.sensors)[5])) {
                        sensor_name.setVisibility(View.VISIBLE);
                        sensor_data.setVisibility(View.VISIBLE);
                        sensor_name.setText(str);
                        sensor_data.setText(MQ135);
                    } else if (str.equals(getResources().getStringArray(R.array.sensors)[6])) {
                        sensor_name.setVisibility(View.VISIBLE);
                        sensor_data.setVisibility(View.VISIBLE);
                        sensor_name.setText(str);
                        sensor_data.setText(MQ2);
                    } else if (str.equals(getResources().getStringArray(R.array.sensors)[7])) {
                        sensor_name.setVisibility(View.VISIBLE);
                        sensor_data.setVisibility(View.VISIBLE);
                        sensor_name.setText(str);
                        sensor_data.setText(MQ7);
                    } else if (str.equals(getResources().getStringArray(R.array.sensors)[8])) {
                        sensor_name.setVisibility(View.VISIBLE);
                        sensor_data.setVisibility(View.VISIBLE);
                        sensor_name.setText(str);
                        sensor_data.setText(NO2);
                    } else if (str.equals(getResources().getStringArray(R.array.sensors)[9])) {
                        sensor_name.setVisibility(View.VISIBLE);
                        sensor_data.setVisibility(View.VISIBLE);
                        sensor_name.setText(str);
                        sensor_data.setText(temp_room);
                    } else if (str.equals(getResources().getStringArray(R.array.sensors)[10])) {
                        sensor_name.setVisibility(View.VISIBLE);
                        sensor_data.setVisibility(View.VISIBLE);
                        sensor_name.setText(str);
                        sensor_data.setText(heart_rate);
                    } else if (str.equals(getResources().getStringArray(R.array.sensors)[11])) {
                        sensor_name.setVisibility(View.VISIBLE);
                        sensor_data.setVisibility(View.VISIBLE);
                        sensor_name.setText(str);
                        sensor_data.setText(oxygen_rate);
                    } else if (str.equals(getResources().getStringArray(R.array.sensors)[12])) {
                        Intent intent = new Intent(MainActivity.this, Show_All_Activity.class);
                        intent.putExtra("temp_c", temp_c);
                        intent.putExtra("temp_f", temp_f);
                        intent.putExtra("humidity", humidity);
                        intent.putExtra("MQ131", MQ131);
                        intent.putExtra("MQ135", MQ135);
                        intent.putExtra("MQ2", MQ2);
                        intent.putExtra("MQ7", MQ7);
                        intent.putExtra("NO2", NO2);
                        intent.putExtra("temp_room", temp_room);
                        intent.putExtra("heart_rate", heart_rate);
                        intent.putExtra("oxygen_rate", oxygen_rate);

                        startActivity(intent);
                    }

                    paramInt = spinner.getSelectedItemPosition();
                    SharedPreferences.Editor editor = getSharedPreferences("sensor", 0).edit();
                    editor.putInt("user_choice", paramInt);
                    editor.commit();
              //  }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

    }



    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    void Showtoast() {
        Toast.makeText((Context)this, "No Connection Available.", Toast.LENGTH_SHORT).show();
    }

}