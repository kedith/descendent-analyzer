import model.StateType;
import model.Token;

import java.util.List;

public class Analyzer {
    //if true it means that the analyzer is searching for the next symbol. If it is false, the analyzer does backtracking
    private StateType state;
    private int index; // position in the input sequence
    private List<Token> workStack; //the history of the productions
    private List<Token> inputStack; //sequence that is to be processed

    public Analyzer() {
    }
}
