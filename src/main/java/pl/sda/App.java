package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        String url =
                "http://api.apixu.com/v1/current.json" +
                        "?key=d48c0d5e40054b6a9e571834181808&q=Paris";

        WeatherService weatherService = new WeatherService(
                "http://api.apixu.com/v1/current.json",
                "d48c0d5e40054b6a9e571834181808"
        );

        WeatherForecast weatherForecast
                = new OrgImplementation(weatherService, "torun");
        WeatherForecast weatherForecast1
                = new FasterImplementation(weatherService, "torun");

        System.out.println(weatherForecast.getWeather());


    }
}