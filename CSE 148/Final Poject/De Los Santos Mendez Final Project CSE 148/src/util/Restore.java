package util;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.Person;
import model.PersonBag;
import model.Textbook;
import model.TextbookBag;

public class Restore {
	public static PersonBag restorePersonBag() {
		PersonBag personBag = PersonBag.getPersonBag(0);
		int count = 0;
		try {
			FileInputStream fis = new FileInputStream("backupFolder/People.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Person personHold;
			
			try {
				while ((personHold = (Person) ois.readObject()) != null) {
					personBag.insert(personHold);
					count++;

				}

			} catch (EOFException e) {
				e.getMessage();
			}
			Person.setIdCount(count);
			// System.out.println(personBag.getNElems());
			ois.close();
			return personBag;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static TextbookBag restoreBookBag() {
		TextbookBag textbookBag = TextbookBag.getTextbookBag(0);
		try {
			FileInputStream fis = new FileInputStream("backupFolder/Textbook.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Textbook textbook;
			try {
				while ((textbook = (Textbook) ois.readObject()) != null) {
					textbookBag.insert(textbook);
				}
			} catch (EOFException e) {
				e.getMessage();
			}
			ois.close();
			return textbookBag;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
