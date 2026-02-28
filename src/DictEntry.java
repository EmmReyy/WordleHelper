import java.security.KeyPair;
import java.util.HashSet;

public class DictEntry {

    public String word;
    public char[] chars;
    public HashSet<Character> charSet = new HashSet<>();
    public int score;
    public boolean hasDupes = false;

    public DictEntry(String word){
        this.word = word;
        this.chars = word.toCharArray();

        boolean[] seen = new boolean[128];
        for (int i = 0; i < 5; i++) {

            charSet.add(chars[i]);
            //this.score += LetterRank.letters.get(chars[i]);
            this.score += LetterRank.letters.getOrDefault(chars[i], 0);
            int index = word.charAt(i);
            if (seen[index]) {
                hasDupes = true;
            }
            seen[index] = true;
        }
    }

    @Override
    public String toString() {
        return word;
    }
}
