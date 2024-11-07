package Api_Auto;

import java.io.File;
import java.util.List;
import org.testng.annotations.Test;
import PClass.LoginResponse;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ShoppingCart {
	public static String token;
	
	@Test
	public static void Login() {
	Response res = Methods.loginRequest().when().post(Methods.getvalue("loginsource")).then().extract().response();
	LoginResponse lp = new LoginResponse();
	lp=res.as(LoginResponse.class);
	String toke= lp.getToken();
	System.out.println(toke);
	}
	
	@Test
	public static void getAllProducts() {
		Response res = Methods.baseRequest("GAP").
					   post(Methods.getvalue("AllProductSource")).
					   then().log().all().spec(Methods.baseResponse("GAP")).extract().response();
		String resString = res.asPrettyString();
		System.out.println(resString);
	}
	
	@Test
	public static void getAllOrders() {
		Response res= Methods.baseRequest("GAO").get(Methods.getvalue("AllOrderSource")).
					  then().spec(Methods.baseResponse("GAO")).extract().response();
		String resString = res.asPrettyString();
		System.out.println(resString);
	}
	
	@Test
	public static void getCartItems() {
	Response res= Methods.baseRequest("GCI").
				  when().get(Methods.getvalue("AllCartDetails")).	
				  then().spec(Methods.baseResponse("GCI")).extract().response();
	String resString = res.asPrettyString();
	System.out.println(resString);
	}
	
	
	@Test
	public static void postProduct() {
		RequestSpecification res= Methods.baseRequest("PP").formParam("productName", "JackAndJill")
										   .formParam("productAddedBy", Methods.getvalue("userID"))
										   .formParam("productCategory", "GoingToHill")
										   .formParam("productSubCategory", "T-Shirts")
										   .formParam("productPrice", "2400000")
										   .formParam("productDescription", "JackOriginals")
										   .formParam("productFor","Marriage")
										   .multiPart("productImage",new File("C:\\Users\\HOME\\OneDrive\\Desktop\\PC WALLPAPERS\\216418.jpg"));
		
			String reS=res.when().log().all().post(Methods.getvalue("PostProduct")).then()
						.spec(Methods.baseResponse("PP")).extract().response().asPrettyString();
		
		JsonPath j = new JsonPath(reS);
		System.out.println(j.getString("productId"));
		System.out.println(j.getString("message"));
	}
	
	
	@Test
	public static void createOrder() {
		Response res= Methods.baseRequest("CO").body(Methods.createorderbody())						
						.when().log().all().post(Methods.getvalue("CreateOrder")).then()
						.spec(Methods.baseResponse("CO")).extract().response();
		String jres=res.toString();
		JsonPath j = new JsonPath(jres);
		List<String> order=j.get("orders");
		
//		List<String> orders=j.getList("orders");
//		
//		List<String> pOI= j.getList("productOrderId");
//		
//		
//		System.out.println(j.getString("productOrderId"));
	}
	
	
	
	
	
}
