package com.lms.librarymanagementsystem.model;

import org.springframework.http.HttpStatus;

public class ResponseMessage {
    private HttpStatus status;
    private String message;

    public ResponseMessage(String failedToSaveCategorie, HttpStatus badRequest) {
    }

    public ResponseMessage(String categorieSavedSuccessfully) {
        this.message = categorieSavedSuccessfully;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
