package main.java.ch.fileloader;

public class Loader {

//    private int puzzle[][] = {
//            {8, 0, 1, 0, 0, 3, 9, 0, 6},
//            {0, 0, 9, 0, 0, 7, 8, 5, 0},
//            {2, 5, 0, 1, 0, 0, 4, 7, 0},
//            {5, 0, 0, 0, 6, 1, 7, 0, 4},
//            {7, 6, 0, 8, 3, 0, 0, 0, 0},
//            {0, 3, 2, 0, 0, 0, 0, 0, 0},
//            {0, 2, 0, 0, 1, 9, 5, 0, 0},
//            {0, 0, 5, 0, 0, 0, 3, 0, 2},
//            {0, 0, 0, 4, 5, 2, 1, 9, 7}
//    };

//     schwirigstes sudoku das es giebt
//    int[][] puzzle = {
//            { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
//            { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
//            { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
//            { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
//            { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
//            { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
//            { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
//            { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
//            { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
//    };



    private int[][] puzzle = {
            {0, 3, 0, 6, 7, 8, 9, 0, 2},
            {0, 7, 2, 1, 9, 5, 3, 4, 8},
            {0, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
    };

    public int[][] loadSudokuArray() {
        System.out.println("Loader Works");

        return puzzle;
    }
}


