package com.example.springbootstarter.api.model.response;

import java.io.Serializable;

public class LetterResponse implements Serializable {

    private String letter;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public static final class Builder {

        private LetterResponse letterResponse = new LetterResponse();

        public Builder withLetter(String letter) {
            letterResponse.setLetter(letter);
            return this;
        }

        public LetterResponse build() {
            return letterResponse;
        }
    }
}
