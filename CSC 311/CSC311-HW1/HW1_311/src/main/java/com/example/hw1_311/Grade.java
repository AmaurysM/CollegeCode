package com.example.hw1_311;

import com.google.gson.annotations.SerializedName;

/**
 * This class contains methods to store grade information.
 * @author Amaurys De Los Santos Mendez
 */
public class Grade {
    @SerializedName("name")
    private String name;

    @SerializedName("category")
    private String category;

    @SerializedName("score")
    private int score;

    /**
     * Creates a grade.
     * @param name Name of the assignment.
     * @param category Category of the assignment.
     * @param score Grade of the assignment.
     */
    public Grade(String name, String category, int score) {
        this.name = name;
        this.category = category;
        this.score = score;
    }

    /**
     * Gives the name of the assignment.
     * @return String Name of the assignment.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the assignment.
     * @param name The new name of the assignment as a String.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gives the category of the assignment.
     * @return String Category of the assignment.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the Category of the assignment.
     * @param category The new category of the assignment.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gives the Score of the assignment.
     * @return Int Score of the assignment.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the Score of the assignment.
     * @param score The new score of the assignment.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * This will show when the object is printed out.
     * @return String The name, category and the score of the Grade object.
     */
    @Override
    public String toString() {
        return name +
                " " + category +
                " " + score;
    }
}
