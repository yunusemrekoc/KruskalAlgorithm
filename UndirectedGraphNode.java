import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class UndirectedGraphNode implements Iterable<UndirectedGraphNode> {

    private final String id;

    private final Set<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(final String id) {
        this.id = id;
        this.neighbors = new LinkedHashSet<>();
    }

    public void connectTo(final UndirectedGraphNode other) {
        this.neighbors.add(other);
        other.neighbors.add(this);
    }

    public boolean isConnectedTo(final UndirectedGraphNode queryNode) {
        return this.neighbors.contains(queryNode);
    }

    public void disconnectFrom(final UndirectedGraphNode neighbor) {
        this.neighbors.remove(neighbor);
        neighbor.neighbors.remove(this);
    }

    public boolean equals(final Object obj) {
        if (!(obj instanceof UndirectedGraphNode)) {
            return false;
        }

        return ((UndirectedGraphNode) obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public Iterator<UndirectedGraphNode> iterator() {
        return new NeighborIterator();
    }

    @Override
    public String toString() {
        return "[UndirectedGraphNode " + id + "]";
    }

    public String getId() {
        return id;
    }

    private class NeighborIterator implements Iterator<UndirectedGraphNode> {

        private final Iterator<UndirectedGraphNode> iterator = 
                UndirectedGraphNode.this.neighbors.iterator();

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public UndirectedGraphNode next() {
            return iterator.next();
        }
    }
}