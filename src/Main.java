    import java.util.*;

    public class Main {

        static ArrayList<DictEntry> curatedDict = new ArrayList<>();
        static HashSet<Character> blackLetters = new HashSet<>();
        static HashMap<Character, ArrayList<Integer>> yellowLetters = new HashMap<>();
        static char[] fixedPos = {'*','*','*','*','*'};
        static FivesDictionary fives = new FivesDictionary("C:\\Users\\gobbl\\IdeaProjects\\WordleHelp\\fiveLetterWords.txt");

        public static boolean hasAllYellowLetters(DictEntry entry){
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

        public static boolean hasNoBlackLetters(DictEntry entry){
            for (char letter : entry.chars){
                if (blackLetters.contains(letter)){
                    return false;
                }
            }
            return true;
        }

        public static boolean correctFixedPos(DictEntry entry){
            for (int i = 0; i < 5; i++){
                if (fixedPos[i] != '*' && (fixedPos[i] != entry.chars[i])){
                    return false;
                }
            }
            return true;
        }

        public static void constraintsParser(String line){
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

        public static void filter(){
            ArrayList<DictEntry> dict = new ArrayList<>();

            for (DictEntry entry : fives.words){
                if (hasAllYellowLetters(entry) && hasNoBlackLetters(entry) && correctFixedPos(entry)){
                    dict.add(entry);
                }
            }
            curatedDict = dict;
            curatedDict.sort(Comparator.comparingInt(d -> d.score));
        }

        public static DictEntry bestWords(){
            DictEntry best = new DictEntry("PLACHOLDER");

            for (DictEntry entry : curatedDict){
                if (!entry.hasDupes)best = entry;
            }
            //return curatedDict.get(curatedDict.size()-1);
            return best;
        }

        public static void curatedPrinter(){
            int numBeforeNuLine = 0;
            for (DictEntry entry : curatedDict){
                if (numBeforeNuLine == 11){
                    System.out.print("\n");
                }
                System.out.print(entry + ", ");
                numBeforeNuLine++;
            }
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (true){
                System.out.println("Enter constraint with this format, 0X 1X 2X 1X 0X, where 0 is grey, 1 is yellow, and 2 is green");
                String line = scanner.nextLine();

                if (line.equalsIgnoreCase("y")){
                    System.out.println(yellowLetters);
                    continue;
                } else if (line.equalsIgnoreCase("b")){
                    System.out.println(blackLetters);
                    continue;
                } else if (line.equalsIgnoreCase("g")){
                    System.out.println(fixedPos);
                    continue;
                }


                constraintsParser(line);
                filter();

                System.out.println("Your best next guess is: " + bestWords());
                System.out.println("Your possible words are: \n{");
                curatedPrinter();
                System.out.println("\n}");
            }
        }
    }
