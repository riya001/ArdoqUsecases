package TestSpaceRiya;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.*;
import org.json.simple.JSONObject;

public class BikeStations {

    public static void main(String[] args) {
        getUpdates dataDump = new getUpdates();
        URL url = null;

        try {
            url = new URL("https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JSONObject objStationInfo = dataDump.getJsonData(url);

        try {
            url = new URL("https://gbfs.urbansharing.com/oslobysykkel.no/station_status.json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JSONObject objStationStatus = dataDump.getJsonData(url);
        
        JSONObject objInfo = (JSONObject) objStationInfo.get("data");
        JSONArray stationsInfo = (JSONArray) objInfo.get("stations");
        JSONObject objStatus  = (JSONObject) objStationStatus.get("data");
        JSONArray stationsStatus = (JSONArray) objStatus.get("stations");

        for (int i = 0; i < stationsInfo.size(); i++){
            JSONObject stationInfo = (JSONObject) stationsInfo.get(i);
            JSONObject stationStatus = (JSONObject) stationsStatus.get(i);

            String name = (String) stationInfo.get("name");
            String idInfo = (String) stationInfo.get("station_id");
            String idStatus = (String) stationStatus.get("station_id");
            Long bikes = (Long) stationStatus.get("num_bikes_available");
            Long docks = (Long) stationStatus.get("num_docks_available");
    
            //Ser i json filen at indeksene er samme stasjon, men legger til en liten sjekk for å være sikker
            if (idInfo.equals(idStatus)){
                System.out.println("Stasjonen " + name + " har status:");
                System.out.println("Ledige sykler: " + bikes);
                System.out.println("Ledige plasser: " + docks);
                System.out.println("\n");
            }  
        }
    }
}
