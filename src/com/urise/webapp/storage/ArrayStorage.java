package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void innerArraySave(Resume resume, int index) {
        storage[counter] = resume;
    }

    @Override
    protected void innerArrayDelete(int index) {
        storage[index] = storage[counter - 1];
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < counter; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

}
