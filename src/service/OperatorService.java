package service;

import javafx.util.Pair;
import repo.OperatorRepository;

import java.util.List;

public class OperatorService {

    private List<Pair<Integer,String>> listaOperatori;

    public int cautaOperator(String token) {
        OperatorRepository or = new OperatorRepository();
        listaOperatori =  or.getOperatorList();
        for (Pair<Integer,String> op : listaOperatori) {
            if (op.getValue().equals(token))
                return op.getKey();
        }
        /*if (token.matches("^[a-zA-Z_$][a-zA-Z_$0-9]*$") && token.length() <= 8)
            return 1;
        if (token.matches("^[0-9]*$"))
            return 2;*/
        return -1;
    }
}
