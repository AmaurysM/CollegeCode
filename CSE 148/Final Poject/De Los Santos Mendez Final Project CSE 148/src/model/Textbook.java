package model;

import java.io.Serializable;

public class Textbook implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -705994431594078756L;
	private String title;
	private String isbn;
	private Name author;
	private double price;
	
	public Textbook(String title, String isbn, Name author, double price) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Name getAuthor() {
		return author;
	}
	public void setAuthor(Name author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "title=" + title + ", isbn=" + isbn + ", author=" + author + ", price=" + price + "]";
	}
	
	@Override
	public Textbook clone() throws CloneNotSupportedException {
		return (Textbook) super.clone();
	}
	
}
