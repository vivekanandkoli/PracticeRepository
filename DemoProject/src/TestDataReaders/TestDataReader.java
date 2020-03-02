package TestDataReaders;

import java.util.Map.Entry;

/**
 * Don't use this class directly, use its factory to get one instance
 *
 * @see TestDataReaderFactory
 */
public class TestDataReader extends YamlFileReader {

    // Paths to the main test data files
//    public static final String BASE_DIR = "test-data/";
//    public static final String USER_ACCOUNTS = BASE_DIR + "user-accounts.yml";

    public static final String BASE_DIR = "test-data/";
    public static final String USER_ACCOUNTS = BASE_DIR + "user-accounts.yml";
    public static final String REGISTRATION_DATA = BASE_DIR + "registrationData.yml";
    public static final String EMAIL_DATA = BASE_DIR + "Email.yml";

    // Cached values
    private TestDataCollection cachedAllCollections;

    /**
     * Create a new reader that will load the given test data file
     *
     * @param path Path to the file containing the test data
     */
    public TestDataReader(String path) {
        super(path);
    }

    /**
     * Returns the collection to which the specified key is mapped.
     *
     * @param key
     * @return Collection of test data (strings or sub-collections)
     */
    public TestDataCollection getCollection(String key) {
        return new TestDataCollection(yamlData.get(key));
    }

    /**
     * Returns all the collections contained in the file.
     * Only first-level collections will be included. First-level strings will be excluded.
     *
     * @return Collection that contains all the first-level collections in the file
     */
    public TestDataCollection getAllCollections() {
        if (cachedAllCollections == null) {
            cachedAllCollections = new TestDataCollection();
            for (Entry<String, Object> entry : yamlData.entrySet()) {
                if (entry.getValue() instanceof String) {
                    continue;
                }
                cachedAllCollections.put(entry.getKey(), new TestDataCollection(entry.getValue()));
            }
        }
        return cachedAllCollections;
    }

    /**
     * Returns all the first-level collections that contain a property with the given value
     *
     * @param property Property to evaluate, must be part of the collection
     * @param value Value that the property must have
     * @return Collection that contains all the collections matching the given condition
     */
    public TestDataCollection getAllCollections(String property, String value) {
        TestDataCollection allCollections = new TestDataCollection();

        for (Entry<String, Object> entry : yamlData.entrySet()) {
            // We discard strings, they can't contain properties
            if (entry.getValue() instanceof String) {
                continue;
            }
            TestDataCollection collection = new TestDataCollection(entry.getValue());
            if (collection.containsKey(property) && collection.get(property).equals(value)) {
                allCollections.put(entry.getKey(), collection);
            }
        }

        return allCollections;
    }
}
