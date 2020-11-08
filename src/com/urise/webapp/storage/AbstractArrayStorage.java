package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int counter = 0;

    public void clear() {
        Arrays.fill(storage, 0, counter - 1 + 1, null);
        counter = 0;
    }

    public int size() {
        return counter;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
            System.out.println("resume " + resume.getUuid() + " is updated");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public Resume[] getAll() {
        Resume[] resumes = new Resume[counter];
        System.arraycopy(storage, 0, resumes, 0, counter);
        return resumes;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (counter == storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else if (index < 0) {
            innerSave(resume, index);
            counter++;
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            innerDelete(index);
            storage[counter - 1] = null;
            counter--;
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void innerDelete(int index);

    protected abstract void innerSave(Resume resume, int index);

}
