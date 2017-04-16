package com.solteam.dictionary.repository;

import com.solteam.dictionary.domain.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author Burak Baldirlioglu
 * @since 4/16/2017 8:53 AM
 */public
@RepositoryRestResource
interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

    @RestResource(path = "by-word")
    Dictionary findByWordIgnoreCase(@Param("word") String word);
}
