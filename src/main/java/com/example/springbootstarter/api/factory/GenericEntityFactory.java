package com.example.springbootstarter.api.factory;

import com.example.springbootstarter.api.model.GenericEntityResponse;
import com.example.springbootstarter.api.model.request.GenericEntityRequest;
import com.example.springbootstarter.model.entity.GenericEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenericEntityFactory {

    public GenericEntity create(GenericEntityRequest genericEntityRequest){
        return new GenericEntity.Builder()
                .withId(genericEntityRequest.getId())
                .withName(genericEntityRequest.getName())
                .build();
    }

    public List<GenericEntityResponse> createResponseList(List<GenericEntity> genericEntities) {
        return genericEntities.stream().map(genericEntity -> createResponse(genericEntity)).collect(Collectors.toList());
    }

    public GenericEntityResponse createResponse(GenericEntity genericEntity) {
        return new GenericEntityResponse.Builder()
                .withId(genericEntity.getId())
                .withName(genericEntity.getName())
                .build();
    }

}
