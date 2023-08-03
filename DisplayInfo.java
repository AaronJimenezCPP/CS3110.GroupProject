public class DisplayInfo {
    private static FAParser parser;
    private StringBuilder statesStr = new StringBuilder();

    public DisplayInfo(FAParser parser){
        this.parser = parser;
    }

    public StringBuilder formatOutput() {
        formatStates();
        formatFinalStates();
        formatAlphabet();
        formatTransitions();
        return statesStr;
    }

    private void formatStates() {
        statesStr.append("1) set of states: { ");
        for (int i = 0; i < parser.getStates(); i++) {
            statesStr.append("state ").append(i);
            if (i < parser.getStates() - 1) {
                statesStr.append(", ");
            }
        }
        statesStr.append(" }\n");
    }

    private void formatFinalStates() {
       statesStr.append("2) set of final state(s): {");
       for (int i = 0; i < parser.getStates(); i++){
           if (parser.getFinalStates()[i]){
               statesStr.append(" state ").append(i).append(',');
           }
       }
       int startIndex = statesStr.length() - 1;
       int endingIndex = statesStr.length();
       statesStr.delete(startIndex, endingIndex);

       statesStr.append(" }\n");
    }

    private void formatAlphabet() {
        statesStr.append("3) alphabet set: { ");
        for(char c: parser.getAlphabet().toCharArray()){
            statesStr.append(c).append(", ");
        }
        int startIndex = statesStr.length() - 2;
        int endingIndex = statesStr.length();
        statesStr.delete(startIndex, endingIndex);
        statesStr.append(" }\n");
    }

    private void formatTransitions() {
        statesStr.append("4) transitions:\n");
        statesStr.append(parser.getStringBuilder());
    }


}
