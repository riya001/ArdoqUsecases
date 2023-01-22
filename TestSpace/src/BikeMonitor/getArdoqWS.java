package BikeMonitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class getArdoqWS {
	
	   JSONObject getJsonData(URL url, String token, String xOrg){
		    JSONObject dataObj = null;
		        try {
		            HttpURLConnection conn =null;
		            conn = (HttpURLConnection) url.openConnection();
		            conn.setRequestProperty("Authorization","Bearer "+token);
		            conn.setRequestProperty("Content-Type","application/json");
		            conn.setRequestProperty("X-org", xOrg);
		            conn.setRequestMethod("GET");
		            conn.connect();


		            //get response
	                int responseCode = conn.getResponseCode();
	                System.out.println("\nSending 'GET' request to URL : " + url);
	                System.out.println("Post parameters : " + token);
	                System.out.println("Response Code : " + responseCode);


		            if (responseCode != 200) {
		                throw new RuntimeException("HttpResponseCode: " + responseCode);
		            } else {
		              
		                String data = "";
		                BufferedReader in =
		                        new BufferedReader(new InputStreamReader(conn.getInputStream()));
		                StringBuffer response = new StringBuffer();
		                while ((data = in.readLine()) != null) {
		                    response.append(data);
		                   System.out.println(data);
		                }
		                in.close();
///		               //Skriver all data til en string
//		                while (scanner.hasNext()) {
//		                   data += scanner.nextLine();
//		                }
//
//		                scanner.close();
//		            
		                //Bruker JSON simple
		                JSONParser parse = new JSONParser();
//		                System.out.println(parse.parse(data));
		                
		            }
		        } catch (Exception e) {
		            System.out.println(e);
		        }
				return dataObj;
		        
		    }

}
