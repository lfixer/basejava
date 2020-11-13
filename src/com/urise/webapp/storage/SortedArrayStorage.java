package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void innerArraySave(Resume resume, int index) {
        int newIndex = -index - 1;
        System.arraycopy(storage, newIndex , storage, newIndex + 1, counter - newIndex);
        storage[-index - 1] = resume;
    }

    @Override
    protected void innerArrayDelete(int index) {
        if (counter - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, counter - 1 - index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, counter, searchKey);
    }

}
