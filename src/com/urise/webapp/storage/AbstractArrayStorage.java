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
    protected void innerSave(Resume resume, int index) {
        if (counter == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        innerArraySave(resume, index);
        counter++;
    }

    @Override
    protected void innerDelete(int index) {
        innerArrayDelete(index);
        storage[counter-- - 1] = null;
    }

    @Override
    protected Resume innerGet(int index) {
        return storage[index];
    }

    @Override
    protected void innerUpdate(int index, Resume resume) {
        storage[index] = resume;
    }

    protected abstract void innerArraySave(Resume resume, int index);

    protected abstract void innerArrayDelete(int index);
}
