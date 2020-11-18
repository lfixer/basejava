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
    protected Resume innerGet(Object key) {
        return storage.get((Integer) key);
    }


    @Override
    protected Object getKey(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }

    @Override
    protected void innerUpdate(Object key, Resume resume) {
        storage.set((Integer) key, resume);
    }

    @Override
    protected void innerDelete(Object key) {
        int index = (Integer) key;
        storage.remove(index);
    }

    @Override
    protected void innerSave(Object key, Resume resume) {
        storage.add(resume);
    }

}
