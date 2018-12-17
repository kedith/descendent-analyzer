package model;

import javafx.util.Pair;
import repo.OperatorRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FIP {
    private List<Pair<Integer,Integer>> fip = new ArrayList<>();

    public FIP() {
    }

    public List<Pair<Integer, Integer>> getFipList() {
        return fip;
    }

    public List<Token> fipToSequence() {
        //StringBuilder sequence = new StringBuilder();
        OperatorRepository or = new OperatorRepository();
        List<Token> tokenList = new ArrayList<>();
        for (Pair<Integer,Integer> p : fip) {
            //System.out.println(or.findOperator(p.getKey()));
            //sequence.append(or.findOperator(p.getKey()));
            //System.out.println("itt");
            //System.out.println(or.getOperatorList().stream().filter(pair->pair.getKey().equals(p.getKey())).collect(Collectors.toList()).get(0).getValue());
            Token token = new Token(or.getOperatorList().stream().filter(pair->pair.getKey().equals(p.getKey())).collect(Collectors.toList()).get(0).getValue());
            tokenList.add(token);
        }
        return tokenList;
    }

}
