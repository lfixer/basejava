package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * Initial resume class
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long SerialVersionUID = 1L;

    private String uuid;
    private String fullName;

    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    public Resume() {
    }

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

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public void setContact(ContactType contact, String data) {
        contacts.put(contact, data);
    }

    public void setSection(SectionType section, AbstractSection data) {
        sections.put(section, data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) && Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, sections, contacts);
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
