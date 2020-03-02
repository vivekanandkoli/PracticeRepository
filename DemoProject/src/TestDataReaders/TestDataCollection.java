package TestDataReaders;

import java.util.HashMap;

/**
 * HashMap implementation that models a collection in a test data file and allows access to its values.
 */
public class TestDataCollection extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;
    private TestDataCollection cachedAllCollections;

    public TestDataCollection() {
        super();
    }

    /**
     * Creates a new instance based on an existing object.
     * Never use casts to transform an object into a TestDataCollection, use this constructor instead.
     *
     * @param fromObject Object to be converted into a TestDataCollection
     */
    @SuppressWarnings("unchecked")
    public TestDataCollection(Object fromObject) {
        super((HashMap<String, Object>) fromObject);
    }

    /**
     * Returns a sub-collection stored under the given key
     *
     * @param key
     * @return Collection of test data (strings or sub-collections)
     */
    public TestDataCollection getCollection(String key) 
    {
        return new TestDataCollection(this.get(key));
    }

    /**
     * Returns a string stored under the given key
     *
     * @param key
     * @return Test data as a string
     */
    public String getString(String key) {
        return (String) this.get(key);
    }

    /**
     * Returns a integer stored under the given key
     *
     * @param key
     * @return Test data as a integer
     */
    public int getInteger(String key) {
        return (int) this.get(key);
    }

    /**
     * Returns all values stored under the given key in a String array
     *
     * @param key
     * @return Test data as a string array
     */
     public String[] getStringArray(String key) {
         String keyValues = (String) this.get(key);
         String keyValue[] = keyValues.split(",");

         for (int i = 0; i < keyValue.length; i++) {
             keyValue[i] = keyValue[i].trim();
         }
         return keyValue;
     }

    /**
     * Returns all the sub-collections contained in this collection
     *
     * @return Collection that contains all the sub-collections
     */
    public TestDataCollection getAllCollections() {
        if (cachedAllCollections == null) {
            cachedAllCollections = new TestDataCollection();
            for (Entry<String, Object> entry : this.entrySet()) {
                if (entry.getValue() instanceof String) {
                    continue;
                }
                cachedAllCollections.put(entry.getKey(), new TestDataCollection(entry.getValue()));
            }
        }
        return cachedAllCollections;
    }

    /**
     * Returns all the sub-collections that contain a property with the given value
     *
     * @param property Property to evaluate, must be part of the sub-collection
     * @param value Value that the property must have
     * @return Collection that contains all the sub-collections matching the given condition
     */
    public TestDataCollection getAllCollections(String property, String value) {
        TestDataCollection allCollections = new TestDataCollection();

        for (Entry<String, Object> entry : this.entrySet()) {
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

    /**
     * Returns all values stored under the given key in an integer array
     *
     * @param key
     * @return Test data as a string array
     */
     public int[] getIntegerArray(String key) {
         String keyValues = (String) this.get(key);
         String keyValue[] = keyValues.split(",");

         Integer[] integerarray=new Integer[keyValue.length];
         int i=0;
         int[] toIntArray = new int[integerarray.length];;
         for(String str:keyValue){
             integerarray[i]=Integer.parseInt(str);
             i++;
         }
         for (int j = 0; j < integerarray.length; j++) {
             toIntArray[j] = integerarray[j].intValue();
         }
         return toIntArray;
     }
}
