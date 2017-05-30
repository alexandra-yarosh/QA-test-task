package ApiTest;

import static org.hamcrest.Matchers.equalTo;

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
public class MeTest {


    @Before
    public void setup (){
}

    @Test
    public void T01_StatusCodeTest() {

   	RestAssured.
       	given()
	       	.header("Authorization","Bearer "+Data.ACCESS_TOKEN)
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .basePath(Data.BASE_PATH)
		    .baseUri(Data.BASE_URI)
	    .when()
	    	.get("/me")
	    .then()
        .statusCode(200)
        .body("data.username", equalTo("alexandra.yarosh.test"))
        .body("data.name",equalTo("Alexandra Yarosh"))
        .body("data.url", equalTo("https://medium.com/@alexandra.yarosh.test"))        ;
        
    }


    @After
    public void afterTest (){
        //Reset Values
       
    }
}