package com.urise.webapp.model;

import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

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
    public boolean equals(Object obj) {
        if (!(obj instanceof Experience)) return false;
        Experience experience = (Experience) obj;
        return name.equals(experience.name) && url.equals(experience.url) && cases.equals(experience.cases);
    }

}
