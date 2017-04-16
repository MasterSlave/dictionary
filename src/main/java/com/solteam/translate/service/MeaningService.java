package com.solteam.translate.service;

import com.solteam.translate.dto.YandexTranslateResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Burak Baldirlioglu
 * @since 4/16/2017 8:57 AM
 */
@Service
public class MeaningService {

    public static final int SUCCESS = 200;
    private static final String yandexTranslateUri = "https://translate.yandex.net/api/v1.5/tr.json/translate";

    public String getMeaning(String word) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("lang", "de-tr");
        map.add("key", "trnsl.1.1.20170416T063438Z.6e0f14edf331e4a1.fb74f613df2f22cac9793f3ce96ff143c7628bd9");
        map.add("text", word);
        YandexTranslateResponse yandexTranslateResponse = new RestTemplate().postForObject(yandexTranslateUri, map, YandexTranslateResponse.class);
        if (yandexTranslateResponse == null || SUCCESS != Integer.valueOf(yandexTranslateResponse.getCode())) {
            return null;
        }
        return yandexTranslateResponse.getTexts()[0];
    }
}
