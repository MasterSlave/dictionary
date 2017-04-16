package com.solteam.dictionary.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dictionary implements Serializable {
    @Id
    @GeneratedValue
    Long id;

    String word;
    String meaning;

    public Dictionary(String word) {
        this.word = word;
    }

    public Dictionary(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }
}