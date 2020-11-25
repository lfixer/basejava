package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapHashStorage extends AbstractStorage {
    private Map<Integer, Resume> storage = new HashMap<>();

    @Override
    protected List<Resume> getList() {
        return null;
    }

    @Override
    protected Resume innerGet(Object key) {
        return null;
    }

    @Override
    protected boolean isNotExist(Object key) {
        return false;
    }

    @Override
    protected Object getKey(String uuid) {
        return null;
    }

    @Override
    protected void innerUpdate(Object key, Resume resume) {

    }

    @Override
    protected void innerDelete(Object key) {

    }

    @Override
    protected void innerSave(Object key, Resume resume) {
        storage.put(resume.hashCode(), resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
