package model;

import javafx.util.Pair;
import repo.OperatorRepository;

import java.util.ArrayList;
import java.util.List;

public class FIP {
    private List<Pair<Integer,Integer>> fip = new ArrayList<>();

    public FIP() {
    }

    public List<Pair<Integer, Integer>> getFipList() {
        return fip;
    }

    public String fipToSequence() {
        StringBuilder sequence = new StringBuilder();
        OperatorRepository or = new OperatorRepository();
        for (Pair<Integer,Integer> p : fip) {
            //System.out.println(or.findOperator(p.getKey()));
            sequence.append(or.findOperator(p.getKey()));
        }
        return sequence.toString();
    }

}
