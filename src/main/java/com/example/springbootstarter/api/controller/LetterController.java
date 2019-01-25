package com.example.springbootstarter.api.controller;

import com.example.springbootstarter.api.exception.ConflictException;
import com.example.springbootstarter.api.exception.NotFoundLetter;
import com.example.springbootstarter.api.model.request.LetterRequest;
import com.example.springbootstarter.api.model.response.LetterResponse;
import com.example.springbootstarter.api.service.LetterService;
import com.example.springbootstarter.model.entity.GenericEntity;
import com.example.springbootstarter.model.repository.GenericEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LetterController {

    private final LetterService letterService;
    private final GenericEntityRepository genericEntityRepository;

    public LetterController(LetterService letterService, GenericEntityRepository genericEntityRepository) {
        this.letterService = letterService;
        this.genericEntityRepository = genericEntityRepository;
    }

    @GetMapping(path = "/example/letters", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LetterResponse> findAll(){
        return letterService.findAll();
    }

    @GetMapping(path = "/example/letters/{letter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LetterResponse> findByLetter(@PathVariable String letter){
        LetterResponse letterResponse;
        try {
            letterResponse = this.letterService.findByLetter(letter);
        } catch (NotFoundLetter notFoundLetter) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(letterResponse);
    }

    @PostMapping(path = "/example/letters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody LetterRequest letterRequest){
        try {
            this.letterService.create(letterRequest);
        } catch (ConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/example/letters/{letter}")
    public ResponseEntity<?> delete(@PathVariable String letter){
        try {
            this.letterService.delete(letter);
        } catch (NotFoundLetter notFoundLetter) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
