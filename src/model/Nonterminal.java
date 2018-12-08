package model;

import java.util.ArrayList;
import java.util.List;

public class Nonterminal extends Token {

    private List<ProductionRule> productionRules;
    public Nonterminal(String value) {
        super(value);
        productionRules = new ArrayList<>();
    }

    public List<ProductionRule> getProductionRule() {
        return productionRules;
    }

    public void setProductionRule(ProductionRule productionRule) {
        productionRules.add(productionRule);
    }
}
