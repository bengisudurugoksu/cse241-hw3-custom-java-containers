package homework3.hw3;

import java.io.StringWriter;

public class JavaSet<E> implements JavaContainer<E> {
    private Object[] array; // Array to store elements
    private int sizee; // Number of elements currently stored
    private int capacity; // Total capacity of the array

    // Default constructor
    public JavaSet() {
        array = new Object[10]; // Initialize array with default size
        sizee = 0; // Initialize size to 0
        capacity = 10; // Initialize capacity to default size
    }

    // Parameterized constructor with custom initial capacity
    public JavaSet(int s) {
        array = new Object[s]; // Initialize array with custom size
        sizee = 0; // Initialize size to 0
        capacity = s; // Initialize capacity to custom size
    }

    // Check if the set contains a specific element
    public boolean contains(E element) {
        for (int i = 0; i < sizee; i++) {
            if (array[i].equals(element)) {
                return true; // Element found
            }
        }
        return false; // Element not found
    }

    // Add an element to the set
    @Override
    public void add(E element) {
        // Check if the element already exists in the set
        if (contains(element)) {
            System.out.println("Warning: Element " + element + " already exists in the set.");
            return; // Element already exists, do not add it again
        }

        // If the set is full, increase its capacity
        if (Capacity_Control()) {
            capacity *= 2; // Double the capacity
            Object[] newArray = new Object[capacity]; // Create a new array with the updated capacity
            for (Object elements : array) {
                newArray[(int) elements] = array[(int) elements]; // Copy elements to the new array
            }
            sizee++; // Increase size
            newArray[sizee - 1] = element; // Add the new element
            array = newArray; // Update the reference to the new array
        } else {
            sizee++; // Increase size
            array[sizee - 1] = element; // Add the new element
        }
    }

    // Get an iterator for iterating over the elements of the set
    public Iterator<E> getIterator() {
        return new JavaSetIterator<>(array);
    }

    // Remove an element from the set
    @Override
    public void remove(E element) {
        int index = -1; // Initialize with an invalid index
        for (int i = 0; i < sizee; i++) {
            if (array[i].equals(element)) { // Use equals for object comparison
                index = i; // Store the index of the element to be removed
                break; // Exit the loop once element is found
            }
        }
        if (index != -1) {
            // Create a new array with one less element
            var newArray = new Object[array.length - 1];
            // Copy elements before the element to be removed
            System.arraycopy(array, 0, newArray, 0, index);
            // Copy elements after the element to be removed
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);
            array = newArray; // Update the reference to the new array
            sizee--; // Decrease size
            System.out.println(element + "removed successfully.");
        } else {
            System.out.println("Given element does not exist in the set.");
        }
    }

    // Get the size of the set
    @Override
    public int size() {
        return sizee;
    }

    // Get the capacity of the set
    public int getCapacity() {
        return capacity;
    }

    // Check if the set needs to increase its capacity
    public boolean Capacity_Control() {
        return size() == getCapacity();
    }

    // Generate a string representation of the set
    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        stringWriter.append("\n");
        stringWriter.append("Class name: ");
        stringWriter.append(this.getClass().getSimpleName());
        stringWriter.append("\n");
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

    // Check if two sets are equal
    @Override
    public boolean equals(Object arr) {
        if (this == arr) {
            return true; // Same object reference
        }
        if (arr == null || getClass() != arr.getClass()) {
            return false; // Different classes or null object
        }
        JavaSet<?> otherSet = (JavaSet<?>) arr;

        if (sizee != otherSet.sizee) {
            return false; // Different sizes
        }

        // Check if all elements of this set exist in the other set
        for (int i = 0; i < sizee; i++) {
            boolean found = false;
            for (int j = 0; j < otherSet.sizee; j++) {
                if (array[i].equals(otherSet.array[j])) {
                    found = true; // Element found in the other set
                    break;
                }
            }
            if (!found) {
                return false; // Element not found in the other set
            }
        }

        return true; // Sets are equal
    }

    // Iterator for iterating over the elements of the set
    public class JavaSetIterator<T> implements Iterator<E> {
        private final Object[] array;
        private int currentIndex;

        // Constructor
        public JavaSetIterator(Object[] array) {
            if (array == null) {
                throw new IllegalArgumentException("Array cannot be null.");
            }
            this.array = array;
            this.currentIndex = 0;
        }

        // Check if there are more elements to iterate over
        @Override
        public boolean hasNext() {
            return currentIndex < array.length;
        }

        // Get the next element
        @Override
        public Object next() {
            if (hasNext()) {
                return array[currentIndex++]; // Return the current element and move to the next index
            }
            throw new UnsupportedOperationException("No more elements in the set.");
        }
    }
}
