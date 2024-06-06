package view;

// import java.util.Arrays;
import java.util.function.Predicate;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Instructor;
import model.Name;
import model.Person;
import model.PersonBag;
// import model.Student;
// import model.Student;
import util.Backup;

public class InstructorView {
	private PersonBag personBag;
	private VBox root;

	public InstructorView(PersonBag personBag) {
		this.personBag = personBag;

		TextField nameSpace = new TextField();
		nameSpace.setPrefSize(200, 30);
		nameSpace.setPromptText("NAME");

		TextField rankSpace = new TextField();
		rankSpace.setPrefSize(150, 30);
		rankSpace.setPromptText("RANK");

		TextField salarySpace = new TextField();
		salarySpace.setPrefSize(100, 30);
		salarySpace.setPromptText("SALARY");
		
		TextField idSpace = new TextField();
		idSpace.setPrefSize(50, 30);
		idSpace.setPromptText("ID");
		
		HBox textBox = new HBox(50);
		textBox.setAlignment(Pos.CENTER);
		textBox.getChildren().addAll(nameSpace, rankSpace, salarySpace, idSpace);

		Button insertBtn = new Button("INCERT TEACHER");
		insertBtn.setPrefHeight(30);

		Button removeBtn = new Button("REMOVE");
		removeBtn.setPrefSize(100, 30);

		Button searchBtn = new Button("SEARCH");
		searchBtn.setPrefSize(100, 30);
		
		Button updateBtn = new Button("UPDATE");
		updateBtn.setPrefSize(100, 30);

		HBox inReBtn = new HBox(80);
		inReBtn.setAlignment(Pos.CENTER);
		inReBtn.getChildren().addAll(insertBtn, removeBtn, searchBtn, updateBtn);

		TextArea outPutArea = new TextArea();
		outPutArea.setMaxSize(500, 300);

		VBox inputAccept = new VBox(50);
		inputAccept.setAlignment(Pos.CENTER);
		inputAccept.getChildren().addAll(textBox, inReBtn, outPutArea);

		insertBtn.setOnAction(e -> {
			String instructorName = nameSpace.getText();

			int index = 0;
			if (instructorName.contains(" ")) {
				while (instructorName.charAt(index) != ' ' || instructorName.length() < index) {
					System.out.println(index);
					index++;
				}
			}

			String firstName = instructorName;
			String lastName = "";
			if (index > 0) {
				firstName = instructorName.substring(0, index);
				lastName = instructorName.substring(index + 1, instructorName.length());
			}

			double salary;
			String rank = rankSpace.getText();

			try {
				salary = Double.parseDouble(salarySpace.getText());
			} catch (NumberFormatException g) {
				salary = 0;
			}

			this.personBag.insert(new Instructor(new Name(firstName, lastName), rank, salary));
			Backup.backupPersonBag();
			outPutArea.appendText(this.personBag.getBag()[personBag.getNElems() - 1].toString() + "\n");

			this.personBag.display();
			nameSpace.clear();
			rankSpace.clear();
			salarySpace.clear();
		});

		removeBtn.setOnAction(e -> {
			String instructorId = idSpace.getText();

			if (instructorId.equals("") == false) {
				Person[] s1 = PersonBag.delete(new Predicate<Person>() {
					@Override
					public boolean test(Person t) {
						if (t instanceof Instructor) {
							return ((Instructor) t).getId().equals(instructorId);
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

			String instructorName = nameSpace.getText();
			int index = 0;
			if (instructorName.contains(" ")) {
				while (instructorName.charAt(index) != ' ') {
					index++;
				}
			}

			String instructorRank = rankSpace.getText();
			String instructorSalary = salarySpace.getText();
			String instructorId = idSpace.getText();
			// String firstNameBuffer;

			if (instructorName.equals("") == false) {
				Person[] p1 = PersonBag.search(new Predicate<Person>() {
					// boolean hold = false;
					@Override
					public boolean test(Person t) {
						if (t instanceof Instructor) {
							if (((Person) t).getName().getFirstName().equals(instructorName) && ((Person) t).getName().getLastName().equals(instructorName)) {
								return true;
							}else if (((Person) t).getName().getFirstName().equals(instructorName)) {
								return true;
							}else if (((Person) t).getName().getLastName().equals(instructorName)) {
								return true;
							}
						}
						return false;
					}
				});

				for (Person i : p1) {
					outPutArea.appendText(i.toString() + "\n");
				}
				nameSpace.clear();
			}

			if (instructorRank.equals("") == false) {
				Person[] p1 = PersonBag.search(new Predicate<Person>() {
					// boolean hold = false;
					@Override
					public boolean test(Person t) {
						if (t instanceof Instructor) {
							if (((Instructor) t).getRank().equals(instructorRank)) {
								return true;
							}
						}
						return false;
					}
				});

				for (Person i : p1) {
					outPutArea.appendText(i.toString() + "\n");
				}
				rankSpace.clear();
			}

			if (instructorSalary.equals("") == false) {
				Person[] p1 = PersonBag.search(new Predicate<Person>() {
					// boolean hold = false;
					@Override
					public boolean test(Person t) {
						if (t instanceof Instructor) {
							if (((Instructor) t).getSalary() == Double.parseDouble(instructorSalary)) {
								return true;
							}
						}
						return false;
					}
				});

				for (Person i : p1) {
					outPutArea.appendText(i.toString() + "\n");
				}
				salarySpace.clear();
			}
			
			if(instructorId.equals("") == false) {
				Person[] p1 = PersonBag.search(new Predicate<Person>() {

					@Override
					public boolean test(Person t) {
						if(t instanceof Instructor) {
							if(((Instructor) t).getId().equals(instructorId)) {
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
			
			String instructorId = idSpace.getText();
			String name = nameSpace.getText();
			String rank = rankSpace.getText();
			String salary = salarySpace.getText();
			
			double newSalary;
			try {
				newSalary = Double.parseDouble(salary);
			} catch (NumberFormatException g) {
				newSalary = 0;
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
			
			if(instructorId.equals("") != true) {
				PersonBag.delete(new Predicate<Person>() {
					@Override
					public boolean test(Person t) {
						if(t instanceof Instructor) {
							return ((Person) t).getId().equals(instructorId);
						}
						return false;
					}
				});
				// Name name, String major, double gpa, String id
				bag.insert(new Instructor(new Name(firstName,lastName),rank,newSalary,instructorId));
				Backup.backupPersonBag();
			}
			
			idSpace.clear();
			nameSpace.clear();
			rankSpace.clear();
			salarySpace.clear();
		});

		root = new VBox(50);
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(inputAccept);
	}

	public VBox getRoot() {
		return root;
	}
}
