package RESTAPI_package;

import java.io.IOException;
import java.util.Properties;

import javax.swing.text.AbstractDocument.Content;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Responses.get_responses;
import Reusable_methods.declaringhost;
import Reusable_methods.jsonpath;
import Reusable_methods.property_load;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class JIRA_End_to_End extends declaringhost
{
  jira_session_creation j;
  Properties p;
  get_responses g;
  Response get_comment_res;
  JsonPath get_comment_json;
  
  @BeforeTest
  public void Objects_initialization() throws IOException
  {
	  j=new jira_session_creation();
	  p=property_load.load_properties();
	  g=new get_responses();
  }
  
  @Test
  public void test() throws IOException
  {
	  
	  host_initialization();
	  String session_id=j.create_session(p.getProperty("username"), p.getProperty("password"));
	  System.out.println("session id is: "+session_id);
	  
	  String cookie_id="JSESSIONID="+session_id;
	  
	  ///// Create issue
	  Response create_issue_res=g.create_issue_response(cookie_id, p.getProperty("key"), p.getProperty("description"));
	  JsonPath create_issue_json=jsonpath.StringtoJson(create_issue_res);
	  String issue_id = create_issue_json.get("id");
	  String key_id=create_issue_json.get("key");
	  System.out.println("The created issue's id is : "+issue_id);
	  System.out.println("The created issue's key is: "+key_id);
	  
	  ///// Get issue
	  Response get_issue_res=g.Get_issue_response(cookie_id, issue_id);
	  JsonPath get_issue_json=jsonpath.StringtoJson(get_issue_res);
	  String issue_description=get_issue_json.get("fields.description");
	  String status_of_issue=get_issue_json.get("fields.status.name");
	  System.out.println("Current status of the issue is: "+status_of_issue);
	  System.out.println("Description of created issue is: "+issue_description);
	  
	  ///// Add comment
	  Response add_comment_res=g.add_comment_response(cookie_id, issue_id, p.getProperty("comment"));
	  JsonPath add_comment_json=jsonpath.StringtoJson(add_comment_res);
	  String comment_id=add_comment_json.get("id");
	  String added_comment=add_comment_json.get("body");
	  System.out.println("Added comment's generated ID is: "+comment_id);
	  System.out.println("Added comment is: "+added_comment);
	  
	  /////Add second comment
	  Response add_comment_res_1=g.add_comment_response(cookie_id, issue_id, p.getProperty("second_comment"));
	  JsonPath add_comment_json_1=jsonpath.StringtoJson(add_comment_res_1);
	  String comment_id_1=add_comment_json_1.get("id");
	  String added_comment_1=add_comment_json_1.get("body");
	  System.out.println("Added comment's generated ID is: "+comment_id_1);
	  System.out.println("Added comment is: "+added_comment_1);
	  
	  /////Get comments
	  get_comment_res=g.get_comment_response(cookie_id, issue_id);
	  get_comment_json=jsonpath.StringtoJson(get_comment_res);
	  System.out.println("Total comments count: "+get_comment_json.get("total"));
	  int j=get_comment_json.get("comments.size()");
	  for(int i=0;i<j;i++)
	  {
		  String comments="comments["+i+"].body";
		  System.out.println("comment "+(i+1)+" is: "+get_comment_json.get(comments));
	  }
	  
	  /////Update comments
	  Response update_comment_res=g.update_comment_response(cookie_id, issue_id, comment_id_1, p.getProperty("update_comment"));
	  JsonPath update_comment_json=jsonpath.StringtoJson(update_comment_res);
	  
	/////Get comments
	      System.out.println("After comments update");
		  get_comment_res=g.get_comment_response(cookie_id, issue_id);
		  get_comment_json=jsonpath.StringtoJson(get_comment_res);
		  
		  
		  System.out.println("Total comments count after update of comments is: "+get_comment_json.get("total"));
		  j=get_comment_json.get("comments.size()");
		  
		  get_comment_json.getList("");
		  for(int i=0;i<j;i++)
		  {
			  String comments="comments["+i+"].body";
			  System.out.println("comment "+(i+1)+" is: "+get_comment_json.get(comments));
		  }
	  
	  /////Delete comment
	  g.delete_comment_response(cookie_id, issue_id, comment_id_1);
	  System.out.println("comment with id:"+comment_id_1+" and comment description as: "+added_comment_1+" is deleted");
	  
	  /////Get comments
	  get_comment_res=g.get_comment_response(cookie_id, issue_id);
	  get_comment_json=jsonpath.StringtoJson(get_comment_res);
	  System.out.println("Total comments count after deletion of comment is: "+get_comment_json.get("total"));
	  j=get_comment_json.get("comments.size()");
	  for(int i=0;i<j;i++)
	  {
		  String comments="comments["+i+"].body";
		  System.out.println("comment "+(i+1)+" is: "+get_comment_json.get(comments));
	  }
	  
	  
	  /////Delete Issue
	  g.delete_issue_response(cookie_id, issue_id);
	  System.out.println("Issue with id as : "+issue_id+" and key id as :"+key_id+" is deleted permanently");
  }
}
