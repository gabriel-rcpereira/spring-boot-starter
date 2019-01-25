package com.example.springbootstarter.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "generic_entity")
public class GenericEntity {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {

        private GenericEntity genericEntity = new GenericEntity();

        public Builder withId(Long id) {
            genericEntity.setId(id);
            return this;
        }

        public Builder withName(String name) {
            genericEntity.setName(name);
            return this;
        }

        public GenericEntity build() {
            return genericEntity;
        }
    }
}
