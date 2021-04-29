package co.dapi.types;

import java.util.Optional;

public class UserInput {
    private final UserInputID id;
    private final int index;
    private final String query;
    private final String answer;

    /**
     * Creates a UserInput object with all of its info.
     *
     * @param id     type of input required.
     * @param index  the index of this UserInput object, starting from 0.
     *               it's used in case more than one user input is requested.
     *               will always be 0 if only one input is requested.
     * @param query  textual description of what is required from the user side.
     * @param answer user input that must be submitted.
     *               in the response it will always be empty.
     */
    public UserInput(UserInputID id, int index, String query, String answer) {
        this.id = id;
        this.index = index;
        this.query = query;
        this.answer = answer;
    }

    /**
     * Creates a UserInput object with only the fields that's needed for submitting
     * a user input in the request.
     *
     * @param id     type of input required.
     * @param index  the index of this UserInput object, starting from 0.
     *               it's used in case more than one user input is requested.
     *               will always be 0 if only one input is requested.
     * @param answer user input that must be submitted.
     */
    public UserInput(UserInputID id, int index, String answer) {
        this.id = id;
        this.index = index;
        this.query = null;
        this.answer = answer;
    }

    /**
     * returns the id of this UserInput, which is the type of input required.
     */
    public UserInputID getId() {
        return id;
    }

    /**
     * returns the index of this UserInput, starting from 0.
     * will always be 0 if only one input is requested.
     */
    public int getIndex() {
        return index;
    }

    /**
     * returns the textual description of what is required from the user side by this UserInput.
     */
    public Optional<String> getQuery() {
        return Optional.ofNullable(query);
    }

    /**
     * returns the UserInput that must be submitted.
     * in the response it will always be empty.
     */
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
