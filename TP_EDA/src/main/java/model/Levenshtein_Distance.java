package model;

public class Levenshtein_Distance {

    public int distance(String str1, String str2){
        int lenght1= str1.length();
        int lenght2 = str2.length();
        int[][] matrix = new int[lenght1][lenght2];
        for(int i=0; i<lenght1; i++){
            matrix[i][0] = i;
        }
        for(int i=0; i<lenght2; i++){
            matrix[0][i] = i;
        }
        for(int i= 1; i < lenght1; i++){
            for(int j= 1; j<lenght2; j++){

                matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i][j-1]) + 1;
                matrix[i][j] = Math.min(matrix[i][j], matrix[i-1][j-1] + (str1.charAt(i) == str2.charAt(j)?0:1));
            }

        }
        return matrix[lenght1 - 1][lenght2 - 1];
    }

    public double normalizedSimilarity(String str1,String str2){
        return (1 - (double)(distance(str1,str2))/Math.max(str1.length(),str2.length()));
    }
}
