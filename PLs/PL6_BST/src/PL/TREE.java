
package PL;

import java.util.ArrayList;
import java.util.List;

/*
 * @author DEI-ESINF
 * @param <E>
 */
public class TREE<E extends Comparable<E>> extends BST<E> {

    /**
     * @param element A valid element within the tree
     * @return true if the element exists in tree false otherwise
     */
    public boolean contains(E element) {
        if (element == null) return false;
        return find(element, root) != null;
    }

    public boolean isLeaf(E element) {
        Node<E> node = find(element, root);
        return (node != null) && (node.getLeft() == null && node.getRight() == null);
    }

    /**
     * build a list with all elements of the tree. The elements in the
     * left subtree in ascending order and the elements in the right subtree
     * in descending order.
     *
     * @return returns a list with the elements of the left subtree
     * in ascending order and the elements in the right subtree is descending order.
     */
    public Iterable<E> ascdes() {
        if (root == null) return null;
        List<E> l = new ArrayList<>();
        ascSubtree(root.getLeft(), l);
        l.add(root.getElement());
        desSubtree(root.getRight(), l);
        return l;
    }

    private void ascSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) return;
        ascSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        ascSubtree(node.getRight(), snapshot);
    }

    private void desSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) return;
        ascSubtree(node.getRight(), snapshot);
        snapshot.add(node.getElement());
        ascSubtree(node.getLeft(), snapshot);
    }

    /**
     * Returns the tree without leaves.
     *
     * @return tree without leaves
     */
    public BST<E> autumnTree() {
        BST<E> newTree = new TREE();
        newTree.root = copyRec(root);
        return newTree;
    }

    private Node<E> copyRec(Node<E> node) {
        if (node == null) return node;
        if (isLeaf(node.getElement())) return null;
        return new Node(node.getElement(), copyRec(node.getLeft()), copyRec(node.getRight()));
    }

    /**
     * @return the the number of nodes by level.
     */
    public int[] numNodesByLevel() {
        int level = 0;
        int[] result = new int[this.height() + 1];
        numNodesByLevel(root, result, level);
        return result;
    }

    private void numNodesByLevel(Node<E> node, int[] result, int level) {
        if (node == null) return;
        result[level]++;
        numNodesByLevel(node.getLeft(), result, level + 1);
        numNodesByLevel(node.getRight(), result, level + 1);
    }

}
