package com.urise.webapp.model;

import java.time.LocalDate;

public class Organization {
    protected String name;
    protected LocalDate startDate;
    protected LocalDate endDate;
    private final String position;
    private final String info;

    public Organization(String name, String position, LocalDate startDate, LocalDate endDate, String info) {
        this.name = name;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.info = info;
    }

    @Override
    public String toString() {
        return  "\n" + name + "\n" + startDate.getYear() + "." + startDate.getMonth() + " - " + endDate.getYear() + "." + endDate.getMonth() + "   " + position + "\n" + info;
    }
}
