package com.example.root.fyp_sensors_app.Ubidot_Chart;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UbidotsClient {

    private UbiListener listener;

    public UbiListener getListener() {
        return listener;
    }

    public void setListener(UbiListener listener) {
        this.listener = listener;
    }

    public interface  UbiListener {
        public void onDataReady( List<Value> result);
    }

    public void handleUbidots(String varID, String apikey, final UbiListener listener) {

        final List<Value> results = new ArrayList<>();


        OkHttpClient client = new OkHttpClient();


        Request.Builder builder = (new Request.Builder()).addHeader("X-Auth-Token", apikey);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://industrial.api.ubidots.com/api/v1.6/variables/");
        stringBuilder.append(varID);
        stringBuilder.append("/values");



        client.newCall(builder.url(stringBuilder.toString()).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Chart", "Network error");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response param1Response) throws IOException {

                String str = Objects.requireNonNull(param1Response.body()).string();
                Log.d("Chart", str);

                try {
                    JSONObject jSONObject = new JSONObject(str);

                    JSONArray jSONArray = jSONObject.getJSONArray("results");
                    for (int b = 0; b < jSONArray.length(); b++) {
                        JSONObject jSONObject1 = jSONArray.getJSONObject(b);

                        Value value = new Value();

                        value.timestamp = jSONObject1.getLong("timestamp");
                        value.value = (float) jSONObject1.getDouble("value");
                        results.add(value);
                    }

                    listener.onDataReady(results);


                } catch (JSONException jSONException) {
                    jSONException.printStackTrace();
                }

            }     });

 }


        protected static class Value {
            public float value;
            public long timestamp;
        }





}