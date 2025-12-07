public class LongestCommonSeq {
    private String str1 = "yesterday";
    private String str2 = "monday";

    public static void main(String[] args) {
        new LongestCommonSeq();
    }
    
    public LongestCommonSeq() {
        int[][] dist = new int[str1.length()+1][str2.length()+1];
        int[][] pred = new int[str1.length()+1][str2.length()+1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) {
                    dist[i][j] = j;
                    if (j == 0) {
                        pred[i][j] = -1; // starting point has no parent
                    } else {
                        pred[i][j] = 1; // cell to the left (insert)
                    }
                } else if (j == 0) {
                    dist[i][j] = i;
                    if (i == 0) {
                        pred[i][j] = -1; // starting point has no parent
                    } else {
                        pred[i][j] = 2; // cell above (delete)
                    }
                } else {
                    int matchScore = dist[i-1][j-1] + match(str1.charAt(i-1), str2.charAt(j-1));
                    int insertScore = dist[i][j-1] + 1;
                    int deleteScore = dist[i-1][j] + 1;
                    int minVal = Math.min(matchScore, Math.min(insertScore, deleteScore));
                    
                    if (matchScore == minVal) {
                        dist[i][j] = matchScore;
                        pred[i][j] = 0; // cell diagonal up/left (match or subst)
                    } else if (insertScore == minVal) {
                        dist[i][j] = insertScore;
                        pred[i][j] = 1; // cell to the left (insert)
                    } else {
                        dist[i][j] = deleteScore;
                        pred[i][j] = 2; // cell above (delete)
                    }
                }
            }
        }
        System.out.println("Longest common sub-sequence: " + dist[str1.length()][str2.length()]);
    }

    public Integer match(char a, char b) {
        if (a == b) {
            return 0;
        } else {
            return 1;
        }
    }


}
