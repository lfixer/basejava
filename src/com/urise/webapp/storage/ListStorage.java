package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

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
    public List<Resume> getList() {
        return storage;
    }

    @Override
    protected Resume innerGet(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    protected Object getKey(String uuid) {
        int index = -1;
        for (Resume r : storage) {
            index++;
            if (r.getUuid().equals(uuid))
                return index;
        }
        return -1;
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

    @Override
    protected boolean isNotExist(Object key) {
        return (Integer) key == -1;
    }

}
