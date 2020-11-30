package graphbase;/*
 * A collection of graph algorithms.
 */

import graphbase.Graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author DEI-ESINF
 */
public class GraphAlgorithms {
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {

        LinkedList<V> qbfs = new LinkedList<>();
        LinkedList<V> qaux = new LinkedList<>();

        qbfs.add(vert);
        qaux.add(vert);
        if (!g.validVertex(vert)) {
            return null;
        }
        while (!qaux.isEmpty()) {
            vert = qaux.remove(0);
            for (V vAdj : g.adjVertices(vert)) {
                if (!qbfs.contains(vAdj)) {
                    qbfs.add(vAdj);
                    qaux.add(vAdj);
                }
            }
        }
        return qbfs;
    }

    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        qdfs.add(vOrig);
        visited[g.getKey(vOrig)] = true;

        for (V vAdj : g.adjVertices(vOrig)) {
            if (!qdfs.contains(vAdj)) {
                DepthFirstSearch(g, vAdj, visited, qdfs);
            }
        }

    }

    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {

        if (!g.validVertex(vert)) {
            return null;
        }
        boolean[] visited = new boolean[g.numVertices()];
        LinkedList<V> qdfs = new LinkedList<>();
        DepthFirstSearch(g, vert, visited, qdfs);
        return qdfs;
    }

    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited, LinkedList<V> path, ArrayList<LinkedList<V>> paths) {

        visited[g.getKey(vOrig)] = true;
        path.push(vOrig);
        for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {
            V vert = edge.getVDest() != vOrig ? edge.getVDest() : edge.getVOrig();
            if (vert == vDest) {
                path.push(vDest);
                paths.add(revPath(path));
                path.pop();
            } else {
                if (!visited[g.getKey(vert)]) {
                    allPaths(g, vert, vDest, visited, path, paths);
                }
            }
        }
        path.pop();
        visited[g.getKey(vOrig)] = false;
    }

    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {

        ArrayList<LinkedList<V>> paths = new ArrayList<>();

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return paths;
        }

        boolean[] visited = new boolean[g.numVertices()];
        LinkedList<V> path = new LinkedList<>();
        allPaths(g, vOrig, vDest, visited, path, paths);

        return paths;
    }

    protected static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig, V[] vertices, boolean[] visited, int[] pathKeys, double[] dist) {

        int vertice = g.getKey(vOrig);

        dist[g.getKey(vOrig)] = 0;

        while (vertice != -1) {
            vOrig = vertices[vertice];
            visited[g.getKey(vOrig)] = true;

            for (V v : g.adjVertices(vOrig)) {

                Edge<V, E> edge = g.getEdge(vOrig, v);

                if (!visited[g.getKey(v)] && dist[g.getKey(v)] > (dist[g.getKey(vOrig)] + edge.getWeight())) {

                    dist[g.getKey(v)] = dist[g.getKey(vOrig)] + edge.getWeight();
                    pathKeys[g.getKey(v)] = vertice;

                }

            }
            vertice = getVertMinDist(dist, visited);

        }

    }

    private static <V, E> int getVertMinDist(double[] dist, boolean[] visited) {
        double aux = Double.MAX_VALUE;
        int vertice = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < aux) {
                aux = dist[i];
                vertice = i;
            }
        }
        return vertice;
    }

    protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {

        int vKey = g.getKey(vDest);
        while (g.getKey(verts[vKey]) != g.getKey(vOrig)) {
            path.addFirst(verts[vKey]);
            vKey = pathKeys[vKey];
        }

        path.addFirst(vOrig);

    }

    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
        }

        if (vOrig.equals(vDest)) {
            shortPath.add(vOrig);
            return 1;
        }
        shortPath.clear();
        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = g.allkeyVerts();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);
        if (dist[g.getKey(vDest)] != Double.MAX_VALUE) {
            getPath(g, vOrig, vDest, vertices, pathKeys, shortPath);
            return dist[g.getKey(vDest)];
        }

        return 0;
    }

    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList<Double> dists) {

        if (!g.validVertex(vOrig)) {
            return false;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = g.allkeyVerts();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);

        dists.clear();
        paths.clear();
        for (int i = 0; i < nverts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (int i = 0; i < nverts; i++) {
            LinkedList<V> shortPath = new LinkedList<>();
            if (dist[i] != Double.MAX_VALUE) {
                getPath(g, vOrig, vertices[i], vertices, pathKeys, shortPath);
            }
            paths.set(i, shortPath);
            dists.set(i, dist[i]);
        }
        return true;
    }

    private static <V, E> LinkedList<V> revPath(LinkedList<V> path) {

        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty()) {
            pathrev.push(pathcopy.pop());
        }

        return pathrev;
    }
}
