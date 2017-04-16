package com.solteam;

import com.solteam.dictionary.domain.Dictionary;
import com.solteam.dictionary.service.DictionaryService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import java.util.stream.Stream;

@SpringBootApplication
public class DictionaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictionaryApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(DictionaryService dictionaryService) {
        return args -> {
            Stream.of("Dammit", "Goal", "Grill").forEach(dictionaryService::addNewWord);

            dictionaryService.getAllWords().forEach(System.out::println);
        };
    }

}

/*
@RestController("/dictionary")
class DictionaryController {

    private final DictionaryService dictionaryService;

    @Autowired
    DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping(name = "/dictionary")
    public List<Dictionary> getDictionary() {
        return dictionaryService.getAllWords();
    }

}
*/
