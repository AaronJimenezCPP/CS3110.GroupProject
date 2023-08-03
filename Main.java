
public class Main {
    public static void main(String[] args) {
        FA m;
        FAParser parser;

        // iterate through all the files
        for(int i = 1; i <= 4; i++){
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

            DisplayInfo info = new DisplayInfo(parser);
            System.out.println(info.formatOutput());
            boolean accepted;
            System.out.println("Results of test strings:");
            for (String str : parser.getTestStrings()) {
                accepted = m.accepts(str);
                String result = accepted ? "Accepted" : "Rejected";

                // Calculate the number of spaces needed for alignment
                int numSpaces = Math.max(1, 15 - str.length()); // Adjust the alignment width as needed

                // Create a string with the calculated number of spaces
                String spaces = new String(new char[numSpaces]).replace('\0', ' ');

                // Print the aligned output
                System.out.println(str + spaces + result);
            }
            System.out.println('\n');
        }
    }
}
