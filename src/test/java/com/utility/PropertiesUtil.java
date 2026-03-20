package com.utility;

import com.constants.Env;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties properties = new Properties();

    public static String readPropertiesFile(Env env, String propertiesName)  {
        File propFile = new File(System.getProperty("user.dir") + "/config/"+env+".properties");
      FileReader fileReader = null;
      try {
        fileReader = new FileReader(propFile);
        properties.load(fileReader);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return properties.getProperty(propertiesName);
    }
}
