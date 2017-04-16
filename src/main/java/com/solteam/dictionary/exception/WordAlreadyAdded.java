package com.solteam.dictionary.exception;

/**
 * @author Burak Baldirlioglu
 * @since 4/16/2017 10:31 AM
 */
public class WordAlreadyAdded extends RuntimeException {

    public WordAlreadyAdded(String message) {
        super(message);
    }
}
