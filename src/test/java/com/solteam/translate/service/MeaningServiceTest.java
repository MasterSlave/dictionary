package com.solteam.translate.service;

import com.solteam.translate.dto.YandexTranslateResponse;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author Burak Baldirlioglu
 * @since 4/16/2017 10:05 AM
 */
public class MeaningServiceTest {
    @Test
    public void getMeaning() throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("lang", "de-tr");
        map.add("key", "trnsl.1.1.20170416T063438Z.6e0f14edf331e4a1.fb74f613df2f22cac9793f3ce96ff143c7628bd9");
        map.add("text", "dammit");
        YandexTranslateResponse yandexTranslateResponse = new RestTemplate().postForObject("https://translate.yandex.net/api/v1.5/tr.json/translate", map, YandexTranslateResponse.class);
        System.out.println(yandexTranslateResponse);
    }

}