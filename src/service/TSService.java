package service;

import model.TS;
import javafx.util.Pair;

import java.util.Comparator;

public class TSService {

    private TS ts;

    public TSService(TS ts) {
        this.ts = ts;
    }

    private int findPositionForPair(Pair<Integer, String> newElem) {
        int poz = 0;
        for (Pair<Integer,String> op : ts.getTsList()) {
            if (op.getValue().compareTo(newElem.getValue()) > 0) {
                return poz;
            }
            poz++;
        }
        return poz;
    }

    public int cautaInTS(String token) {
        for (Pair<Integer,String> op : this.ts.getTsList()) {
            if (op.getValue().equals(token))
                return op.getKey();
        }
        return -1;
    }

    public void adaugaInTS(String token) {
        if (this.ts.getTsList().size() == 0) {
            Pair<Integer, String> op = new Pair<>(100, token);
            this.ts.getTsList().add(this.findPositionForPair(op), op);
        } else {
            int maxId;
            maxId = this.ts.getTsList().stream().max(Comparator.comparing(p->p.getKey())).get().getKey();
            Pair<Integer, String> op = new Pair<>(maxId+1, token);
            this.ts.getTsList().add(this.findPositionForPair(op), op);
        }
    }
}
