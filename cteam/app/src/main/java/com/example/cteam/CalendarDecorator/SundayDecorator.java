package com.example.cteam.CalendarDecorator;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.example.cteam.Calendar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class SundayDecorator implements DayViewDecorator {

    private final java.util.Calendar calendar = java.util.Calendar.getInstance();

    public SundayDecorator() {
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekDay = calendar.get(java.util.Calendar.DAY_OF_WEEK);
        return weekDay == java.util.Calendar.SUNDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.RED));
    }
}
