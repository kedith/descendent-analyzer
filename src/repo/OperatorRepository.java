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
        Pair<Integer, String> p17 = new Pair<>(17, "double");
        Pair<Integer, String> p18 = new Pair<>(18, "-");
        Pair<Integer, String> p19 = new Pair<>(19, "+");
        Pair<Integer, String> p20 = new Pair<>(20, "/");
        Pair<Integer, String> p21 = new Pair<>(21, "*");
        Pair<Integer, String> p22 = new Pair<>(22, "%");
        //Pair<Integer, String> p23 = new Pair<>(23, ">>");
        //Pair<Integer, String> p24 = new Pair<>(24, "<<");
        Pair<Integer, String> p25 = new Pair<>(25, "<");
        Pair<Integer, String> p26 = new Pair<>(26, ">");
        //Pair<Integer, String> p27 = new Pair<>(27, "==");
        //Pair<Integer, String> p28 = new Pair<>(28, "<=");
        //Pair<Integer, String> p29 = new Pair<>(29, ">=");
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
        operatorList.add(p17);
        operatorList.add(p18);
        operatorList.add(p19);
        operatorList.add(p20);
        operatorList.add(p21);
        operatorList.add(p22);
        //operatorList.add(p23);
        //operatorList.add(p24);
        operatorList.add(p25);
        operatorList.add(p26);
        //operatorList.add(p27);
        //operatorList.add(p28);
        //operatorList.add(p29);
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
