/*
 * Copyright (c) 2022 Ian Clement. All rights reserved.
 */

package ca.qc.johnabbott.cs4p6.survey;

/**
 * Short-answer style questions expect a response containing a limited length of text.
 */
public class ShortAnswerQuestion implements Questionable {

    private static final int DEFAULT_LENGTH = 128;

    // Fields

    private String title;
    private String question;
    private boolean required;
    private int limit;

    // Constructors

    public ShortAnswerQuestion(String question) {
        this("", question, DEFAULT_LENGTH, false);
    }

    public ShortAnswerQuestion(String title, String question) {
        this(title, question, DEFAULT_LENGTH, false);
    }

    public ShortAnswerQuestion(String title, String question, boolean required) {
        this(title, question, DEFAULT_LENGTH, required);
    }

    public ShortAnswerQuestion(String title, String question, int limit) {
        this(title, question, limit, false);
    }

    public ShortAnswerQuestion(String title, String question, int limit, boolean required) {
        this.title = title;
        this.question = question;
        this.limit = limit;
        this.required = required;
    }

    // Methods

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public boolean isValidResponse(String response) {
        return response.trim().length() <= limit;
    }

//    @Override
//    public String prompt() {
//        return String.format("Please answer the question with at most %d characters.", limit);
//    }
}
