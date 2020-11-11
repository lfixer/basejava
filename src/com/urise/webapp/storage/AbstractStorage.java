package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public abstract class AbstractStorage implements Storage {
    protected Storage storage;
    protected int counter = 0;

    public abstract void clear();

    public abstract void save(Resume resume);

    public abstract Resume get(String uuid);

    public abstract void delete(String uuid);

    public abstract Resume[] getAll();

    public abstract int size();

    public abstract void update(Resume resume);
}
