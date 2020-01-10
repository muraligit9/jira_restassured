package Reusable_methods;

import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;

public class declaringhost 
{
  public void host_initialization() throws IOException
  {
	  Properties p=property_load.load_properties();
	   RestAssured.baseURI=p.getProperty("host");
  }
}
