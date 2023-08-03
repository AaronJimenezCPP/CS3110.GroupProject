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
            // 1st line is # states
            states = Integer.parseInt(br.readLine());

            // 2nd line is final states, separated by spaces
            finalStates = new boolean[states];
            for (String str : br.readLine().trim().split("\\s+")) {
                finalStates[Integer.parseInt(str)] = true;
            }

            // 3rd line is alphabet, separated by spaces
            alphabet = br.readLine().replace(" ", "");

            // Next lines are transitions
            // Initialize transitions to -1 (signifies non-existing transition)
            transitions = new int[states][alphabet.length()];
            for (int i = 0; i < transitions.length; i++) {
                for (int j = 0; j < transitions[i].length; j++) {
                    transitions[i][j] = -1;
                }
            }
            
            // Loop through rest of lines, which are either transition or test string 
            String thisLine;
            while ((thisLine = br.readLine()) != null) {
                // A transition
                if (thisLine.startsWith("(")) {
                    // Remove parenthesis
                    thisLine = thisLine.replace("(", "").replace(")", ""); 
                    
                    // Read current state, alphabet index, and new state. Add to transitions table
                    String[] split = thisLine.split("\\s+");
                    int currentState = Integer.parseInt(split[0]);
                    int alphaIndex = alphabet.indexOf(split[1]);
                    int newState = Integer.parseInt(split[2]);
                    transitions[currentState][alphaIndex] = newState;
                }
                // A test string
                else {
                    testStrings.add(thisLine);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
