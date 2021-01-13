package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File storage;

    @Override
    protected List<Resume> getList() {
        File[] files = storage.listFiles();
        if (files == null) {
            throw new StorageException("Storage is empty");
        }
        List<Resume> resumes = new ArrayList<>(files.length);
        for (File f: files) {
            resumes.add(innerGet(f));
        }
        return resumes;
    }

    @Override
    protected Resume innerGet(File key) {
        return readResumeFromFile(key);
    }

    @Override
    protected boolean isNotExist(File key) {
        return !key.exists();
    }

    @Override
    protected File getKey(String uuid) {
        return new File(storage, uuid);
    }

    @Override
    protected void innerUpdate(File key, Resume resume) {
        writeResume(key, resume);
    }

    @Override
    protected void innerDelete(File key) {
        if (!key.delete()) {
            throw new StorageException("Deleting error");
        }
    }

    @Override
    protected void innerSave(File key, Resume resume) {
        writeResume(key, resume);
    }

    @Override
    public void clear() {
        File[] files = storage.listFiles();
        if (files != null) {
            for (File f: files) {
                innerDelete(f);
            }
        }
    }

    @Override
    public int size() {
        String[] files = storage.list();
        if (files == null) {
            throw new StorageException("Storage is empty");
        }
        return files.length;
    }

    protected abstract Resume readResumeFromFile(File key);

    protected abstract void writeResume(File key, Resume resume);

}
