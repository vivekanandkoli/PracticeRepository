package TestDataReaders;

import java.util.HashMap;
import java.util.Map;

/**
 * Use this class to get readers for any test data file, it will cache them for higher performance
 */
public class TestDataReaderFactory {
    private static TestDataReaderFactory instance;
    private Map<String, TestDataReader> openReaders;

    private TestDataReaderFactory() {
        openReaders = new HashMap<String, TestDataReader>();
    }

    public static TestDataReaderFactory getInstance() {
        if (instance == null) {
            instance = new TestDataReaderFactory();
        }
        return instance;
    }

    /**
     * Instantiate a new reader for the given test data file.
     * If a reader was already open for that file it will reuse it.
     *
     * @param path Path to the test data  file
     * @return The test data reader that can be used to obtain the values
     */
    public TestDataReader open(String path) {
        TestDataReader testDataReader;
        if (!openReaders.containsKey(path)) {
            testDataReader = new TestDataReader(path);
            openReaders.put(path, testDataReader);
        } else {
            testDataReader = openReaders.get(path);
        }
        return testDataReader;
    }
}
