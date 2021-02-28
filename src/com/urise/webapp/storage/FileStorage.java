package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FileStorage extends AbstractStorage<File> {
    private final File storage;

    private Strategy strategy;

    protected FileStorage(File storage, Strategy strategy) {
        Objects.requireNonNull(storage, "directory must not be null");

        this.strategy = strategy;
        if (!storage.isDirectory()) {
            throw new IllegalArgumentException(storage.getAbsolutePath() + " is not directory");
        }
        if (!storage.canRead() || !storage.canWrite()) {
            throw new IllegalArgumentException(storage.getAbsolutePath() + " is not readable/writable");
        }
        this.storage = storage;
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> resumes = new ArrayList<>();
        for (File f : filesList(storage)) {
            resumes.add(innerGet(f));
        }
        return resumes;
    }

    @Override
    protected Resume innerGet(File key) {
        try {
            return strategy.read(new BufferedInputStream(new FileInputStream(key)));
        } catch (IOException e) {
            throw new StorageException("File read error", key.getName());
        }
    }

    @Override
    protected boolean isExist(File key) {
        return key.exists();
    }

    @Override
    protected File getKey(String uuid) {
        return new File(storage, uuid);
    }

    @Override
    protected void innerUpdate(File key, Resume resume) {
        try {
            strategy.write(resume, new BufferedOutputStream(new FileOutputStream(key)));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid());
        }
    }

    @Override
    protected void innerDelete(File key) {
        if (!key.delete()) {
            throw new StorageException("Deleting error");
        }
    }

    @Override
    protected void innerSave(File key, Resume resume) {
        try {
            key.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + key.getAbsolutePath(), key.getName());
        }
        innerUpdate(key, resume);
    }

    @Override
    public void clear() {
        for (File f : filesList(storage)) {
            innerDelete(f);
        }
    }

    @Override
    public int size() {
        return filesList(storage).length;
    }

    protected File[] filesList(File storage) {
        File[] files = storage.listFiles();
        if (files == null) {
            throw new StorageException("Storage is empty");
        }
        return files;
    }

}
