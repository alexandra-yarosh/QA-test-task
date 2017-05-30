package utils;

import com.jayway.restassured.RestAssured;

public class Utils extends RestAssured{

	static RestAssured utils ;
	public static RestAssured getUtils(){
		
		if (utils != null)
			return utils;
		else {
			utils= new RestAssured();
			return utils;
		}
		
	}
}
