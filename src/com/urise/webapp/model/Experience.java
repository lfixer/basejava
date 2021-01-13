package com.urise.webapp.model;

import java.net.URL;
import java.time.LocalDate;

public class Experience {
    private final String name;
    private final URL url;
    private final Case[] cases;

    public Experience(String name, URL url, Case...cases) {
        this.name = name;
        this.url = url;
        this.cases = cases;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\n" + name + "\n" + url + "\n");
        for (Case c: cases) {
            result.append(c.toString());
        }
        return result.toString();
    }

    public static class Case {
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
    }
}
