package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.strategy.Strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private final Path storage;

    private final Strategy strategy;

    protected PathStorage(String dir, Strategy strategy) {
        storage = Paths.get(dir);
        Objects.requireNonNull(storage, "directory must not be null");
        this.strategy = strategy;
        if (!Files.isDirectory(storage) || !Files.isWritable(storage)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::innerDelete);
    }

    @Override
    public int size() {
        return ((int) getFilesList().count());
    }

    @Override
    protected Path getKey(String uuid) {
        return storage.resolve(uuid);
    }

    @Override
    protected void innerUpdate(Path key, Resume resume) {
        try {
            strategy.write(resume, new BufferedOutputStream(Files.newOutputStream(key)));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path key) {
        return Files.isRegularFile(key);
    }

    @Override
    protected void innerSave(Path key, Resume resume) {
        try {
            Files.createFile(key);
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path " + key.getFileName(), resume.getUuid(), e);
        }
        innerUpdate(key, resume);
    }

    @Override
    protected Resume innerGet(Path key) {
        try {
            return strategy.read(new BufferedInputStream(Files.newInputStream(key)));
        } catch (IOException e) {
            throw new StorageException("Path read error" + key.getFileName(), e);
        }
    }

    @Override
    protected void innerDelete(Path key) {
        try {
            Files.delete(key);
        } catch (IOException e) {
            throw new StorageException("Path delete error" + key.getFileName(), e);
        }
    }

    @Override
    protected List<Resume> getAll() {
        return getFilesList().map(this::innerGet).collect(Collectors.toList());
    }

    protected Stream<Path> getFilesList() {
        try {
            return Files.list(storage);
        } catch (IOException e) {
            throw new StorageException("Path read error" + storage.getFileName(), e);
        }
    }
}
