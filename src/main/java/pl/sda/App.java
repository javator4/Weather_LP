package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import pl.sda.model.Current;
import pl.sda.model.Weather;
import pl.sda.weatherService.WeatherService;
import pl.sda.model.Location;

import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {



        String url = "http://api.apixu.com/v1/current.json" +
                "?key=8480236ee7a3432fa6781558191307&q=Paris";

        WeatherService weatherService = new WeatherService(
                "http://api.apixu.com/v1/current.json",
                "d48c0d5e40054b6a9e571834181808"
        );

        Current current = weatherService.getJSONData("Torun").getCityWeather();
        Location location = weatherService.getJSONData("Torun").getLocation();

        System.out.println("LAT: " + location.getLat());
        System.out.println("LON: " + location.getLon());

        ObjectMapper objectMapper = new ObjectMapper();

        try{
            Weather weather = objectMapper.readValue(new URL(url), Weather.class);
            objectMapper.writeValue(new File("data.jason"), weather);
            System.out.println(weather.getLocation().getCountry());
        }catch (IOException e){
            e.printStackTrace();
        }




    }
}
