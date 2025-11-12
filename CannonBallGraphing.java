// import java.awt.geom.Point2D;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.PriorityQueue;
// import java.util.Queue;
// import java.util.Scanner;

// public class CannonBallGraphing {
//     // private HashMap<Double[], HashMap<Double[], Double>> cannonGraph = new HashMap<>();
//     // private ArrayList<Double[]> cannons = new ArrayList<>(); // used to be Point
//     private HashMap<Point2D, HashMap<Point2D, Double>> cannonGraph = new HashMap<>();
//     private ArrayList<Point2D> cannons = new ArrayList<>(); 
//     private Point2D sourceP;
//     private Point2D targetP;

//     public void main(String[] args) {
//         CannonBallGraphing cannonBallGraphing = new CannonBallGraphing();
//     }

//     public CannonBallGraphing() {
//         Scanner sc = new Scanner(System.in);

//         String[] source = sc.nextLine().split(" ");
//         // Double[] sourceN = {Double.parseDouble(source[0]), Double.parseDouble(source[1])};
//         sourceP = new Point2D.Double(Double.parseDouble(source[0]), Double.parseDouble(source[1]));
//         cannons.add(sourceP);

//         String[] target = sc.nextLine().split(" ");
//         // Double[] targetN = {Double.parseDouble(target[0]), Double.parseDouble(target[1])};
//         this.targetP = new Point2D.Double(Double.parseDouble(target[0]), Double.parseDouble(target[1]));

//         int numCannons = sc.nextInt();
//         sc.nextLine();

//         for (int i = 0; i < numCannons; i++) {
//             String[] cannon = sc.nextLine().split(" ");
//             // Double[] cannonN = {Double.parseDouble(cannon[0]), Double.parseDouble(cannon[1])};
//             Point2D.Double cannonP = new Point2D.Double(Double.parseDouble(cannon[0]), Double.parseDouble(cannon[1]));
//             cannons.add(cannonP);
//         }
//         cannons.add(targetP); //add at the end because if added at the anywhere else it won't work 
//         cannonGraph();
//         dijkstra();
//     }

//     private void dijkstra() {
//         HashMap<Point2D, Double> dist = new HashMap<>();
//         HashMap<Point2D, Point2D> prev = new HashMap<>();
//         Queue<Double> queueP = new PriorityQueue<Double>();
        
//         for (Point2D cannon : cannons) {
//             dist.put(cannon, Double.MAX_VALUE);
//         }
//         dist.put(sourceP, 0.0);
//         queueP.add(dist.get(sourceP));

//         while (!queueP.isEmpty()) {
//             Point2D u = new Point2D();
//             for (Point2D point : dist.keySet()) {
//                 if (dist.get(point) == queueP.peek()) {
//                     u = point;
//                 }
//             }
            
//             if (u == targetP){
//                 break;
//             }

//             for (Point2D neighbor : cannonGraph.get(u).keySet()) {
//                 double weight = cannonGraph.get(u).get(neighbor);
//                 double alt = dist.get(u) + weight;

//                 if (alt < dist.get(neighbor)) {
//                     dist.put(u, weight);
//                     prev.put(neighbor, u);
//                     queueP.add(dist.get(neighbor));
//                 }
//             }
//         }

//     }

//     private void cannonGraph() {
//         cannonGraph = new HashMap<>();
//         for (int i = 0; i < cannons.size() - 1; i++) {
//             for (int j = i + 1; j < cannons.size(); j++) {
//                 double time = makeWeightedEdge(cannons.get(i), cannons.get(j));
//                 if (i == 0) { 
//                     HashMap<Point2D, Double> weightedEdgeF = new HashMap<>(); // for vertice i
//                     HashMap<Point2D, Double> weightedEdgeB = new HashMap<>(); // for vertice f
                    
//                     weightedEdgeF.put(cannons.get(j), time);
//                     cannonGraph.put(cannons.get(i), weightedEdgeF);

//                     weightedEdgeB.put(cannons.get(i), time);
//                     cannonGraph.put(cannons.get(j), weightedEdgeB);
//                 } else {
//                     cannonGraph.get(cannons.get(i)).put(cannons.get(j), time);
//                     cannonGraph.get(cannons.get(j)).put(cannons.get(i), time);
//                 }
                
