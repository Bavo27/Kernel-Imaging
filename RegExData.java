import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegExData {
    public void main(String[] args) {
        String[] months = {
            "january", "January", "February", "february", "March", "march", "April", "april",
            "May", "may", "June", "june", "July", "july", "August", "august",
            "September", "september", "October", "october", "November", "november", "December", "december"
        };
        String regExMonths = String.join("|", months);
        String re = "(.*("+regExMonths+") [0-9]{1,2}+, [0-9]{2,4}+).*|.*([0-9]{1,2}+ ("+regExMonths+"), [0-9]{2,4}+).*|.*([0-9]{1,2}+/[0-9]{1,2}+/[0-9]{2,4}+).*|.*([0-9]{1,2}+-[0-9]{1,2}+-[0-9]{2,4}+).*";
        String str = "dhsajgdhsa 15 May, 2025 ahkshdj";
        // String str = "May 15, 2025";
        // String str = "04/04/2005";
        // String str = "04/04/05";
        // String str = "04-04-2005";
        // String str = "January 15, 2025";


        Pattern pt = Pattern.compile(re);
        Matcher m = pt.matcher(str);

        Boolean res = m.matches();
        System.out.println(res);
    }

}
