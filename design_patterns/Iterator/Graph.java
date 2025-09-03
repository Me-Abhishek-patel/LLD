package design_patterns.Iterator;

import java.util.*;

// Graph class representing a directed graph
class Graph {
    private Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
    
    // Add edge from -> to
    public void addEdge(int from, int to) {
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }
    
    // Get neighbors of a node
    public List<Integer> getNeighbors(int nodeId) {
        return adjacencyList.getOrDefault(nodeId, new ArrayList<>());
    }
    
    // Get all nodes in the graph
    public Set<Integer> getAllNodes() {
        return adjacencyList.keySet();
    }
}
