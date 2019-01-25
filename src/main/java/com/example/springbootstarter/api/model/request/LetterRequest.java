package com.example.springbootstarter.api.model.request;

public class LetterRequest {

    private String letter;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public static final class Builder {

        private LetterRequest letterRequest = new LetterRequest();

        public Builder withLetter(String letter) {
            letterRequest.setLetter(letter);
            return this;
        }

        public LetterRequest build() {
            return letterRequest;
        }
    }
}
