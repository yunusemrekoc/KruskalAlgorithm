public class DisjointSet<E> {

    private DisjointSet<E> parent;

    private int rank;

    public DisjointSet() {
        this.parent = this;
        this.rank = 0;
    }

    public DisjointSet<E> find(final DisjointSet<E> set) {
        return getRoot(set);
    }

    public void union(final DisjointSet<E> otherSet) {
        final DisjointSet<E> otherRoot = getRoot(otherSet);
        final DisjointSet<E> thisRoot = getRoot(this);

        if (otherRoot == thisRoot) {
            return;
        }

        if (otherRoot.rank < thisRoot.rank) {
            otherRoot.parent = thisRoot.parent;
        } else if (otherRoot.rank > thisRoot.rank) {
            thisRoot.parent = otherRoot.parent;
        } else {
            thisRoot.parent = otherRoot.parent;
            otherRoot.rank = thisRoot.rank + 1;
        }
    }

    private DisjointSet<E> getRoot(final DisjointSet<E> set) {
        if (set.parent == set) {
            return set;
        }

        return getRoot(set.parent);
    }
}