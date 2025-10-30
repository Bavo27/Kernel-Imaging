import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class WordGraph {
    private ArrayList<String> words = new ArrayList<>();
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

        makeGraph();
    }

    public void makeGraph() {   
        HashMap<String, List<String>> adjancencyList = new HashMap<>();
        char[] alphabet = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };

        //iterates through word list
        for (int i = 0; i < words.size(); i++) {
            StringBuilder sb = new StringBuilder(words.get(i));
            // iterate through each character
            for (int j = 0; j < 3; j++) {
                // iterate through each character in the alphabet swapping it with the current character. if it matches connect the nodes
                for (int h = 0; h < alphabet.length; h++) {
                    sb.setCharAt(j, alphabet[h]);
                    String word = sb.toString();
                    for (int g = 0; g<words.size(); g++) {
                        if (word == words.get(g)){
                            //add to adjacency list
                        }
                    }
                }
            } 
        }
    }
}
