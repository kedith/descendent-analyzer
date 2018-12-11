package controller;

import model.StateMachine;
import model.StateNode;

public class Controller {
    private StateMachine stateMachine;
    private String wordToCreate;
    private String formedWord;
    private String acceptedWord;

    public Controller(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public boolean acceptsWord(String word) {
        wordToCreate = word;
        formedWord = "";
        acceptedWord = "";
        return createWord(stateMachine.getStateNodes().get(0));
    }

    private boolean createWord(StateNode currentState) {
        if(currentState.isFinal())
            acceptedWord = formedWord;
        if (wordToCreate.equals(formedWord) && currentState.isFinal())
            return true;
        if (wordToCreate.length() == formedWord.length())
            return false;
        String symbol = Character.toString(wordToCreate.charAt(formedWord.length()));

        if (currentState.makeTransition(symbol) != null) {
            formedWord += symbol;
            return createWord(currentState.makeTransition(symbol));
        }
        return false;
    }

    public String longestAcceptedPrefix(String word){
        acceptsWord(word);
        return acceptedWord;
    }
}
