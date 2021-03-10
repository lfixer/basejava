package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Strategy {
    public void write(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((SingleLineSection) section).getText());
                    case ACHIEVEMENT, QUALIFICATIONS -> writeList(dos, ((BulletedLineSection) section).getList());
                    case EXPERIENCE, EDUCATION -> writeOrganisation(dos, ((Organisation) section).getList());
                }
            }
        }
    }

    public Resume read(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> resume.setSection(sectionType, new SingleLineSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS -> resume.setSection(sectionType, new BulletedLineSection(readList(dis)));
                    case EXPERIENCE, EDUCATION -> resume.setSection(sectionType, new Organisation(readOrganisation(dis)));
                    default -> throw new IllegalStateException("Unexpected value: " + sectionType);
                }
            }
            return resume;
        }
    }

    private void writeList(DataOutputStream dos, ArrayList<String> list) throws IOException {
        dos.writeInt(list.size());
        for (String s : list) {
            dos.writeUTF(s);
        }
    }

    private ArrayList<String> readList(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        ArrayList<String> list = new ArrayList<>(size);
        for (int j = 0; j < size; j++) {
            list.add(dis.readUTF());
        }
        return list;
    }

    private void writeOrganisation(DataOutputStream dos, List<Experience> list) throws IOException {
        dos.writeInt(list.size());
        for (Experience e : list) {
            dos.writeUTF(e.getName());
            dos.writeUTF(e.getUrl());
            dos.writeInt(e.getCases().size());
            for (Experience.Case c : e.getCases()) {
                dos.writeUTF(c.getPosition());
                writeDate(dos, c.getStartDate());
                writeDate(dos, c.getEndDate());
                dos.writeUTF(c.getInfo());
            }
        }
    }

    private ArrayList<Experience> readOrganisation(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        ArrayList<Experience> list = new ArrayList<>(size);
        for (int j = 0; j < size; j++) {
            Experience e = new Experience(dis.readUTF(), new URL(dis.readUTF()));
            ArrayList<Experience.Case> cases = new ArrayList<>();
            int casesSize = dis.readInt();
            for (int k = 0; k < casesSize; k++) {
                cases.add(new Experience.Case(dis.readUTF(), readDate(dis), readDate(dis), dis.readUTF()));
            }
            e.setCases(cases);
            list.add(e);
        }
        return list;
    }

    private void writeDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonthValue());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }
}

