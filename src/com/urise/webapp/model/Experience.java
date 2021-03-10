package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Experience implements Serializable {
    private static final long SerialVersionUID = 1L;

    private String name;
    private URL url;
    private List<Case> cases;

    public Experience() {
    }

    public Experience(String name, URL url) {
        this.name = name;
        this.url = url;
    }

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

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url.toString();
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(ArrayList<Case> cases) {
        this.cases = cases;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Case implements Serializable {
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String position;
        private String info;

        public Case() {
        }

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

        public String getPosition() {
            return position;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getInfo() {
            return info;
        }
    }
}
