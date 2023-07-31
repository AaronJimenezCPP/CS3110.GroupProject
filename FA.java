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

        // For each character in the string
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int alphabetIndex = alphabet.indexOf(c);
            
            // Transition to next state using this current state and char's index in the alphabet
            this.currentState = transitions[this.currentState][alphabetIndex];
        }
        
        return finalStates[currentState];
    }
}