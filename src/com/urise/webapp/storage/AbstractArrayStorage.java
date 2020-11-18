package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int counter = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, counter - 1 + 1, null);
        counter = 0;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[counter];
        System.arraycopy(storage, 0, resumes, 0, counter);
        return resumes;
    }

    @Override
    protected void innerSave(Object key, Resume resume) {
        if (counter == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        innerArraySave(resume, (Integer) key);
        counter++;
    }

    @Override
    protected void innerDelete(Object key) {
        innerArrayDelete((Integer) key);
        storage[counter-- - 1] = null;
    }

    @Override
    protected Resume innerGet(Object key) {
        return storage[(Integer) key];
    }

    @Override
    protected void innerUpdate(Object key, Resume resume) {
        storage[(Integer) key] = resume;
    }


    protected abstract void innerArraySave(Resume resume, int index);

    protected abstract void innerArrayDelete(int index);
}
