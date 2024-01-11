package co.dapi.types;

import java.util.Optional;

public class UserInput {
    private UserInputID id;
    private int index;
    private String query;
    private String answer;

    /**
     * Creates an empty UserInput object.
     *
     */
    public UserInput() {
    }

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
     * Sets the id field.
     */
    public void setId(UserInputID id) {
        this.id = id;
    }

    /**
     * returns the index of this UserInput, starting from 0.
     * will always be 0 if only one input is requested.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the index.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * returns the textual description of what is required from the user side by this UserInput.
     */
    public Optional<String> getQuery() {
        return Optional.ofNullable(query);
    }

    /**
     * Sets the query.
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * returns the UserInput that must be submitted.
     * in the response it will always be empty.
     */
    public Optional<String> getAnswer() {
        return Optional.ofNullable(answer);
    }

    /**
     * Sets the answer.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
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
