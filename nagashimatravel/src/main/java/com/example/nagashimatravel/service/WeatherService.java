package com.example.nagashimatravel.service;



import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
	@Value("${weather.api.key}")
	private String apiKey;

    public Map<String, Object> getWeather(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + apiKey + "&lang=ja&units=metric";

        RestTemplate restTemplate = new RestTemplate();

        // JSONをMapとして取得
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        return response;
    }
}