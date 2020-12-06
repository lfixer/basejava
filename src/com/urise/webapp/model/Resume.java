package com.urise.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;

    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

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

    public String getFullName() {
        return fullName;
    }

    public void setContact(ContactType contact, String data) {
        contacts.put(contact, data);
    }

    public void setData(SectionType section, String data) {
        sections.put(section, new SingleLineSection(data));
    }

    public <T> void setData(SectionType section, ArrayList<T> data) {
        if (data.get(0) instanceof String) {
            sections.put(section, new BulletedLineSection((ArrayList<String>) data));
        } else {
            sections.put(section, new Organisation((List<Experience>) data));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;
        Resume resume = (Resume) o;
        return Objects.equals(getUuid(), resume.getUuid()) &&
                Objects.equals(getFullName(), resume.getFullName()) &&
                Objects.equals(sections, resume.sections) &&
                Objects.equals(contacts, resume.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getFullName(), sections, contacts);
    }

    @Override
    public int compareTo(Resume o) {
        int comparator = fullName.compareTo(o.fullName);
        return comparator != 0 ? comparator : uuid.compareTo(o.uuid);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Имя: ").append(fullName).append("\nid: ").append(uuid).append("\n");
        for (ContactType key : contacts.keySet()) {
            result.append(key).append(" : ").append(contacts.get(key)).append("\n");
        }
        for (SectionType key : sections.keySet()) {
            result.append(key.toString()).append(" : ").append(sections.get(key).toString()).append("\n\n");
        }
        return String.valueOf(result);
    }
}
