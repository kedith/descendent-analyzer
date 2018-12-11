import console.DisplayResult;
import console.FileReader;
import controller.Controller;
import model.FIP;
import model.StateMachine;
import model.TS;
import javafx.util.Pair;
import service.OperatorService;
import service.TSService;

import java.io.IOException;
import java.util.List;

public class AnalizatorLexical {

    private FIP fip = new FIP();
    private TS ts = new TS();
    private boolean eroare = false;

    public FIP getFip() {
        return fip;
    }

    public TS getTs() {
        return ts;
    }

    public boolean isEroare() {
        return eroare;
    }

    public void constructFIPandTS() {
        List<String> listaLinii = null;

        try {
            FileReader fr = new FileReader("program.txt");
            listaLinii = fr.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TSService tsService = new TSService(ts);
        OperatorService os = new OperatorService();

        StateMachine stateMachineForInt = new StateMachine("number.txt");
        Controller controllerForInt = new Controller(stateMachineForInt);

        StateMachine stateMachineForId = new StateMachine("identificator8.txt");
        Controller controllerForId = new Controller(stateMachineForId);


        //String pref = controllerForId.longestAcceptedPrefix("abcdefgheid$f");
        //System.out.println(pref);
        //String pref1 = controllerForId.longestAcceptedPrefix("12abcd45a");
        //System.out.println(pref1);
        //System.out.println("tihamer".charAt(10));
        //System.out.println(" tihamer   ".trim().length());
        int nrLinie = 0;
        String linieAux;
        //boolean eroare = false;

        for (String linie: listaLinii) {
            nrLinie++;
            linieAux = linie;
            eroare = false;
            linie = linie.trim();
            while (linie.length()>0 && !eroare) {
                String identificator = controllerForId.longestAcceptedPrefix(linie);
                String constInt = controllerForInt.longestAcceptedPrefix(linie);
                Pair<Integer,Integer> p;
                if (identificator.length()>0 && os.cautaOperator(identificator) == -1) {
                    if (tsService.cautaInTS(identificator) == -1) {
                        tsService.adaugaInTS(identificator);
                    }
                    p = new Pair<>(1, tsService.cautaInTS(identificator));
                    fip.getFipList().add(p);
                    linie = linie.substring(identificator.length(),linie.length());
                    linie = linie.trim();
                }
                else if (constInt.length()>0 && os.cautaOperator(constInt) == -1) {
                    if (tsService.cautaInTS(constInt) == -1) {
                        tsService.adaugaInTS(constInt);
                    }
                    p = new Pair<>(2, tsService.cautaInTS(constInt));
                    fip.getFipList().add(p);
                    linie = linie.substring(constInt.length(),linie.length());
                    linie = linie.trim();
                }
                else {
                    String constructOperator = "";
                    boolean found = false;
                    while (linie.length() > 0 && !found) {
                        constructOperator = constructOperator + linie.charAt(0);
                        linie = linie.substring(1);
                        if (os.cautaOperator(constructOperator) != -1) {
                            p = new Pair<>(os.cautaOperator(constructOperator), 0);
                            fip.getFipList().add(p);
                            found = true;
                        }
                    }
                    if (!found) {
                        eroare = true;
                    }
                    linie = linie.trim();
                }

            }
            if (eroare) {
                System.out.println("Error!!!");
                System.out.println("nr linie: " + nrLinie);
                System.out.println("linie:" + linieAux);
                break;
            }
        }
        //if (!eroare) {
        //    DisplayResult.displayFIP(fip);
        //    DisplayResult.displayTS(ts);
        //}
    }
}
