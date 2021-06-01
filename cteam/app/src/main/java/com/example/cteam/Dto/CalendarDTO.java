package com.example.cteam.Dto;

import java.io.Serializable;

public class CalendarDTO implements Serializable  {

    public String calendar_date;
    public String calendar_icon;
    public String calendar_memo;
    public String calendar_hour;
    public String calendar_minute;
    public String calendar_id;

    public CalendarDTO(String calendar_date, String calendar_icon, String calendar_memo,String calendar_hour, String calendar_minute,String calendar_id) {
        this.calendar_date = calendar_date;
        this.calendar_icon = calendar_icon;
        this.calendar_memo = calendar_memo;
        this.calendar_hour=calendar_hour;
        this.calendar_minute=calendar_minute;
        this.calendar_id = calendar_id;
    }

    public CalendarDTO(String calendar_icon, String calendar_memo) {
        this.calendar_icon = calendar_icon;
        this.calendar_memo = calendar_memo;
    }


    public String getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(String calendar_id) {
        this.calendar_id = calendar_id;
    }

    public String getCalendar_date() {
        return calendar_date;
    }
    public void setCalendar_date(String calendar_date) {
        this.calendar_date = calendar_date;
    }
    public String getCalendar_icon() {
        return calendar_icon;
    }
    public void setCalendar_icon(String calendar_icon) {
        this.calendar_icon = calendar_icon;
    }
    public String getCalendar_memo() {
        return calendar_memo;
    }
    public void setCalendar_memo(String calendar_memo) {
        this.calendar_memo = calendar_memo;
    }

    public String getCalendar_hour() {
        return calendar_hour;
    }

    public void setCalendar_hour(String calendar_hour) {
        this.calendar_hour = calendar_hour;
    }

    public String getCalendar_minute() {
        return calendar_minute;
    }

    public void setCalendar_minute(String calendar_minute) {
        this.calendar_minute = calendar_minute;
    }
}
