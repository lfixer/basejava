package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        checkIndex(index, resume.getUuid());
        innerUpdate(index, resume);
        System.out.println("Resume " + resume.getUuid() + " is updated");
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        checkIndex(index, uuid);
        innerDelete(index);
        System.out.println("Resume " + uuid + " is deleted");
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            innerSave(resume, index);
            System.out.println("Resume " + resume.getUuid() + " is saved");
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        checkIndex(index, uuid);
        return innerGet(index);
    }

    protected void checkIndex(int index, String uuid) {
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract Resume innerGet(int index);

    protected abstract int getIndex(String uuid);

    protected abstract void innerUpdate(int index, Resume resume);

    protected abstract void innerDelete(int index);

    protected abstract void innerSave(Resume resume, int index);

}
