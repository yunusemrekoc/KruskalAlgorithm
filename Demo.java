import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Demo {

    private static final int SIZE = 100000;
    private static final float EDGE_LOAD_FACTOR = 15.0f;

    public static void main(final String... args) {
        final List<UndirectedGraphNode> graph = new ArrayList<>();

        // This graph is from English-language Wikipedia-article on 
        // Kruskal's algorithm:
        final UndirectedGraphNode a = new UndirectedGraphNode("A");
        final UndirectedGraphNode b = new UndirectedGraphNode("B");
        final UndirectedGraphNode c = new UndirectedGraphNode("C");
        final UndirectedGraphNode d = new UndirectedGraphNode("D");
        final UndirectedGraphNode e = new UndirectedGraphNode("E");

        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);

        final WeightFunction wf = new WeightFunction();

        a.connectTo(e);
        a.connectTo(b);
        b.connectTo(e);
        e.connectTo(c);
        b.connectTo(c);
        c.connectTo(d);
        e.connectTo(d);

        wf.put(a, e, 1.0);
        wf.put(a, b, 3.0);
        wf.put(b, e, 4.0);
        wf.put(e, c, 6.0);
        wf.put(b, c, 5.0);
        wf.put(c, d, 2.0);
        wf.put(e, d, 7.0);

        final MSTFinder finder = new KruskalMSTFinder();
        final List<UndirectedGraphEdge> mst = 
                finder.findMinimumSpanningTree(graph, wf);

        for (final UndirectedGraphEdge edge : mst) {
            System.out.println(edge + " " + 
                    wf.get(edge.firstNode(), edge.secondNode()));
        }

        System.out.println("MST done.");

        final long seed = System.currentTimeMillis();

        System.out.println("Seed: " + seed);
        final Random rnd = new Random(seed);
        final Pair<List<UndirectedGraphNode>, WeightFunction> data =
                createRandomGraph(SIZE, EDGE_LOAD_FACTOR, rnd);

        System.out.println("Graph constructed.");

        final long ta = System.currentTimeMillis();
        final List<UndirectedGraphEdge> tree =
                finder.findMinimumSpanningTree(data.first, data.second);

        System.out.println(
                "Time: " + (System.currentTimeMillis() - ta) + " ms.");
    }

    public static final class Pair<F, S> {

        public final F first;
        public final S second;

        public Pair(final F first, final S second) {
            this.first = first;
            this.second = second;
        }
    }

    public static <E> E choose(final List<E> list, final Random rnd) {
        if (list.isEmpty()) {
            return null;
        }

        return list.get(rnd.nextInt(list.size()));
    }

    private static Pair<List<UndirectedGraphNode>, WeightFunction>
            createRandomGraph(final int size, 
                              final float edgeLoadFactor,
                              final Random rnd) {
        final List<UndirectedGraphNode> graph = new ArrayList<>(size);
        final WeightFunction wf = new WeightFunction();

        for (int i = 0; i < size; ++i) {
            graph.add(new UndirectedGraphNode("" + i));
        }

        int edges = (int)(Math.min(1.0f, edgeLoadFactor) * size);

        while (edges > 0) {
            final UndirectedGraphNode u = choose(graph, rnd);
            final UndirectedGraphNode v = choose(graph, rnd);
            u.connectTo(v);
            wf.put(u, v, 10.0 * rnd.nextDouble() - 5.0);
            --edges;
        }

        return new Pair(graph, wf);
    }
}