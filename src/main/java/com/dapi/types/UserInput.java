package com.dapi.types;

import java.util.Optional;

public class UserInput {
    private final String id;
    private final int index;
    private final String query;
    private final String answer;

    public UserInput(String id, int index, String query, String answer) {
        this.id = id;
        this.index = index;
        this.query = query;
        this.answer = answer;
    }

    public UserInput(String id, int index, String answer) {
        this.id = id;
        this.index = index;
        this.query = null;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public Optional<String> getQuery() {
        return Optional.ofNullable(query);
    }

    public Optional<String> getAnswer() {
        return Optional.ofNullable(answer);
    }
}
