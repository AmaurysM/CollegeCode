package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.PersonBag;
import model.TextbookBag;

public class ChangeView {
	private HBox root;
	
	public ChangeView() {
		Button student = new Button("STUDENT");
		student.setMaxSize(100, 50);
		Button instructor = new Button("INSTRUCTOR");
		instructor.setMaxSize(100, 50);
		Button books = new Button("TEXTBOOKS");
		books.setMaxSize(100, 50);
		
		VBox holdViews = new VBox(20);

		holdViews.setPadding(new Insets(10, 10, 10, 10));
		holdViews.getChildren().addAll(student,instructor,books);
		
		PersonBag personBag = PersonBag.getPersonBag(0);
		TextbookBag textbookBag = TextbookBag.getTextbookBag(0);
		
		StudentView studentView = new StudentView(personBag);
		InstructorView instructorView = new InstructorView(personBag);
		BookView bookView = new BookView(textbookBag);
		
		root = new HBox(50);
		root.getChildren().addAll(holdViews);
		
		student.setOnAction(e -> {
			if(root.getChildren().size() > 1) {
				root.getChildren().remove(1);
			}
			root.getChildren().add(studentView.getRoot());
		});
		instructor.setOnAction(e -> {
			if(root.getChildren().size() > 1) {
				root.getChildren().remove(1);
			}
			root.getChildren().add(instructorView.getRoot());
		});
		books.setOnAction(e -> {
			if(root.getChildren().size() > 1) {
				root.getChildren().remove(1);
			}
			root.getChildren().add(bookView.getRoot());
		});
	}
	public HBox getRoot() {
		return root;
	}
}
