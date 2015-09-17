package com.demo.ssm.po;

/**
 * Created by gmy on 15/9/17.
 */
public class Point {
    int id;
    double lng;
    double lat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Point(double lng, double lat, int id) {
        this.id = id;
        this.lng = lng;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
