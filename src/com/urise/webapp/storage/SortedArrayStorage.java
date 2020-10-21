package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        System.out.println(index);
        if (counter == storage.length) {
            System.out.println("storage is full");
        } else if (counter == 0) {
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
        } else {
            System.out.println("resume " + resume.getUuid() + " is already exist");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("resume " + uuid + " is not found");
        } else {
            for (int i = index; i < counter - 1; i++) {
                storage[i] = storage[i + 1];
            }
            counter--;
        }
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, counter, searchKey);
    }

}
