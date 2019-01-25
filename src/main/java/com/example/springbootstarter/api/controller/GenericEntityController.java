package com.example.springbootstarter.api.controller;

import com.example.springbootstarter.api.factory.GenericEntityFactory;
import com.example.springbootstarter.api.model.GenericEntityResponse;
import com.example.springbootstarter.api.model.NotFoundGenericEntityException;
import com.example.springbootstarter.api.model.request.GenericEntityRequest;
import com.example.springbootstarter.api.service.GenericEntityService;
import com.example.springbootstarter.model.entity.GenericEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GenericEntityController {

    private final GenericEntityService genericEntityService;
    private final GenericEntityFactory genericEntityFactory;

    public GenericEntityController(GenericEntityService genericEntityService, GenericEntityFactory genericEntityFactory) {
        this.genericEntityService = genericEntityService;
        this.genericEntityFactory = genericEntityFactory;
    }

    @GetMapping(path = "/example/genericentities")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(genericEntityFactory.createResponseList(this.genericEntityService.finAll()));
    }

    @PostMapping(path = "/example/genericentities")
    public ResponseEntity<?> create(@RequestBody GenericEntityRequest genericEntityRequest){
        GenericEntity genericEntityCreated = genericEntityService
                .create(genericEntityFactory.create(genericEntityRequest));

        return new ResponseEntity<>(genericEntityFactory.createResponse(genericEntityCreated), HttpStatus.CREATED);
    }

    @PutMapping(path = "/example/genericentities/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody GenericEntityRequest genericEntityRequest){
        GenericEntity genericEntityUpdated;
        try {
            genericEntityUpdated = genericEntityService.update(id, genericEntityFactory.create(genericEntityRequest));
        } catch (NotFoundGenericEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genericEntityFactory.createResponse(genericEntityUpdated), HttpStatus.OK);
    }

    @DeleteMapping(path = "/example/genericentities/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try {
            genericEntityService.delete(id);
        } catch (NotFoundGenericEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/example/genericentities/{id}")
    public ResponseEntity<?> fingById(@PathVariable long id){
        GenericEntityResponse response = null;
        try {
            response = genericEntityFactory.createResponse(genericEntityService.findById(id));
        } catch (NotFoundGenericEntityException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/example/genericentities/search")
    public ResponseEntity<?> findByName(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(genericEntityFactory.createResponseList(genericEntityService.findByName(name)));
    }

}
