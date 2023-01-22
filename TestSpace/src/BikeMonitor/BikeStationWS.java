package BikeMonitor;


import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/* 01: Get Workspaces details
 * 02: List of Components inside Bikestation Workspace
 * 03: Update components dynamically at Ardoq WS
*/


public class BikeStationWS {
	static URL url = null;
	static String urlDNS ="https://caseinterviewriya.ardoq.com/api/v2";
	static String token = "66b1adc0259c4b03a7179438473d8366";
	static String xOrg = "caseinterviewriya";
	private static int a1f8841d00141638fe839db4;
	
    public static void main(String[] args) throws IOException, ParseException {
    
        
 // 01: Get Workspaces details
        System.out.println("\n Here you go! WS list \n");
        getWSlist();
        
 // 02: Get Components List
        System.out.println("\n Here you go! Components list \n");
        getComponents();
        
 // 03: Update components dynamically at Ardoq WS    
        System.out.println("\n Here you go! You can update your Workspace Components \n");
        updateComponents();
        
    }
    
    private static void updateComponents() {
		// TODO Auto-generated method stub
		
	}

	private static void getComponents()  throws IOException, ParseException {
		// TODO Auto-generated method stub
    	getArdoqWScomponents dataDumpComponents = new getArdoqWScomponents();
    	try {
			url = new URL(urlDNS+"/components?rootWorkspace=a06cc9e698672e67f1f3715c");
			System.out.println(url);	
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	JSONObject objWSInfo = dataDumpComponents.getJsonData(url, token, xOrg);
		
	}

	public static void getWSlist() throws IOException, ParseException {
 
    	getArdoqWS dataDump = new getArdoqWS();
		try {
			url = new URL(urlDNS+"/workspaces");
			System.out.println(url);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject objWSInfo = dataDump.getJsonData(url, token, xOrg);


    
    }    
	public static void updateComponents1() throws IOException, ParseException {
		 
    	updateArdoqComponents dataDumpUpdate = new updateArdoqComponents();
    	int id = a1f8841d00141638fe839db4;
    	int version=8;
		try {
			url = new URL(urlDNS+"/components/"+id+"?ifVersionMatch="+version+"&componentKey=BW-4");
			System.out.println(url);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject objWSInfo = dataDumpUpdate.getJsonData(url, token, xOrg);


    
    }   
	
}
