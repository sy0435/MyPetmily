package com.example.cteam.Dto;

import java.io.Serializable;

public class BoardDTO implements Serializable {
    private String id;
    private String subject;
    private String title;
    private String city;
    private String region;
    private String date;
    private String num;
    private String city2="";

    public BoardDTO() {    }

    public BoardDTO(String id, String subject, String title, String city, String region, String date, String num) {
        this.id = id;
        this.subject = subject;
        this.title = title;
        this.city = city;
        this.region = region;
        this.date = date;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNum() {
        return num;
    }

    public  void setNum(String num) {
        this.num = num;
    }

    public String getCity2(){return city2=city+region; }
}
