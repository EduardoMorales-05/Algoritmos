public class BTree<E extends Comparable<E>> {
	private BNode<E> root;
	private int orden;
	private boolean up;
	private BNode<E> nDes;
	public BTree(int orden)
	{ this.orden = orden;
	this.root = null;
	}
	public boolean isEmpty() {
		return this.root == null;
	}
	public void insert(E cl)
	{ up = false;
	E mediana;
	BNode<E> pnew;
	mediana = push(this.root, cl);
	if (up) {

	    pnew = new BNode<E>(this.orden);

	    pnew.count = 1;
	    pnew.keys.set(0, mediana);

	    pnew.childs.set(0, this.root);
	    pnew.childs.set(1, nDes);

	    if (this.root != null)
	        this.root.parent = pnew;

	    if (nDes != null)
	        nDes.parent = pnew;

	    this.root = pnew;
	}
	}
	private E push(BNode<E> current, E cl) {

	    int pos[] = new int[1];
	    E mediana;

	    if (current == null) {
	        up = true;
	        nDes = null;
	        return cl;
	    }

	    boolean fl = current.searchNode(cl, pos);

	    if (fl) {
	        System.out.println("Item duplicado");
	        up = false;
	        return null;
	    }

	    mediana = push(current.childs.get(pos[0]), cl);

	    if (up) {

	    	if(current.nodeFull(this.orden)) {
	            mediana = dividedNode(current, mediana, pos[0]);
	        } else {
	            putNode(current, mediana, nDes, pos[0]);
	            up = false;
	        }
	    }

	    return mediana;
	}
	private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {

	    int i;

	    for (i = current.count - 1; i >= k; i--) {

	        current.keys.set(i + 1, current.keys.get(i));
	        current.childs.set(i + 2, current.childs.get(i + 1));
	    }

	    current.keys.set(k, cl);
	    current.childs.set(k + 1, rd);

	    if (rd != null)
	        rd.parent = current;

	    current.count++;
	}
	
	private E dividedNode(BNode<E> current, E cl, int k) {
	    BNode<E> rd = nDes;
	    int i, posMdna;
	    posMdna = (k <= this.orden / 2) ?
	              this.orden / 2 :
	              this.orden / 2 + 1;
	    nDes = new BNode<E>(this.orden);
	    for (i = posMdna; i < this.orden - 1; i++) {
	        nDes.keys.set(i - posMdna, current.keys.get(i));
	        nDes.childs.set(i - posMdna + 1,
	                        current.childs.get(i + 1));
	    }
	    nDes.count = (this.orden - 1) - posMdna;
	    current.count = posMdna;
	    if (k <= this.orden / 2)
	        putNode(current, cl, rd, k);
	    else
	        putNode(nDes, cl, rd, k - posMdna);

	    E median = current.keys.get(current.count - 1);
	    nDes.childs.set(0, current.childs.get(current.count));
	    current.count--;
	    for (i = 0; i <= current.count; i++) {
	        BNode<E> child = current.childs.get(i);

	        if (child != null)
	            child.parent = current;
	    }
	    for (i = 0; i <= nDes.count; i++) {
	        BNode<E> child = nDes.childs.get(i);
	        if (child != null)
	            child.parent = nDes;
	    }
	    return median;
	}
	
	@Override
	public String toString() {

	    if (isEmpty())
	        return "BTree is empty...";

	    StringBuilder sb = new StringBuilder();

	    sb.append("Id.Nodo\tClaves Nodo\tId.Padre\tId.Hijos\n");

	    writeTree(root, sb);

	    return sb.toString();
	}

	private void writeTree(BNode<E> current, StringBuilder sb) {

	    if (current == null)
	        return;

	    sb.append(current.idNode).append("\t");

	    sb.append("(");

	    for (int i = 0; i < current.count; i++) {

	        sb.append(current.keys.get(i));

	        if (i < current.count - 1)
	            sb.append(", ");
	    }

	    sb.append(")\t");

	    if (current.parent == null)
	        sb.append("--\t");
	    else
	        sb.append("[").append(current.parent.idNode).append("]\t");

	    boolean tieneHijos = false;

	    sb.append("[");

	    for (int i = 0; i <= current.count; i++) {

	        BNode<E> hijo = current.childs.get(i);

	        if (hijo != null) {

	            if (tieneHijos)
	                sb.append(", ");

	            sb.append(hijo.idNode);
	            tieneHijos = true;
	        }
	    }

	    sb.append("]");

	    sb.append("\n");

	    for (int i = 0; i <= current.count; i++)
	        writeTree(current.childs.get(i), sb);
	}
  public boolean search(E cl)
{
    return search(root, cl);
}

private boolean search(BNode<E> current, E cl)
{
    if(current == null)
        return false;

    int pos[] = new int[1];

    boolean found = current.searchNode(cl, pos);

    if(found)
    {
        System.out.println(cl + " se encuentra en el nodo " + current.idNode + " en la posición " + pos[0]);

        return true;
    }

    return search(current.childs.get(pos[0]), cl);
}
public void searchRange(E min, E max)
{
    if(min.compareTo(max) > 0)
    {
        System.out.println("Rango inválido");
        return;
    }

    searchRange(root, min, max);

    System.out.println();
}
private void searchRange(BNode<E> current, E min, E max)
{
    if(current == null)
        return;

    int i;

    for(i = 0; i < current.count; i++)
    {
        E key = current.keys.get(i);

        if(key.compareTo(min) > 0)
        {
            searchRange(current.childs.get(i), min, max);
        }

        if(key.compareTo(min) >= 0 &&
           key.compareTo(max) <= 0)
        {
            System.out.print(key + " ");
        }

        if(key.compareTo(max) > 0)
            return;
    }

    searchRange(current.childs.get(current.count), min, max);
}
    public void remove(E key) {
        if (root == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        root.remove(key, orden);

        // Si la raíz quedó vacía tras una fusión, su único hijo pasa a ser la nueva raíz
        if (root.count == 0) {
            if (root.childs.get(0) == null) {
                root = null;
            } else {
                root = root.childs.get(0);
                root.parent = null;
            }
        }
    }

}
