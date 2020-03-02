package TestDataReaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

/**
 * Provides the ability to read data from YAML files
 */
public class YamlFileReader {

    protected Map<String, Object> yamlData;


    /**
     * Opens and parses the file and makes the data available through the get methods
     *
     * @param pathToFile File to open
     */
    @SuppressWarnings("unchecked")
    public YamlFileReader(String pathToFile) {
        Yaml yaml = new Yaml();
        try {
            InputStream input = new FileInputStream(new File(pathToFile));
            yamlData = (Map<String, Object>) yaml.load(input);
        } catch (Throwable e) {
            throw new RuntimeException("Cannot load YAML file " + pathToFile + ": " + e.getMessage(), e);
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if there is no mapping for the key.
     *
     * @param key
     * @return Value stored in the file for that key
     */
    public Object get(String key) {
        return yamlData.get(key);
    }

    /**
     * Returns the string to which the specified key is mapped.
     *
     * @param key
     * @return Value stored in the file for that key, converted to a String
     */
    public String getString(String key) {
        return (String) yamlData.get(key);
    }
}
