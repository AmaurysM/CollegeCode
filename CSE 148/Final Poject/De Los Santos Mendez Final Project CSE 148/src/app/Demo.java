package app;

// import java.util.Arrays;

import javafx.application.Application;
// import javafx.application.Platform;
// import javafx.geometry.Pos;
import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.TextArea;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//import model.Instructor;
//import model.Person;
//import model.PersonBag;
//import model.Student;
//import model.Textbook;
//import model.TextbookBag;
//import util.Backup;
import util.Restore;
//import util.Utilities;
import view.ChangeView;
//import view.StudentView;

public class Demo extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ChangeView changeView = new ChangeView();
		Scene scene = new Scene(changeView.getRoot(), 950, 500);
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("People And Books");
		primaryStage.show();

	}

	public static void main(String[] args) {
		
		//Utilities.importStudentsAndTeachers();
//		PersonBag personBag = PersonBag.getPersonBag(100);
//		Person p1 = new Person("A");
//		Person p2 = new Person("B");
//		personBag.insert(p1);
//		personBag.insert(p2);
////		Backup.backupPersonBag();
////		PersonBag restoreBag = Restore.restorePersonBag();
////		System.out.println("After restoration: ");
////		restoreBag.display();
//
//		System.out.println("Show PersonBag under another name: ");
//		PersonBag newPersonBagName = PersonBag.getPersonBag(0);
//		newPersonBagName.display();
//
//		Backup.backupPersonBag();

//		PersonBag myPersonBag = Restore.restorePersonBag();
//		myPersonBag.display();
		// 1
		// System.out.println(Utilities.emitName());

		// 2
//		String[][] hold = Utilities.emitTitleAndIsbn("finalProject/Textbooks/textbook_titles.txt","finalProject/Textbooks/textbook_isbns.txt");
//		for(int i = 0; i < hold.length; i++) {
//			if(hold[i][0] != null) {
//				System.out.println(Arrays.toString(hold[i]));
//			}
//		}
		// 3
//		Double price = Utilities.emitPrice();
//		
//		System.out.println(price);
		// 4
		// "finalProject/Textbooks/textbook_titles.txt" ,
		// "finalProject/Textbooks/textbook_isbns.txt"
//		System.out.println("start");
//		Textbook[] books = Utilities.importBooks("finalProject/names/FirstNames.txt", "finalProject/names/LastNames.txt", "finalProject/Textbooks/textbook_titles.txt", "finalProject/Textbooks/textbook_isbns.txt");
//		TextbookBag lib = TextbookBag.getTextbookBag(10);
//		lib.show();
//		// lib.
//		// System.out.println(books[0]);
//		//lib.show();
//		System.out.println("end");

//		String[] hold = Utilities.makeArray("finalProject/names/FirstNames.txt");
//		int count = 0;
//		for(String i: hold) {
//			System.out.println(count +" "+ i);
//			count++;
//		}
		// System.out.println(hold[0]);
//		//5
//		
//		Student[] classroom = Utilities.importStudent("finalProject/names/FirstNames.txt", "finalProject/names/LastNames.txt", "finalProject/majors/Majors.txt");
//		for(Student i : classroom) {
//			System.out.println(i);
//		}
//		// System.out.println(Arrays.toString(classroom));

		// 6
//		PersonBag room = PersonBag.getPersonBag(0);
//		
//		Utilities.importInstructors();
//		room.display();

//		TextbookBag lib = TextbookBag.getTextbookBag(0);
		
		
//		PersonBag world = PersonBag.getPersonBag(0);
//		//Utilities.importInstructors();
//		Utilities.importStudents();
//		lib.display();		
		//Utilities.importBooks();
		//Utilities.importStudentsAndTeachers();
//		
//		
//		
		 //Backup.backupPersonBag();
		 //Backup.backupBookBag();
		
		//PersonBag bag = 
		Restore.restorePersonBag();
		//TextbookBag textBag = 
		Restore.restoreBookBag();
		// textBag.display();
		//bag.getNElems();
		//System.out.println(bag.getNElems());
		launch(args);

	}

}
