package com.urise.webapp;

import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;

public class MainTestSortedArrayStorage {
    static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        Resume r7 = new Resume();
        Resume r2 = new Resume();
        Resume r3 = new Resume();
        Resume r5 = new Resume();
        Resume r6 = new Resume();

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r6);
        ARRAY_STORAGE.save(r7);
        ARRAY_STORAGE.update(r6);

        printAll();
        ARRAY_STORAGE.delete(r5.getUuid());
        ARRAY_STORAGE.delete(r6.getUuid());
        ARRAY_STORAGE.delete(r3.getUuid());
        printAll();
        ARRAY_STORAGE.delete(r2.getUuid());
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
