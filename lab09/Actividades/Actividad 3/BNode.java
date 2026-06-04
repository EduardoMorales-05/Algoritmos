package btree;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {

    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;
    private static int nextId = 1;
    protected int idNode;

    public BNode(int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n + 1);
        this.count = 0;

        this.idNode = nextId++;

        for (int i = 0; i < n; i++) {
            keys.add(null);
        }

        for (int i = 0; i < n + 1; i++) {
            childs.add(null);
        }
    }


    public boolean nodeFull() {
        return count == keys.size();
    }


    public boolean nodeEmpty() {
        return count == 0;
    }


    public boolean searchNode(E key, int[] pos) {

        int i = 0;

        while (i < count && key.compareTo(keys.get(i)) > 0) {
            i++;
        }

        pos[0] = i;

        if (i < count && key.compareTo(keys.get(i)) == 0) {
            return true;
        }

        return false;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Nodo ").append(idNode).append(": [");

        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));

            if (i < count - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");

        return sb.toString();
    }
}
