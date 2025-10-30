import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class RegExData {
    public void main(String[] args) {
        String[] months = {
            "january", "January", "February", "february", "March", "march", "April", "april",
            "May", "may", "June", "june", "July", "july", "August", "august",
            "September", "september", "October", "october", "November", "november", "December", "december"
        };
        String regExMonths = String.join("|", months);
        
        String filePath = "./RegEx.txt";
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String re = "(("+regExMonths+") [0-9]{1,2}+, [0-9]{2,4})"+ // May 15, 2025
                "|([0-9]{1,2}+ ("+regExMonths+"), [0-9]{2,4})"+ // 15 May, 2025
                "|([0-9]{1,2}+/[0-9]{1,2}+/[0-9]{2,4})"+ // 05/15/2025
                "|([0-9]{1,2}+-[0-9]{1,2}+-[0-9]{2,4})"+ // 05-15-2025
                "|([0-9]{2,4}+/[0-9]{1,2}+/[0-9]{1,2})"; // 2025/05/15
                Pattern pt = Pattern.compile(re);
                Matcher m = pt.matcher(line);

                while (m.find()) {
                    System.out.println(m.group());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

}