//             }
//         }
//     }

//     private double makeWeightedEdge(Point2D point1, Point2D point2) {
//         double dist = Point2D.distance(point1.getX(), point1.getY(), point2.getX(), point2.getY());
//         // double dist = Math.sqrt(Math.pow(point2[0]-point1[0], 2)+Math.pow(point2[1]-point1[1], 2));
//         if (point1 == sourceP) {
//             return dist/5;
//         }
//         double time = 2.0;
        
//         dist = dist - 50; 
//         dist = Math.abs(dist);
        
//         time += dist/5;
//         return time;
//     }
    
// }
import java.awt.geom.Point2D;
import java.util.*;

public class CannonBallGraphing {

    private HashMap<Point2D.Double, HashMap<Point2D.Double, Double>> cannonGraph = new HashMap<>();
    private ArrayList<Point2D.Double> cannons = new ArrayList<>();

    public static void main(String[] args) {
        new CannonBallGraphing();
    }

    public CannonBallGraphing() {
        Scanner sc = new Scanner(System.in);

        String[] sourceLine = sc.nextLine().trim().split(" ");
        Point2D.Double source = new Point2D.Double(Double.parseDouble(sourceLine[0]), Double.parseDouble(sourceLine[1]));
        cannons.add(source);

        String[] targetLine = sc.nextLine().trim().split(" ");
        Point2D.Double target = new Point2D.Double(Double.parseDouble(targetLine[0]), Double.parseDouble(targetLine[1]));

        int numCannons = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < numCannons; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");
            Point2D.Double cannon = new Point2D.Double(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
            cannons.add(cannon);
        }

        cannons.add(target); // add target as a node at the end

        buildCannonGraph(source);
        dijkstra(source, target);
    }

    private void buildCannonGraph(Point2D.Double source) {
        cannonGraph.clear();

        for (int i = 0; i < cannons.size(); i++) {
            Point2D.Double a = cannons.get(i);
            cannonGraph.putIfAbsent(a, new HashMap<>());

            for (int j = i + 1; j < cannons.size(); j++) {
                Point2D.Double b = cannons.get(j);
                cannonGraph.putIfAbsent(b, new HashMap<>());

                double time = makeWeightedEdge(a, b, source);

                cannonGraph.get(a).put(b, time);
                cannonGraph.get(b).put(a, time);
            }
        }
    }

    private double makeWeightedEdge(Point2D.Double point1, Point2D.Double point2, Point2D.Double source) {
        double dist = point1.distance(point2);

        // Walking from source (no cannon yet)
        if (point1.equals(source)) {
            return dist / 5.0; // walking speed
        }

        // Using a cannon: 2s to launch + adjusted travel time
        double time = 2.0;
        dist = Math.abs(dist - 50);
        time += dist / 5.0;
        return time;
    }

    private void dijkstra(Point2D.Double source, Point2D.Double target) {
        class Node {
            Point2D.Double point;
            double dist;
            Node(Point2D.Double p, double d) { point = p; dist = d; }
        }

        HashMap<Point2D.Double, Double> dist = new HashMap<>();
        HashMap<Point2D.Double, Point2D.Double> prev = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.dist));

        for (Point2D.Double p : cannons) {
            dist.put(p, Double.MAX_VALUE);
            prev.put(p, null);
        }

        dist.put(source, 0.0);
        pq.add(new Node(source, 0.0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            Point2D.Double u = current.point;

            if (u.equals(target)) break; // reached target

            for (Map.Entry<Point2D.Double, Double> entry : cannonGraph.get(u).entrySet()) {
                Point2D.Double neighbor = entry.getKey();
                double weight = entry.getValue();

                double alt = dist.get(u) + weight;
                if (alt < dist.get(neighbor)) {
                    dist.put(neighbor, alt);
                    prev.put(neighbor, u);
                    pq.add(new Node(neighbor, alt));
                }
            }
        }

        // Output shortest time and (optional) path
        System.out.printf("Shortest time: %.6f seconds%n", dist.get(target));

        // Uncomment this block if you want to print the path:
        
        List<Point2D.Double> path = new ArrayList<>();
        for (Point2D.Double at = target; at != null; at = prev.get(at)) {
            path.add(0, at);
        }
    }
}