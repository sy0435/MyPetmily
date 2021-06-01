package com.example.cteam;

import java.io.Serializable;

public class MemberDTO implements Serializable {
    private String name;
    private String id;
    private String pw;
    private String findPwAsk;
    private String findAnswer;
    private String phoneNum;

    public MemberDTO() { }

    public MemberDTO(String name, String id, String pw, String findPwAsk, String findAnswer, String phoneNum) {
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.findPwAsk = findPwAsk;
        this.findAnswer = findAnswer;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getFindPwAsk() {
        return findPwAsk;
    }

    public void setFindPwAsk(String findPwAsk) {
        this.findPwAsk = findPwAsk;
    }

    public String getFindAnswer() {
        return findAnswer;
    }

    public void setFindAnswer(String findAnswer) {
        this.findAnswer = findAnswer;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}