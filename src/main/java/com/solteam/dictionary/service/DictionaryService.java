package com.solteam.dictionary.service;

import com.solteam.dictionary.domain.Dictionary;
import com.solteam.dictionary.exception.MeaningNotFound;
import com.solteam.dictionary.exception.WordAlreadyAdded;
import com.solteam.dictionary.repository.DictionaryRepository;
import com.solteam.translate.service.MeaningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Burak Baldirlioglu
 * @since 4/16/2017 8:53 AM
 */
@Service
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    private final MeaningService meaningService;

    @Autowired
    DictionaryService(DictionaryRepository dictionaryRepository, MeaningService meaningService) {
        this.dictionaryRepository = dictionaryRepository;
        this.meaningService = meaningService;
    }

    public List<Dictionary> getAllWords() {
        return dictionaryRepository.findAll();
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
        return dictionaryRepository.save(new Dictionary(newWord, meaning));
    }
}
