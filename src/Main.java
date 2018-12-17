import console.DisplayResult;
import console.ReadSequence;
import model.Nonterminal;
import model.ProductionRule;
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
                    r1 = new ProductionRule();
                    r2 = new ProductionRule();
                    r3 = new ProductionRule();
                    r1.setRule(Arrays.asList(new Token("a"), nonterminal, new Token("b"), nonterminal)); //aSbS
                    r2.setRule(Arrays.asList(new Token("a"), nonterminal)); //aS
                    r3.setRule(Collections.singletonList(new Token("c"))); //c

                    nonterminal.setProductionRules(Arrays.asList(r1,r2,r3));

                    parser.initialize(nonterminal, sequence);
                    if (parser.parse()) {
                        System.out.println("Sequence " + sequence + " accepted!");
                    } else {
                        System.out.println("Sequence " + sequence + " not accepted!");
                    }
                    break;
                case 2:
                    System.out.println("Loading...");
                    AnalizatorLexical al = new AnalizatorLexical();
                    al.constructFIPandTS();
                    if (al.isEroare()) {
                        System.out.println("Eroare lexicala!");
                    }

                    Nonterminal afisare = new Nonterminal("AFIS");
                    ProductionRule afisRule1 = new ProductionRule();
                    afisRule1.setRule(Arrays.asList(new Token("cout"), new Token("<<"), new Token("ID"), new Token(";"))); //!!!!
                    afisare.setProductionRules(Arrays.asList(afisRule1));

                    Nonterminal citire = new Nonterminal("CITIRE");
                    ProductionRule citRule1 = new ProductionRule();
                    citRule1.setRule(Arrays.asList(new Token("cin"), new Token(">>"), new Token("ID"), new Token(";"))); //!!!!
                    citire.setProductionRules(Arrays.asList(citRule1));

                    Nonterminal expresie = new Nonterminal("EXPR");
                    ProductionRule exprRule1 = new ProductionRule();
                    ProductionRule exprRule2 = new ProductionRule();
                    ProductionRule exprRule3 = new ProductionRule();
                    ProductionRule exprRule4 = new ProductionRule();
                    ProductionRule exprRule5 = new ProductionRule();
                    ProductionRule exprRule6 = new ProductionRule();
                    ProductionRule exprRule7 = new ProductionRule();
                    exprRule1.setRule(Arrays.asList(new Token("ID")));
                    exprRule2.setRule(Arrays.asList(new Token("CONST")));
                    exprRule3.setRule(Arrays.asList(new Token("ID"), new Token("*"), expresie));
//                    exprRule4.setRule(Arrays.asList(expresie, new Token("+"), expresie));
//                    exprRule5.setRule(Arrays.asList(expresie, new Token("%"), expresie));
//                    exprRule6.setRule(Arrays.asList(expresie, new Token("*"), expresie));
//                    exprRule7.setRule(Arrays.asList(expresie, new Token("/"), expresie));
                    expresie.setProductionRules(Arrays.asList(exprRule1, exprRule2, exprRule3));//, exprRule4, exprRule5, exprRule6, exprRule7));

                    Nonterminal atribuire = new Nonterminal("ATRIBUIRE");
                    ProductionRule atrRule1 = new ProductionRule();
                    atrRule1.setRule(Arrays.asList(new Token("ID"), new Token("="), expresie, new Token(";"))); //!!!!
                    atribuire.setProductionRules(Arrays.asList(atrRule1));

                    Nonterminal tip = new Nonterminal("T");
                    ProductionRule tipRule1 = new ProductionRule();
                    ProductionRule tipRule2 = new ProductionRule();
                    tipRule1.setRule(Arrays.asList(new Token("int")));
                    tipRule2.setRule(Arrays.asList(new Token("double")));
                    tip.setProductionRules(Arrays.asList(tipRule1, tipRule2));

                    Nonterminal decl = new Nonterminal("D");
                    ProductionRule declRule1 = new ProductionRule();
                    declRule1.setRule(Arrays.asList(tip, new Token("ID"), new Token(";"))); //!!!!
                    decl.setProductionRules(Arrays.asList(declRule1));

                    Nonterminal instr = new Nonterminal("I");
                    ProductionRule instrRule1 = new ProductionRule();
                    ProductionRule instrRule2 = new ProductionRule();
                    ProductionRule instrRule3 = new ProductionRule();
                    ProductionRule instrRule4 = new ProductionRule();
                    instrRule1.setRule(Collections.singletonList(decl));
                    instrRule2.setRule(Collections.singletonList(atribuire));
                    instrRule3.setRule(Collections.singletonList(citire));
                    instrRule4.setRule(Collections.singletonList(afisare));
                    instr.setProductionRules(Arrays.asList(instrRule1, instrRule2, instrRule3, instrRule4));


                    Nonterminal listai = new Nonterminal("L");
                    ProductionRule listaiRule1 = new ProductionRule();
                    ProductionRule listaiRule2 = new ProductionRule();
                    listaiRule1.setRule(Collections.singletonList(instr));
                    listaiRule2.setRule(Arrays.asList(instr, listai));
                    listai.setProductionRules(Arrays.asList(listaiRule1, listaiRule2));  /**listai = instr | listai instr*/

                    Nonterminal antet = new Nonterminal("A");
                    ProductionRule antetRule = new ProductionRule();
                    antetRule.setRule(Arrays.asList(new Token("int"), new Token("main"), new Token("("), new Token(")")));
                    antet.setProductionRules(Collections.singletonList(antetRule)); /**antet= int main ( )*/
                    Nonterminal program = new Nonterminal("P");
                    ProductionRule programRule = new ProductionRule();
                    programRule.setRule(Arrays.asList(antet, new Token("{"),listai , new Token("}"))); //<-recursiv la stanga
                    program.setProductionRules(Collections.singletonList(programRule));
                    //rules!!!

                    TokenParser programParser = new TokenParser();

                    List<Token> programSequence = new ArrayList<>(al.getFip().fipToSequence());

                    programSequence.forEach(lt -> System.out.println(lt.getValue()+" "));
                    programParser.initialize(program, programSequence);
                    try {
                        if (programParser.parse()) {
                            System.out.println("Sequence " + programSequence + " accepted!");
                        } else {
                            System.out.println("Sequence " + programSequence + " not accepted!");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Optiune gresita\n");

            }
            System.out.print("Alegeti o optiune: ");
            myint = keyboard.nextInt();
            if (myint==0) {
                System.out.println("Multumim frumos! :)");
            }
        }
    }


}
