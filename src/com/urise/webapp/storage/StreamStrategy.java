package com.urise.webapp.storage;


import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class StreamStrategy implements Strategy {

    public void write(Resume resume, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    public Resume read(InputStream is) {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
