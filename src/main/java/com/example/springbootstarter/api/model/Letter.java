package com.example.springbootstarter.api.model;

public class Letter {

    private String letter;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Letter))
            return false;

        Letter another = (Letter)obj;
        return (another.letter.equals(this.letter));
    }

    public static class Builder {

        private Letter letter = new Letter();

        public Builder withLetter(String l) {
            letter.setLetter(l);
            return this;
        }

        public Letter build() {
            return letter;
        }
    }
}
