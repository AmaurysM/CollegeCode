package view;

import java.util.function.Predicate;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Name;
import model.Textbook;
import model.TextbookBag;
import util.Backup;

public class BookView {
	private TextbookBag textbookBag;
	private VBox root;

	public BookView(TextbookBag textbookBag) {
		this.textbookBag = textbookBag;
		// String title, String isbn, Name author, double price
		TextField titleSpace = new TextField();
		titleSpace.setPrefHeight(30);
		titleSpace.setPromptText("BOOK TITLE");

		TextField authorSpace = new TextField();
		authorSpace.setPrefHeight(30);
		authorSpace.setPromptText("AUTHOR");

		TextField priceSpace = new TextField();
		priceSpace.setPrefHeight(30);
		priceSpace.setPromptText("PRICE");

		TextField isbnSpace = new TextField();
		isbnSpace.setPrefHeight(30);
		isbnSpace.setPromptText("ISBN");

		HBox textBox = new HBox(50);
		textBox.setAlignment(Pos.CENTER);
		textBox.getChildren().addAll(titleSpace, authorSpace, priceSpace, isbnSpace);

		Button insertBtn = new Button("ADD BOOK");
		insertBtn.setPrefSize(100, 30);

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
			String bookTitle = titleSpace.getText();
			String isbn = isbnSpace.getText();
			String bookAuthor = authorSpace.getText();
			String price = priceSpace.getText();

			int index = 0;
			if (bookAuthor.contains(" ")) {
				while (bookAuthor.charAt(index) != ' ') {
					index++;
				}
			}
			String firstName = bookAuthor;
			String lastName = "";
			if (index > 0) {
				firstName = bookAuthor.substring(0, index);
				lastName = bookAuthor.substring(index + 1, bookAuthor.length());
			}

			if (price.equals("")) {
				price = "0";
			}
			this.textbookBag
					.insert(new Textbook(bookTitle, isbn, new Name(firstName, lastName), Double.parseDouble(price)));
			Backup.backupBookBag();
			outPutArea.appendText(this.textbookBag.getBag()[this.textbookBag.getNElems() - 1].toString() + "\n");

			titleSpace.clear();
			isbnSpace.clear();
			authorSpace.clear();
			priceSpace.clear();
		});

		removeBtn.setOnAction(e -> {

			String isbn = isbnSpace.getText();

			if (isbn.equals("") == false) {
				Textbook[] s1 = textbookBag.delete(new Predicate<Textbook>() {
					@Override
					public boolean test(Textbook t) {
						if (t instanceof Textbook) {
							return ((Textbook) t).getIsbn().equals(isbn);
						}
						return false;
					}
				});
				for (Textbook i : s1) {
					outPutArea.appendText(i.toString() + "\n");
				}
				isbnSpace.clear();
			}

			Backup.backupBookBag();
		});

		searchBtn.setOnAction(e -> {
			String bookTitle = titleSpace.getText();
			String isbn = isbnSpace.getText();
			String bookAuthor = authorSpace.getText();
			String price = priceSpace.getText();

			if (bookTitle.equals("") == false) {
				Textbook[] s1 = textbookBag.search(new Predicate<Textbook>() {
					@Override
					public boolean test(Textbook t) {
						return ((Textbook) t).getTitle().equals(bookTitle);
					}
				});

				for (Textbook i : s1) {
					outPutArea.appendText(i.toString() + "\n");
				}
				titleSpace.clear();
			}

			if (isbn.equals("") == false) {
				Textbook[] s1 = textbookBag.search(new Predicate<Textbook>() {
					@Override
					public boolean test(Textbook t) {
						if (t instanceof Textbook) {
							return ((Textbook) t).getIsbn().equals(isbn);
						}
						return false;
					}
				});
				for (Textbook i : s1) {
					outPutArea.appendText(i.toString() + "\n");
				}
				isbnSpace.clear();
			}

			if (bookAuthor.equals("") == false) {
				Textbook[] s1 = textbookBag.search(new Predicate<Textbook>() {
					@Override
					public boolean test(Textbook t) {
						if (t instanceof Textbook) {
							return ((Textbook) t).getAuthor().getFirstName().equals(bookAuthor);
						}
						return false;
					}
				});
				for (Textbook i : s1) {
					outPutArea.appendText(i.toString() + "\n");
				}
				authorSpace.clear();
			}

			if (price.equals("") == false) {
				Textbook[] s1 = textbookBag.search(new Predicate<Textbook>() {
					@Override
					public boolean test(Textbook t) {
						if (t instanceof Textbook) {
							return ((Textbook) t).getPrice() == Double.parseDouble(price);
						}
						return false;
					}
				});
				for (Textbook i : s1) {
					outPutArea.appendText(i.toString() + "\n");
				}
				priceSpace.clear();
			}
		});

		updateBtn.setOnAction(e -> {
			TextbookBag bag = TextbookBag.getTextbookBag(0);

			String title = titleSpace.getText();
			String author = authorSpace.getText();
			String price = priceSpace.getText();
			String isbn = isbnSpace.getText();

			double newPrice;
			try {
				newPrice = Double.parseDouble(price);
			} catch (NumberFormatException g) {
				newPrice = 0;
			}
			int index = 0;
			if (author.contains(" ")) {
				while (author.charAt(index) != ' ') {
					index++;
				}
			}
			
			String firstName = author;
			String lastName = "";
			if (index > 0) {
				firstName = author.substring(0, index);
				lastName = author.substring(index + 1, author.length());
			}

			if (isbn.equals("") != true) {
				Textbook[] s1 = textbookBag.delete(new Predicate<Textbook>() {
					@Override
					public boolean test(Textbook t) {
						if (t instanceof Textbook) {
							return ((Textbook) t).getIsbn().equals(isbn);
						}
						return false;
					}
				});

				bag.insert(new Textbook(title, isbn, new Name(firstName, lastName), newPrice));
				outPutArea.appendText(s1[0].toString() + "\n");
				Backup.backupBookBag();
			}

			titleSpace.clear();
			authorSpace.clear();
			priceSpace.clear();
			isbnSpace.clear();
		});

		root = new VBox(50);
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(inputAccept);
	}

	public VBox getRoot() {
		return root;
	}
}
