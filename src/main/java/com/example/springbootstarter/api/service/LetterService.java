package com.example.springbootstarter.api.service;

import com.example.springbootstarter.api.exception.ConflictException;
import com.example.springbootstarter.api.exception.NotFoundLetter;
import com.example.springbootstarter.api.factory.LetterFactory;
import com.example.springbootstarter.api.model.Letter;
import com.example.springbootstarter.api.model.request.LetterRequest;
import com.example.springbootstarter.api.model.response.LetterResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LetterService {

    private List<Letter> letters;
    private final LetterFactory letterFactory;

    public LetterService(List<Letter> letters,
                         LetterFactory letterFactory) {
        this.letters = letters;
        this.letterFactory = letterFactory;
    }

    public void create(LetterRequest letterRequest) throws ConflictException {
        Letter letter = letterFactory.create(letterRequest);
        if (getObjectLetter(letter.getLetter()).isPresent())
            throw new ConflictException();

        this.letters.add(letter);
    }

    public void delete(String letter) throws NotFoundLetter {
        Letter letterFound = getObjectLetter(letter)
                .orElseThrow(() -> new NotFoundLetter());
        this.letters.remove(letterFound);
    }

    public List<LetterResponse> findAll(){
        return letterFactory.createLettersResponse(this.letters);
    }

    public LetterResponse findByLetter(String letter) throws NotFoundLetter {
        Letter letterFound = getObjectLetter(letter)
                .orElseThrow(() -> new NotFoundLetter());

        return letterFactory.createLetterResponse(letterFound);
    }

    private Optional<Letter> getObjectLetter(String letter) {
        this.letters.remove(null);
        return this.letters.stream()
                .filter(filter -> filter != null & filter.getLetter().equals(letter))
                .findFirst();
    }
}
