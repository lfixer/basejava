package com.urise.webapp.model;

public class TextData extends DataType {
    private String text;

    public TextData(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
