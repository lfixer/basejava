package com.urise.webapp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class WorkPlace extends Place {
    private String position;
    private String responsibility;

    public WorkPlace(String name, String position, String startDate, String endDate, String responsibility) {
        try {
            this.name = name;
            this.position = position;
            this.startDate = new SimpleDateFormat("MM.yyyy").parse(startDate);
            this.endDate = new SimpleDateFormat("MM.yyyy").parse(endDate);
            this.responsibility = responsibility;
        }
        catch (ParseException e) {
            System.out.println("Invalid date format");
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.yyyy");
        return  "\n" + name + "\n" + dateFormat.format(startDate) + " - " + dateFormat.format(endDate) + "   "+ position + "\n" + responsibility;
    }
}
