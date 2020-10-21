package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        if (counter == storage.length) {
            System.out.println("storage is full");
        } else if (getIndex(resume.getUuid()) == -1) {
            storage[counter] = resume;
            counter++;
        } else {
            System.out.println("resume " + resume.getUuid() + " is already exist");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("resume " + uuid + " is not found");
        } else {
            storage[index] = storage[counter - 1];
            storage[counter - 1] = null;
            counter--;
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < counter; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
