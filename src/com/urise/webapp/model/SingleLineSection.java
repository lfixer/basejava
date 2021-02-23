package com.urise.webapp.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleLineSection)) return false;
        SingleLineSection that = (SingleLineSection) o;
        return getText().equals(that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText());
    }
}
