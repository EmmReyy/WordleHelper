import java.util.HashMap;

public record LetterRank() {
    private static String letterRankLine = "etaoinshrdlcumwfgypbvkjxqz";
    public static HashMap<Character, Integer> letters = new HashMap<>();

    static {
        for (int i = 0; i < 26; i++) {
            letters.put(letterRankLine.charAt(i), i + 1);
        }
    }

    public static String getLetterRankLine(){
        return letterRankLine;
    }

    public static void setLetterRankLine(String nuLine){
        letterRankLine = nuLine;
    }
}
