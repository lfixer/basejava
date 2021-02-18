package com.urise.webapp.model;

public class SingleLineSection extends AbstractSection {
    private static final long SerialVersionUID = 1L;

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SingleLineSection)) return false;
        return text.equals(((SingleLineSection) obj).text);
    }
}
