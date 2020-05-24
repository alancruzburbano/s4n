package main.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    private Properties properties = new Properties();

    public void initProperties() {
        try {
            FileReader propertiesStream = new FileReader("application.properties");
            properties.load(propertiesStream);
        } catch (IOException e) {
            System.out.println("load properties failed");
            e.printStackTrace();
        }
    }

    public String readPropertieByKey(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

}
