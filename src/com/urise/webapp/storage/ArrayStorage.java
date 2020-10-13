package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int counter;

    public void clear() {
        for (int i = 0; i < counter; i++) {
            storage[i] = null;
        }
        counter = 0;
    }

    public void save(Resume r) {
        if (counter == 10000)
            System.out.println("storage is full");
        else if (isSaved(r.getUuid()) == -1) {
            storage[counter] = r;
            counter++;
        } else
            System.out.println("resume is already exist");
    }

    public Resume get(String uuid) {
        int index = isSaved(uuid);
        if (index == -1) {
            System.out.println("resume is not found");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = isSaved(uuid);
        if (index == -1) {
            System.out.println("resume is not found");
        } else {
            storage[index] = storage[counter - 1];
            storage[counter - 1] = null;
            counter--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[counter];
        System.arraycopy(storage, 0, resumes, 0, counter);
        return resumes;
    }

    public int size() {
        return counter;
    }

    public void update(Resume r) {
        int index = isSaved(r.getUuid());
        if (index == -1) {
            System.out.println("resume is not found");
        } else {
            System.out.println("resume is updated");
        }
    }

    public int isSaved(String uuid) {
        for (int i = 0; i < counter; i++) {
            if (uuid.equals(storage[i].getUuid()))
                return i;
        }
        return -1;
    }
}
