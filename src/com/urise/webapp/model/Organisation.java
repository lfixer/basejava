package com.urise.webapp.model;

import java.util.List;

public class Organisation extends AbstractSection {
    private static final long SerialVersionUID = 1L;

    private final List<Experience> organizations;

    public Organisation(List<Experience> organizations) {
        this.organizations = organizations;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Experience organization : organizations) {
            result.append(organization.toString()).append("\n");
        }
        return result.toString();
    }

    public List<Experience> getList() {
        return organizations;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Organisation)) return false;
        return organizations.equals(((Organisation) obj).organizations);
    }
}
