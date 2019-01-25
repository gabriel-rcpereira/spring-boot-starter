package com.example.springbootstarter.api.model;

public class GenericEntityResponse {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public static final class Builder {

        private GenericEntityResponse genericEntityResponse = new GenericEntityResponse();

        public Builder withId(long id) {
            this.genericEntityResponse.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.genericEntityResponse.name = name;
            return this;
        }

        public GenericEntityResponse build() {
            return genericEntityResponse;
        }
    }
}
