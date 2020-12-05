package com.urise.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    private final String uuid;
    private final String fullName;

    private final Map<SectionType, AbstractSection> sectionData = new EnumMap<>(SectionType.class);
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

    public String getFullName() { return fullName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;
        Resume resume = (Resume) o;
        return getUuid().equals(resume.getUuid()) &&
                fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), fullName);
    }

    @Override
    public int compareTo(Resume o) {
        int comparator = fullName.compareTo(o.fullName);
        return comparator != 0 ? comparator : uuid.compareTo(o.uuid);
    }

    @Override
    public String toString() {
        return uuid + ", " + fullName;
    }

    public String getContact(ContactType contact) {
        return contacts.get(contact);
    }

    public AbstractSection getData(SectionType type) {
        return sectionData.get(type);
    }

    public String getContacts() {
        StringBuilder result = new StringBuilder();
        for (ContactType key : contacts.keySet()) {
            result.append(key).append(" : ").append(contacts.get(key)).append("\n");
        }
        return result.toString();
    }

    public String getAllData() {
        StringBuilder result = new StringBuilder();
        for (SectionType key : sectionData.keySet()) {
            result.append(key.toString()).append(" : ").append(sectionData.get(key).toString()).append("\n\n");
        }
        return result + "\n";
    }

    public void setContact(ContactType contact, String data) {
        contacts.put(contact, data);
    }

    public void setData(SectionType section, String data) {
        sectionData.put(section, new SingleLineSection(data));
    }

    public void setData(SectionType section, ArrayList<String> data) {
        sectionData.put(section, new BulletedLineSection(data));
    }

    public void setExperience(SectionType section, ArrayList<Organization> data) {
        sectionData.put(section, new Experience(data));
    }

}
