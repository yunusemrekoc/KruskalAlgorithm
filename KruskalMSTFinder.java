import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class KruskalMSTFinder implements MSTFinder {

    private final Map<UndirectedGraphNode, 
                      DisjointSet<UndirectedGraphNode>> map;
    private final Set<UndirectedGraphEdge> edgeSet;
    private final List<UndirectedGraphEdge> edgeList;

    public KruskalMSTFinder() {
        this.map = new HashMap<>();
        this.edgeSet = new LinkedHashSet<>();
        this.edgeList = new ArrayList<>();
    }

    @Override
    public List<UndirectedGraphEdge> 
        findMinimumSpanningTree(final List<UndirectedGraphNode> graph,
                                final WeightFunction weightFunction) {
        try {
            prepareEdgeList(graph, weightFunction);
            final List<UndirectedGraphEdge> minimumSpanningTree = new ArrayList<>();

            for (final UndirectedGraphEdge edge : edgeList) {
                final UndirectedGraphNode u = edge.firstNode();
                final UndirectedGraphNode v = edge.secondNode();

                DisjointSet<UndirectedGraphNode> us = map.get(u);
                DisjointSet<UndirectedGraphNode> vs = map.get(v);

                us = us.find(us);
                vs = vs.find(vs);

                if (us != vs) {
                    minimumSpanningTree.add(edge);
                    us.union(vs);
                }
            }

            clearAll();
            return minimumSpanningTree;
        } catch (final Exception e) {
            clearAll();
            return null;
        }
    }

    private void clearAll() {
        map.clear();
        edgeSet.clear();
        edgeList.clear();
    }

    private void prepareEdgeList(final List<UndirectedGraphNode> graph,
                                 final WeightFunction weightFunction) {
        for (final UndirectedGraphNode node : graph) {
            map.put(node, new DisjointSet<>());

            for (final UndirectedGraphNode neighbor : node) {
                edgeSet.add(
                        new UndirectedGraphEdge(node, 
                                                neighbor, 
                                                weightFunction
                                                        .get(node, neighbor)));
            }
        }

        edgeList.addAll(edgeSet);
        Collections.<UndirectedGraphEdge>sort(edgeList);
    }
}