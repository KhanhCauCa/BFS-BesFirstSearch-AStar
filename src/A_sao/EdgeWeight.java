package A_sao;

public class EdgeWeight {
    String dinhDen;
    int cost; 

    public EdgeWeight(String dinhDen, int cost) {
        this.dinhDen = dinhDen;
        this.cost = cost;
    }
    @Override
    public String toString() {
        return "(" + dinhDen + ", " + cost + ")";
    }
}
