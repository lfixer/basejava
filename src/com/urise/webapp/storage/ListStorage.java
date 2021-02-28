package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public List<Resume> getAll() {
        return storage;
    }

    @Override
    protected Resume innerGet(Integer key) {
        return storage.get(key);
    }

    @Override
    protected Integer getKey(String uuid) {
        int index = -1;
        for (Resume r : storage) {
            index++;
            if (r.getUuid().equals(uuid))
                return index;
        }
        return -1;
    }

    @Override
    protected void innerUpdate(Integer key, Resume resume) {
        storage.set(key, resume);
    }

    @Override
    protected void innerDelete(Integer key) {
        int index = key;
        storage.remove(index);
    }

    @Override
    protected void innerSave(Integer key, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected boolean isExist(Integer key) {
        return key != -1;
    }

}
