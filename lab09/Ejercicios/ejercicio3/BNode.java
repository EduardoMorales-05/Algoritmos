import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {

    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    private static int nextId = 1;
    protected int idNode;

    protected BNode<E> parent;

    public BNode(int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n + 1);
        this.count = 0;

        this.idNode = nextId++;
        this.parent = null;

        for (int i = 0; i < n; i++)
            keys.add(null);

        for (int i = 0; i < n + 1; i++)
            childs.add(null);
    }

    public boolean nodeFull(int orden) {
        return count == orden - 1;
    }

    public boolean nodeEmpty() {
        return count == 0;
    }

    public boolean searchNode(E key, int[] pos) {

        int i = 0;

        while (i < count && key.compareTo(keys.get(i)) > 0)
            i++;

        pos[0] = i;

        return i < count && key.compareTo(keys.get(i)) == 0;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Nodo ").append(idNode).append(": [");

        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));

            if (i < count - 1)
                sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

    public void remove(E key, int orden) {
        int[] pos = new int[1];
        boolean encontrado = searchNode(key, pos);
        int idx = pos[0];
        boolean esHoja = (childs.get(0) == null);

        int minClaves = (int) Math.ceil(orden / 2.0) - 1;

        if (encontrado) {
            if (esHoja) {
                removeFromLeaf(idx);
            } else {
                removeFromNonLeaf(idx, orden);
            }
        } else {
            if (esHoja) {
                System.out.println("La clave " + key + " no existe en el árbol.");
                return;
            }

            boolean esUltimoHijo = (idx == count);
            BNode<E> hijoObjetivo = childs.get(idx);

            // Si el hijo donde debe estar la clave tiene menos del mínimo, lo reestructuramos primero
            if (hijoObjetivo.count < minClaves) {
                fill(idx, orden);
            }

            // Si después de reestructurar el último hijo se fusionó, retrocedemos un índice
            if (esUltimoHijo && idx > count) {
                childs.get(idx - 1).remove(key, orden);
            } else {
                childs.get(idx).remove(key, orden);
            }
        }
    }

    private void removeFromLeaf(int idx) {
        // Desplaza las claves hacia la izquierda
        for (int i = idx + 1; i < count; i++) {
            keys.set(i - 1, keys.get(i));
        }
        keys.set(count - 1, null);
        count--;
    }

    private void removeFromNonLeaf(int idx, int orden) {
        E k = keys.get(idx);
        int minClaves = (int) Math.ceil(orden / 2.0) - 1;

        BNode<E> hijoIzquierdo = childs.get(idx);
        BNode<E> hijoDerecho = childs.get(idx + 1);

        if (hijoIzquierdo.count >= minClaves) {
            E pred = getPred(idx);
            keys.set(idx, pred);
            hijoIzquierdo.remove(pred, orden);
        } else if (hijoDerecho.count >= minClaves) {
            E succ = getSucc(idx);
            keys.set(idx, succ);
            hijoDerecho.remove(succ, orden);
        } else {
            merge(idx, orden);
            hijoIzquierdo.remove(k, orden);
        }
    }

    private E getPred(int idx) {
        BNode<E> actual = childs.get(idx);
        while (actual.childs.get(0) != null) {
            actual = actual.childs.get(actual.count);
        }
        return actual.keys.get(actual.count - 1);
    }

    private E getSucc(int idx) {
        BNode<E> actual = childs.get(idx + 1);
        while (actual.childs.get(0) != null) {
            actual = actual.childs.get(0);
        }
        return actual.keys.get(0);
    }

    private void fill(int idx, int orden) {
        int minClaves = (int) Math.ceil(orden / 2.0) - 1;

        if (idx != 0 && childs.get(idx - 1).count >= minClaves) {
            borrowFromPrev(idx);
        } else if (idx != count && childs.get(idx + 1).count >= minClaves) {
            borrowFromNext(idx);
        } else {
            if (idx != count) {
                merge(idx, orden);
            } else {
                merge(idx - 1, orden);
            }
        }
    }

    private void borrowFromPrev(int idx) {
        BNode<E> hijo = childs.get(idx);
        BNode<E> hermano = childs.get(idx - 1);

        // Desplaza las claves e hijos del nodo actual a la derecha
        for (int i = hijo.count - 1; i >= 0; i--) {
            hijo.keys.set(i + 1, hijo.keys.get(i));
        }
        for (int i = hijo.count; i >= 0; i--) {
            hijo.childs.set(i + 1, hijo.childs.get(i));
        }

        // El hijo toma la clave del padre
        hijo.keys.set(0, this.keys.get(idx - 1));

        // Pasa el último hijo del hermano como primer hijo del nodo actual
        if (hermano.childs.get(0) != null) {
            hijo.childs.set(0, hermano.childs.get(hermano.count));
            hijo.childs.get(0).parent = hijo;
            hermano.childs.set(hermano.count, null);
        }

        // El padre toma la última clave del hermano
        this.keys.set(idx - 1, hermano.keys.get(hermano.count - 1));
        hermano.keys.set(hermano.count - 1, null);

        hijo.count++;
        hermano.count--;
    }

    private void borrowFromNext(int idx) {
        BNode<E> hijo = childs.get(idx);
        BNode<E> hermano = childs.get(idx + 1);

        // El hijo toma la clave del padre
        hijo.keys.set(hijo.count, this.keys.get(idx));

        // Toma el primer hijo del hermano
        if (hermano.childs.get(0) != null) {
            hijo.childs.set(hijo.count + 1, hermano.childs.get(0));
            hijo.childs.get(hijo.count + 1).parent = hijo;
        }

        // El padre toma la primera clave del hermano
        this.keys.set(idx, hermano.keys.get(0));

        // Desplaza hacia la izquierda los elementos del hermano
        for (int i = 1; i < hermano.count; i++) {
            hermano.keys.set(i - 1, hermano.keys.get(i));
        }
        hermano.keys.set(hermano.count - 1, null);

        for (int i = 1; i <= hermano.count; i++) {
            hermano.childs.set(i - 1, hermano.childs.get(i));
        }
        hermano.childs.set(hermano.count, null);

        hijo.count++;
        hermano.count--;
    }

    private void merge(int idx, int orden) {
        BNode<E> hijo = childs.get(idx);
        BNode<E> hermano = childs.get(idx + 1);

        int minClaves = (int) Math.ceil(orden / 2.0) - 1;

        // Baja la clave del padre al hijo izquierdo
        hijo.keys.set(minClaves, this.keys.get(idx));

        // Copia las claves del hermano derecho al hijo izquierdo
        for (int i = 0; i < hermano.count; i++) {
            hijo.keys.set(minClaves + 1 + i, hermano.keys.get(i));
        }

        // Copia los hijos del hermano derecho al hijo izquierdo
        if (hermano.childs.get(0) != null) {
            for (int i = 0; i <= hermano.count; i++) {
                hijo.childs.set(minClaves + 1 + i, hermano.childs.get(i));
                hijo.childs.get(minClaves + 1 + i).parent = hijo;
            }
        }

        // Desplaza las claves y los hijos del padre actual a la izquierda
        for (int i = idx + 1; i < count; i++) {
            this.keys.set(i - 1, this.keys.get(i));
        }
        this.keys.set(count - 1, null);

        for (int i = idx + 2; i <= count; i++) {
            this.childs.set(i - 1, this.childs.get(i));
        }
        this.childs.set(count, null);

        hijo.count += hermano.count + 1;
        this.count--;
    }

}
