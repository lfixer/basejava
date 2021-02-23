package com.urise.webapp.model;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Experience implements Serializable {
    private static final long SerialVersionUID = 1L;

    private final String name;
    private final URL url;
    private final List<Case> cases;

    public Experience(String name, URL url, Case...cases) {
        this.name = name;
        this.url = url;
        this.cases = Arrays.asList(cases);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\n" + name + "\n" + url + "\n");
        for (Case c: cases) {
            result.append(c.toString());
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Experience)) return false;
        Experience that = (Experience) o;
        return name.equals(that.name) &&
                url.equals(that.url) &&
                cases.equals(that.cases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, cases);
    }

    public static class Case implements Serializable {
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Case)) return false;
            Case aCase = (Case) o;
            return startDate.equals(aCase.startDate) &&
                    endDate.equals(aCase.endDate) &&
                    position.equals(aCase.position) &&
                    info.equals(aCase.info);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, position, info);
        }
    }
}
