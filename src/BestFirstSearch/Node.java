package BestFirstSearch;

public class Node implements Comparable<Node> {
    public String dinh;
    public int heuristic;

    public Node(String dinh, int heuristic) {
        this.dinh = dinh;
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.heuristic, other.heuristic);
    }
}