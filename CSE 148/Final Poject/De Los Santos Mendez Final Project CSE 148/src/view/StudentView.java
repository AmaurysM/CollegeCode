package view;

//  import java.util.Arrays;
import java.util.function.Predicate;

//  import javafx.beans.value.ChangeListener;
//  import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
//  import javafx.scene.Node;
import javafx.scene.control.Button;
//  import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//  import model.Instructor;
import model.Name;
import model.Person;
import model.PersonBag;
import model.Student;
import util.Backup;

public class StudentView {
	private PersonBag personBag;
	private VBox root;

	public StudentView(PersonBag personBag) {
		this.personBag = personBag;

		TextField nameSpace = new TextField();
		nameSpace.setPrefSize(200, 30);
		nameSpace.setPromptText("NAME");

		TextField majorSpace = new TextField();
		majorSpace.setPrefSize(100, 30);
		majorSpace.setPromptText("MAJOR");
		
		TextField gpaSpace = new TextField();
		gpaSpace.setPrefSize(50, 30);
		gpaSpace.setPromptText("GPA");
		
		TextField idSpace = new TextField();
		idSpace.setPrefSize(50, 30);
		idSpace.setPromptText("ID");

		HBox nameGpaBox = new HBox(50);
		nameGpaBox.setAlignment(Pos.CENTER);
		nameGpaBox.getChildren().addAll(nameSpace, majorSpace, gpaSpace,idSpace);

		Button insertBtn = new Button("ADD STUDENT");
		insertBtn.setPrefSize(100, 30);

		Button removeBtn = new Button("REMOVE");
		removeBtn.setPrefSize(100, 30);

		Button searchBtn = new Button("SEARCH");
		searchBtn.setPrefSize(100, 30);
		
		Button updateBtn = new Button("UPDATE");
		updateBtn.setPrefSize(100, 30);

		HBox inReBtn = new HBox(80);
		inReBtn.setAlignment(Pos.CENTER);
		inReBtn.getChildren().addAll(insertBtn, searchBtn, removeBtn,updateBtn);

		TextArea outPutArea = new TextArea();
		outPutArea.setMaxSize(500, 300);
		
		//  ListView<String> outPutArea = new ListView<String>();
		//	 outPutArea.setMaxSize(500, 300);

		VBox inputAccept = new VBox(50);
		inputAccept.setAlignment(Pos.CENTER);
		inputAccept.getChildren().addAll(nameGpaBox, inReBtn, outPutArea);
		// double gpa = 0;

		insertBtn.setOnAction(e -> {
			String studentName = nameSpace.getText();

			int index = 0;
			if (studentName.contains(" ")) {
				while (studentName.charAt(index) != ' ') {
					index++;
				}
			}

			String firstName = studentName;
			String lastName = "";
			if (index > 0) {
				firstName = studentName.substring(0, index);
				lastName = studentName.substring(index + 1, studentName.length());
			}
			double gpa;

			String major = majorSpace.getText();

			try {
				gpa = Double.parseDouble(gpaSpace.getText());
			} catch (NumberFormatException g) {
				gpa = 0;
			}

			if (firstName.equals("")) {
				firstName = lastName;
				lastName = "";
			}
			if(firstName != "") {
				this.personBag.insert(new Student(new Name(firstName, lastName), major, gpa));
				Backup.backupPersonBag();
				// outPutArea.getItems().add(this.personBag.getBag()[personBag.getNElems() - 1].toString() + "\n");
				outPutArea.appendText(this.personBag.getBag()[personBag.getNElems() - 1].toString() + "\n");
			}
			
			// System.out.println();
			// this.personBag.display();
			nameSpace.clear();
			majorSpace.clear();
			gpaSpace.clear();
		});

		removeBtn.setOnAction(e -> {
			String studentId = idSpace.getText();

			if (studentId.equals("") == false) {
				Person[] s1 = PersonBag.delete(new Predicate<Person>() {
					@Override
					public boolean test(Person t) {
						if (t instanceof Student) {
							return ((Student) t).getId().equals(studentId);
						}
						return false;
					}
				});
				//outPutArea.getItems().add(Arrays.toString(s1) + "\n");
				for (Person i : s1) {
					//outPutArea.getItems().add(i.toString() + "\n");
					outPutArea.appendText(i.toString() + "\n");
				}
				idSpace.clear();
			}
			

		});

		searchBtn.setOnAction(e -> {

			String studentName = nameSpace.getText();
			String studentId = idSpace.getText();
			
			int index = 0;
			if (studentName.contains(" ")) {
				while (studentName.charAt(index) != ' ') {
					index++;
				}
			}
			String firstName = (String)studentName;
			String lastName = "";
			if (index > 0) {
				firstName = studentName.substring(0, index);
				lastName = studentName.substring(index + 1, studentName.length());
			}

			String studentMajor = majorSpace.getText();
			String studentGpa = gpaSpace.getText();
			// String firstNameBuffer;
			final String holdFName = firstName;
			final String holdLName = lastName;
			if (studentName.equals("") == false) {
				Person[] p1 = PersonBag.search(new Predicate<Person>() {
					// boolean hold = false;
					@Override
					public boolean test(Person t) {
						if (t instanceof Student) {
							
							if(((Person) t).getName().getFirstName().equals(holdFName) && ((Person) t).getName().getLastName().equals(holdLName)) {
								return true;
							}else if (((Person) t).getName().getFirstName().equals(holdFName)) {
								return true;
							}else if (((Person) t).getName().getLastName().equals(holdLName)) {
								return true;
							}

						}
						return false;
					}
				});
				for (Person i : p1) {
					//outPutArea.getItems().add(i.toString() + "\n");
					outPutArea.appendText(i.toString() + "\n");
				}
				// outPutArea.appendText(Arrays.toString(p1) + "\n");
				nameSpace.clear();
			}

			if (studentMajor.equals("") == false) {
				Person[] p1 = PersonBag.search(new Predicate<Person>() {
					// boolean hold = false;
					@Override
					public boolean test(Person t) {
						if (t instanceof Student) {
							
							try {
								if (((Student) t).getMajor().equals(studentMajor)) {
									return true;
								}
							} catch (ClassCastException e) {
								return false;
							}
						}
						return false;
					}
				});

				for (Person i : p1) {
					// outPutArea.getItems().add(i.toString() + "\n");
					outPutArea.appendText(i.toString() + "\n");
				}
				majorSpace.clear();
			}

			if (studentGpa.equals("") == false) {
				Person[] p1 = PersonBag.search(new Predicate<Person>() {
					// boolean hold = false;
					@Override
					public boolean test(Person t) {
						if (t instanceof Student) {
							if (((Student) t).getGpa() == Double.parseDouble(studentGpa)) {
								return true;
							}
						}
						return false;
					}
				});

				for (Person i : p1) {
					// outPutArea.getItems().add(i.toString() + "\n");
					outPutArea.appendText(i.toString() + "\n");
				}
				idSpace.clear();
			}
			
			if(studentId.equals("") == false) {
				Person[] p1 = PersonBag.search(new Predicate<Person>() {

					@Override
					public boolean test(Person t) {
						if(t instanceof Student) {
							if(((Student) t).getId().equals(studentId)) {
								return true;
							}
						}
						return false;
					}
					
				});
				for (Person i : p1) {

					outPutArea.appendText(i.toString() + "\n");
				}
				idSpace.clear();
			}

		});
		
		updateBtn.setOnAction(e->{
			PersonBag bag = PersonBag.getPersonBag(0);
			
			String studentId = idSpace.getText();
			String name = nameSpace.getText();
			String major = majorSpace.getText();
			String gpa = gpaSpace.getText();
			
			double newGpa;
			try {
				newGpa = Double.parseDouble(gpa);
			} catch (NumberFormatException g) {
				newGpa = 0;
			}
			int index = 0;
			if (name.contains(" ")) {
				while (name.charAt(index) != ' ') {
					index++;
				}
			}

			String firstName = name;
			String lastName = "";
			if (index > 0) {
				firstName = name.substring(0, index);
				lastName = name.substring(index + 1, name.length());
			}
			
			if(studentId.equals("") != true) {
				Person[] i1 = PersonBag.delete(new Predicate<Person>() {
					@Override
					public boolean test(Person t) {
						if(t instanceof Student) {
							return ((Person) t).getId().equals(studentId);
						}
						return false;
					}
				});
				// Name name, String major, double gpa, String id
				bag.insert(new Student(new Name(firstName,lastName),major,newGpa,studentId));
				outPutArea.appendText(i1.toString());
				Backup.backupPersonBag();
			}
			
			idSpace.clear();
			nameSpace.clear();
			majorSpace.clear();
			gpaSpace.clear();
		});
		
		root = new VBox(50);
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(inputAccept);
	}

	public VBox getRoot() {
		return root;
	}
}
