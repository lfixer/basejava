package com.urise.webapp.model;

public class SingleLineSection extends AbstractSection {
    private final String text;

    public SingleLineSection(String text) {
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
