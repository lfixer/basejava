package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int counter = 0;

    public void clear() {
        Arrays.fill(storage, 0, counter-1+1 , null);
        counter = 0;
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

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("resume " + uuid + " is not found");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        Resume[] resumes = new Resume[counter];
        System.arraycopy(storage, 0, resumes, 0, counter);
        return resumes;
    }

    protected abstract int getIndex(String uuid);

}
