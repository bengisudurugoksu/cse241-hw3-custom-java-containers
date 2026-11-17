package homework3.hw3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    /**  Method to determine the type of input (Integer, Double, or String)*/
    private static Class<?> determineType(String input) {
        /**  Try parsing the input as an integer*/
        try {
            Integer.parseInt(input);
            return Integer.class;
        } catch (NumberFormatException e) {
            /**  Try parsing the input as a double*/
            try {
                Double.parseDouble(input);
                return Double.class;
            } catch (NumberFormatException ex) {
                /**  If parsing fails, assume it's a string*/
                return String.class;
            }
        }
    }

    /**  Method to write elements from an iterator to a file*/
    private static <E> void writeToFile(Iterator<E> iterator, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            while (iterator.hasNext()) {
                Object element = iterator.next();
                if (element != null) {
                    writer.write(element.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        /**  Creating instances of JavaVector and JavaSet*/
        /**  Performing operations on JavaVector and JavaSet objects*/
        /**  (Adding elements, checking equality, removing elements, etc.)*/
        JavaVector<Integer> obj= new JavaVector<>();
        obj.add(5);
        obj.add(6);
        obj.add(6);
        JavaVector<Integer> ob= new JavaVector<>();
        ob.add(6);
        ob.add(5);
        boolean yuh = ob.equals(obj);
        System.out.println(yuh);
        System.out.println(obj.size());
        String str = obj.toString();
        System.out.println(str);
        obj.remove(6);
        obj.remove(7);
        String str1 = obj.toString();
        System.out.println(str1);
        JavaVector<String> obj2= new JavaVector<>();
        obj2.add("aaa");
        obj2.add("bbb");
        System.out.println(obj2.getIterator());
        String str2 = obj2.toString();
        System.out.println(str2);
        JavaSet<String> obj3= new JavaSet<>();
        obj3.add("aaa");
        obj3.add("bbb");
        obj3.add("aaa");
        String str3 = obj3.toString();
        System.out.println(str3);
        JavaSet<String> obj4= new JavaSet<>();
        obj4.add("bbb");
        obj4.add("aaa");
        boolean value=obj3.equals(obj4);
        System.out.println(value);

        /**  Creating a Scanner object to take user input*/
        Scanner scanner = new Scanner(System.in);
        try {
            /**  Asking the user to choose the type of array to create*/
            System.out.println("\nChoose the type of array to create:");
            System.out.println("1. JavaVector");
            System.out.println("2. JavaSet");
            System.out.print("Enter your choice (1 or 2): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); /**  Consume newline*/
            if (choice == 1) {
                /**  If the user chooses JavaVector*/
                System.out.println("Enter the size of the vector: ");
                int size;
                try {
                    size = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Error: Input is not a valid integer.");
                    return;
                }

                /**  Creating a JavaVector object with the specified size and adding elements*/
                JavaVector<Object> a = new JavaVector<>(size);
                Class<?> elementType = null;

                /**  Taking user input for each element and adding it to the JavaVector*/
                for (int i = 0; i < size; i++) {
                    System.out.print("Enter element " + (i + 1) + ": ");
                    String input = scanner.nextLine();
                    Object element;

                    if (elementType == null) {
                        /**  Determine the type of the first element*/
                        elementType = determineType(input);
                    }
                    Class<?> currentType = determineType(input);
                    if (!currentType.equals(elementType)) {
                        System.out.println("Error: Input is not the same type as the first given element type.");
                        return;
                    }
                    /**  Compare the type of each subsequent input with the type of the first input*/
                    try {
                        if (elementType == Integer.class) {
                            element = Integer.parseInt(input);
                        } else if (elementType == Double.class) {
                            element = Double.parseDouble(input);
                        } else {
                            element = input; /**  Treat as string*/
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Input is not the same type of the first given element type.");
                        return;
                    }

                    /**  Add the element to the vector*/
                    a.add(element);
                }

                /**  Print the contents of the JavaVector*/
                System.out.println(a.toString());

                /**  Write the contents of the JavaVector to a file*/
                writeToFile(a.getIterator(), "uservectorinput.txt");

            } else if (choice == 2) {
                /**  If the user chooses JavaSet*/
                System.out.println("Enter the size of the set: ");
                int size;
                try {
                    size = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Error: Input is not a valid integer.");
                    return;
                }

                /**  Creating a JavaSet object with the specified size and adding elements*/
                JavaSet<Object> b = new JavaSet<>(size);
                Class<?> elementType = null;

                /**  Taking user input for each element and adding it to the JavaSet*/
                for (int i = 0; i < size; i++) {
                    System.out.print("Enter element " + (i + 1) + ": ");
                    String input = scanner.nextLine();
                    Object element;

                    if (elementType == null) {
                        /**  Determine the type of the first element*/
                        elementType = determineType(input);
                    }
                    /**  Compare the type of each subsequent input with the type of the first input*/
                    Class<?> currentType = determineType(input);
                    if (!currentType.equals(elementType)) {
                        System.out.println("Error: Input is not the same type as the first given element type.");
                        return;
                    }
                    try {
                        if (elementType == Integer.class) {
                            element = Integer.parseInt(input);
                        } else if (elementType == Double.class) {
                            element = Double.parseDouble(input);
                        } else {
                            element = input; /**  Treat as string*/
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Input is not the same type of the first given element type.");
                        return;
                    }

                    /**  Adding element to the set*/
                    b.add(element);
                }

                /**  Print the contents of the JavaSet*/
                System.out.println(b.toString());

                /**  Write the contents of the JavaSet to a file*/
                writeToFile(b.getIterator(), "usersetinput.txt");

            } else {
                /**  If the user enters an invalid choice*/
                System.out.println("Invalid choice.");
                return;
            }

        } finally {
            /**  Closing the scanner in a finally block*/
            scanner.close();
        }

        /** Writing the contents of JavaVector and JavaSet objects to files*/
        writeToFile(obj.getIterator(), "vector.txt");
        writeToFile(obj2.getIterator(), "vector_string.txt");
        writeToFile(obj3.getIterator(), "set.txt");
        writeToFile(obj4.getIterator(), "set_string.txt");
    }
}
