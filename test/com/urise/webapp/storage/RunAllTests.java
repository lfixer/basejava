package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MapStorageTest.class,
        ListStorageTest.class,
        SortedArrayStorageTest.class,
        ArrayStorageTest.class,
        MapHashStorageTest.class,
        FileStorageTest.class,
        PathStorageTest.class,
        XmlPathStorageTest.class,
        JsonPathStorageTest.class,
        DataPathStorageTest.class,
})
public class RunAllTests {
}
