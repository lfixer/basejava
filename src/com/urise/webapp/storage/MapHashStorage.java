package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapHashStorage extends AbstractStorage {
    private Map<Integer, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume innerGet(Object key) {
        return storage.get(key);
    }

    @Override
    protected boolean isNotExist(Object key) {
        return (key == null) ? true : false;
    }

    @Override
    protected Object getKey(String uuid) {
        return storage.containsKey(uuid.hashCode()) ? uuid.hashCode() : null;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void innerUpdate(Object key, Resume resume) {
        storage.replace((Integer) key, resume);
    }

    @Override
    protected void innerDelete(Object key) {
        storage.remove(key);
    }

    @Override
    protected void innerSave(Object key, Resume resume) {
        storage.put(resume.getUuid().hashCode(), resume);
    }

    public List<Resume> getList() {
        return new ArrayList(storage.values());
    }
}