package com.example.springbootstarter.model.repository;

import com.example.springbootstarter.model.entity.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenericEntityRepository extends JpaRepository<GenericEntity, Long> {

    List<GenericEntity> findByNameContaining(String name);

    @Override
    <S extends GenericEntity> S save(S entity);
}
