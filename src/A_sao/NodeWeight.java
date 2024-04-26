package A_sao;

public class NodeWeight{
    String tenDinh;
    int g; 
    int h; 
    int f;

    public NodeWeight(String tenDinh, int g, int h, int f) {
        this.tenDinh = tenDinh;
        this.g = g;
        this.h = h;
        this.f = f;
    }
    @Override
    public String toString() {
        return tenDinh + "-" + f;
    }
}
