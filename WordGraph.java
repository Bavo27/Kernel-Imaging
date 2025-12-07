import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class WordGraph {
    private ArrayList<String> words = new ArrayList<>();
    private HashMap<String, ArrayList<String>> adjancencyList = new HashMap<>();
    private String source;
    private String target;
    private Map<String, String> state = new HashMap<>();
    private Map<String, String> pred = new HashMap<>();
    private Queue<String> queue = new ArrayDeque<>();

    public static void main(String[] args) {
        new WordGraph();
    }

    public WordGraph() {
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
        
        this.source = "pen";
        this.target = "dog";
        
        makeAdjancencyList();
        BFS();

        ArrayList<String> path = reconstructPath();
        String pathString = "";
        
        for (String word : path) {
            pathString += " -> " + word;
        }
        pathString = source + pathString;
        System.out.println(pathString);
    }

    public void makeAdjancencyList() {

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

    public Map<String, String> BFS() {
        for (String word : adjancencyList.keySet()) {
            state.put(word, "undiscovered");
            pred.put(word, "-1");
        }
        state.replace(source, "discovered");
        queue.add(source);

        String u = "";
        while (!queue.isEmpty()) {
            u = queue.remove();
            for (String neighbor : adjancencyList.get(u)) {
                if (state.get(neighbor) == "undiscovered") {
                    state.replace(neighbor, "discovered");
                    pred.replace(neighbor, u);
                    queue.add(neighbor);
                }
                state.replace(u, "processed");
            }
        }

        return pred;
    }

    private ArrayList<String> reconstructPath() {
        ArrayList<String> path = new ArrayList<>();
        String curr = target;

        while(pred.get(curr) != "-1") {
            path.addFirst(curr);
            curr = pred.get(curr);
        }

        return path;
    }

}
