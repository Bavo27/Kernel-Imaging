import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegExData {
    public void main(String[] args) {
        String re = "([a-zA-Z]{3,9}+ [0-9]{1,2}+, [0-9]{2,4}+)|([0-9]{1,2}+ [a-zA-Z]{3,9}+, [0-9]{2,4}+)|([0-9]{1,2}+/[0-9]{1,2}+/[0-9]{2,4}+)|([0-9]{1,2}+-[0-9]{1,2}+-[0-9]{2,4}+)";
        // String str = "15 May, 2025";
        // String str = "May 15, 2025";
        // String str = "04/04/2005";
        // String str = "04/04/05";
        String str = "04-04-2005";


        Pattern pt = Pattern.compile(re);
        Matcher m = pt.matcher(str);

        Boolean res = m.matches();
        System.out.println(res);
    }

}
