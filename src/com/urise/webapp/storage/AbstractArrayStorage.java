package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int counter = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, counter, null);
        counter = 0;
    }

    @Override
    public int size() {
        return counter;
    }


    @Override
    protected void innerSave(Integer key, Resume resume) {
        if (counter == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        innerArraySave(resume, key);
        counter++;
    }

    @Override
    protected void innerDelete(Integer key) {
        innerArrayDelete(key);
        storage[counter-- - 1] = null;
    }

    @Override
    protected Resume innerGet(Integer key) {
        return storage[key];
    }

    @Override
    protected void innerUpdate(Integer key, Resume resume) {
        storage[key] = resume;
    }

    @Override
    protected boolean isNotExist(Integer key) {
        return key < 0;
    }

    @Override
    protected List<Resume> getList() {
        return Arrays.asList(Arrays.copyOf(storage, size()));
    }

    protected abstract void innerArraySave(Resume resume, int index);

    protected abstract void innerArrayDelete(int index);
}
