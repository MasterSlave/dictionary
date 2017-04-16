package com.solteam.translate.dto;

import lombok.Data;

/**
 * @author Burak Baldirlioglu
 * @since 4/16/2017 10:02 AM
 */
@Data
public class YandexTranslateRequest {

    private String key;
    private String text;
    private String lang;

}
