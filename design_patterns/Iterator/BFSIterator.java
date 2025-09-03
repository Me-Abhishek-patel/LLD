package design_patterns.Iterator;

import java.util.*;

// Breadth-First Search Iterator
class BFSIterator implements GraphIterator {
    private Queue<Integer> queue = new LinkedList<>();
    private Set<Integer> visited = new HashSet<>();
    private Graph graph;
    
    public BFSIterator(Graph graph, int startNode) {
        this.graph = graph;
        queue.offer(startNode);
        visited.add(startNode);
    }
    
    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
    
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        int current = queue.poll();
        
        // Add unvisited neighbors to queue
        for (int neighbor : graph.getNeighbors(current)) {
            if (!visited.contains(neighbor)) {
                queue.offer(neighbor);
                visited.add(neighbor);
            }
        }
        
        return current;
    }
}
