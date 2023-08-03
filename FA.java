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
        this.currentState = 0;

        // Empty string
        if (str.equals("") || str.equals("Λ")) {
            // If initial state is final state
            return finalStates[currentState];
        }
        else { 
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i); // get next character in string
                int alphabetIndex = alphabet.indexOf(c);

                // if c is not in the alphabet, return false
                if (alphabetIndex == -1) {
                    if (Character.isAlphabetic(c)) {
                        alphabetIndex = alphabet.indexOf('Α');
                    }
                    else if (Character.isDigit(c)) {
                        alphabetIndex = alphabet.indexOf('Δ');
                    }

                    if (alphabetIndex == -1) {
                        return false;
                    }  
                }
                
                // -1 means no valid transition, which means reject
                if (transitions[this.currentState][alphabetIndex] != -1) {
                    return false;
                }
                
                // Transition to next state using this current state and char's index in the alphabet
                this.currentState = transitions[this.currentState][alphabetIndex];
            }
        }
        
        return finalStates[currentState];
    }

    public String info() {
        return statesInfo() + finalStatesInfo() + alphabetInfo() + transitionInfo();
    }

    public String statesInfo() {
        String str = "States: {";
        for (int i = 0; i < states; i++) {
            str += i;
            if (i < states - 1)
                str += ", ";
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
            if (i < finalList.size() - 1)
                str += ", ";
        }

        return str + "}" + "\n";
    }

    public String alphabetInfo() {
        String str = "Alphabet: {";

        for (int i = 0; i < alphabet.length(); i++) {
            str += alphabet.charAt(i);
            if (i < alphabet.length() - 1) 
                str += ", ";
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