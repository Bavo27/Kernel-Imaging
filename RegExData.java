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
        String re = "(.*("+regExMonths+") [0-9]{1,2}+, [0-9]{2,4}+).*|.*([0-9]{1,2}+ ("+regExMonths+"), [0-9]{2,4}+).*|.*([0-9]{1,2}+/[0-9]{1,2}+/[0-9]{2,4}+).*|.*([0-9]{1,2}+-[0-9]{1,2}+-[0-9]{2,4}+).*|.*([0-9]{2,4}+/[0-9]{1,2}+/[0-9]{1,2}+).*";
        // String str = "dhsajgdhsa 15 May, 2025 ahkshdj";
        String str = "I was born on 15 May, 2025 but you could also say May 15, 2025. Weirdly I celebrate my brithday on 04/04/2005, 04/04/05 for short 2005/04/04, for chinese calendar or 04-04-2005 for military. gay";
        // String str = "daskjhdgsahj 04/04/2005 hsjkdhkahksad";
        // String str = "04/04/05";
        // String str = "2025/12/30";
        // String str = "04-04-2005";
        // String str = "January 15, 2025";

        Pattern pt = Pattern.compile(re);
        Matcher m = pt.matcher(str);

        if (m.find()) {
            for (int i = 1; i <= 5; i++) {
                String matches = m.group(i);
                if (matches != null){
                    System.out.println(matches);
                }
            }
        }

        Boolean res = m.matches();
        System.out.println(res);
    }

}
