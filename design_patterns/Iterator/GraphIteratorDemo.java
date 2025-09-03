package design_patterns.Iterator;

public class GraphIteratorDemo {
    public static void main(String[] args) {
        // Create a graph
        Graph graph = new Graph();
        
        // Add edges (creating the graph from the image)
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(5, 6);
        
        // BFS Traversal
        System.out.println("BFS Traversal:");
        GraphIterator bfs = new BFSIterator(graph, 1);
        while (bfs.hasNext()) {
            System.out.print(bfs.next() + " ");
        }
        
        System.out.println("\n\nDFS Traversal:");
        // DFS Traversal
        GraphIterator dfs = new DFSIterator(graph, 1);
        while (dfs.hasNext()) {
            System.out.print(dfs.next() + " ");
        }
    }
}
