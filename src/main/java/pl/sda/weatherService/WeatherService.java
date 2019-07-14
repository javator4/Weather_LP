package pl.sda.weatherService;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherService {

    private String url;
    private String key;
    private String finalURL;
    private String data = "";


    public WeatherService(String url, String key) {
        this.url = url;
        this.key = key;
        this.finalURL = this.url + "?key=" + key + "&q=";

    }

    public WeatherService getJSONData(String city) {
        if (this.data.isEmpty()) {
            this.finalURL = finalURL + city;
            try {
                this.data = IOUtils.toString(new URL(this.finalURL),
                        Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }}

            return this;

    }


    public Current getCityWeather() {


        JSONObject jsonobject = new JSONObject(data);

        String temp = jsonobject.getJSONObject("current")
                .get("temp_c").toString();
        System.out.println(temp);

        Current current = Current.builder()
                .temp_c(Float.parseFloat(temp))
                .build();
        return current;
    }


    public Location getLocation() {
        String stringKey = "location";
        JSONObject jsonObject = new JSONObject(data).getJSONObject(stringKey);

        String lat = jsonObject.get("lat").toString();
        String lon = jsonObject.get("lon").toString();
        String country = jsonObject.get("country").toString();
        String name = jsonObject.get("name").toString();

        Location location = Location.builder()
                .lat(Float.parseFloat(lat))
                .lon(Float.parseFloat(lon))
                .country(country)
                .name(name).build();


        return location;

    }
    public Weather getWeather(){

        JSONObject jsonObject = new JSONObject(this.data);

        String temp = jsonObject.getJSONObject("current")
                .get("temp_c").toString();
        String lat = jsonObject.getJSONObject("location").get("lat").toString();
        String lon = jsonObject.getJSONObject("location").get("lon").toString();
        String country = jsonObject.getJSONObject("location").get("country").toString();
        String name = jsonObject.getJSONObject("location").get("name").toString();


        Weather weather = new Weather();

        Current current = Current.builder()
                .temp_c(Float.parseFloat(temp))
                .build();
        Location location = Location.builder()
                .lat(Float.parseFloat(lat))
                .lon(Float.parseFloat(lon))
                .country(country)
                .name(name).build();
        weather.setCurrent(current);
        weather.setLocation(location);

        return weather;
    }
}

