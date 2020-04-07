package main.java.ch.fileloader;

// download http://www.java2s.com/Code/Jar/j/Downloadjsonsimple11jar.htm and add it as a library
import org.json.simple.JSONObject;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SudokuSpaghettiWriter {
    public static void main(String[] args) throws FileNotFoundException {
        int puzzle[][] = {
                {8, 0, 1, 0, 0, 3, 9, 0, 6},
                {0, 0, 9, 0, 0, 7, 8, 5, 0},
                {2, 5, 0, 1, 0, 0, 4, 7, 0},
                {5, 0, 0, 0, 6, 1, 7, 0, 4},
                {7, 6, 0, 8, 3, 0, 0, 0, 0},
                {0, 3, 2, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 1, 9, 5, 0, 0},
                {0, 0, 5, 0, 0, 0, 3, 0, 2},
                {0, 0, 0, 4, 5, 2, 1, 9, 7}
        };

        // Create spaghetti string from sudoku array:
        String sudokuSpaghetti = "";

        for (int row = 0; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle.length; col++) {
                sudokuSpaghetti += String.valueOf(puzzle[row][col]);
            }
        }

        // Create JSONObject
        JSONObject jsonObject = new JSONObject();

        // Put data to JSONObject
        jsonObject.put("spaghettistring", sudokuSpaghetti);

        // Write JSON to file:
        PrintWriter printWriter = new PrintWriter("sudokuspaghetti.json");
        printWriter.write(jsonObject.toJSONString());
        printWriter.flush();
        printWriter.close();
    }
}