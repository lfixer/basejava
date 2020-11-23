package com.urise.webapp.model;

import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }
    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public int hashCode() {
        return (uuid.hashCode()*10 + fullName.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (this == null || getClass() != object.getClass()) return false;
        Resume resume = (Resume) object;
        if (!(uuid.equals(resume.uuid)))
            return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int compareTo(Resume o) {
        if (fullName.equals(o.fullName))
            return uuid.compareTo(o.uuid);
        else
            return fullName.compareTo(o.fullName);
    }

    @Override
    public String toString() {
        return uuid + ", " + fullName;
    }
}
