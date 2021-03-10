package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Strategy {
    void write(Resume r, OutputStream os) throws IOException;

    Resume read(InputStream is) throws IOException;
}
