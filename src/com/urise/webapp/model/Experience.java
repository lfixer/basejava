package com.urise.webapp.model;

import java.net.URL;
import java.time.LocalDate;

public class Experience {
    private final String name;
    private final URL url;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String position;
    private final String info;

    public Experience(String name, URL url, String position, LocalDate startDate, LocalDate endDate, String info) {
        this.name = name;
        this.url = url;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.info = info;
    }

    @Override
    public String toString() {
        return  "\n" + name + "\n" + url + "\n" + startDate.getYear() + "." + startDate.getMonth() + " - " + endDate.getYear() + "." + endDate.getMonth() + "   " + position + "\n" + info;
    }
}
