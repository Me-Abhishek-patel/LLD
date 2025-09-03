package design_patterns.Iterator;

import java.util.*;

// Depth-First Search Iterator
class DFSIterator implements GraphIterator {
    private Stack<Integer> stack = new Stack<>();
    private Set<Integer> visited = new HashSet<>();
    private Graph graph;
    
    public DFSIterator(Graph graph, int startNode) {
        this.graph = graph;
        stack.push(startNode);
    }
    
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        int current = stack.pop();
        if (!visited.contains(current)) {
            visited.add(current);
            // Add unvisited neighbors to stack in reverse order
            List<Integer> neighbors = new ArrayList<>(graph.getNeighbors(current));
            Collections.reverse(neighbors);
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        } else {
            return next(); // Skip already visited nodes
        }
        
        return current;
    }
}
