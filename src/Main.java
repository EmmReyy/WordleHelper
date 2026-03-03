    import java.util.*;

    public class Main {

        static ArrayList<DictEntry> curatedDict = new ArrayList<>();

        static FivesDictionary fives = new FivesDictionary("C:\\Users\\gobbl\\IdeaProjects\\WordleHelp\\fiveLetterWords.txt");

        public static void filter(ConstraintsHandler constraintsHandler){
            ArrayList<DictEntry> dict = new ArrayList<>();

            for (DictEntry entry : fives.words){
                if (constraintsHandler.adheresConstraints(entry)){
                    dict.add(entry);
                }
            }
            curatedDict = dict;
            curatedDict.sort(Comparator.comparingInt(d -> d.score));
        }

        public static DictEntry bestWords(){
            DictEntry best = new DictEntry("");

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
            ConstraintsHandler constraintsHandler = new ConstraintsHandler();
            Scanner scanner = new Scanner(System.in);

            while (true){
                System.out.println("Enter constraint with this format, 0X 1X 2X 1X 0X, where 0 is grey, 1 is yellow, and 2 is green");
                String line = scanner.nextLine();

                constraintsHandler.updateConstraints(line);
                filter(constraintsHandler);

                System.out.println("Your best next guess is: " + bestWords());
                System.out.println("Your possible words are: \n{");
                curatedPrinter();
                System.out.println("\n}");
            }
        }
    }
