package com.solteam;

import com.solteam.dictionary.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Stream;

@SpringBootApplication
public class DictionaryApplication {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication.run(DictionaryApplication.class, args);
    }

    private DictionaryService dictionaryService;

    @Bean
    CommandLineRunner commandLineRunner(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
        return args -> {
            Stream.of("Dammit", "Goal", "Grill").forEach(this::addWord);

            dictionaryService.getAllWords().forEach(System.out::println);
        };
    }

    private void addWord(String newWord) {
        try {
            dictionaryService.addNewWord(newWord);
        } catch (Exception e) {
            logger.info("Error while adding some stuff : ", e);
        }
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
