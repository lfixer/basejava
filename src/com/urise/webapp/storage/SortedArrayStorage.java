package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void innerSave(Resume resume, int index) {
        if (counter == 0) {
            storage[0] = resume;
            counter++;
        } else if (-index - 1 == counter) {
            storage[-index - 1] = resume;
            counter++;
        } else if (counter > 0 && index < 0 && (-index - 1) != counter) {
            for (int i = counter - 1; i > -index - 2; i--) {
                storage[i + 1] = storage[i];
            }
            storage[-index - 1] = resume;
            counter++;
        }
    }

    protected void innerDelete(int index) {
        for (int i = index; i < counter - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, counter, searchKey);
    }

}
