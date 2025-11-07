
import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

public class CannonBallGraphing {
    private HashMap<Point, String[]> cannonsGraph = new HashMap<>();
    public static void main(String[] args) {
        CannonBallGraphing cannonBallGraphing = new CannonBallGraphing();
    }

    public CannonBallGraphing() {
        Scanner sc = new Scanner(System.in);

        String[] source = sc.nextLine().split(" ");
        Point sourceP = new Point(Integer.parseInt(source[0]), Integer.parseInt(source[1]));

        String[] target = sc.nextLine().split(" ");
        Point targetP = new Point(Integer.parseInt(target[0]), Integer.parseInt(target[1]));

        int numCannons = sc.nextInt();

        while (sc.hasNextLine()) {
            // make these to points and then add them
            String[] cannon = sc.nextLine().split(" ");
        }
    }
}
