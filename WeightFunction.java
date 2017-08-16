import java.util.HashMap;
import java.util.Map;

public class WeightFunction {

    private final Map<UndirectedGraphNode, 
                  Map<UndirectedGraphNode, Double>> map;

    public WeightFunction() {
        this.map = new HashMap<>();
    }

    public void put(final UndirectedGraphNode u, 
                    final UndirectedGraphNode v,
                    final double weight) {
        putImpl(u, v, weight);
        putImpl(v, u, weight);
    }

    public double get(final UndirectedGraphNode u, 
                      final UndirectedGraphNode v) {
        return map.get(u).get(v);
    }

    private void putImpl(final UndirectedGraphNode u, 
                         final UndirectedGraphNode v,
                         final double weight) {
        if (!map.containsKey(u)) {
            map.put(u, new HashMap<>());
        }

        map.get(u).put(v, weight);
    }
}