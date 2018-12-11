package repo;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class OperatorRepository {

    private List<Pair<Integer, String>> operatorList;

    public OperatorRepository() {
        initialize();
    }

    private void initialize() {
        operatorList = new ArrayList<>();
        Pair<Integer, String> p1 = new Pair<>(1, "ID");
        Pair<Integer, String> p2 = new Pair<>(2, "CONST");
        Pair<Integer, String> p3 = new Pair<>(3, "(");
        Pair<Integer, String> p4 = new Pair<>(4, ")");
        Pair<Integer, String> p5 = new Pair<>(5, "{");
        Pair<Integer, String> p6 = new Pair<>(6, "}");
        Pair<Integer, String> p7 = new Pair<>(7, "return");
        Pair<Integer, String> p8 = new Pair<>(8, "if");
        Pair<Integer, String> p9 = new Pair<>(9, "while");
        Pair<Integer, String> p10 = new Pair<>(10, "int");
        Pair<Integer, String> p11 = new Pair<>(11, "cin");
        Pair<Integer, String> p12 = new Pair<>(12, "cout");
        Pair<Integer, String> p13 = new Pair<>(13, "int");
        Pair<Integer, String> p14 = new Pair<>(14, "main");
        Pair<Integer, String> p15 = new Pair<>(15, ";");
        Pair<Integer, String> p16 = new Pair<>(16, "=");
        operatorList.add(p1);
        operatorList.add(p2);
        operatorList.add(p3);
        operatorList.add(p4);
        operatorList.add(p5);
        operatorList.add(p6);
        operatorList.add(p7);
        operatorList.add(p8);
        operatorList.add(p9);
        operatorList.add(p10);
        operatorList.add(p11);
        operatorList.add(p12);
        operatorList.add(p13);
        operatorList.add(p14);
        operatorList.add(p15);
        operatorList.add(p16);
    }

    public List<Pair<Integer, String>> getOperatorList() {
        return operatorList;
    }

    public String findOperator(int key) {

        for (Pair<Integer, String> p : operatorList) {
            if (p.getKey().equals(key)) {
                return p.getValue();
            }
        }
        return null;
    }

}
