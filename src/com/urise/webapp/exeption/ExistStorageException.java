package com.urise.webapp.exeption;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " is already exist", uuid);
    }
}
