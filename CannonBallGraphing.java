
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CannonBallGraphing {
    private HashMap<Point, HashMap<Point, Double>> cannonGraph = new HashMap<>();
    private ArrayList<Point> cannons = new ArrayList<>();
    private Point sourceP;
    private Point targetP;
    public static void main(String[] args) {
        CannonBallGraphing cannonBallGraphing = new CannonBallGraphing();
    }

    public CannonBallGraphing() {
        Scanner sc = new Scanner(System.in);

        String[] source = sc.nextLine().split(" ");
        this.sourceP = new Point(Integer.parseInt(source[0]), Integer.parseInt(source[1]));

        String[] target = sc.nextLine().split(" ");
        this.targetP = new Point(Integer.parseInt(target[0]), Integer.parseInt(target[1]));

        int numCannons = sc.nextInt();

        while (sc.hasNextLine()) {
            // make these to points and then add them
            String[] cannon = sc.nextLine().split(" ");
            Point cannonP = new Point(Integer.parseInt(cannon[0]), Integer.parseInt(cannon[1]));
            cannons.add(cannonP);
        }
        cannonGraph();
        dijkstra();
    }

    private void dijkstra() {
        
    }

    private void cannonGraph() {
        cannonGraph = new HashMap<>();
        for (int i = 0; i < cannons.size() - 1; i++) {
            for (int j = i + 1; j < cannons.size(); j++) {
                double time = makeWeightedEdge(cannons.get(i), cannons.get(j));
                if (i == 0) { 
                    HashMap<Point, Double> weightedEdgeF = new HashMap<>(); // for vertice i
                    HashMap<Point, Double> weightedEdgeB = new HashMap<>(); // for vertice f
                    
                    weightedEdgeF.put(cannons.get(j), time);
                    cannonGraph.put(cannons.get(i), weightedEdgeF);

                    weightedEdgeB.put(cannons.get(i), time);
                    cannonGraph.put(cannons.get(j), weightedEdgeB);
                } else {
                    cannonGraph.get(cannons.get(i)).put(cannons.get(j), time);
                    cannonGraph.get(cannons.get(j)).put(cannons.get(i), time);
                }
                
            }
        }
    }

    private double makeWeightedEdge(Point point1, Point point2) {
        double time = 2.0;
        double dist = Point.distance(point1.getX(), point1.getY(), point2.getX(), point2.getY());
        
        dist = dist - 50; 
        dist = Math.abs(dist);
        
        time += dist/5;
        return time;
    }
    
}