package design_patterns.Iterator;

import java.util.*;

// Node class to represent graph vertices
class Node {
    int id;
    
    public Node(int id) {
        this.id = id;
    }
    
    public String toString() {
        return "Node(" + id + ")";
    }
}
