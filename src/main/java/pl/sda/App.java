package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
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

   private static Logger logger = Logger.getLogger((App.class));



    public static void main( String[] args )
    {
        logger.info("URUCHOMIENIE APLIKACJI");
        logger.warn("WARNING");
        logger.debug("DEBUG");
        logger.error("ERROR");


        String url =
                "http://api.apixu.com/v1/current.json" +
                        "key=8480236ee7a3432fa6781558191307&q=Paris";

        WeatherService weatherService = new WeatherService(
                "http://api.apixu.com/v1/current.json",
                "8480236ee7a3432fa6781558191307"
        );

        WeatherForecast weatherForecast
                = new OrgImplementation(weatherService, "torun");
        WeatherForecast weatherForecast1
                = new FasterImplementation(weatherService, "torun");

        System.out.println(weatherForecast.getWeather());

        JsonDataFaster jsonDataFaster = new JsonDataFaster();
        jsonDataFaster.setApiKey("8480236ee7a3432fa6781558191307");
        jsonDataFaster.setUrl("http://api.apixu.com/v1/current.json");
        jsonDataFaster.setCity("Warszawa");
        jsonDataFaster.build();

        System.out.println(jsonDataFaster.getWeather());

    }
}