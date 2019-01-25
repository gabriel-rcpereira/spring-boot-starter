package com.example.springbootstarter.api.service;

import com.example.springbootstarter.api.model.NotFoundGenericEntityException;
import com.example.springbootstarter.model.entity.GenericEntity;
import com.example.springbootstarter.model.repository.GenericEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericEntityService {

    private final GenericEntityRepository genericEntityRepository;

    public GenericEntityService(GenericEntityRepository genericEntityRepository) {
        this.genericEntityRepository = genericEntityRepository;
    }

    public GenericEntity create(GenericEntity genericEntity){
        return genericEntityRepository.save(genericEntity);
    }

    public GenericEntity update(long id, GenericEntity genericEntity) throws NotFoundGenericEntityException {
        findById(id);
        return genericEntityRepository.save(genericEntity);
    }

    public void delete(long id) throws NotFoundGenericEntityException {
        GenericEntity genericEntity = findById(id);
        genericEntityRepository.delete(genericEntity);
    }

    public List<GenericEntity> finAll(){
        return genericEntityRepository.findAll();
    }

    public GenericEntity findById(long id) throws NotFoundGenericEntityException {
        return genericEntityRepository.findById(id).orElseThrow(() -> new NotFoundGenericEntityException());
    }

    public List<GenericEntity> findByName(String name) {
        return genericEntityRepository.findByNameContaining(name + "%");
    }
}
