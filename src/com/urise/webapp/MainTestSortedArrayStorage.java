package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;

import java.io.FileNotFoundException;

public class MainTestSortedArrayStorage {
    static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("name1");
        Resume r2 = new Resume("name2");
        Resume r3 = new Resume("name3");
        Resume r4 = new Resume("name4");
        Resume r5 = new Resume("name5");
        Resume r6 = new Resume("name6");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r6);

        printAll();
        ARRAY_STORAGE.delete(r6.getUuid());
        ARRAY_STORAGE.delete(r3.getUuid());
        printAll();
        ARRAY_STORAGE.delete(r2.getUuid());
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
