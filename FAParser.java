import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FAParser {
    private int states;
    private boolean[] finalStates;
    private int[][] transitions;
    private String alphabet;
    private ArrayList<String> testStrings = new ArrayList<>();

    public FAParser(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Read first line to get the number of states
            states = Integer.parseInt(br.readLine());
            finalStates = new boolean[states];

            // Get all the final states, use loop in case of multiple states
            String nextLine = br.readLine();
            String[] tempArr = nextLine.trim().split("\\s+");
            for (String str : tempArr) {
                int tempNum = Integer.parseInt(str);
                finalStates[tempNum] = true;
            }

            // reads alphabet line
            nextLine = br.readLine();
            // removes spaces from the string
            alphabet = nextLine.replace(" ", "");

            // Count the number of lines with parentheses to determine the number of rows
            int numRows = 0;
            while ((nextLine = br.readLine()) != null) {
                if (nextLine.startsWith("(")) {
                    numRows++;
                }
            }

            // Initialize the 2D array with the determined number of rows and columns
            int numColumns = alphabet.length();
            transitions = new int[numRows][numColumns];

            // Reset the reader to read from the beginning of the file
            br.close();
            BufferedReader br2 = new BufferedReader(new FileReader(fileName));

            // Skip the first three lines (already read above)
            br2.readLine();
            br2.readLine();
            br2.readLine();

            // get all the transitions
            while ((nextLine = br2.readLine()) != null) {
                if (nextLine.startsWith("(")) {
                    // Remove parenthesis
                    nextLine = nextLine.substring(1, nextLine.length() - 1);
                    String[] tempStrArr = nextLine.split("\\s+");
                    int currentState = Integer.parseInt(tempStrArr[0]);
                    int alphaIndex = alphabet.indexOf(tempStrArr[1]);
                    int value = Integer.parseInt(tempStrArr[2]);
                    transitions[currentState][alphaIndex] = value;
                } else { // once transitions are done, get the test strings
                    testStrings.add(nextLine);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Add getters to access the parsed data
    public int getStates() {
        return states;
    }

    public boolean[] getFinalStates() {
        return finalStates;
    }

    public int[][] getTransitions() {
        return transitions;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public ArrayList<String> getTestStrings() {
        return testStrings;
    }
}
