public class LongestCommonSeq {
    private String str1 = "CTGCACGGTAGAATGACGCTTATAATGGACTTCGACATGGCAATAACCCCCCGTTTCTACCTCAAGAGGAGAAAAGTATTAACATGACTGCTGTCGGCACAAGGGCCAAAGAAGC";
    private String str2 = "GAAGTGCGCATTTCGTTCTTGCAGCTCGTCAGTACTTTCAGAATCATGGC";

    public static void main(String[] args) {
        new LongestCommonSeq();
    }
    
    public LongestCommonSeq() {
    // pattern is a string n long; text is a string m long
	// Dynamic programming here builds two big tables 
	// m is a table that is n+1 rows by m+1 columns and holds the edit distance
	// p is a table that is the same size. It will hold a value that indicates the 
	// best of the three options: 0 for match or subst, 1 for insert, 2 for delete
	// let dist = an n+1 by m+1 matrix
	// let pred = an n+1 by m+1 matrix
	// for i = 0 to n do
	//       for j = 0 to m do
	//             if i = 0 then
	//                   let dist[i, j] = j
	//                   if j = 0 then
	//                         let pred[i, j] = -1   // starting point has no parent
	//                   else
	//                         let pred[i, j] = 1  // cell to the left (insert)
	//             else if j = 0 then
	//                   let dist[i, j] = i
	//                   if i = 0 then
	//                         let pred[i, j] = -1   // starting point has no parent
	//                   else
	//                         let pred[i, j] = 2  // cell above (delete)
	//             else
	//                   let matchScore = dist[i-1, j-1] + match(patt[i], text[j])
	//                   let insertScore = dist[i, j-1] + 1
	//                   let deleteScore = dist[i-1, j] + 1
	//                   let minVal = min(matchScore, insertScore, deleteScore)
	//                   if matchScore = minVal then
	//                         let dist[i, j] = matchScore
	//                         let pred[i, j] = 0   // cell diagonal up/left (match or subst)
	//                   else if insertScore = minVal then
	//                         let dist[i, j] = insertScore
	//                         let pred[i, j] = 1   // cell to the left (insert)
	//                   else
	//                         let dist[i, j] = deleteScore
	//                         let pred[i, j] = 2  // cell above (delete)
	// return dist[n, m], pred
        //}
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
        System.out.println("Edit distance: " + dist[str1.length()][str2.length()]);
    }

    public Integer match(char a, char b) {
        if (a == b) {
            return 0;
        } else {
            return 1;
        }
    }

    // private int match(char a, char b){
    //     if a == b: 
    //         return 0
    //     else: 
    //         return 1
    // }

}
