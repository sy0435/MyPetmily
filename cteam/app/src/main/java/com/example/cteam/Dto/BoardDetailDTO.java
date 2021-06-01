package com.example.cteam.Dto;

import java.io.Serializable;

public class BoardDetailDTO implements Serializable {
    String board_subject, board_title, board_content, member_id2, petname, board_date,
            board_imagepath, board_city, board_region, petimagepath, board_num2;

    public BoardDetailDTO(String board_num2, String board_subject, String board_title, String board_content,
                          String member_id2, String petname, String board_date, String board_imagepath,
                          String board_city, String board_region, String petimagepath) {
        super();
        this.board_num2 = board_num2;
        this.board_subject = board_subject;
        this.board_title = board_title;
        this.board_content = board_content;
        this.member_id2 = member_id2;
        this.petname = petname;
        this.board_date = board_date;
        this.board_imagepath = board_imagepath;
        this.board_city = board_city;
        this.board_region = board_region;
        this.petimagepath = petimagepath;
    }

    public String getboard_num2() {
        return board_num2;
    }

    public void setboard_num2(String board_num2) {
        this.board_num2 = board_num2;
    }

    public String getBoard_subject() {
        return board_subject;
    }

    public void setBoard_subject(String board_subject) {
        this.board_subject = board_subject;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getmember_id2() {
        return member_id2;
    }

    public void setmember_id2(String member_id2) {
        this.member_id2 = member_id2;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getBoard_date() {
        return board_date;
    }

    public void setBoard_date(String board_date) {
        this.board_date = board_date;
    }

    public String getBoard_imagepath() {
        return board_imagepath;
    }

    public void setBoard_imagepath(String board_imagepath) {
        this.board_imagepath = board_imagepath;
    }

    public String getBoard_city() {
        return board_city;
    }

    public void setBoard_city(String board_city) {
        this.board_city = board_city;
    }

    public String getBoard_region() {
        return board_region;
    }

    public void setBoard_region(String board_region) {
        this.board_region = board_region;
    }

    public String getPetimagepath() {
        return petimagepath;
    }

    public void setPetimagepath(String petimagepath) {
        this.petimagepath = petimagepath;
    }




}
