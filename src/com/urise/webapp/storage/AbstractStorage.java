package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        innerUpdate(checkKey(resume.getUuid()), resume);
        System.out.println("Resume " + resume.getUuid() + " is updated");
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        innerDelete(checkKey(uuid));
        System.out.println("Resume " + uuid + " is deleted");
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK key = getKey(resume.getUuid());
        if (isNotExist(key)) {
            innerSave(key, resume);
            System.out.println("Resume " + resume.getUuid() + " is saved");
        } else {
            LOG.warning("Resume " + resume.getUuid() + " is exist");
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return innerGet(checkKey(uuid));
    }

    private SK checkKey(String uuid) {
        SK key = getKey(uuid);
        if (isNotExist(key)) {
            LOG.warning("Resume " + uuid + " is not exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = getList();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> getList();

    protected abstract Resume innerGet(SK key);

    protected abstract boolean isNotExist(SK key);

    protected abstract SK getKey(String uuid);

    protected abstract void innerUpdate(SK key, Resume resume);

    protected abstract void innerDelete(SK key);

    protected abstract void innerSave(SK key, Resume resume);

}
