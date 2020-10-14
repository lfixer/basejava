package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int counter;

    public void clear() {
        Arrays.fill(storage, 0, counter - 1, null);
        counter = 0;
    }

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

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("resume " + uuid + " is not found");
            return null;
        }
        return storage[index];
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

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("resume " + resume.getUuid() + " is not found");
        } else {
            storage[index] = resume;
            System.out.println("resume " + resume.getUuid() + " is updated");
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < counter; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
