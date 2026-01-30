/*
 * Creates an ArrayList of WeatherStation objects using
 * bouy data from the NOAA website.
 */
import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Welcome03_List {
   public static void main(String[] args) {  

// check WeatherSTation for mods
      DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml").load();
      ArrayList<WeatherStation> allstns = ds.fetchList("WeatherStation", "station/station_name", 
             "station/station_id", "station/state",
             "station/latitude", "station/longitude");
      //ds1.printUsageString(); 
      System.out.println("Total stations: " + allstns.size());
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a state abbreviation: ");
      String state = sc.next();
      System.out.println("Stations in " + state);
      
      TreeMap<Double, WeatherStation> stationsByLatitude = new TreeMap<>();
      for (WeatherStation ws : allstns) {
         if (ws.isLocatedInState(state)) {
            stationsByLatitude.put(ws.getLat(), ws);
         }
      }
      
      System.out.println("Sorted by latitude (furthest south first):");
      for (Double latitude : stationsByLatitude.keySet()) {
         WeatherStation ws = stationsByLatitude.get(latitude);
         System.out.println("  " + ws.getId() + ": " + ws.getName() + " (Lat: " + latitude + ")");
      }
      System.out.println("Total stations in " + state + ": " + stationsByLatitude.size());
     sc.close();
   }
}