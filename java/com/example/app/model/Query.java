package com.example.app.model;

public class Query {

    private String query;

    public Query() {}

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "Query{" +
                "query='" + query + '\'' +
                '}';
    }
}
