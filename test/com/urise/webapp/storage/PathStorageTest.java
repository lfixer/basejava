package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new StreamStrategy()));
    }

    @Test
    @Override
    public void save() {
        storage.save(RESUME_4);
        Assert.assertEquals(4, storage.size());
        Resume r = storage.get(RESUME_4.getUuid());
        Assert.assertEquals(RESUME_4.getUuid(), r.getUuid());
        Assert.assertEquals(RESUME_4.getFullName(), r.getFullName());
    }

    @Test
    @Override
    public void get() {
        Resume r = storage.get(UUID_2);
        Assert.assertEquals(RESUME_2.getUuid(), r.getUuid());
        Assert.assertEquals(RESUME_2.getFullName(), r.getFullName());
    }

    @Test
    @Override
    public void getAllSorted() {
        List<Resume> expectedResumes = new ArrayList<>();
        expectedResumes.add(RESUME_1);
        expectedResumes.add(RESUME_2);
        expectedResumes.add(RESUME_3);
        List<Resume> actualResumes = storage.getAllSorted();
        Assert.assertEquals(expectedResumes.get(0).getFullName(), actualResumes.get(0).getFullName());
        Assert.assertEquals(expectedResumes.get(1).getFullName(), actualResumes.get(1).getFullName());
        Assert.assertEquals(expectedResumes.get(2).getFullName(), actualResumes.get(2).getFullName());
    }
}