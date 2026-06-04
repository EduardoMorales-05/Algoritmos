public class main {
	public static void main(String[] args) {
	    BTree<Integer> arbol = new BTree<>(4);
	    int[] datos = {
	        31,12,19,3,10,13,16,22,25,28,
	        41,57,63,33,35,40,49,52,55,
	        60,62,67,70,72
	    };
	    for (int x : datos)
	        arbol.insert(x);
	    System.out.println(arbol);
      System.out.println(arbol.search(52));
  }
}
