/*
 * Copyright (c) 2021 Ian Clement. All rights reserved.
 */

package ca.qc.johnabbott.cs4p6.survey;

/**
 * Long-answer style questions expect a response containing any amount of text.
 */
public class LongAnswerQuestion implements Questionable {

    // Fields

    private String title;
    private String question;
    private boolean required;

    // Constructors

    public LongAnswerQuestion(String question) {
        this("", question, false);
    }

    public LongAnswerQuestion(String title, String question) {
        this(title, question, false);
    }

    public LongAnswerQuestion(String title, String question, boolean required) {
        this.title = title;
        this.question = question;
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
        return true;
    }

//    @Override
//    public String prompt() {
//        return "Just write any text.";
//    }


}
