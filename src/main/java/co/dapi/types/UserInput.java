package co.dapi.types;

import java.util.Optional;

public class UserInput {
    private final UserInputID id;
    private final int index;
    private final String query;
    private final String answer;

    public UserInput(UserInputID id, int index, String query, String answer) {
        this.id = id;
        this.index = index;
        this.query = query;
        this.answer = answer;
    }

    public UserInput(UserInputID id, int index, String answer) {
        this.id = id;
        this.index = index;
        this.query = null;
        this.answer = answer;
    }

    public UserInputID getId() {
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

    public enum UserInputID {
        otp,
        secret_question,
        captcha,
        pin,
        confirmation,
        token
    }
}
