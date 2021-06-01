package com.example.cteam.Dto;

public class MemberDTO {
    String member_id;
    String member_pw;
    String member_name;
    String member_phonenum;
    String member_qeustion;
    String member_answer;

    // 암호 없이 멤버정보를 가져올때
    public MemberDTO(String member_id, String member_name, String member_phonenum, String member_qeustion, String member_answer) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_phonenum = member_phonenum;
        this.member_qeustion = member_qeustion;
        this.member_answer = member_answer;
    }

    // 데이터베이스에 멤버정보를 추가할때
    public MemberDTO(String member_id, String member_pw,String member_name, String member_phonenum, String member_qeustion, String member_answer) {
        this.member_id = member_id;
        this.member_pw = member_pw;
        this.member_name = member_name;
        this.member_phonenum = member_phonenum;
        this.member_qeustion = member_qeustion;
        this.member_answer = member_answer;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_pw() {
        return member_pw;
    }

    public void setMember_pw(String member_pw) {
        this.member_pw = member_pw;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_phonenum() {
        return member_phonenum;
    }

    public void setMember_phonenum(String member_phonenum) {
        this.member_phonenum = member_phonenum;
    }

    public String getMember_qeustion() {
        return member_qeustion;
    }

    public void setMember_qeustion(String member_qeustion) {
        this.member_qeustion = member_qeustion;
    }

    public String getMember_answer() {
        return member_answer;
    }

    public void setMember_answer(String member_answer) {
        this.member_answer = member_answer;
    }
}
