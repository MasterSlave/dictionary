package com.solteam.translate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Burak Baldirlioglu
 * @since 4/16/2017 9:59 AM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class YandexTranslateResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("lang")
    private String lang;

    @JsonProperty("text")
    private String[] texts;

}
