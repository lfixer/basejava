package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void innerSave(Resume resume, int index) {
        int newIndex = -index - 1;
        System.arraycopy(storage, newIndex + 1 - 1, storage, newIndex + 1, counter - newIndex);
        storage[-index - 1] = resume;
    }

    protected void innerDelete(int index) {
        if (counter - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, counter - 1 - index);
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, counter, searchKey);
    }

}
