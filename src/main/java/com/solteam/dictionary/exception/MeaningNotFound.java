package com.solteam.dictionary.exception;

/**
 * @author Burak Baldirlioglu
 * @since 4/16/2017 10:25 AM
 */
public class MeaningNotFound extends RuntimeException {
    public MeaningNotFound(String message) {
        super(message);
    }
}
