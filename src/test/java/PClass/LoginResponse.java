package PClass;

import java.util.HashMap;

import org.testng.annotations.Test;

public class LoginResponse {

		public String token;
		public String userId;
		public String message;
		
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
			
		}
		public String getUserID() {
			return userId;
		}
		public void setUserID(String userID) {
			this.userId = userID;
			
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		public static String hashMap(String key,String value) {
	    	 HashMap<String,String>hs= new HashMap<>();
	    	 hs.put(key, value); 
	    	 
	    	 return hs.get(key);
		}
}
