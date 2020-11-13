package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapStorage extends AbstractStorage {

    @Override
    public void clear() {

    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    protected Resume innerGet(int index) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    protected void innerUpdate(int index, Resume resume) {

    }

    @Override
    protected void innerDelete(int index) {

    }

    @Override
    protected void innerSave(Resume resume, int index) {
    }
}
