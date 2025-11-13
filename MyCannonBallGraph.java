// import java.awt.geom.Point2D;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.PriorityQueue;
// import java.util.Queue;
// import java.util.Scanner;

// public class CannonBallGraphing {
//     private HashMap<Point2D.Double, HashMap<Point2D.Double, Double>> cannonGraph = new HashMap<>();
//     private ArrayList<Point2D.Double> cannons = new ArrayList<>();

//     public void main(String[] args) {
//         new CannonBallGraphing();
//     }

//     public CannonBallGraphing() {
//         Scanner sc = new Scanner(System.in);

//         String[] source = sc.nextLine().split(" ");
//         // Double[] sourceN = {Double.parseDouble(source[0]), Double.parseDouble(source[1])};
//         Point2D.Double sourceP = new Point2D.Double(Double.parseDouble(source[0]), Double.parseDouble(source[1]));
//         cannons.add(sourceP);

//         String[] target = sc.nextLine().split(" ");
//         // Double[] targetN = {Double.parseDouble(target[0]), Double.parseDouble(target[1])};
//         Point2D.Double targetP = new Point2D.Double(Double.parseDouble(target[0]), Double.parseDouble(target[1]));

//         int numCannons = sc.nextInt();
//         sc.nextLine();

//         for (int i = 0; i < numCannons; i++) {
//             String[] cannon = sc.nextLine().split(" ");
//             // Double[] cannonN = {Double.parseDouble(cannon[0]), Double.parseDouble(cannon[1])};
//             Point2D.Double cannonP = new Point2D.Double(Double.parseDouble(cannon[0]), Double.parseDouble(cannon[1]));
//             cannons.add(cannonP);
//         }
//         sc.close();
//         cannons.add(targetP); //add at the end because if added at the anywhere else it won't work 
//         buildCannonGraph(sourceP, targetP);
//         dijkstra(sourceP, targetP);
//     }

//     private void dijkstra(Point2D.Double sourceP, Point2D.Double targetP) {
//         HashMap<Point2D.Double, Double> dist = new HashMap<>();
//         HashMap<Point2D.Double, Point2D> prev = new HashMap<>();
//         PriorityQueue<Point2D.Double> queueP = new PriorityQueue<>((a, b) -> Double.compare(dist.get(a), dist.get(b)));

//         class Node {
//             Point2D.Double point;
//             double dist;
//             Node(Point2D.Double p, double d) { point = p; dist = d; }
//         }

//         for (Point2D.Double cannon : cannons) {
//             dist.put(cannon, Double.MAX_VALUE);
//         }
//         dist.put(sourceP, 0.0);
//         queueP.add(sourceP);

//         while (!queueP.isEmpty()) {
//             Point2D.Double u = queueP.poll();
            
//             if (u.equals(targetP)){
//                 break;
//             }


//             System.out.println("new u: ");
//             // for (Point2D.Double neighbor : cannonGraph.get(u).keySet()) {
//             //     if (neighbor.equals(targetP)){
//             //         // System.out.println("target");
//             //         continue;
//             //     }
//             //     double weight = cannonGraph.get(u).get(neighbor);
//             //     // System.out.println(weight);

//             //     double alt = dist.get(u) + weight;
//             //     // System.out.println(alt);

//             //     if (alt < dist.get(neighbor)) {
//             //         dist.put(neighbor, weight);
//             //         prev.put(neighbor, u);
//             //         queueP.add(neighbor);
//             //     }
//             // }

//             for (HashMap.Entry<Point2D.Double, Double> entry : cannonGraph.get(u).entrySet()) {
//                 Point2D.Double neighbor = entry.getKey();
//                 double weight = entry.getValue();

//                 double alt = dist.get(u) + weight;
//                 if (alt < dist.get(neighbor)) {
//                     dist.put(neighbor, alt);
//                     prev.put(neighbor, u);
//                     queueP.add(new Node(neighbor, alt));
//                 }
//             }
//         }
        
//         for (Double time : dist.values()) {
//             System.out.println("shortest time: " + time);
//         }
//         //6.8
//         //10.8
//         //14.
//         //19.
//         // System.out.println(dist.get(targetP));
//     }
    
//     private void buildCannonGraph(Point2D.Double source, Point2D.Double target) {
//         cannonGraph.clear();

//         for (int i = 0; i < cannons.size(); i++) {
//             Point2D.Double a = cannons.get(i);
//             cannonGraph.putIfAbsent(a, new HashMap<>());

//             for (int j = i + 1; j < cannons.size(); j++) {
//                 Point2D.Double b = cannons.get(j);
//                 cannonGraph.putIfAbsent(b, new HashMap<>());

//                 double time = makeWeightedEdge(a, b, source);

//                 cannonGraph.get(a).put(b, time);
//                 if(!b.equals(target)) {
//                     cannonGraph.get(b).put(a, time);
//                 }
//             }
//         }
//     }

//     private double makeWeightedEdge(Point2D.Double point1, Point2D.Double point2, Point2D.Double sourceP) {
//         double dist = Point2D.Double.distance(point1.getX(), point1.getY(), point2.getX(), point2.getY());
//         // double dist = Math.sqrt(Math.pow(point2[0]-point1[0], 2)+Math.pow(point2[1]-point1[1], 2));
//         if (point1.equals(sourceP)) {
//             return dist/5;
//         }
//         double time = 2.0;
        
//         dist = dist - 50; 
//         dist = Math.abs(dist);
        
//         time += dist/5;
//         return time;
//     }
    
// }
