package model;

//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Predicate;

import util.Backup;

public class PersonBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 416402043803106802L;
	private static Person[] arr;
	private static int nElems = 0;

	private static PersonBag personBag;

	private PersonBag(int maxSize) {
		arr = new Person[maxSize];
	}

	public static PersonBag getPersonBag(int maxSize) {
		if (personBag == null) {
			personBag = new PersonBag(maxSize);
		}
		return personBag;
	}

	public Person[] getBag() {
		return arr;
	}

	public int getNElems() {
		return nElems;
	}

	public void insert(Person person) {
		try {
			arr[nElems] = person;
			nElems++;
		} catch (ArrayIndexOutOfBoundsException e) {
			Person[] hold = arr.clone();
			arr = new Person[hold.length + 10];
			int count = 0;
			for (Person i : hold) {
				arr[count++] = i;
			}
			insert(person);
		}
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(arr[i]);
		}
		// System.out.println();
	}

	public static Person[] search(Predicate<Person> predicate) {
		Person[] hold = new Person[nElems];
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if (predicate.test(arr[i])) {
				hold[count++] = arr[i];
			}
		}
		return Arrays.copyOf(hold, count);
	}


	public static Person[] delete(Predicate<Person> predicate) {
		Person[] hold = new Person[nElems];
		
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if (predicate.test(arr[i])) {
				hold[count++] = arr[i];
				for (int h = i; h < nElems - 1; h++) {
					arr[h] = arr[h + 1];
				}
				nElems--;
				i--;
			}
		}
		
		Backup.backupPersonBag();
		
		return Arrays.copyOf(hold, count);
	}
}
