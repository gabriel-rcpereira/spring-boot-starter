package com.example.springbootstarter.api.model.request;

import javax.validation.constraints.NotNull;

public class GenericEntityRequest {

    private long id;
    @NotNull
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {

        private GenericEntityRequest genericEntityRequest = new GenericEntityRequest();

        public Builder withId(long id) {
            genericEntityRequest.setId(id);
            return this;
        }

        public Builder withName(String name) {
            genericEntityRequest.setName(name);
            return this;
        }

        public GenericEntityRequest build() {
            return genericEntityRequest;
        }
    }
}
