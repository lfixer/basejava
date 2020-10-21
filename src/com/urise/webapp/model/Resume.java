package com.urise.webapp.model;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    // Unique identifier
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (this == null || getClass() != object.getClass()) return false;
        Resume resume = (Resume) object;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int compareTo(Resume o) {
        return Integer.parseInt(uuid.substring(4)) - Integer.parseInt(o.uuid.substring(4));
    }

    @Override
    public String toString() {
        return uuid;
    }
}
