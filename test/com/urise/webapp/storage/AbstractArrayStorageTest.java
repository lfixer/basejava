package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() {
        try {
            for (int i = storage.size() + 1; i <= STORAGE_LIMIT; i++) {
                storage.save(new Resume("NAME_" + i));
            }
        } catch (StorageException e) {
            Assert.fail("Overflow is not expected");
        }
        storage.save(new Resume("dummy"));
    }
}
