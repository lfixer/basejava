package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

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
        if (isNotExist(key)) {
            innerSave(key, resume);
            System.out.println("Resume " + resume.getUuid() + " is saved");
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        return innerGet(checkKey(uuid));
    }

    private Object checkKey(String uuid) {
        Object key = getKey(uuid);
        if (isNotExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = getList();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> getList();

    protected abstract Resume innerGet(Object key);

    protected abstract boolean isNotExist(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract void innerUpdate(Object key, Resume resume);

    protected abstract void innerDelete(Object key);

    protected abstract void innerSave(Object key, Resume resume);

}
