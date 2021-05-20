package com.sanger.muni.error.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException() {
        super("No results can be found");
    }

    public EntityNotFoundException(String entity) {
        super("No results can be found with entity " + entity);
    }

}
