package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Objects;

public class BulletedLineSection extends AbstractSection {
    private static final long SerialVersionUID = 1L;

    private final ArrayList<String> list;

    public BulletedLineSection(ArrayList<String> list) {
        this.list = list;
    }

    public ArrayList<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String s : list) {
            result.append(s).append("\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BulletedLineSection)) return false;
        BulletedLineSection that = (BulletedLineSection) o;
        return getList().equals(that.getList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getList());
    }
}
