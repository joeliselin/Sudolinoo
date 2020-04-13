package main.java.ch.michisolver;

public class SolverTools {
    // This is an array containing an object for every sudoku field
    private final int gridLength = 9; // A sudoku has a grid length of 9 Fields
    private final int noOfFields = 81; // A sudoku has 81 fields
    private int[][] unsolvedSudoku = new int[gridLength][gridLength];
    private FieldAsClass[] fieldAsClassArray = new FieldAsClass[noOfFields];


    public void configureAllFields(int[][] sudokuArray) {
        // Store initial sudoku in case of the solver has to restart
        unsolvedSudoku = sudokuArray;
        for (int row = 0; row < gridLength; row++) {
            for (int col = 0; col < gridLength; col++) {

                // Calculate current field number (0-80)
                int currentFieldNo = row * gridLength + col;

                // Initialize a new object in fieldAsClassArray:
                fieldAsClassArray[currentFieldNo] = new FieldAsClass();

                // Get current field parameters
                int currentSector = findOutSector(row, col);
                int currentValue = sudokuArray[row][col];

                // Asign parameters to field:
                fieldAsClassArray[currentFieldNo].setFieldNo(currentFieldNo);
                fieldAsClassArray[currentFieldNo].setRowNo(row);
                fieldAsClassArray[currentFieldNo].setColumNo(col);
                fieldAsClassArray[currentFieldNo].setSectorNo(currentSector);

                // Mark fields with a number other than 0 as solved
                if (currentValue != 0) {
                    fieldAsClassArray[currentFieldNo].setSolved();
                    fieldAsClassArray[currentFieldNo].setFieldValue(currentValue);
                }
                fieldAsClassArray[currentFieldNo].printFieldInfo();
            }
        }
    }

    private int findOutSector(int row, int col) {
        int sector;
        if (col < 3) {
            sector = 1;
        } else if (col < 6) {
            sector = 2;
        } else {
            sector = 3;
        }
        if (row < 3) {
            sector += 0;
        } else if (row < 6) {
            sector += 3;
        } else {
            sector += 6;
        }

        return sector;
    }

    public void removeSolvedNumbersFromOtherFields() {
        // Search solved fields in all fields
        for (int solvedField = 0; solvedField < noOfFields; solvedField++) {
            // When a solved and yet unprocessed field is found ...
            if (fieldAsClassArray[solvedField].isSolved() && !fieldAsClassArray[solvedField].hasBeenProcessed()) {
                // Clear this number from all other fields according to sudoku rules
                for (int clearField = 0; clearField < noOfFields; clearField++) {
                    // Check only unsolvedfields:
                    if (!fieldAsClassArray[clearField].isSolved()) {
                        boolean possibilityShouldBeCleared = false;
                        // Check if field is in same row
                        if (fieldAsClassArray[solvedField].getRowNo() == fieldAsClassArray[clearField].getRowNo()) {
                            possibilityShouldBeCleared = true;
                        }
                        // Check if field is in same column
                        else if (fieldAsClassArray[solvedField].getColumnNo() == fieldAsClassArray[clearField].getColumnNo()) {
                            possibilityShouldBeCleared = true;
                        }
                        // Check if field is in same sector
                        else if (fieldAsClassArray[solvedField].getSectorNo() == fieldAsClassArray[clearField].getSectorNo()) {
                            possibilityShouldBeCleared = true;
                        }
                        // If one of the sudoku rules applies, clear possibility from field
                        if (possibilityShouldBeCleared) {
                            fieldAsClassArray[clearField].removePossibleNo(fieldAsClassArray[solvedField].getFieldValue());
                        }
                        //fieldAsClassArray[clearField].printFieldInfo();
                    }
                }
                // Mark the processed field as processed
                fieldAsClassArray[solvedField].setProcessed();
            }
        }
    }

    public void selectNextSolvedField() {
        int fieldWithMinimumPossibilities = 0;
        int minimumNoOfPossibilities = 9; // bigger than 9 is not possible

        // iterate all fields
        for (int i = 0; i < noOfFields; i++) {
            // check unsolved fields only
            if (!fieldAsClassArray[i].isSolved()) {
                if (fieldAsClassArray[i].countPossibleNumbers() <= minimumNoOfPossibilities) {
                    minimumNoOfPossibilities = fieldAsClassArray[i].countPossibleNumbers();
                    fieldWithMinimumPossibilities = i;
                }
            }
        }
        System.out.println("The next field marked as solved is field No " + fieldWithMinimumPossibilities + " with " + minimumNoOfPossibilities + " possible numbers");

        // IF minimumNoOfPossibilites==0 THE SOLVER HAS MOVED TO A DEAD-END AND HAS TO RESTART FROM SCRATCH
        if (minimumNoOfPossibilities == 0) {
            System.out.println("SOLVER HAS HIT A DEAD END! RESTART SOLVER WITH INTIAL SUDOKU");
            configureAllFields(unsolvedSudoku);
        } else {
            // Set a random possibility value as field value
            fieldAsClassArray[fieldWithMinimumPossibilities].setFieldValue(fieldAsClassArray[fieldWithMinimumPossibilities].getAPossibleValueByRandom());
            // Mark field as solved:
            fieldAsClassArray[fieldWithMinimumPossibilities].setSolved();
        }
    }

    public int countNoOfUnsolvedFields() {
        int noOfUnsolvedFields = 0;
        for (int i = 0; i < noOfFields; i++) {
            if (!fieldAsClassArray[i].isSolved()) {
                noOfUnsolvedFields++;
            }
        }

        System.out.println(noOfUnsolvedFields + " fields are not solved yet");
        return noOfUnsolvedFields;
    }

    public int[][] getSolvedSudokuArray() {
        int[][] solvedSudoku = new int[9][9];
        for (int row = 0; row < solvedSudoku.length; row++) {
            for (int col = 0; col < solvedSudoku.length; col++) {
                int fieldNumber = row * solvedSudoku.length + col;
                // Assign field values to array
                solvedSudoku[row][col] = fieldAsClassArray[fieldNumber].getFieldValue();
                System.out.print(fieldAsClassArray[fieldNumber].getFieldValue());
            }
        }
        return solvedSudoku;
    }
}
