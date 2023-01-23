package com.example.root.fyp_sensors_app.Ubidot_Chart;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.root.fyp_sensors_app.R;


public class Chart_view extends AppCompatActivity {

    private void Showtoast() {
        Toast.makeText(Chart_view.this, "No Connection Available.", Toast.LENGTH_SHORT).show();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.chart_view);


        if (!isNetworkAvailable())
            Showtoast();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frgmt, new ChartFragment());
        fragmentTransaction.commit();
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}