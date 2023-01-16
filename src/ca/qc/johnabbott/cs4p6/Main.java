/*
 * Copyright (c) 2021 Ian Clement. All rights reserved.
 */

package ca.qc.johnabbott.cs4p6;

import ca.qc.johnabbott.cs4p6.survey.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Programming IV Intro Survey
 *
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 * @version 2
 */
public class Main {

    public static final String OUTPUT_FILE_NAME = "output.txt";

    /**
     * Entry point to the program.
     * @param args Arguments passed into the program (ex: through the command shell).
     */
    public static void main(String[] args) {
        Survey survey = createIntroductionSurvey();

        survey.administer(new Scanner(System.in));

        try {
            outputResponses(survey.getResponses());
        }
        catch (FileNotFoundException e) {
            System.err.println("Something went wrong writing the survey questions to file...");
            System.err.println("The error message is: " + e.getMessage());
            System.err.println("You should probably copy your answers just in case!");
        }

        System.out.println("Your responses have been written to a file \"" + OUTPUT_FILE_NAME + "\"");
        System.out.println("Please upload this file to the Moodle dropbox called \"Survey\"");
    }

    /**
     * Create a survey with "getting to know you" questions.
     * @return The survey populated with questions of different types.
     */
    private static Survey createIntroductionSurvey() {
        Survey survey = new Survey("Welcome to Programming IV!");

        survey.addQuestion(new ShortAnswerQuestion("Q1.", "What name would you like me to use when addressing you?", 30, true));
        survey.addQuestion(new ShortAnswerQuestion("Q2.", "What is your github username?", 39,true));
        survey.addQuestion(new ShortAnswerQuestion("Q3.", "What are your pronouns? (ex: she/her)", 30));
        survey.addQuestion(new ShortAnswerQuestion("Q4.", "What is your favourite programming language?"));
        survey.addQuestion(new LongAnswerQuestion("Q5.", "What motivates you to code?"));
        survey.addQuestion(new LongAnswerQuestion("Q6.", "What is your favourite text editor?"));
        survey.addQuestion(new LongAnswerQuestion("Q7.", "If you could choose to do anything for a day, what would it be?"));
        survey.addQuestion(new LongAnswerQuestion("Q8.", "Is there anything you'd like me to know in particular about you?"));
        survey.addQuestion(new QuestionQuestion("Q9.", "Ask me a question!"));

        return survey;
    }

    /**
     * Output the user's responses to a CSV file.
     * @param responses The list of responses that the user filled out.
     * @throws FileNotFoundException If the output file is not found.
     */
    private static void outputResponses(List<Response> responses) throws FileNotFoundException {
        FileOutputStream fileOut = new FileOutputStream(OUTPUT_FILE_NAME);
        PrintWriter writer = new PrintWriter(fileOut);

        // Get the timestamp of the last answered question.
        Date last = Utility.maxBy(responses, response -> response.getTimestamp())
                .get()
                .getTimestamp();

        // Format the java Date in a readable format.
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        writer.print(format.format(last));

        // Print all responses.
        for (Response response : responses) {
            String value = response.getResponse();

            writer.print(',');

            if (value.contains(",")) {
                value = "\"" + value + "\"";
            }

            writer.print(value);
        }

        // Close the print writer and therefore the file.
        writer.close();
    }
}
