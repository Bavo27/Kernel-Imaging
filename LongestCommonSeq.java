public class LongestCommonSeq {
    private String str1 = "longest common subsequence";
    private String str2 = "sequence";

    public static void main(String[] args) {
        new LongestCommonSeq();
    }
    
    public LongestCommonSeq() {
        String[][] LCS = new String[str1.length()+1][str2.length()+1];

        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0 || j == 0) {
                    LCS[i][j] = "";
                } else {
                    if (str1.charAt(i-1) == str2.charAt(j-1)) {
                        LCS[i][j] = LCS[i-1][j-1] + str1.charAt(i-1);
                    } else {
                        LCS[i][j] = LCS[i-1][j];
                    }
                }
            }
        }
        String lcs = LCS[str1.length()][str2.length()];
        System.out.println("Longest common sub-sequence: " + lcs);
        System.out.println("the length is: " + lcs.length());
    }

}
