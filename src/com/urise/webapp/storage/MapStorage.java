package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

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
        return ((key == null) || getKey((String) key) == null) ? true : false;
    }

    @Override
    protected Object getKey(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void innerUpdate(Object key, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void innerDelete(Object key) {
        storage.remove(key);
    }

    @Override
    protected void innerSave(Object key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    public List<Resume> getList() {
        return new ArrayList(storage.values());
    }
}