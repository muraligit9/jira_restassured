package Reusable_methods;

import org.json.simple.JSONObject;

public class payloads
{

	public static String create_issue() 
	{
		JSONObject j=new JSONObject();
		JSONObject j1=new JSONObject();
		JSONObject j2=new JSONObject();
		JSONObject j3=new JSONObject();
		j3.put("name", "Bug");
		j2.put("key", "REST");
		j1.put("project", j2);
		j1.put("summary", "fourth issue");
		j1.put("description", "fourth defect created");
		j1.put("issuetype", j3);
		j.put("fields", j1);
		
		return j.toString();
	}

}
