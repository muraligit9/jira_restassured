package Reusable_methods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class property_load 
{
    public static Properties load_properties() throws IOException
    {
    	FileInputStream fis=new FileInputStream("C:\\Users\\MURALI\\eclipse-workspace\\RESTAPI\\RESTAPI_project\\src\\main\\java\\parameters.properties");
 	   Properties prop= new Properties();
 	   prop.load(fis);
 	   return prop;
    }
}
