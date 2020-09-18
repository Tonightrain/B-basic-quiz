package com.thoughtworks.capability.gtb.resume.exception;

import static com.thoughtworks.capability.gtb.resume.component.ErrorMessage.PERSON_NOT_FOUND;

public class PersonIsNotExistException extends RuntimeException {
    public PersonIsNotExistException() {
        super(PERSON_NOT_FOUND);
    }
}
