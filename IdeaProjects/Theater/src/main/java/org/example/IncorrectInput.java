package org.example;

public class IncorrectInput extends Exception {
    public IncorrectInput(String message) {
        super("Incorrect input: " + message);
    }
}
