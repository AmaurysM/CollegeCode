package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.PersonBag;
import model.TextbookBag;

public class Backup {

//	static FileOutputStream fos;
//	static ObjectOutputStream oss;

	public static void backupPersonBag() {
		PersonBag bag = PersonBag.getPersonBag(10);
		try {
			FileOutputStream fos = new FileOutputStream("backupFolder/People.dat");
			ObjectOutputStream oss = new ObjectOutputStream(fos);

			for (int i = 0; i < bag.getNElems(); i++) {
				oss.writeObject(bag.getBag()[i]);
			}

			oss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static FileOutputStream fos;
	static ObjectOutputStream oss;

	public static void backupBookBag() {
		TextbookBag bag = TextbookBag.getTextbookBag(10);
		try {
			FileOutputStream fos = new FileOutputStream("backupFolder/Textbook.dat");
			ObjectOutputStream oss = new ObjectOutputStream(fos);
			for (int i = 0; i < bag.getNElems(); i++) {
				oss.writeObject(bag.getBag()[i]);
			}
			// oss.writeObject(TextbookBag.getTextbookBag(0));
			oss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void backupNewPerson() {
		try {
			fos = new FileOutputStream("backupFolder/People.dat");
			ObjectOutputStream oss = new ObjectOutputStream(fos);
			System.out.println("1");

			oss.writeObject(PersonBag.getPersonBag(0).getBag()[PersonBag.getPersonBag(0).getNElems() - 1]);
			oss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void backupNewBook() {
		try {
			FileOutputStream fos = new FileOutputStream("backupFolder/Textbook.dat");
			ObjectOutputStream oss = new ObjectOutputStream(fos);

			oss.writeObject(TextbookBag.getTextbookBag(0).getBag()[TextbookBag.getTextbookBag(0).getNElems() - 1]);
			oss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
