package homework3.hw3;
import java.io.StringWriter;

public class JavaVector<E> implements JavaContainer<E> {
    private Object[] array; /**  Array to store elements */
    private int sizee; /**Current size of the vector*/
    private int capacity; /** Maximum capacity of the vector*/

    /** Default constructor initializes array with capacity 10*/
    public JavaVector() {
        array = new Object[10];
        sizee = 0;
        capacity = 10;
    }

    /**Constructor with specified initial capacity*/
    public JavaVector(int s) {
        array = new Object[s];
        sizee = 0;
        capacity = s;
    }

    /** Method to add an element to the vector*/
    @Override
    public void add(E element) {
        /** If the vector is full, double its capacity*/
        if (Capacity_Control()) {
            capacity *= 2;
            Object[] newArray = new Object[capacity];
            /**Copy elements from the old array to the new one*/
            for (Object elements : array) {
                newArray[(int) elements] = array[(int) elements];
            }
            sizee++;
            newArray[sizee - 1] = element;
            array = newArray;
        } else {
            sizee++;
            array[sizee - 1] = element;
        }
    }

    /** Method to get an iterator for the vector*/
    public Iterator<E> getIterator() {
        return new JavaVectorIterator<>(array);
    }

    /** Method to remove an element from the vector*/
    @Override
    public void remove(E element) {
        int index = -1; /* Initialize with an invalid index*/
        /** Find the index of the element to remove*/
        for (int i = 0; i < sizee; i++) {
            if (array[i].equals(element)) {
                index = i;
                break; /*Exit the loop once element is found*/
            }
        }
        /**If element is found, remove it*/
        if (index != -1) {
            var newArray = new Object[array.length - 1];
            /**Copy elements before the index*/
            System.arraycopy(array, 0, newArray, 0, index);
            /** Copy elements after the index*/
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);
            array = newArray; /** Update the reference to the new array*/
            sizee--; /** Decrease the size*/
            System.out.println(element + " removed successfully.");
        } else {
            System.out.println("Given element does not exist in the vector.");
        }
    }

    /**Method to get the current size of the vector*/
    @Override
    public int size() {
        return sizee;
    }

    /** Method to get the maximum capacity of the vector*/
    public int getCapacity() {
        return capacity;
    }

    /** Method to check if the vector is full*/
    public boolean Capacity_Control() {
        return size() == getCapacity();
    }

    /** Method to return a string representation of the vector*/
    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        stringWriter.append("\n");
        stringWriter.append("Class name: ");
        stringWriter.append(this.getClass().getSimpleName());
        stringWriter.append("\n");
        /** Append elements to the string*/
        for (int i = 0; i < sizee; i++) {
            stringWriter.append(String.valueOf(array[i]));
            if (i != sizee - 1) {
                stringWriter.append(", ");
            }
        }
        stringWriter.append("\n");
        stringWriter.append("Size: ");
        stringWriter.append(String.valueOf(sizee));
        stringWriter.append("\n");
        return stringWriter.toString();
    }

    /** Method to check if two vectors are equal*/
    @Override
    public boolean equals(Object arr) {
        if (this == arr) {
            return true;
        }
        if (arr == null || getClass() != arr.getClass()) {
            return false;
        }
        JavaVector<?> otherVector = (JavaVector<?>) arr;

        /** Compare sizes*/
        if (sizee != otherVector.sizee) {
            return false;
        }

        /** Compare each element*/
        for (int i = 0; i < sizee; i++) {
            if (!array[i].equals(otherVector.array[i])) {
                return false;
            }
        }

        return true;
    }

    /** Inner class to implement Iterator for the vector*/
    public class JavaVectorIterator<T> implements Iterator<E> {
        private final Object[] array;
        private int currentIndex;

        /** Constructor for the iterator*/
        public JavaVectorIterator(Object[] array) {
            if (array == null) {
                throw new IllegalArgumentException("Array cannot be null.");
            }
            this.array = array;
            this.currentIndex = 0;
        }

        /** Method to check if there are more elements in the vector*/
        @Override
        public boolean hasNext() {
            return currentIndex < array.length;
        }

        /** Method to get the next element from the vector*/
        @Override
        public Object next() {
            if (hasNext()) {
                return array[currentIndex++];
            }
            throw new UnsupportedOperationException("No more elements in the vector.");
        }
    }
}
