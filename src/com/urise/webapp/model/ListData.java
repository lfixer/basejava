package com.urise.webapp.model;

import java.util.ArrayList;

public class ListData<T> extends DataType {
    private final ArrayList<T> list;

    public ListData(ArrayList<T> list) {
        this.list = list;
    }

    public ArrayList<T> getList() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (T t : list) {
            result.append(t.toString()).append("\n");
        }
        return result.toString();
    }
}
