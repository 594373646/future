package com.lgl.geo.common;

/**
 * 经纬度类 member表示地理位置的标识，longitude和latitude表示经纬度。
 *
 * @author lgl
 * @Date 2023/9/20 2:17
 */
public class Location {
    private String member;
    private double longitude;
    private double latitude;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
