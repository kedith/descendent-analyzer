import model.Nonterminal;
import model.StateType;
import model.Token;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    //if true it means that the analyzer is searching for the next symbol. If it is false, the analyzer does backtracking
    private StateType state;
    private int index; // position in the input sequence
    private List<Token> workStack; //the history of the productions
    private List<Token> inputStack; //sequence that is to be processed

    private String sequence; //needs to be checked by the analyzer if it is accepted
    private Nonterminal start;

    public Parser() {
        workStack = new ArrayList<>();
        inputStack = new ArrayList<>();
    }

    public Parser(StateType state, int index, List<Token> workStack, List<Token> inputStack) {
        this.state = state;
        this.index = index;
        this.workStack = new ArrayList<>(workStack);
        this.inputStack = new ArrayList<>(inputStack);
    }

    /**
     * puts the nonterminal from the beginning of the input stack and appends it to the end of the work stack. Instead of the nonterminal
     * the input stack starts now with the first rule of the nonterminal
     */
    public void expansion() {
        workStack.add(inputStack.remove(0));
        Nonterminal nonterminal = (Nonterminal) workStack.get(workStack.size() - 1);
        inputStack.addAll(0, nonterminal.getProductionRule().getRule());
    }

    /**
     * The first terminal from the input stack is appended to the end of the work stack and the index is incremented
     */
    public void advance() {
        index++;
        workStack.add(inputStack.remove(0));
    }

    public void localFailure() {
        state = StateType.R;
    }

    /**
     * Decrements the index and moves the last token from the work stack to the beginning of the input stack
     */
    public void goBack() {
        index--;
        inputStack.add(0, workStack.remove(workStack.size() - 1));
    }

    /**
     * Another try has 3 options:
     * 1. if the nonterminal at the end of the workstack has another unused rule, then that rule is put to the beginning of the inputstack
     * instead of the old one
     * 2. if there is possibility for backtracking then set back the nonterminal to the beginning of the inputstack(and delete the rule from before)
     * 3. if the index is 0 and the nonterminal is the start terminal then change to the error state
     */
    public void anotherTry() {
        Nonterminal nonterminal = (Nonterminal) workStack.get(workStack.size() - 1);
        if (nonterminal.ruleAmount() > nonterminal.getChosenRule() + 1) {
            state = StateType.Q;
            int ruleLength = nonterminal.getProductionRule().getRule().size();
            inputStack.subList(0, ruleLength).clear(); //remove old rule
            nonterminal.nextRule();
            inputStack.addAll(0, nonterminal.getProductionRule().getRule());
        } else {
            if (index == 0 && workStack.size() == 1 && workStack.get(0).equals(start) && inputStack.equals(start.getProductionRule().getRule())) {
                state = StateType.E;
                int ruleLength = nonterminal.getProductionRule().getRule().size();
                inputStack.subList(0, ruleLength).clear(); //remove old rule
                inputStack.add(workStack.remove(0));
            } else {
                state = StateType.R;
                int ruleLength = nonterminal.getProductionRule().getRule().size();
                inputStack.subList(0, ruleLength).clear(); //remove old rule
                inputStack.add(workStack.remove(workStack.size() - 1));
            }
        }
    }

    public void success() {
        state = StateType.T;
    }

    public List<Token> getWorkStack() {
        return workStack;
    }

    public StateType getState() {
        return state;
    }

    public List<Token> getInputStack() {
        return inputStack;

    }

    public void setStart(Nonterminal start) {
        this.start = start;
    }
}
