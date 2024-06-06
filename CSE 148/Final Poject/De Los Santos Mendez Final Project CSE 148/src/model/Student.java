package model;

import java.io.Serializable;

//import p1_comparable.Student;

public class Student extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -686023995288258726L;

	private String major;
	private double gpa;

	public Student(Name name, String major, double gpa) {
		super(name);
		this.major = major;
		this.gpa = gpa;
		if(gpa > 4.0) {
			this.gpa = 4.0;
		}
		if(gpa < 0.0) {
			this.gpa = 0.0;
		}
		
	}
	
	public Student(Name name, String major, double gpa, String id) {
		super(name,id);
		this.major = major;
		this.gpa = gpa;
		if(gpa > 4.0) {
			this.gpa = 4.0;
		}
		if(gpa < 0.0) {
			this.gpa = 0.0;
		}
		
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	@Override
	public String toString() {
		return getName() + ", ID: " + getId() + ", Major: " + major + ", GPA: " + gpa;
	}
	
}
