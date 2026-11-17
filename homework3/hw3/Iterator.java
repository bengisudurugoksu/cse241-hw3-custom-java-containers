package homework3.hw3;
/**  Interface for iterating over elements of a collection*/
public interface Iterator<E> {   
    /**  Check if there are more elements in the collection to iterate over*/
    boolean hasNext();   
    /**  Get the next element from the collection*/
    Object next();
}

