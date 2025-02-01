package com.csc311.hw3amaurysdm.model;
/**
 * This class contains methods to store grade information.
 * @author Amaurys De Los Santos Mendez
 */
public class Grade{
    private String name;
    private String category;
    private Integer score;

    public Grade(String name, String category, Integer score) {
        this.name = name;
        this.category = category;
        this.score = score;
    }
    /**
     * Gives the Grade name.
     * @return String name of the grade.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the grade.
     * @param name The new name of the assignment as a string.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gives the Grade category.
     * @return String category of the grade.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the grade.
     * @param category The new category of the assignment as a String.
     */
    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * Gives the Grade score.
     * @return String score of the grade.
     */
    public Integer getScore() {
        return score;
    }
    /**
     * Sets the score of the grade.
     * @param score The new score of the assignment as a String.
     */
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name +
                ", " + category +
                ", " + score ;
    }

}
