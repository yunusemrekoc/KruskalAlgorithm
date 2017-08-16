public class UndirectedGraphEdge implements Comparable<UndirectedGraphEdge> {

    private final UndirectedGraphNode u;
    private final UndirectedGraphNode v;
    private final double weight;

    public UndirectedGraphEdge(final UndirectedGraphNode u, 
                               final UndirectedGraphNode v,
                               final double weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    } 

    public UndirectedGraphNode firstNode() {
        return u;
    }

    public UndirectedGraphNode secondNode() {
        return v;
    }

    public String toString() {
        return "[UndirectedGraphEdge between " + u.getId() + " and " + 
                v.getId() + "]";
    }

    public int hashCode() {
        return u.hashCode() ^ v.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof UndirectedGraphEdge)) {
            return false;
        }

        final UndirectedGraphEdge edge = (UndirectedGraphEdge) obj;

        if (this.u.equals(edge.u) && this.v.equals(edge.v)) {
            return true;
        }

        return this.u.equals(edge.v) && this.v.equals(edge.u);
    }

    /**
     * Sorts a sequence of edges into descending order by edge weight.
     * 
     * @param o the other edge to compare against.
     * @return a negative value if the input edge has larger weight,
     *         a positive value if the input edge has smaller weight, and
     *         the value zero if the two weights are equal.
     */
    @Override
    public int compareTo(final UndirectedGraphEdge o) {
        return Double.compare(this.weight, o.weight);
    }
}