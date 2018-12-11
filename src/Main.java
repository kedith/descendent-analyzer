import console.ReadSequence;
import model.Nonterminal;
import model.ProductionRule;
import model.StateType;
import model.Token;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        System.out.println("0 - pentru oprirea programului\n" +
                "1 - analizator sintactic pentru secventa \n" +
                "2 - analizator sintactic pentru un program\n");
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Alegeti o optiune: ");
        int myint = keyboard.nextInt();

        while (myint != 0) {
            switch (myint) {
                case 1:
                    String sequence = ReadSequence.read("sequence.txt");
                    System.out.println("Readed sequence:" + sequence);
                    Parser parser = new Parser();
                    Nonterminal nonterminal = new Nonterminal("S");
                    ProductionRule r1,r2,r3;
                    //Nonterminal n1 = new Nonterminal("S");//,n2 = new Nonterminal("S"),n3 = new Nonterminal("S");
                    r1 = new ProductionRule();
                    r2 = new ProductionRule();
                    r3 = new ProductionRule();
                    r1.setRule(Arrays.asList(new Token("a"), nonterminal, new Token("b"), nonterminal)); //aSbS
                    r2.setRule(Arrays.asList(new Token("a"), nonterminal)); //aS
                    r3.setRule(Collections.singletonList(new Token("c"))); //c

                    nonterminal.setProductionRules(Arrays.asList(r1,r2,r3));
                    //nonterminal.setProductionRules(Arrays.asList(r1,r2,r3));
                    //n1.setProductionRules(Arrays.asList(r1,r2,r3));
                    //n1.setProductionRules(Arrays.asList(r1,r2,r3));

                    parser.initialize(nonterminal, sequence);
                    if (parser.parse()) {
                        System.out.println("Sequence " + sequence + " accepted!");
                    } else {
                        System.out.println("Sequence " + sequence + " not accepted!");
                    }
                    break;
                case 2:
                    System.out.println("Loading...");
                    break;
                default:
                    System.out.println("Optiune gresita\n");

            }
            System.out.print("Alegeti o optiune: ");
            myint = keyboard.nextInt();
        }
    }


}
