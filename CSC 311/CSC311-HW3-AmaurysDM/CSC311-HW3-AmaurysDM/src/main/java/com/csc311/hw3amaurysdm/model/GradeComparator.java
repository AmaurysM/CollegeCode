package com.csc311.hw3amaurysdm.model;

import java.util.Comparator;
/**
 * This class contains methods to compare grade information.
 * @author Amaurys De Los Santos Mendez
 */
public class GradeComparator implements Comparator {

    /**
     * Comparator method. Compares Two grade objects. This is used to sort them from biggest to smallest score.
     * @param o1 First object.
     * @param o2 Second object.
     */
    @Override
    public int compare(Object o1, Object o2) {
        Grade g1 = (Grade) o1;
        Grade g2 = (Grade) o2;
        return -g1.getScore().compareTo(g2.getScore());
    }
}
