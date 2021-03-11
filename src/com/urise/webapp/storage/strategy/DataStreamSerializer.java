package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements Strategy {
    public void write(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                System.out.println(entry.getKey().name());
                dos.writeUTF(entry.getValue());
                System.out.println(entry.getValue());
            });
            writeWithException(resume.getSections().entrySet(), dos, entry -> {
                SectionType type = entry.getKey();
                dos.writeUTF(type.name());
                AbstractSection section = entry.getValue();
                switch (type) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((SingleLineSection) section).getText());
                    case ACHIEVEMENT, QUALIFICATIONS -> writeWithException(((BulletedLineSection) section).getList(), dos, dos::writeUTF);
                    case EXPERIENCE, EDUCATION -> writeWithException(((Organisation) section).getList(), dos, experience -> {
                        dos.writeUTF(experience.getName());
                        dos.writeUTF(experience.getUrl());
                        writeWithException(experience.getCases(), dos, aCase -> {
                            dos.writeUTF(aCase.getPosition());
                            writeDate(dos, aCase.getStartDate());
                            writeDate(dos, aCase.getEndDate());
                            dos.writeUTF(aCase.getInfo());
                        });
                    });
                }
            });
        }
    }

    public Resume read(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readMap(dis, () -> {
                String a = dis.readUTF();
                String b = dis.readUTF();
                System.out.println("Contact:" + a + "Contact content:" + b);
                resume.setContact(ContactType.valueOf(a), b);
            });

            readMap(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                System.out.println(sectionType);
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> resume.setSection(sectionType, new SingleLineSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS -> resume.setSection(sectionType, new BulletedLineSection(readCollection(dis, dis::readUTF)));
                    case EXPERIENCE, EDUCATION -> resume.setSection(sectionType, new Organisation(readCollection(dis, () ->
                            new Experience(dis.readUTF(), new URL(dis.readUTF()), readCollection(dis, () ->
                                    new Experience.Case(dis.readUTF(), readDate(dis), readDate(dis), dis.readUTF()))))));
                    default -> throw new IllegalStateException("Unexpected value: " + sectionType);
                }
            });
            return resume;
        }
    }

    private AbstractSection readSection(DataInputStream dis) throws IOException {
        return new Organisation(readCollection(dis, () ->
                new Experience(dis.readUTF(), new URL(dis.readUTF()), readCollection(dis, () ->
                        new Experience.Case(dis.readUTF(), readDate(dis), readDate(dis), dis.readUTF())))));
    }

    private interface CollectionWriter<T> {
        void write(T t) throws IOException;
    }

    private interface CollectionReader<T> {
        T read() throws IOException;
    }

    private interface MapReader {
        void readItem() throws IOException;
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, CollectionWriter<T> collectionWriter) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            collectionWriter.write(item);
        }
    }

    private <T> List<T> readCollection(DataInputStream dis, CollectionReader<T> collectionReader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(collectionReader.read());
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

    private void readMap(DataInputStream dis, MapReader mapReader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            mapReader.readItem();
        }
    }
}

