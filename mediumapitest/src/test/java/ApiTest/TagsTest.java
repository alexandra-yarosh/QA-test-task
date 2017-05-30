package ApiTest;

import static org.hamcrest.Matchers.*;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import utils.Data;

import com.jayway.restassured.RestAssured;




@FixMethodOrder(MethodSorters.NAME_ASCENDING) //For Ascending order test execution
public class TagsTest {


    @Before
    public void setup (){
}

    @Test
    public void T01_ThatShouldBePossibleToCreatePostWithTags() {

   	String dateStamp = new DateTime().toString("dd-mm-yyyy hh:MM");
   	
    RestAssured.
       	given()
	       	.header("Authorization","Bearer " + Data.ACCESS_TOKEN)
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .basePath(Data.BASE_PATH)
		    .baseUri(Data.BASE_URI)
		    .body("{ \"title\": \"Tags. " + dateStamp+ "\","+
			    	  "\"contentFormat\": \"html\","+
			    	  "\"content\": \"Post with tags\","+
			    	  "\"publishStatus\":\"draft\","+
					  "\"tags\":[\"medium\",\"sport\", \"API-test\"]"+
				  "}")
	    .when()
	    	.post("/users/" + Data.USER_ID + "/posts")
	    .then()
	        .statusCode(201)
	        .body("data.authorId", equalTo(Data.USER_ID))
	        .body("data.title", equalTo("Tags. " + dateStamp))
	        .rootPath("data.tags")
	        .body("size()",is(3))
	        .body("", hasItems("api-test","sports","medium"));
  
    }

    @Test
    public void T02_ThatShouldBePossibleToCreatePostWithNoMoreThen5Tags() {

   	String dateStamp = new DateTime().toString("dd-mm-yyyy hh:MM");
   	
    RestAssured.
       	given()
	       	.header("Authorization","Bearer " + Data.ACCESS_TOKEN)
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .basePath(Data.BASE_PATH)
		    .baseUri(Data.BASE_URI)
		    .body("{ \"title\": \"Tags. " + dateStamp+ "\","+
			    	  "\"contentFormat\": \"html\","+
			    	  "\"content\": \"Post with tags\","+
			    	  "\"publishStatus\":\"draft\","+
					  "\"tags\":[\"medium\",\"sport\", \"API-test\", \"music\", \"movies\",\"auto-tests\"]"+
				  "}")
	    .when()
	    	.post("/users/" + Data.USER_ID + "/posts")
	    .then()
	        .statusCode(201)
	        .body("data.authorId", equalTo(Data.USER_ID))
	        .body("data.title", equalTo("Tags. " + dateStamp))
	        .rootPath("data.tags")
	        .body("size()",is(5))
	        .body("", hasItems("api-test","sports","medium","music","movies"));
  
    }
    @Test
    public void T03_TagsLongerThen25CharsShouldBeCutOff() {

   	String dateStamp = new DateTime().toString("dd-mm-yyyy hh:MM");
   	
    RestAssured.
       	given()
	       	.header("Authorization","Bearer " + Data.ACCESS_TOKEN)
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .basePath(Data.BASE_PATH)
		    .baseUri(Data.BASE_URI)
		    .body("{ \"title\": \"Tags. " + dateStamp+ "\","+
			    	  "\"contentFormat\": \"html\","+
			    	  "\"content\": \"Post with tags\","+
			    	  "\"publishStatus\":\"draft\","+
					  "\"tags\":[\"medium\",\"sport\", \"tagwith26characters1234567\"]"+
				  "}")
	    .when()
	    	.post("/users/" + Data.USER_ID + "/posts")
	    .then()
	        .statusCode(201)
	        .body("data.authorId", equalTo(Data.USER_ID))
	        .body("data.title", equalTo("Tags. " + dateStamp))
	        .rootPath("data.tags")
	        .body("size()",is(2))
	        .body("", hasItems("sports","medium"));
  
    }

    @Test
    public void T04_TagsWithUnsuportedCharsShouldBeCutOff() {

   	String dateStamp = new DateTime().toString("dd-mm-yyyy hh:MM");
   	
    RestAssured.
       	given()
	       	.header("Authorization","Bearer " + Data.ACCESS_TOKEN)
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .basePath(Data.BASE_PATH)
		    .baseUri(Data.BASE_URI)
		    .body("{ \"title\": \"Tags. " + dateStamp+ "\","+
			    	  "\"contentFormat\": \"html\","+
			    	  "\"content\": \"Post with tags\","+
			    	  "\"publishStatus\":\"draft\","+
					  "\"tags\":[\"me@medium\",\"sport_tag\", \"rest-api\"]"+
				  "}")
	    .when()
	    	.post("/users/" + Data.USER_ID + "/posts")
	    .then()
	        .statusCode(201)
	        .body("data.authorId", equalTo(Data.USER_ID))
	        .body("data.title", equalTo("Tags. " + dateStamp))
	        .rootPath("data.tags")
	        .body("size()",is(1))
	        .body("", hasItem("rest-api"));
  
    }

    @Test
    public void T05_TagsWithInvalidDataFormatShouldFailThePost() {

   	String dateStamp = new DateTime().toString("dd-mm-yyyy hh:MM");
   	
    RestAssured.
       	given()
	       	.header("Authorization","Bearer " + Data.ACCESS_TOKEN)
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .basePath(Data.BASE_PATH)
		    .baseUri(Data.BASE_URI)
		    .body("{ \"title\": \"Tags. " + dateStamp+ "\","+
			    	  "\"contentFormat\": \"html\","+
			    	  "\"content\": \"Post with tags\","+
			    	  "\"publishStatus\":\"draft\","+
					  "\"tags\":[42,\"sport_tag\", \"rest-api\"]"+
				  "}")
	    .when()
	    	.post("/users/" + Data.USER_ID + "/posts")
	    .then()
	        .statusCode(500);
    // Bug here!!! - Response should be HTTP 400 Bad Request
  
    }
    
    @After
    public void afterTest (){
        //Reset Values
       
    }
}