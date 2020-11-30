import java.util.Comparator;

/**
 * @author Paulo Moreira 1180778
 * @author Pedro Borda de Água 1180809
 */
public class TPC<L> extends BST<Link> {

    private final Comparator cpf = Comparator.comparing(Link::getNumFronteiras).reversed().thenComparing(Link::getPopulacao);

    @Override
    public void insert(Link l) {
        root = insert(l, root);
    }

    private Node<Link> insert(Link l, Node<Link> node) {
        if (node == null) return new Node<>(l, null, null);
        if (cpf.compare(l, node.getElement()) >= 0) node.setRight(insert(l, node.getRight()));
        if (cpf.compare(l, node.getElement()) < 0) node.setLeft(insert(l, node.getLeft()));
        return node;
    }
}
