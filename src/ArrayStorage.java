/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int ResumeCounter;

    void clear() {
        for (int i = 0; i < ResumeCounter; i++) {
            storage[i] = null;
        }
        ResumeCounter = 0;
    }

    void save(Resume r) {
        storage[ResumeCounter] = r;
        ResumeCounter++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < ResumeCounter; i++) {
            if (storage[i].uuid.equals(uuid))
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        int i = 0;
        while (!storage[i].uuid.equals(uuid)) {
            i++;
        }
        storage[i] = null;
        if (ResumeCounter - i + 1 >= 0) System.arraycopy(storage, i + 1, storage, i + 1 - 1, ResumeCounter - i + 1);
        storage[ResumeCounter - 1] = null;
        ResumeCounter--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] ResumesWithoutNulls = new Resume[ResumeCounter];
        if (ResumeCounter >= 0) System.arraycopy(storage, 0, ResumesWithoutNulls, 0, ResumeCounter);
        return ResumesWithoutNulls;
    }

    int size() {
        return (ResumeCounter);
    }
}
