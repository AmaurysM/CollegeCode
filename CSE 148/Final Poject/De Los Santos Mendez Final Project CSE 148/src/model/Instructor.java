package model;

import java.io.Serializable;

public class Instructor extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7554527638905313693L;
	private String rank;
	private double salary;

	public Instructor(Name name, String rank, double salary) {
		super(name);
		this.rank = rank;
		this.salary = salary;
	}
	
	public Instructor(Name name, String rank, double salary, String id) {
		super(name,id);
		this.rank = rank;
		this.salary = salary;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return getName() + ", rank=" + rank + ", salary=" + salary + ", ID=" + this.getId();
	}

}
