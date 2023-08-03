import java.util.ArrayList;
import java.util.List;

public class FA {
    // # of states
    private int states;
    // Current state, in range 0 to states-1
    private int currentState = 0;
    // finalStates[q] is true if q is a final state
    private boolean[] finalStates;
    // List of accepted characters
    private String alphabet;
    // transitions[p][ai] gives q
    // p is current state, ai is index of alphabet char, q is next state
    private int[][] transitions;

    public FA(int states, String alphabet, boolean[] finalStates, int[][] transitions) {
        this.states = states;
        this.alphabet = alphabet;
        this.finalStates = finalStates;
        this.transitions = transitions;
    }

    public boolean accepts(String str) {
        // State 0 is initial state by definition
        this.currentState = 0;

        // Empty string
        if (str.equals("") || str.equals("Λ")) 
            // If initial state is a final state
            return finalStates[currentState];
        else { 
            for (int i = 0; i < str.length(); i++) {
                // Get next char in string
                char c = str.charAt(i); 
                // Get index of char in alphabet
                int alphabetIndex = alphabet.indexOf(c);

                // if raw char is not in the alphabet
                if (alphabetIndex == -1) {
                    // If alphabbetic, search for greek Α in alphabet 
                    if (Character.isAlphabetic(c)) 
                        alphabetIndex = alphabet.indexOf('Α');
                    // If digit, search for Δ in alphabet
                    else if (Character.isDigit(c)) 
                        alphabetIndex = alphabet.indexOf('Δ');

                    // If still not found, reject
                    if (alphabetIndex == -1) 
                        return false;
                }
                
                // -1 means no valid transition, reject
                if (transitions[this.currentState][alphabetIndex] == -1) 
                    return false;
                
                // Transition to next state using current state and char's index in the alphabet
                this.currentState = transitions[this.currentState][alphabetIndex];
            }
        }
        
        // If ended in a final state
        return finalStates[currentState];
    }

    // Return an info string about this FA
    public String info() {
        return statesInfo() + finalStatesInfo() + alphabetInfo() + transitionInfo();
    }

    public String statesInfo() {
        String str = "States: {";
        for (int i = 0; i < states; i++) {
            str += i;
            if (i < states - 1) str += ", ";
        }

        return str + "}" + "\n";
    }

    public String finalStatesInfo() {
        List<Integer> finalList = new ArrayList<>();

        for (int i = 0; i < finalStates.length; i++) {
            if (finalStates[i]) finalList.add(i);
        }

        String str = "Final States: {";
        for (int i = 0; i < finalList.size(); i++) {
            str += finalList.get(i);
            if (i < finalList.size() - 1) str += ", ";
        }

        return str + "}" + "\n";
    }

    public String alphabetInfo() {
        String str = "Alphabet: {";

        for (int i = 0; i < alphabet.length(); i++) {
            str += alphabet.charAt(i);
            if (i < alphabet.length() - 1) str += ", ";
        }

        return str + "}" + "\n";
    }

    public String transitionInfo() {
        String str = "Transitions:" + "\n";

        for (int i = 0; i < transitions.length; i++) {
            int[] stateTransitions = transitions[i];
            for (int j = 0; j < stateTransitions.length; j++) {
                if (transitions[i][j] != -1) {
                    str += "\t" + i + " " + alphabet.charAt(j) + " " + transitions[i][j] + "\n";
                } 
            }
        }

        return str;
    }
}