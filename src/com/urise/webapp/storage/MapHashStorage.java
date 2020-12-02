package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapHashStorage extends AbstractStorage<Integer> {
    private final Map<Integer, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume innerGet(Integer key) {
        return storage.get(key);
    }

    @Override
    protected boolean isNotExist(Integer key) {
        return key == null;
    }

    @Override
    protected Integer getKey(String uuid) {
        return storage.containsKey(uuid.hashCode()) ? uuid.hashCode() : null;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void innerUpdate(Integer key, Resume resume) {
        storage.replace(key, resume);
    }

    @Override
    protected void innerDelete(Integer key) {
        storage.remove(key);
    }

    @Override
    protected void innerSave(Integer key, Resume resume) {
        storage.put(resume.getUuid().hashCode(), resume);
    }

    public List<Resume> getList() {
        return new ArrayList<>(storage.values());
    }
}