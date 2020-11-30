
package graphexamples;

import graph.AdjacencyMatrixGraph;
import graph.GraphAlgorithms;

import java.util.LinkedList;

/**
 * A class to represent a labyrinth map with rooms, doors and exits
 * Methods discover the nearest exit and the path to it
 * Stores a graph of private Room vertex and Door edges
 *
 * @author DEI-ESINF
 */
public class LabyrinthCheater {

    AdjacencyMatrixGraph<Room, Door> map;

    public LabyrinthCheater() {
        map = new AdjacencyMatrixGraph<Room, Door>();
    }

    /**
     * Inserts a new room in the map
     *
     * @param name name of the room
     * @return false if city exists in the map
     */
    public boolean insertRoom(String name, boolean hasExit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Inserts a new door in the map
     * fails if room does not exist or door already exists
     *
     * @param from room
     * @param to   room
     * @return false if a room does not exist or door already exists between rooms
     */
    public boolean insertDoor(String from, String to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns a list of rooms which are reachable from one room
     *
     * @param room name of the room
     * @return list of room names or null if room does not exist
     */
    public Iterable<String> roomsInReach(String room) {
        if (!map.checkVertex(new Room(room))) return null;
        LinkedList<Room> rooms = GraphAlgorithms.DFS(map, new Room(room));
        LinkedList<String> names = new LinkedList<>();
        for (Room r : rooms) names.add(r.name);
        return names;
    }

    /**
     *
     *     /**
     *      * Returns the nearest room with an exit
     *      *
     *      * @param room from room
     *      * @return room name or null if from room does not exist or there is no reachable exit
     *
     *

     public String nearestExit(String room) {
     *if (!map.checkVertex(new Room(room))) return null;
     *LinkedList<Room> rooms = GraphAlgorithms.BFS(map, new Room(room));
     *for (Room r : rooms) {
     *if (r.has exit){
     *r.getName
     *
     }
     *}
     *}
     */


    /**
     * Returns the shortest path to the nearest room with an exit
     *
     * @param from from room
     * @return list of room names or null if from room does not exist or there is no reachable exit
     */
    public LinkedList<String> pathToExit(String from) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private class Room {
        String name;

        public Room(String name) {
            this.name = name;
        }
    }

    private class Door {
        String name;

        public Door(String name) {
            this.name = name;
        }
    }

}
