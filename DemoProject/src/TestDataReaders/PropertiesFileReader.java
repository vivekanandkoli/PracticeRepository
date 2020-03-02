package TestDataReaders;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Load and Read data form Properties File

 * @author Vivekanand.Koli
 *
 */

	public class PropertiesFileReader {

	Properties prop=new Properties();
	
/**
 * This method loads the default properties file
 * @return 
 */

	public void loadPropertiesFile() 
	{
	InputStream input=null;
	try 
	{
		input=new FileInputStream("config.properties");
		//Load Properties file
		prop.load(input);
	}
	catch(IOException ex)
	{
		ex.printStackTrace();
	}

	}
	
	/**
	 * Obtain the value of given property	
	 */
	
	public String getPropertyValue(String property)
	{
        return prop.getProperty(property);
    }
	
	/**
	 *set new property value in existing properties file 
	 * @param property
	 * @param value
	 */
	
	public void setPropertyValue(String property,String value) {
		
		prop.setProperty(property, value);
	}
	
}

