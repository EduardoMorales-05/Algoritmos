public class Main 
{
  public static void main(String[] args)
  {
    BTree<Integer> tree = new BTree<>(4);

    tree.insert(50);
    tree.insert(20);
    tree.insert(70);
    tree.insert(10);
    tree.insert(30);
    tree.insert(60);
    tree.insert(80);

    System.out.println(tree.search(50));
    System.out.println(tree.search(10));
    System.out.println(tree.search(80));
    System.out.println(tree.search(100));
  }
}
