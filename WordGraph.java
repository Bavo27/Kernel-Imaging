import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class WordGraph {
    private ArrayList<String> words = new ArrayList<>();
    private HashMap<String, ArrayList<String>> adjancencyList = new HashMap<>();

    public void main(String[] args) {
        File file = new File("threeletterwords.txt");
        
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                words.add(line);
            }
        } catch (FileNotFoundException e) {
        System.err.println("Error: File not found - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
        makeAdjancencyList();
    }

    public void makeAdjancencyList() {   
        //iterates through word list
        for (int i = 0; i < words.size(); i++) {
            String word1 = words.get(i);
            for (int j = i + 1; j < words.size(); j++){
                String word2 = words.get(j);
                int dif = checkWords(word1, word2);
                if (dif == 1) {
                    addToAdjacency(word1, word2);
                }
            }
        }
        search();
    }

    private void addToAdjacency(String word1, String word2) {
        if (!adjancencyList.containsKey(word1)) {
            ArrayList<String> edgeList = new ArrayList<>();
            edgeList.add(word2);
            adjancencyList.put(word1, edgeList);
        } else {
            adjancencyList.get(word1).add(word2);
        }
        if (!adjancencyList.containsKey(word2)) {
            ArrayList<String> edgeList = new ArrayList<>();
            edgeList.add(word1);
            adjancencyList.put(word2, edgeList);
        } else {
            adjancencyList.get(word2).add(word1);
        }
    }

    public int checkWords(String word1, String word2) {
        int totDif = 0;
        for (int i = 0; i < 3 ; i++) {
            if (word1.charAt(i) != word2.charAt(i)){
                totDif++;
            }
        }
        return totDif;
    }

    public String search() {
        String path = "";
        
        return path;
    }
}
