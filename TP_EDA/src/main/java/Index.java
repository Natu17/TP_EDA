import model.PlaceLocation;
import org.apache.commons.collections.iterators.IteratorEnumeration;
import org.apache.commons.csv.CSVRecord;

import java.util.*;

public class Index {

    SortedMap<String, CSVRecord> index;
    public Index() {
        index =new TreeMap<>(Comparator.naturalOrder());
    }


    public int distance(String str1A, String str2A){
        String str1 =str1A.toLowerCase();
        String str2 =str2A.toLowerCase();
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

    public double normalizedSimilarity(String str1, String str2){

        return (1 - (double)(distance(str1.toLowerCase(),str2.toLowerCase()))/Math.max(str1.length(),str2.length()));
    }
    public double similarytyWhite(String str1, String str2) {
        Scanner scanner2 = new Scanner(str2.toLowerCase());
        double distance = 0.0;
        String str2B = str2;
        double ponderation = 0;
        double normalizeDistance;
        while (scanner2.hasNext()) {
            str2B = scanner2.next();
            Scanner scanner1 = new Scanner(str1.toLowerCase());

            String str1A = str1;
            while (scanner1.hasNext()) {
                str1A = scanner1.next();
                normalizeDistance = normalizedSimilarity(str1A, str2B);
                if (normalizeDistance > 0.7) {
                    distance = distance + normalizeDistance * (str1A.length() + str2B.length()) * (str1A.length() + str2B.length());
                    ponderation = ponderation + (str1A.length() + str2B.length()) * (str1A.length() + str2B.length());
                } else {
                    distance = distance + normalizeDistance * (str1A.length() + str2B.length());
                    ponderation = ponderation + str1A.length() + str2B.length();
                }
            }
        }
        normalizeDistance = normalizedSimilarity(str1, str2);
        if (normalizeDistance > 0.7) {
            distance = distance +  normalizeDistance* (str1.length() + str2B.length()) * (str1.length() + str2B.length());
            ponderation = ponderation + (str1.length() + str2B.length()) * (str1.length() + str2B.length());
        }else {
            distance = distance + normalizeDistance * (str1.length() + str2.length());
            ponderation = ponderation + str1.length() + str2.length();
        }

        if (ponderation == 0)
            throw new NullPointerException();
        else
            return (distance / ponderation);
    }


/*
        while (scanner1.hasNext() || scanner2.hasNext()){
            if(!scanner1.hasNext()){
                str2B = scanner2.next();
                distance = Math.max(distance,normalizedSimilarity(str1A,str2B));
            }else {
                if (!scanner2.hasNext()) {
                    str1A = scanner1.next();
                    distance = Math.max(distance,normalizedSimilarity(str1A,str2B));

                } else{
                    str1A = scanner1.next();
                    str2B = scanner2.next();
                    distance = Math.max(distance,normalizedSimilarity(str1A,str2B)); //1
                }

            }
        }
        return distance;
    }

 */



    public void put(String str, CSVRecord csv){
        index.put(str,csv);
    }

    public List<PlaceLocation> levenshtein(String str1){
        Queue<PlaceLocation> results = new PriorityQueue<>();
        for(Map.Entry<String,CSVRecord> entry: index.entrySet()){
            Double similarity = similarytyWhite(str1,entry.getKey());
            if(results.size() < 10){
                PlaceLocation placeLocation = new PlaceLocation(entry.getKey(), Double.valueOf(entry.getValue().get("latitud")), Double.valueOf(entry.getValue().get("longitud")));
                placeLocation.similarity(similarity);
                results.add(placeLocation);
            }
            else{
                    PlaceLocation placeLocation = results.peek();
                    if(Double.compare(placeLocation.getSimilarity(),similarity) < 0){
                        results.remove(placeLocation);
                        placeLocation = new PlaceLocation(entry.getKey(), Double.valueOf(entry.getValue().get("latitud")), Double.valueOf(entry.getValue().get("longitud")));
                        placeLocation.similarity(similarity);
                        results.add(placeLocation);
                    }

                }
             }

        List<PlaceLocation> list = new ArrayList<>();
        for (int i = 0 ; i< 10; i++)
        {
            list.add(i, results.poll());
        }


        Collections.reverse(list);
        return list;
    }


}
