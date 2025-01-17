package main.java.ch.solverrandomnumber;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An object of the Field class represents a single field with all the attributes that are required by the solver.
 *
 * @author Michael Wettstein
 */
public class Field {

    private boolean isSolved;
    private boolean hasBeenProcessed; // true if solved and related fields processed
    private int fieldNo; // 0-80
    private int rowNo; // 0-8
    private int columnNo; // 0-8
    private int sectorNo; // 1-9
    private int fieldValue; // 1-9
    private TreeSet<Integer> possibilities;

    public Field() {
        isSolved = false;
        hasBeenProcessed = false;
        // Create possibilities Set:
        Integer arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Collections.addAll(this.possibilities = new TreeSet<Integer>(Arrays.asList(arr)));
    }

    // SETTER AND METHODS TO CHANGE VALUES:
    public void setFieldNo(int fieldNo) {
        this.fieldNo = fieldNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public void setColumnNo(int columnNo) {
        this.columnNo = columnNo;
    }

    public void setSectorNo(int sectorNo) {
        this.sectorNo = sectorNo;
    }

    public void removePossibleNo(int numberToRemove) {
        this.possibilities.remove(numberToRemove);
    }

    public void setFieldValue(int fieldValue) {
        this.fieldValue = fieldValue;
    }

    public void setSolved() {
        isSolved = true;
        this.possibilities.clear(); // removes all possibilities
    }

    public void setProcessed() {
        this.hasBeenProcessed = true;
    }

    /**
     * This method is used if the solver cannot reduce the number of possibilites to a single number.
     * In this case this method simply guesses a number.
     */
    public void setAPossibleValueByRandom() {
        int noOfPossibilities = possibilities.size();
        int randomNo = ThreadLocalRandom.current().nextInt(0, noOfPossibilities);
        Integer[] possibilityArray = new Integer[possibilities.size()];
        possibilities.toArray(possibilityArray);
        setFieldValue(possibilityArray[randomNo]);
    }

    // GETTER AND METHODS TO REQUEST VALUES :

    public boolean isSolved() {
        return this.isSolved;
    }

    public int getColumnNo() {
        return columnNo;
    }

    public int getRowNo() {
        return rowNo;
    }

    public int getSectorNo() {
        return sectorNo;
    }

    public int getFieldValue() {
        return fieldValue;
    }

    public int countPossibleNumbers() {
        int noOfPossibilities = possibilities.size();
        return noOfPossibilities;
    }

    public boolean hasBeenProcessed() {
        return hasBeenProcessed;
    }
}