import model.Nonterminal;
import model.ProductionRule;
import model.StateType;
import model.Token;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void expansion() {
        List<Token> workStack = new ArrayList<>();
        workStack.add(new Token("a"));
        List<Token> inputStack = new ArrayList<>();
        Nonterminal nonterminal = new Nonterminal("S");
        ProductionRule productionRule = new ProductionRule();
        List<Token> rule = Arrays.asList(new Token("b"), new Token("a"));
        productionRule.setRule(rule);
        nonterminal.setProductionRule(productionRule);
        inputStack.add(nonterminal);
        inputStack.add(new Token("c"));
        Parser parser = new Parser(StateType.Q,1,workStack, inputStack, nonterminal); // (q,1,a,Sc)
        parser.expansion(); //should make (q,1,aS,bac)

        assertEquals("a", parser.getWorkStack().get(0).getValue());
        assertEquals("S", parser.getWorkStack().get(1).getValue());
        assertEquals("b", parser.getInputStack().get(0).getValue());
        assertEquals("a", parser.getInputStack().get(1).getValue());
        assertEquals("c", parser.getInputStack().get(2).getValue());
    }

    @Test
    void anotherTryWithNewRule(){
        List<Token> workStack = new ArrayList<>();
        workStack.add(new Token("a"));
        List<Token> inputStack = new ArrayList<>();
        Nonterminal nonterminal = new Nonterminal("S");
        ProductionRule productionRule1 = new ProductionRule();
        productionRule1.setRule(Arrays.asList(new Token("b"), new Token("a")));
        nonterminal.setProductionRule(productionRule1);
        ProductionRule productionRule2 = new ProductionRule();
        productionRule2.setRule(Arrays.asList(new Token("a"), new Token("b")));
        nonterminal.setProductionRule(productionRule2);
        workStack.add(nonterminal);
        inputStack.addAll(productionRule1.getRule());
        inputStack.add(new Token("c"));
        Parser parser = new Parser(StateType.R,1,workStack, inputStack, nonterminal); // (r,1,aS1,bac)
        assertEquals(0,nonterminal.getChosenRule());
        parser.anotherTry(); // should generate (r,1,aS2,abc)

        assertEquals(1, nonterminal.getChosenRule());
        assertEquals("a",parser.getInputStack().get(0).getValue());
        assertEquals("b",parser.getInputStack().get(1).getValue());
        assertEquals("c",parser.getInputStack().get(2).getValue());
    }

    @Test
    void anotherTryWithError(){
        List<Token> workStack = new ArrayList<>();
        List<Token> inputStack = new ArrayList<>();
        Nonterminal nonterminal = new Nonterminal("S");
        ProductionRule productionRule1 = new ProductionRule();
        productionRule1.setRule(Arrays.asList(new Token("b"), new Token("a")));
        nonterminal.setProductionRule(productionRule1);
        ProductionRule productionRule2 = new ProductionRule();
        productionRule2.setRule(Arrays.asList(new Token("a"), new Token("b")));
        nonterminal.setProductionRule(productionRule2);
        workStack.add(nonterminal);
        nonterminal.nextRule();
        inputStack.addAll(productionRule2.getRule());
        Parser parser = new Parser(StateType.R,0,workStack, inputStack, nonterminal); // (r,0,S2,ba)
        parser.anotherTry(); //should generate (r,0,epsilon,S)

        assertEquals(StateType.E,parser.getState());
        assertEquals(0,parser.getWorkStack().size());
        assertEquals("S", parser.getInputStack().get(0).getValue());
    }

    @Test
    void anotherTryBacktracking(){
        List<Token> workStack = new ArrayList<>();
        List<Token> inputStack = new ArrayList<>();
        workStack.add(new Token("c"));
        Nonterminal nonterminal = new Nonterminal("S");
        ProductionRule productionRule1 = new ProductionRule();
        productionRule1.setRule(Arrays.asList(new Token("b"), new Token("a")));
        nonterminal.setProductionRule(productionRule1);
        ProductionRule productionRule2 = new ProductionRule();
        productionRule2.setRule(Arrays.asList(new Token("a"), new Token("b")));
        nonterminal.setProductionRule(productionRule2);
        workStack.add(nonterminal);
        nonterminal.nextRule();
        inputStack.addAll(productionRule2.getRule());
        Parser parser = new Parser(StateType.R,2,workStack, inputStack,  nonterminal); // (r,2,cS2,ba)
        parser.anotherTry(); //should generate (r,2,c,S)

        assertEquals(StateType.R, parser.getState());
        assertEquals("c", parser.getWorkStack().get(0).getValue());
        assertEquals("S", parser.getInputStack().get(0).getValue());
    }

    @Test
    void printProduction(){
        List<Token> workStack = new ArrayList<>();
        workStack.add(new Token("a"));
        List<Token> inputStack = new ArrayList<>();
        Nonterminal nonterminal = new Nonterminal("S");
        ProductionRule productionRule = new ProductionRule();
        List<Token> rule = Arrays.asList(new Token("b"), new Token("a"));
        productionRule.setRule(rule);
        nonterminal.setProductionRule(productionRule);
        inputStack.add(new Token("c"));
        inputStack.add(nonterminal);
        workStack.add(nonterminal);
        Parser parser = new Parser(StateType.Q,1,workStack, inputStack,nonterminal); // (q,1,aS0,cS)

        parser.printProduction();
    }


    @Test
    void parse(){
        Parser parser = new Parser();
        Nonterminal nonterminal = new Nonterminal("S");
        ProductionRule r1,r2,r3;
        Nonterminal n1 = new Nonterminal("S"),n2 = new Nonterminal("S"),n3 = new Nonterminal("S");
        r1 = new ProductionRule();
        r2 = new ProductionRule();
        r3 = new ProductionRule();
        r1.setRule(Arrays.asList(new Token("a"), n1, new Token("b"), n2)); //aSbS
        r2.setRule(Arrays.asList(new Token("a"), n3)); //aS
        r3.setRule(Collections.singletonList(new Token("c")));

        nonterminal.setProductionRules(Arrays.asList(r1,r2,r3));
        n1.setProductionRules(Arrays.asList(r1,r2,r3));
        n2.setProductionRules(Arrays.asList(r1,r2,r3));
        n3.setProductionRules(Arrays.asList(r1,r2,r3));

        parser.initialize(nonterminal,"acbc");
        assertTrue(parser.parse());

        parser.initialize(nonterminal,"cb");
        assertFalse(parser.parse());
    }
}