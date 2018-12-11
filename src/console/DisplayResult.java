package console;

import model.FIP;
import model.TS;

public class DisplayResult {

    public static void displayTS(TS ts) {
        System.out.println("TS: \n");
        ts.getTsList().forEach(p -> System.out.println(p.getKey() + " : " + p.getValue() + "\n"));
    }

    public static void displayFIP(FIP fip) {
        System.out.println("FIP: \n");
        fip.getFipList().forEach(p -> System.out.println(p.getKey() + " : " + p.getValue() + "\n"));
    }
}
