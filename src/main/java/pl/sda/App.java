package pl.sda;

import org.apache.commons.io.IOUtils;
import pl.sda.weatherService.WeatherService;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        WeatherService weatherService = new WeatherService(
                "http://api.apixu.com/v1/current.json",
                "8480236ee7a3432fa6781558191307"
                );

        weatherService.getCityWeather("Torun");
        System.out.println("Hello World!");
    }
}
