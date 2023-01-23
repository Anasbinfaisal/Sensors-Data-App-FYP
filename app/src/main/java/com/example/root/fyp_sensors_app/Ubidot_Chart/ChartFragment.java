package com.example.root.fyp_sensors_app.Ubidot_Chart;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;


import com.example.root.fyp_sensors_app.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChartFragment extends Fragment {

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");


    //private String API_KEY = "BBFF-395c11e3885b0d1dd91a482786d3bb75c9d";
    private String API_KEY = "BBFF-KEnwZ7G2ZiPwR7T2lkYntRe5Z9GMRO"; //token

    private String ECGVarId = "6283e60a1d84727c77c81b0f";

    private LineChart ecgChart;

    public ChartFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View v = paramLayoutInflater.inflate(R.layout.fragment_chart, paramViewGroup, false);
        ecgChart = v.findViewById(R.id.linechart1);

        initChartECG(ecgChart);


        (new UbidotsClient()).handleUbidots(ECGVarId, API_KEY, result -> {


            Log.d("Chart", "======== On data Ready ===========");

            List<Entry> entries = new ArrayList();

            List<String> labels = new ArrayList<>();

            for (int i = 0; i < result.size(); i++) {

                Entry be = new Entry(i, result.get(i).value);
                entries.add(be);
                Log.d("Chart", be.toString());


                // Convert timestamp to date
                Date d = new Date(result.get(i).timestamp);

                // Create Labels
                labels.add(sdf.format(d));

            }

            LineDataSet lse = new LineDataSet(entries, "Voltage");

            lse.setAxisDependency(YAxis.AxisDependency.LEFT);
            lse.setHighlightEnabled(true);
            lse.setLineWidth(2);
            lse.setColor(Color.CYAN);
            lse.setCircleColor(Color.CYAN);
            lse.setCircleRadius(3);
            lse.setCircleHoleRadius(1);
            lse.setDrawHighlightIndicators(true);
            lse.setHighLightColor(Color.RED);
            lse.setValueTextSize(10);
            lse.setValueTextColor(Color.BLACK);


            LineData ld = new LineData(lse);

            ld.setValueTextColor(Color.BLACK);
            ld.setValueTextSize(9f);



            ecgChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
            ecgChart.getXAxis().setLabelCount(lse.getEntryCount());


            getActivity().runOnUiThread(() ->  ecgChart.animateY(1000));


            ecgChart.setData(ld);

        });

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
    }


    private void initChartECG(LineChart chart) {


        chart.setTouchEnabled(true);

        chart.setDrawGridBackground(true);
        chart.getAxisRight().setEnabled(false);
        chart.setDrawGridBackground(true);
        chart.getXAxis().setGranularityEnabled(true);
        chart.getXAxis().setGranularity(10f);
       chart.getXAxis().setLabelCount(100);
        YAxis leftAxis = chart.getAxisLeft();

        leftAxis.setAxisLineWidth(2);
        leftAxis.setDrawGridLines(true);


        // X-Axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);

    }


}
