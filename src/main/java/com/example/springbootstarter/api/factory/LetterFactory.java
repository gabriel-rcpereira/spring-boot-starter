package com.example.springbootstarter.api.factory;

import com.example.springbootstarter.api.model.Letter;
import com.example.springbootstarter.api.model.request.LetterRequest;
import com.example.springbootstarter.api.model.response.LetterResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LetterFactory {

    public Letter create(LetterRequest letter) {
        return new Letter.Builder()
                .withLetter(letter.getLetter())
                .build();
    }

    public List<LetterResponse> createLettersResponse(List<Letter> letters) {
        return letters.stream()
                .map(this::createLetterResponse)
                .collect(Collectors.toList());
    }

    public LetterResponse createLetterResponse(Letter letter) {
        return new LetterResponse.Builder()
                .withLetter(letter.getLetter())
                .build();
    }

}
