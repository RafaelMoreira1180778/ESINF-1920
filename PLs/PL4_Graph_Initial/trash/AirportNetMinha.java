package graphbase;

import java.util.*;

public class AirportNetMinha {

    private Graph<String, Integer> airport;


    public AirportNetMinha() {
        airport = new Graph(true);
    }

    public int getKey(String arpt) {
        return airport.getKey(arpt);
    }

    ;

    public String getAirport(int key) {
        String[] o = airport.getKeyVerts();
        if (o == null) return null;
        return o[key];
    }

    ;

    public Integer traffic(String arpt, String arpt2) {
        Edge<String, Integer> edge = airport.getEdge(arpt, arpt2);
        if (edge == null) return null;
        return edge.getElement();
    }

    public Map<String, Integer> nRoutes() {
        int nr = 0;
        Map<String, Integer> ar = new LinkedHashMap<>();
        for (String v : airport.vertices) {
            nr = airport.inDegree(v) + airport.outDegree(v);
            ar.put(v, nr);
        }
    }

    public String maxMiles() {
        double max = 0;
        List<String> arptMax = new ArrayList();
        for (String v : airport.vertices()) {
            for (Edge<String, Integer> e : airport.outGoingEdges(v)) {
                if (e.getWeight() > max) {
                    max = e.getWeight();
                    arptMax.removeAll(arprtMax);
                    arptMax.add(e);
                }
                if (e.getWeight() == max) {
                    arprtMax.add(e);
                }
            }
        }
        return arptMax;
    }

    public void reachable(String a1, String a2) {
        LinkedList<String> arpts = DepthFirstSearch(airport, ar);
        for (String s : arpts) {
            if (s.equals(s)) {
                return true;
            }
            return false;
        }
    }

}
