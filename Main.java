public class Main {
    public static void main(String[] args) {
        // Iterate through all the files
        for(int i = 1; i <= 4; i++){
            String filename = "M" + i + ".txt";

            // used to get the values from the files
            ParsedFA parsedFA = FAParser.fromFile(filename);

            // Get parsed FA
            FA m = parsedFA.m;

            System.out.println("\n==================");
            System.out.println(filename  + " Results");
            System.out.println("==================");

            System.out.print(m.info());
            System.out.println("==================");

            for (String str : parsedFA.testStrings) 
                System.out.println((m.accepts(str) ? "Accepted": "Rejected") + ": " + str);

            System.out.println("==================");
        }
    }
}