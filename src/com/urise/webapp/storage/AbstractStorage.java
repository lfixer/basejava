package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        innerUpdate(checkKey(resume.getUuid()), resume);
        System.out.println("Resume " + resume.getUuid() + " is updated");
    }

    public void delete(String uuid) {
        innerDelete(checkKey(uuid));
        System.out.println("Resume " + uuid + " is deleted");
    }

    public void save(Resume resume) {
        Object key = getKey(resume.getUuid());
        if ((key instanceof Integer && (Integer) key < 0) || key instanceof String && key.equals("")) {
            innerSave(key, resume);
            System.out.println("Resume " + resume.getUuid() + " is saved");
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        return innerGet(checkKey(uuid));
    }

    protected Object checkKey(String uuid) {
        Object key = getKey(uuid);
        if ((key instanceof Integer && (Integer) key < 0) || key instanceof String && key.equals("")) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    protected abstract Resume innerGet(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract void innerUpdate(Object key, Resume resume);

    protected abstract void innerDelete(Object key);

    protected abstract void innerSave(Object key, Resume resume);

}
