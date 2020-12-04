package com.urise.webapp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EducationPlace extends Place {
    private String course;

    public EducationPlace(String name, String startDate, String endDate, String course) {
        try {
            this.name = name;
            this.startDate = new SimpleDateFormat("MM.yyyy").parse(startDate);
            this.endDate = new SimpleDateFormat("MM.yyyy").parse(endDate);
            this.course = course;
        }
        catch (ParseException e) {
            System.out.println("Invalid date format");
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.yyyy");
        return  "\n" + name + "\n" + dateFormat.format(startDate) + " - " + dateFormat.format(endDate) + "\n" + course;
    }
}
