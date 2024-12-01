package com.dsa;

import java.util.Iterator;
/*
 * List of Implementation using Array -generic
 */
import java.util.Scanner;

class DynamicArray1<Type> implements Iterable<Type> { // variables - methods
	private static final int initalCapacity = 16;
	private static final int SHRINK_THRESHOLD = 3; // This is where you define the threshold
	private Type arr[]; // type of array
	private int size;
	private int capacity;

	@SuppressWarnings("unchecked")
	DynamicArray1() {
		size = 0;
		arr = (Type[]) new Object[initalCapacity]; // create object from initalCapacity then convert based on type
		capacity = initalCapacity;
	}

	public void add(Type value) {
		if (size == capacity) { // checking
			expandArray(); // expand the array if the size reaches the capacity
		}
		arr[size++] = value; // add the value and increase the size
	}

	private void expandArray() {
		capacity *= 2; // double the capacity
		java.util.Arrays.copyOf(arr, capacity); // update the array with the new capacity
	}

	public void display() {
		System.out.println("List of elements: ");
		for (int i = 0; i < size; i++) {
			System.out.println(arr[i] + " "); // print each element in the list
		}
		// System.out.println(); // for a new line after the list
	}

	public void insertAtpos(int position, Type value) {
		if (size == capacity)
			expandArray(); // expand the array if the size reaches the capacity
		for (int i = size - 1; i >= position; i--) {
			arr[i + 1] = arr[i]; // shift elements backward
		}
		arr[position] = value; // insert the new value at the specified position
		size++; // Increase the size
	}

	public void deletAtpos(int position) {
		for (int i = position + 1; i <= size; i++) {
			arr[i - 1] = arr[i]; // shift elements forward
		}
		size--; // Decrease the size

		if (capacity > initalCapacity && capacity > SHRINK_THRESHOLD * size) {
			shrinkArray();
		}
	}

	private void shrinkArray() {
		capacity /= 2; // halve the capacity
		arr = java.util.Arrays.copyOf(arr, capacity); // shrink the array
	}

	public int length() {
		return size;
	}

	@Override
	public Iterator<Type> iterator() {
		return new Iterator<Type>() { // custom iterator object - return
			int index = 0;

			public Type next() {
				return arr[index++];
			}

			public boolean hasNext() {
				return index < size;
			}
		};
	}
}

public class DynamicArray {
	public static void main(String[] args) {
		int value, position;
		DynamicArray1<Integer> list = new DynamicArray1<Integer>();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("\n-------- List Menu -----------\n");
			System.out.println("1.Insert at End\n");
			System.out.println("2.Display the list\n");
			System.out.println("3.Insert at specified position \n");
			System.out.println("4.Delete from specified position\n");
			System.out.println("5.Exit\n");
			System.out.println("\n--------------------------------------\n");
			System.out.println("Enter your choice:\t");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the data: ");
				value = sc.nextInt();
				list.add(value);
				break;
			case 2:
				list.display();
				for (int a : list) { // using for-each loop to iterate over the list
					System.out.print(a + " "); // print each element in the list
				}
				break;
			case 3:
				System.out.println("Enter the position(starts at 0): ");
				position = sc.nextInt();
				if (position < 0) {
					System.out.println("Invalid Position");
					break;
				}
				System.out.print("Enter the data: ");
				value = sc.nextInt();
				list.insertAtpos(position, value);
				break;
			case 4:
				System.out.println("Enter the position(starts at 0): ");
				position = sc.nextInt();
				if (position < 0) {
					System.out.println("Invalid Position");
					break;
				}
				list.deletAtpos(position);
				break;
			case 5:
				sc.close(); // close scanner when exiting
				System.exit(0);
			default:
				System.out.println("Invalid choice");
			}
		}
	}

}
