import java.io.*;
import java.util.*;

public class FivesDictionary {

    public ArrayList<DictEntry> words = new ArrayList<>();

    public FivesDictionary(String path){
        parseText(path);
    }

    void parseText(String path){
        try {
            BufferedReader buff = new BufferedReader(new FileReader(path));

            String line;
            while ((line=buff.readLine()) != null){
                words.add(new DictEntry(line));
            }
        } catch (Exception e) {
            System.out.println(e);;
        }
    }
}
