package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organisation extends AbstractSection {
    private static final long SerialVersionUID = 1L;

    private ArrayList<Experience> organizations;

    public Organisation() {
    }

    public Organisation(ArrayList<Experience> organizations) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organisation)) return false;
        Organisation that = (Organisation) o;
        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }
}
