package com.example.root.fyp_sensors_app.Model;

public class Sensor {
    private String data;
    private String name;

    public Sensor() {}

    public Sensor(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public String getName() {
        return this.name;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setName(String name) {
        this.name = name;
    }
}