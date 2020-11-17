package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> storage = new TreeMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume innerGet(Object key) {
        return storage.get(key);
    }

    @Override
    protected Object getKey(String uuid) {
        if (storage.containsKey(uuid))
            return uuid;
        return "";
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
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
        storage.remove((String) key);
    }

    @Override
    protected void innerSave(Object key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }
}