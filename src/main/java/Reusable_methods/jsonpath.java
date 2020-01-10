package Reusable_methods;

import java.io.IOException;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class jsonpath 
{
   public static JsonPath StringtoJson(Response r) throws IOException
   {
       String res_string=r.asString();
	   JsonPath js= new JsonPath(res_string);
	   
	   return js;
   }
   public static XmlPath Stringtoxml(Response r)
   {
	   String res_string=r.asString();
	   XmlPath js= new XmlPath(res_string);
	   
	   return js;
	   
	   
   }
}
