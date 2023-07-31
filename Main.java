public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        FA m1 = new FA(
            2,
            "01",
            new boolean[] {false, true},
            new int[][] {
                {1, 0}, // From state 0
                {1, 0}, // From state 1
            }
        );

        System.out.println("M1 accepts 11010: " + m1.accepts("11010"));
    }
}