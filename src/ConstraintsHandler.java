import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ConstraintsHandler {

    private HashSet<Character> blackLetters = new HashSet<>();
    private  HashMap<Character, ArrayList<Integer>> yellowLetters = new HashMap<>();
    private char[] fixedPos = {'*','*','*','*','*'};

    public boolean hasAllYellowLetters(DictEntry entry){
        for(char letter : yellowLetters.keySet()){
            if (!entry.charSet.contains(letter)){
                return false;
            } else {
                for (Integer ndx : yellowLetters.get(letter)){
                    if (entry.chars[ndx] == letter){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean hasNoBlackLetters(DictEntry entry){
        for (char letter : entry.chars){
            if (blackLetters.contains(letter)){
                return false;
            }
        }
        return true;
    }

    public boolean correctFixedPos(DictEntry entry){
        for (int i = 0; i < 5; i++){
            if (fixedPos[i] != '*' && (fixedPos[i] != entry.chars[i])){
                return false;
            }
        }
        return true;
    }

    public void updateConstraints(String line) {
        String[] lineSplit = line.split(" ");
        //System.out.println(lineSplit[0] +", "+ lineSplit[1] +", "+ lineSplit[2] +", "+ lineSplit[3] +", "+ lineSplit[4]);
        for (int i = 0; i < 5; i++){
            if (lineSplit[i].charAt(0) == '0'){
                blackLetters.add(lineSplit[i].charAt(1));
            }
            //clunky and gross, fix later
            else if (lineSplit[i].charAt(0) == '1'){
                //yellowLetters.put(lineSplit[i].charAt(1), new ArrayList<>());
                yellowLetters.computeIfAbsent(lineSplit[i].charAt(1), k -> new ArrayList<>());
                yellowLetters.get(lineSplit[i].charAt(1)).add(i);
            }
            else if (lineSplit[i].charAt(0) == '2'){
                fixedPos[i] = lineSplit[i].charAt(1);
            }
        }
    }

    public boolean adheresConstraints(DictEntry entry){
        if (hasAllYellowLetters(entry) && hasNoBlackLetters(entry) && correctFixedPos(entry)){
            return true;
        }
        return false;
    }
}
