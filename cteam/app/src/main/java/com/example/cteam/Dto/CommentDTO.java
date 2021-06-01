package com.example.cteam.Dto;

import java.io.Serializable;

public class CommentDTO implements Serializable {
    private String member_id;
    private String board_num;
    private String content;
    private String writedate;
    private String writer_image;
    private String comment_num;

    public CommentDTO(String member_id, String board_num, String content, String writedate, String writer_image, String comment_num) {
        this.member_id = member_id;
        this.board_num = board_num;
        this.content = content;
        this.writedate = writedate;
        this.writer_image = writer_image;
        this.comment_num = comment_num;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getBoard_num() {
        return board_num;
    }

    public void setBoard_num(String board_num) {
        this.board_num = board_num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWritedate() {
        return writedate;
    }

    public void setWritedate(String writedate) {
        this.writedate = writedate;
    }

    public String getWriter_image() {
        return writer_image;
    }

    public void setWriter_image(String writer_image) {
        this.writer_image = writer_image;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }


}
