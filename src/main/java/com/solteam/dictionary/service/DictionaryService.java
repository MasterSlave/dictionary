package com.solteam.dictionary.service;

import com.solteam.dictionary.domain.Dictionary;
import com.solteam.dictionary.exception.MeaningNotFound;
import com.solteam.dictionary.exception.WordAlreadyAdded;
import com.solteam.dictionary.orm.DictionaryRedisRepository;
import com.solteam.dictionary.repository.DictionaryRepository;
import com.solteam.translate.service.MeaningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Burak Baldirlioglu
 * @since 4/16/2017 8:53 AM
 */
@Service
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;
    private final DictionaryRedisRepository dictionaryRedisRepository;

    private final MeaningService meaningService;

    @Autowired
    DictionaryService(DictionaryRepository dictionaryRepository, DictionaryRedisRepository dictionaryRedisRepository, MeaningService meaningService) {
        this.dictionaryRepository = dictionaryRepository;
        this.dictionaryRedisRepository = dictionaryRedisRepository;
        this.meaningService = meaningService;
    }

    public List<Dictionary> getAllWords() {
        Map<String, Dictionary> allDictionaries = dictionaryRedisRepository.findAllDictionaries();
        return new ArrayList<>(allDictionaries.values());
    }

    public Dictionary addNewWord(String newWord) {
        Dictionary dictionary = dictionaryRepository.findByWordIgnoreCase(newWord);
        if (dictionary != null) {
            throw new WordAlreadyAdded("Word already added!");
        }
        String meaning = meaningService.getMeaning(newWord);
        if (meaning == null || newWord.equals(meaning)) {
            throw new MeaningNotFound("Meaning not found!");
        }
        dictionary = new Dictionary(newWord, meaning);
        dictionaryRedisRepository.saveDictionary(dictionary);
        return dictionaryRepository.save(dictionary);
    }
}
