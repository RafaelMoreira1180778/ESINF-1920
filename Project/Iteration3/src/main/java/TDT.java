import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * @author Paulo Moreira 1180778
 * @author Pedro Borda de Água 1180809
 */
public class TDT<L> extends BST<Link> {

    private final Comparator clat = Comparator.comparing(Link::getLatitude);
    private final Comparator clon = Comparator.comparing(Link::getLongitude);

    @Override
    public void insert(Link l) {
        root = insert(l, root, 0);
    }

    public Link find(double x, double y) {
        return find(x, y, root, 0).getElement();
    }

    public Node<Link> find(double x, double y, Node<Link> node, int depth) {
        if (node == null) return new Node<>(null, null, null);
        double lat = node.getElement().getLatitude();
        double lon = node.getElement().getLongitude();
        if (lat == x && lon == y) return node;
        if (depth % 2 == 0) {
            if (lat >= x) return find(x, y, node.getLeft(), depth + 1);
            if (lat < x) return find(x, y, node.getRight(), depth + 1);
        } else {
            if (lon >= y) return find(x, y, node.getLeft(), depth + 1);
            if (lon < y) return find(x, y, node.getRight(), depth + 1);
        }
        return node;
    }

    public List<Link> encontrarPaisesRetangulo(double x1, double y1, double x2, double y2) {
        double xmin = Math.min(x1, x2);
        double ymin = Math.min(y1, y2);
        double xmax = Math.max(x1, x2);
        double ymax = Math.max(y1, y2);
        return encontrarPaisesRetangulo(xmax, ymax, xmin, ymin, root, 0, new ArrayList<>());
    }

    public Link procuraVizinhoMaisProximo(double x, double y) {
        Node<Link> paisMaisProximo = paisMaisProximo(x, y, root, 0, Double.MAX_VALUE, root);
        Link pmp = paisMaisProximo.getElement();
        double lat = paisMaisProximo.getElement().getLatitude();
        double lon = paisMaisProximo.getElement().getLongitude();
        double minDist = distance(x, y, lat, lon);
        double x1 = lat + minDist;
        double y1 = lon + minDist;
        double x2 = lat - minDist;
        double y2 = lon - minDist;
        List<Link> list = encontrarPaisesRetangulo(x1, y1, x2, y2);
        for (Link l : list) {
            if (distance(x, y, l.getLatitude(), l.getLongitude()) < minDist) {
                minDist = distance(x, y, l.getLatitude(), l.getLongitude());
                pmp = l;
            }
        }
        return pmp;
    }

    private Node<Link> insert(Link l, Node<Link> node, int depth) {
        if (node == null) return new Node<Link>(l, null, null);
        if (depth % 2 == 0) {
            if (clat.compare(l, node.getElement()) >= 0) node.setRight(insert(l, node.getRight(), depth + 1));
            if (clat.compare(l, node.getElement()) < 0) node.setLeft(insert(l, node.getLeft(), depth + 1));
        } else {
            if (clon.compare(l, node.getElement()) >= 0) node.setRight(insert(l, node.getRight(), depth + 1));
            if (clon.compare(l, node.getElement()) < 0) node.setLeft(insert(l, node.getLeft(), depth + 1));
        }
        return node;
    }

    private List<Link> encontrarPaisesRetangulo(double xmax, double ymax, double xmin, double ymin, Node<Link> node, int depth, List<Link> l) {
        if (node == null) return l;
        double lat = node.getElement().getLatitude();
        double lon = node.getElement().getLongitude();
        if (depth % 2 == 0) {
            if (lat > xmax) {
                encontrarPaisesRetangulo(xmax, ymax, xmin, ymin, node.getLeft(), depth + 1, l);
            } else {
                if (lat < xmin) {
                    encontrarPaisesRetangulo(xmax, ymax, xmin, ymin, node.getRight(), depth + 1, l);
                } else {
                    if (lon >= ymin && lon <= ymax) {
                        l.add(node.getElement());
                    }
                    encontrarPaisesRetangulo(xmax, ymax, xmin, ymin, node.getLeft(), depth + 1, l);
                    encontrarPaisesRetangulo(xmax, ymax, xmin, ymin, node.getRight(), depth + 1, l);
                }
            }
        } else {
            if (lon > ymax) {
                encontrarPaisesRetangulo(xmax, ymax, xmin, ymin, node.getLeft(), depth + 1, l);
            } else {
                if (lon < ymin) {
                    encontrarPaisesRetangulo(xmax, ymax, xmin, ymin, node.getRight(), depth + 1, l);
                } else {
                    if (lat >= xmin && node.getElement().getLatitude() <= xmax) {
                        l.add(node.getElement());
                    }
                    encontrarPaisesRetangulo(xmax, ymax, xmin, ymin, node.getLeft(), depth + 1, l);
                    encontrarPaisesRetangulo(xmax, ymax, xmin, ymin, node.getRight(), depth + 1, l);
                }
            }
        }
        return l;
    }

    private Node<Link> paisMaisProximo(double x, double y, Node<Link> node, int depth, double closestDistance, Node<Link> closestNode) {
        if (node == null) return closestNode;
        double lat = node.getElement().getLatitude();
        double lon = node.getElement().getLongitude();
        double d = distance(x, y, lat, lon);
        if (d < closestDistance) {
            closestDistance = d;
            closestNode = node;
        }
        if (depth % 2 == 0) {
            if (lat >= x) closestNode = paisMaisProximo(x, y, node.getLeft(), depth + 1, closestDistance, closestNode);
            if (lat < x) closestNode = paisMaisProximo(x, y, node.getRight(), depth + 1, closestDistance, closestNode);
        } else {
            if (lon >= y) closestNode = paisMaisProximo(x, y, node.getLeft(), depth + 1, closestDistance, closestNode);
            if (lon < y) closestNode = paisMaisProximo(x, y, node.getRight(), depth + 1, closestDistance, closestNode);
        }
        return node;
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
