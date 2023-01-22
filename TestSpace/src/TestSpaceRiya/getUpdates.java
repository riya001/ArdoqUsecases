package TestSpaceRiya;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class getUpdates {
    
    JSONObject getJsonData(URL url){
    JSONObject dataObj = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Henter respons
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
              
                String data = "";
                Scanner scanner = new Scanner(url.openStream());
              
               //Skriver all data til en string
                while (scanner.hasNext()) {
                   data += scanner.nextLine();
                }

                scanner.close();
            
                //Bruker JSON simple
                JSONParser parse = new JSONParser();
                System.out.println(parse.parse(data));
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
		return dataObj;
        
    }
}
