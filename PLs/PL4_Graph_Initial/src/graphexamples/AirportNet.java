package graphexamples;

import graphbase.Edge;
import graphbase.Graph;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static graphbase.GraphAlgorithms.DepthFirstSearch;

/**
 * @author DEI-ESINF
 */

public class AirportNet {


    Graph<String, Integer> airport;

    public AirportNet() {
        airport = new Graph(true);

    }

    public void addAirport(String a) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addRoute(String a1, String a2, double miles, Integer npasseng) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int keyAirport(String airp) {

        return airport.getKey(airp);
    }

    public String airportbyKey(int key) {
        String[] v = airport.allkeyVerts();
        if (v == null) {
            return null;
        }
        return v[key];
    }

    public Integer trafficAirports(String airp1, String airp2) {
        Edge<String, Integer> edge = airport.getEdge(airp1, airp2);
        if (edge == null) {
            return null;
        }
        return edge.getElement();
    }

    public Double milesAirports(String airp1, String airp2) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String nroutesAirport() {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<String> AirpMaxMiles() {
        double max = 0;
        List<String> airpMax = null;
        for (String v : airport.vertices()) {
            for (Edge<String, Integer> e : airport.outgoingEdges(v)) {
                if (e.getWeight() > max) {
                    max = e.getWeight();
                    airpMax.removeAll(airpMax);
                    airpMax.add(v);
                }
                if (e.getWeight() == max) {
                    airpMax.add(v);
                }
            }
        }
        return airpMax;
    }

    public Boolean ConnectAirports(String airp1, String airp2) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map<String, Integer> nRoutes() {
        int nr;
        Map<String, Integer> ar = new LinkedHashMap<>();
        for (String s : airport.vertices()) {
            nr = airport.inDegree(s) + airport.outDegree(s);
            ar.put(s, nr);
        }
        return ar;
    }

    public boolean reachable(String a1, String a2) {
        LinkedList<String> airps = DepthFirstSearch(airport, a1);

        for (String s : airps) {
            if (s.equals(a2)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public String toString() {
        return airport.toString();
    }
}
