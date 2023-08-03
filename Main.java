
public class Main {
    public static void main(String[] args) {
        FA m;
        FAParser parser;

        // iterate through all the files
        for(int i = 1; i <= 2; i++){
            String filename = "M" + i + ".txt";

            // used to get the values from the files
            parser = new FAParser(filename);

            // set create new FA with values from current file
            m = new FA(
                    parser.getStates(),
                    parser.getAlphabet(),
                    parser.getFinalStates(),
                    parser.getTransitions()
            );

            boolean accepted;
            System.out.println("\n==================");
            System.out.println(filename  + " Results");
            System.out.println("==================");
            System.out.print(m.info());
            System.out.println("==================");
            for (String str : parser.getTestStrings()) {
                accepted = m.accepts(str);
                System.out.println((accepted ? "Accepted": "Rejected") + ": " + str);
            }
            System.out.println("==================");
        }
    }
}

// Α, Δ, Λ
// Don't need handling for Λ. Simply if initial state is final state
