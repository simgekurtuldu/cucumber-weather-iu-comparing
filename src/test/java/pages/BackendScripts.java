package pages;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BackendScripts {
    public static Object weather;
    public static String getWeatherDetails(String sehir,int gun) {
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?q="+sehir+",TR&appid=22d6f929bc7922748f3beaceba364155");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader( //karakter parçalarını okuyarak ve bunları dahili bir arabellekte saklayarak G/Ç işlemlerinin sayısını en aza indirmemizi sağlar.
                    (conn.getInputStream())));

            JSONTokener tokener = new JSONTokener(br);
            JSONObject json = new JSONObject(tokener);
            weather = json.getJSONArray("list").getJSONObject(gun).getJSONArray("weather").getJSONObject(0).get("main");
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather.toString();
    }

    public static String getApiWeatherDetails(String weather){
        String api_weather;
        switch (weather){
            case "Clear":
                api_weather = "Açık";
                break;
            case "Clouds":
                api_weather = "Parçalı bulutlu";
                break;
            case "light snow":
                api_weather = "Sağanak kar yağışı";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + weather);
        }
        return api_weather;
    }
}
