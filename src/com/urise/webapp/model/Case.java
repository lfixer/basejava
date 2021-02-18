package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Case implements Serializable {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String position;
    private final String info;

    public Case(String position, LocalDate startDate, LocalDate endDate, String info) {
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.info = info;
    }

    @Override
    public String toString() {
        return startDate.getYear() + "." + startDate.getMonth() + " - " + endDate.getYear() + "." + endDate.getMonth() + "   " + position + "\n" + info + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Case)) return false;
        Case c = (Case) obj;
        return position.equals(c.position) && startDate.equals(c.startDate) && endDate.equals(c.endDate) && info.equals(c.info);
    }
}
