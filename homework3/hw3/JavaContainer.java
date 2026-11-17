package homework3.hw3;
/**  JavaContainer: generic container for holding elements*/
public interface JavaContainer<E> {    
    /**  Add an element to the container*/
    void add(E obj1);    
    /**  Remove an element from the container*/
    void remove(E obj2);    
    /**  Get the size of the container*/
    int size();    
    /**  Get an iterator to iterate over the elements in the container*/
    Iterator<E> getIterator();    
    /**  Get a string representation of the container*/
    String toString();    
    /**  Check if another object is equal to this container*/
    boolean equals(Object obj3);
}

