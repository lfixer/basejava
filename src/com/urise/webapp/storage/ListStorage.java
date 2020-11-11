package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ListStorage extends AbstractStorage {
    ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume resume) {
        if (storage.indexOf(resume) > 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        else {
            storage.add(resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        Iterator<Resume> iterator = storage.iterator();
        while(iterator.hasNext()) {
            Resume resume = iterator.next();
            if (resume.getUuid().equals(uuid))
                return resume;
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        int index = storage.indexOf(get(uuid));
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            storage.remove(index);
            System.out.println("resume " + uuid + " is deleted");
        }
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = storage.toArray(Resume[]::new);
        return resumes;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void update(Resume resume) {
        int index = storage.indexOf(resume);
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage.set(index, resume);
            System.out.println("resume " + resume.getUuid() + " is updated");
        }
    }
}
