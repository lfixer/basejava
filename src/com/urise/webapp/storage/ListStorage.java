package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    private ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(Resume[]::new);
    }

    @Override
    protected Resume innerGet(int index) {
        return storage.get(index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }

    @Override
    protected void innerUpdate(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected void innerDelete(int index) {
        storage.remove(index);
    }

    @Override
    protected boolean innerSave(Resume resume, int index) {
        storage.add(resume);
        return true;
    }

}
