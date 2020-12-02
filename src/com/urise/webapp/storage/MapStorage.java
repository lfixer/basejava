package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume innerGet(String key) {
        return storage.get(key);
    }

    @Override
    protected boolean isNotExist(String key) {
        return key == null;
    }

    @Override
    protected String getKey(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void innerUpdate(String key, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void innerDelete(String key) {
        storage.remove(key);
    }

    @Override
    protected void innerSave(String key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    public List<Resume> getList() {
        return new ArrayList<>(storage.values());
    }
}