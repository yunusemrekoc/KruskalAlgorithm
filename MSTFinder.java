import java.util.List;


public interface MSTFinder {

    /**
     * Computes the minimum-spanning tree and returns it in the form of a list
     * of undirected edges. If an error occurred during the computation, 
     * <code>null</code> is returned.
     * 
     * @param graph         the graph.
     * @param weightFuntion the weight function over the edges of 
     *                      <code>graph</code>.
     * @return              the list of edges comprising a minimum-spanning 
     *                      tree, or <code>null</code> if an error occurred.
     */
    public List<UndirectedGraphEdge> 
        findMinimumSpanningTree(final List<UndirectedGraphNode> graph,
                                final WeightFunction weightFuntion);
}