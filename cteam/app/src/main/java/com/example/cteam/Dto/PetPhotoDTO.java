package com.example.cteam.Dto;

import java.io.Serializable;

public class PetPhotoDTO implements Serializable {
    private String petPhoto_imgpath;
    private String petPhoto_content;
    private String petPhoto_date;
    private String petName;
    private String petPhoto_no;

    public PetPhotoDTO(String petPhoto_imgpath, String petPhoto_content, String petPhoto_date, String petName) {
        this.petPhoto_imgpath = petPhoto_imgpath;
        this.petPhoto_content = petPhoto_content;
        this.petPhoto_date = petPhoto_date;
        this.petName = petName;
    }

    public PetPhotoDTO(String petPhoto_imgpath, String petPhoto_content, String petPhoto_date, String petName,String petPhoto_no) {
        this.petPhoto_imgpath = petPhoto_imgpath;
        this.petPhoto_content = petPhoto_content;
        this.petPhoto_date = petPhoto_date;
        this.petName = petName;
        this.petPhoto_no= petPhoto_no;
    }

    public String getPetPhoto_no() {
        return petPhoto_no;
    }

    public void setPetPhoto_no(String petPhoto_no) {
        this.petPhoto_no = petPhoto_no;
    }

    public String getPetPhoto_imgpath() {
        return petPhoto_imgpath;
    }

    public void setPetPhoto_imgpath(String petPhoto_imgpath) {
        this.petPhoto_imgpath = petPhoto_imgpath;
    }

    public String getPetPhoto_content() {
        return petPhoto_content;
    }

    public void setPetPhoto_content(String petPhoto_content) {
        this.petPhoto_content = petPhoto_content;
    }

    public String getPetPhoto_date() {
        return petPhoto_date;
    }

    public void setPetPhoto_date(String petPhoto_date) {
        this.petPhoto_date = petPhoto_date;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }
}