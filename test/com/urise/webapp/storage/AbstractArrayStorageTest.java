package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.RealSystem;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage = new ArrayStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";


    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setup() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_2);
        storage.update(resume);
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        Resume resume = new Resume("dummy");
        storage.update(resume);
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_2, storage.get(UUID_2).getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception{
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] resumes1 = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Resume[] resumes2 = storage.getAll();
        Assert.assertArrayEquals(resumes1, resumes2);
    }

    @Test
    public void save() {
        storage.save(new Resume("uuid4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume("uuid3"));
    }

    @Test
    public void delete() throws Exception {
        storage.delete("uuid2");
        Assert.assertEquals(2, storage.size());
        storage.delete("uuid1");
        Assert.assertEquals(1, storage.size());
        storage.delete("uuid3");
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void storageOverflow() throws Exception {
        try {
            storage.save(new Resume("uuid4"));
            Assert.fail("Overflow is not expected");
        } catch (AssertionError e) {
            
        }
    }
}