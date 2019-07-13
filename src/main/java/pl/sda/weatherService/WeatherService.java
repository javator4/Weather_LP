package pl.sda.weatherService;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pl.sda.model.Current;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherService {

    private String url;
    private String key;
    private String finalURL;


    public WeatherService(String url, String key) {
        this.url = url;
        this.key = key;
        this.finalURL = this.url + "?key=" + key + "&q=";

    }

    public Current getCityWeather(String city) {
        this.finalURL = this.finalURL + city;
        System.out.println(this.finalURL);

        try {
            String data = IOUtils.toString(new URL(this.finalURL),
                    Charset.forName("UTF-8"));
            JSONObject jsonobject = new JSONObject(data);
            String temp = jsonobject.getJSONObject("current").get("temp_c").toString();
            System.out.println(temp);

            Current current = new Current();
            current.setHumidity(Integer.parseInt(temp));
            current.setWind_kmph(Float.parseFloat(temp));
            current.setIs_day(Integer.parseInt(temp));
            current.setCloud(Integer.parseInt(temp));
            current.setWind_degree(Integer.parseInt(temp));
            current.setTemp_c(Float.parseFloat(temp));

            return current;


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
