package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Instructor;
import model.Name;
import model.PersonBag;
import model.Student;
import model.Textbook;
import model.TextbookBag;

public class Utilities {

	// public static

	private static String[] firstNames = makeArray("finalProject/names/FirstNames.txt");
	private static String[] lastNames = makeArray("finalProject/names/LastNames.txt");
	private static String[] majorArr = makeMajorArray("finalProject/majors/Majors.txt");
	private static String[] rankArr = makeArray("finalProject/ranks/Ranks.txt");
	private static String[][] tiltleAndIsbnArr = makeTitleAndIsbn("finalProject/Textbooks/textbook_titles.txt",
			"finalProject/Textbooks/textbook_isbns.txt");

	public static int getLinesInFile(File file) {
		int nLines = 0;
		try {
			Scanner scanner = new Scanner(file, "UTF-8");
			while (scanner.hasNextLine()) {
				//String line = 
				scanner.nextLine();
				nLines++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return nLines;
	}

	public static String[] makeArray(String file) {
		File fileName = new File(file);
		String[] hold = new String[getLinesInFile(fileName)];

		Scanner scan = null;
		try {
			scan = new Scanner(fileName, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < hold.length; i++) {
			hold[i] = scan.nextLine();
		}
		return hold;
	}

	public static String[] makeMajorArray(String file) {
		File fileName = new File(file);
		String[] hold = new String[getLinesInFile(fileName)];

		try {
			Scanner scan = new Scanner(fileName, "UTF-8");
			String lineOfMajors = scan.nextLine();
			hold = lineOfMajors.split(", ");
			scan.close();

			return hold;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return hold;
	}

	public static String[][] makeTitleAndIsbn(String titleFileName, String isbnFileName) {
// "finalProject/Textbooks/textbook_titles.txt" , "finalProject/Textbooks/textbook_isbns.txt"

		String[] titleFile = makeArray(titleFileName);
		String[] isbnFile = makeArray(isbnFileName);
		String[][] titleAndIsbn = new String[titleFile.length][2];

		for (int i = 0; i < titleFile.length; i++) {
			titleAndIsbn[i][0] = titleFile[i];
			titleAndIsbn[i][1] = isbnFile[i];
		}

		return titleAndIsbn;

	}

	public static double getRandom(double max, double min) {
		double rand = (((double) ((int) ((Math.random() * (max - min + 1)) * 10))) / 10);
		if (rand > max) {
			rand = max;
		}
		return rand;
	}

	public static Name emitName() {
		return new Name(firstNames[(int) getRandom(firstNames.length-1, 0)],
				lastNames[(int) getRandom(lastNames.length-1, 0)]);
	}

	public static String[] emitTitleAndIsbn() {
		// String[][] twoDArr = tiltleAndIsbnArr;
		String[] arr = new String[tiltleAndIsbnArr.length];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = (tiltleAndIsbnArr[i][0] + ", " + tiltleAndIsbnArr[i][1]);
		}

		return arr;
	}

	public static double emitPrice() {
		return getRandom(200.00, 0);
	}

	public static String emitMajor() {
		// String[] fileOfMajors = makeMajorArray(majorFile);

		return majorArr[(int) getRandom(majorArr.length-1, 0)];
	}

	public static String emitRank() {
		// String[] rankArr = makeArray(rankFile);
		int ran = (int) getRandom(rankArr.length - 1, 0);

		return rankArr[ran];
	}

	public static void importBooks() {

		TextbookBag bag = TextbookBag.getTextbookBag(10);
		Textbook[] lib = new Textbook[tiltleAndIsbnArr.length];
		for (int i = 0; i < tiltleAndIsbnArr.length; i++) {
			lib[i] = new Textbook(tiltleAndIsbnArr[i][0], tiltleAndIsbnArr[i][1], emitName(), emitPrice());
			bag.insert(lib[i]);

		}

	}

	public static void importStudents() {
		PersonBag room = PersonBag.getPersonBag(0);
		for (int i = 0; i < 1000; i++) {//Name name, String major, double gpaemitMajor()
			room.insert(new Student(emitName(), emitMajor(), getRandom(4, 0)));
		}

	}

	public static void importInstructors() {
		PersonBag room = PersonBag.getPersonBag(0);
		for (int i = 0; i < 500; i++) {
			
			room.insert(new Instructor(emitName(), emitRank(), getRandom(10000, 0)));
		}
	}

	public static void importStudentsAndTeachers() {
		importStudents();
		importInstructors();
	}

}
