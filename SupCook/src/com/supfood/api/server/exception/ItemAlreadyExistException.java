package com.supfood.api.server.exception;


public class ItemAlreadyExistException extends RuntimeException {

    private Object item;

    public ItemAlreadyExistException(Object item) {
        this(item, "The following item already exists: " + item);
    }

    public ItemAlreadyExistException(Object item, String message) {
        super(message);
        this.item = item;
    }

    public Object getEntity() {
        return item;
    }

}
