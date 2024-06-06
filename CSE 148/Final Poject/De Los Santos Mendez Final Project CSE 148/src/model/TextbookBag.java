package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Predicate;

// import util.Backup;

public class TextbookBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7027398376617401879L;
	private static Textbook[] bookArr;
	private static int nElems = 0;

	private static TextbookBag textbookBag;

	private TextbookBag(int bagSize) {
		bookArr = new Textbook[bagSize];
	}

	public static TextbookBag getTextbookBag(int bagSize) {
		if (textbookBag == null) {
			textbookBag = new TextbookBag(bagSize);
		}
		return textbookBag;
	}

	public Textbook[] getBag() {
		return bookArr;
	}

	public int getNElems() {
		return nElems;
	}

	public void insert(Textbook textbook) {
		try {
			bookArr[nElems] = textbook;
			nElems++;
		} catch (ArrayIndexOutOfBoundsException e) {
			Textbook[] hold = bookArr.clone();
			bookArr = new Textbook[hold.length + 10];
			int count = 0;
			for (Textbook i : hold) {
				bookArr[count++] = i;
			}
			insert(textbook);
		}
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(bookArr[i]);
		}
	}

	public Textbook[] search(Predicate<Textbook> predicate) {
		Textbook[] hold = new Textbook[bookArr.length];
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if (predicate.test(bookArr[i])) {
				hold[count++] = bookArr[i];
			}
		}
		return Arrays.copyOf(hold, count);
	}

	public Textbook[] delete(Predicate<Textbook> predicate) {
		Textbook[] hold = new Textbook[nElems];
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if (predicate.test(bookArr[i])) {
				hold[count++] = bookArr[i];
				for (int h = i; h < nElems-1; h++) {
					bookArr[h] = bookArr[h+1];
				}
				nElems--;
				i--;
			}
		}
//		Backup.backupNewBook();
		return Arrays.copyOf(hold, count);
	}

}
