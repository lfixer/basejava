package com.urise.webapp.model;

import java.util.ArrayList;

public class ListData extends DataType {
    private final ArrayList<String> list;

    public ListData(ArrayList<String> list) {
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
}