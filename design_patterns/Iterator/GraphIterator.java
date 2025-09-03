package design_patterns.Iterator;

import java.util.Iterator;

// Interface for graph traversal iterators
interface GraphIterator extends Iterator<Integer> {
    // Inherits hasNext() and next() from Iterator interface
}
