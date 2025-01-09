import java.util.Scanner;
class Avltree{
    int key, height;
    Node left, right;
    Node(int d) {
        key = d;
        height = 1;
    }
}
public class SimpleAVLTree {
    private Node root;
    private int height(Node N) {
        return (N == null) ? 0 : N.height;
    }
    // Right rotate
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }
    // Left rotate
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }
    // Get balance factor
    private int getBalance(Node N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }
    // Insert a node
    public Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        else return node; // Duplicate keys not allowed
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);
        // Balancing the tree
        if (balance > 1 && key < node.left.key) return rightRotate(node);
        if (balance < -1 && key > node.right.key) return leftRotate(node);
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    // In-order traversal
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }
    public static void main(String[] args) {
        SimpleAVLTree tree = new SimpleAVLTree();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements to insert: ");
        int n = scanner.nextInt();
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            int key = scanner.nextInt();
            tree.root = tree.insert(tree.root, key);
        }
        System.out.println("In-order traversal of the AVL tree:");
        tree.inOrder(tree.root);
        scanner.close();
    }
}
