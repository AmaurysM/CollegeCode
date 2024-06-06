package model;

import java.io.Serializable;

//import p1_comparable.Student;

public abstract class Person implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5691032575689479140L;
	private Name name;
	private String id;

	private static int idCount = 0;

	public Person(Name name) {
		this.name = name;
		id = String.valueOf(idCount++);
	}
	
	public Person(Name name, String id) {
		this.name = name;
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String Id) {
		this.id = Id; 
	}
	
	public static void setIdCount(int idCount) {
		Person.idCount = idCount;
	}
	
	@Override
	public String toString() {
		return "Firstname=" + name + ", id=" + id;
	}
	
	@Override
	public Person clone() throws CloneNotSupportedException {
		return (Person) super.clone();
	}
	
}
