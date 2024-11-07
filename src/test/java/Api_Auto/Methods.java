package Api_Auto;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import PClass.LoginResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.*;

public class Methods extends LoginResponse {

	public static RequestSpecification req;
	public static ResponseSpecification res;
	public static Properties prop;
	public static FileInputStream fi;
	public static LoginResponse lpg;

	public static String getvalue(String value)  {
		try {fi = new FileInputStream("D:\\JavaLearn\\Academy\\config.properties");
		} catch (FileNotFoundException e) {}
		prop =new Properties();
		try {prop.load(fi);
		} catch (IOException e) {
			e.printStackTrace();}
		if(value.contentEquals("baseURL")) {
			value = prop.getProperty("url");}
		else if(value.contentEquals("token")) {
			value = prop.getProperty("Authorization");}
		else if(value.contentEquals("loginsource")) {
			value = prop.getProperty("LoginSource");}
		else if(value.contentEquals("email")) {
			value = prop.getProperty("email");}
		else if(value.contentEquals("password")) {
			value = prop.getProperty("password");}
		else if(value.contentEquals("AllProductSource")) {
			value = prop.getProperty("AllProductSource");}
		else if(value.contentEquals("AllOrderSource")) {
			value = prop.getProperty("AllOrderSource");}
		else if(value.contentEquals("PostProduct")) {
			value = prop.getProperty("PostProduct");}
		else if(value.contentEquals("userID")) {
			value = prop.getProperty("userID");}
		else if(value.contentEquals("AllCartDetails")) {
			value = prop.getProperty("AllCartDetails");}
		else if(value.contentEquals("country")) {
			value = prop.getProperty("country");}
		else if(value.contentEquals("pOI")) {
			value = prop.getProperty("pOI");}
		else if(value.contentEquals("CreateOrder")) {
			value = prop.getProperty("CreateOrder");}
		
		return value;	
		}
	
	
	public static RequestSpecification loginRequest() {
		req=new RequestSpecBuilder().setBaseUri(Methods.getvalue("baseURL"))
				.addHeader("Content-Type","application/json").setBody(Methods.userregisterbody())
				.build();
		req= given().spec(req);
		return req;
	}
	
	
	public static RequestSpecification baseRequest(String scenario) {
		if (scenario.equals("GAP")) {
			req=new RequestSpecBuilder().setBaseUri(Methods.getvalue("baseURL")).setContentType(ContentType.JSON)
					.addHeader("Authorization",Methods.getvalue("token"))
					.build();}
		else if(scenario.equals("GCI")) {
			req=new RequestSpecBuilder().setBaseUri(Methods.getvalue("baseURL")).addHeader("Accept","*/*")
					.addHeader("Authorization",Methods.getvalue("token"))
					.build();}
		else if(scenario.equals("GAO")) {
			req=new RequestSpecBuilder().setBaseUri(Methods.getvalue("baseURL")).addHeader("Accept","*/*")
					.addHeader("Authorization",Methods.getvalue("token"))
					.build();}
		else if(scenario.equals("PP")) {
			req=new RequestSpecBuilder().setBaseUri(Methods.getvalue("baseURL"))
					.addHeader("Authorization",Methods.getvalue("token"))
					.build();}
		else if(scenario.equals("CO")) {
			req=new RequestSpecBuilder().setBaseUri(Methods.getvalue("baseURL")).setContentType(ContentType.JSON)
					.addHeader("Accept","*/*")
					.addHeader("Authorization",Methods.getvalue("token"))
					.build();}
			
		req= given().spec(req);
		return req;
	}
	
	
	public static ResponseSpecification baseResponse(String scenario) {
	    
		if (scenario.contains("GAP")) {
			res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();}
		else if(scenario.contains("GCI")) {
			res=new ResponseSpecBuilder().expectStatusCode(200).build();}
		else if(scenario.contains("GAO")) {
			res=new ResponseSpecBuilder().expectStatusCode(200).build();}
		else if(scenario.contains("AP")) {
			res=new ResponseSpecBuilder().expectStatusCode(201).build();}
		else if(scenario.contains("PP")) {
			res=new ResponseSpecBuilder().expectStatusCode(201).build();}
		else if(scenario.contains("CO")) {
			res=new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();}
		return res;
	}
	
	
	public static String userregisterbody() {
		String payload = "{\r\n"
				+ "   \"userEmail\": \""+Methods.getvalue("email")+"\", \r\n"
				+ "   \"userPassword\": \""+Methods.getvalue("password")+"\"\r\n"
				+ "}";
		return payload;}
	
	public static String createorderbody() {
		String payload="{\r\n"
				+ "    \"orders\": [\r\n"
				+ "        {\r\n"
				+ "            \"country\": \""+Methods.getvalue("country")+"\", \r\n"
				+ "            \"productOrderedId\": \""+Methods.getvalue("pOI")+"\"\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		return payload;
	}
	
	
	public static JsonPath jp(String response) {
		JsonPath j = new JsonPath(response);
		return j;
	}
	
	
	
	public static void storedata(List input) {
		List<String> in = new ArrayList<>();
		in.addAll(input);
		
	}
	
}
