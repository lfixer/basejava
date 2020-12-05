package com.urise.webapp.model;

import java.util.List;

public class Experience extends AbstractSection {
    private final List<Organization> organizations;

    public Experience(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Organization organization : organizations) {
            result.append(organization.toString()).append("\n");
        }
        return result.toString();
    }
}
