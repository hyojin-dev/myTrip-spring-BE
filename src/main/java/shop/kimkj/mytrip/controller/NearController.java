package shop.kimkj.mytrip.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.kimkj.mytrip.dto.NearDto;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@PropertySource("classpath:env.properties") // env.properties에 저장된 키 값 가져올 수 있게 경로 설정
public class NearController {

    @Value("${TOUR_KEY}")
    private String TOUR_KEY;

    @Value("${WEATHER_KEY}")
    private String WEATHER_KEY;

    @PostMapping("/near")
    public String getNearPlace(@RequestParam String lat_give, @RequestParam String lng_give) throws IOException {
        StringBuffer result = new StringBuffer();
        JSONObject jsonObject = null;
        try {
            String apiUrl = String.format(
                    "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?" +
                    "serviceKey=%s" +
                    "&contentTypeId=12" +
                    "&mapX=%s&mapY=%s" +
                    "&radius=4000&listYN=Y&MobileOS=ETC" +
                    "&MobileApp=TourAPI3.0_Guide" +
                    "&arrange=E" +
                    "&numOfRows=10" +
                    "&pageNo=1"
                    , TOUR_KEY, lng_give, lat_give);

            URL url = new URL(apiUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));

            String returnLine;

            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            jsonObject = XML.toJSONObject(result.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray near = jsonObject.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
        return near.toString();
    }


    @PostMapping("/near/place/intro")
    public String getNearDetailIntro(@RequestParam(name = "content_id_give") String contentId) throws IOException {
        StringBuffer result = new StringBuffer();
        JSONObject jsonObject = null;
        try {
            String apiUrl = String.format(
                    "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?" +
                    "serviceKey=%s" +
                    "&contentId=%s" +
                    "&MobileOS=ETC" +
                    "&MobileApp=TourAPI3.0_Guide" +
                    "&defaultYN=Y&firstImageYN=Y" +
                    "&areacodeYN=Y&catcodeYN=Y" +
                    "&addrinfoYN=Y&mapinfoYN=Y" +
                    "&overviewYN=Y&transGuideYN=Y"
                    , TOUR_KEY, contentId);

            URL url = new URL(apiUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));

            String returnLine;

            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            jsonObject = XML.toJSONObject(result.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject nearDetail = jsonObject.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item");

        return nearDetail.toString();
    }

    @PostMapping("/near/list")
    public String getNearPlaceList(@RequestBody NearDto nearDto) {
        String quantity = nearDto.getQuantity();
        String lat = nearDto.getLat();
        String lng = nearDto.getLng();
        String type = nearDto.getType();
        String code = "";

        switch (type) {
            case "trip":
                code = "12";
                break;
            case "food":
                code = "39";
                break;
            case "accommodation":
                code = "32";
                break;
            case "festival":
                code = "15";
                break;
        }

        StringBuffer result = new StringBuffer();
            JSONObject jsonObject = null;
            try {
                String apiUrl = String.format(
                        "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?" +
                                "serviceKey=%s" +
                                "&contentTypeId=%s" +
                                "&mapX=%s" +
                                "&mapY=%s" +
                                "&radius=5000&listYN=Y&MobileOS=ETC" +
                                "&MobileApp=TourAPI3.0_Guide" +
                                "&arrange=E&numOfRows=%s" +
                                "&pageNo=1"
                        , TOUR_KEY, code, lng, lat, quantity);

                URL url = new URL(apiUrl);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));

                String returnLine;

                while ((returnLine = bufferedReader.readLine()) != null) {
                    result.append(returnLine);
                }

                jsonObject = XML.toJSONObject(result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONArray nearList = jsonObject.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");

            JSONObject jsonObjectNear = new JSONObject();
            jsonObjectNear.put("near_list", nearList);

            return jsonObjectNear.toString();
        }


    @PostMapping("/near/place/weather")
    public String getWeatherNear(@RequestParam(name = "place_lat") String placeLat, @RequestParam(name = "place_lng") String placeLng) {
        StringBuffer result = new StringBuffer();
        try {
            String apiUrl = String.format(
                    "https://api.openweathermap.org/data/2.5/weather?" +
                    "lat=%s&lon=%s" +
                    "&appid=%s&units=metric"
                    , placeLat, placeLng, WEATHER_KEY);

            URL url = new URL(apiUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));

            String returnLine;

            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}