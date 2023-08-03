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

        int alphabetIndex = -1; // initial state is false;

        // for empty strings
        if (str == "") {
            // final character in the alphabet for all M's is for empty string
            alphabetIndex = alphabet.length() - 1;
            this.currentState = transitions[this.currentState][alphabetIndex];
            return finalStates[currentState];
        }else if (str.matches(".*[a-zA-Z].*")){ // for M3.txt...
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i); // get next character in string
                    if (Character.isLetter(c)) { // if character is a letter, then it's a 0
                        alphabetIndex = 0;
                    } else if (Character.isDigit(c)) { // if character is a digit, then it's a 1
                        alphabetIndex = 1;
                    } else {
                        return false; // if c is not in alphabet, return false;
                    }
                    // Transition to next state using this current state and char's index in the alphabet
                    this.currentState = transitions[this.currentState][alphabetIndex];
                }
        } else { // for all the other Machines
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i); // get next character in string
                alphabetIndex = alphabet.indexOf(c);

                // if c is not in the alphabet, return false
                if (alphabetIndex == -1) {
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
                str += "\t" + i + " " + alphabet.charAt(j) + " " + transitions[i][j] + "\n";
            }
        }

        return str;
    }
}