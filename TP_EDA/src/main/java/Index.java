import model.PlaceLocation;
import org.apache.commons.collections.iterators.IteratorEnumeration;
import org.apache.commons.csv.CSVRecord;

import java.util.*;

public class Index {

    SortedMap<String, PlaceLocation> index;
    public Index() {
        index =new TreeMap<>(Comparator.naturalOrder());
    }


    public int distance(String str1, String str2) {
        if (str1.length() <= str2.length()) {
            return levenDistance(str1,str2);
        } else {
            return levenDistance(str2,str1);
        }

    }

    private int levenDistance(String str1, String str2){
        int[] string;
        int lenght1 = str1.length();
        int lenght2 = str2.length();
        string = new int[lenght1];
        for (int i = 0; i < lenght1; i++) {
            string[i] = i;
        }
        int value = str2.length();
        for (int j = 1; j < lenght2; j++) {
            for (int i = 1; i < lenght1; i++) {
                int aux = value;
                value = Math.min(value, string[i]) + 1;
                value = Math.min(value, string[i - 1] + (str1.charAt(i) == str2.charAt(j) ? 0 : 1));
                string[i-1] = aux;
            }
            string[lenght1 - 1] = value;
        }
        return value = string[lenght1 - 1];
    }
    public double normalizedSimilarity(String str1,String str2){
        return (1 - (double)(distance(str1,str2))/Math.max(str1.length(),str2.length()));
    }


    public double similarytyWhite(String str1, String str2) {
        Scanner scanner2 = new Scanner(str2.toLowerCase());
        double distance = 0.0;
        String str2B = str2;
        double ponderation = 0;
        double normalizeDistance;

        normalizeDistance = normalizedSimilarity(str1, str2);
        if (normalizeDistance != 1) {
            distance = distance + Math.pow(normalizedSimilarity(str1, str2), 17) * (str1.length());
            ponderation = ponderation + str1.length();

            while (scanner2.hasNext()) {
                str2B = scanner2.next();
                Scanner scanner1 = new Scanner(str1.toLowerCase());

                String str1A = str1;
                while (scanner1.hasNext()) {
                    str1A = scanner1.next();
                    normalizeDistance = normalizedSimilarity(str1A, str2B);

                    distance = distance + Math.pow(normalizeDistance, 17) * (str1A.length());
                    ponderation = ponderation + str1A.length();
                }

            }
            if (ponderation == 0)
                throw new NullPointerException();
            else
                return (distance / ponderation);
        }

        return normalizeDistance;

    }

  /*

                if (normalizeDistance >= 0.8) {
                    distance = distance + normalizeDistance *Math.pow(str1A.length(),3);
                    ponderation = ponderation + Math.pow(str1A.length(),3);
                } else {
                    distance = distance + normalizeDistance * (str1A.length());
                    ponderation = ponderation + str1A.length();
                }
            }
        }
            normalizeDistance = normalizedSimilarity(str1, str2);
        if (normalizeDistance >= 0.8) {
            distance = distance +  normalizeDistance*Math.pow(str1.length(),3);
            ponderation = ponderation + Math.pow(str1.length(),3);
        }else {
            distance = distance + normalizeDistance * (str1.length());
            ponderation = ponderation + str1.length();
        }

 */

    public void put(String str, PlaceLocation placeLocation){
        index.put(str,placeLocation);
    }

    public List<PlaceLocation> search(String str1){
        Queue<PlaceLocation> results = new PriorityQueue<>();
        for(Map.Entry<String,PlaceLocation> entry: index.entrySet()){
            Double similarity = similarytyWhite(str1,entry.getKey());
            if(results.size() < 10){
                PlaceLocation placeLocation = entry.getValue();
                placeLocation.similarity(similarity);
                results.add(placeLocation);
            }
            else{
                    PlaceLocation placeLocation = results.peek();
                    if(Double.compare(placeLocation.getSimilarity(),similarity) < 0){
                        results.remove(placeLocation);
                        placeLocation = entry.getValue();
                        placeLocation.similarity(similarity);
                        results.add(placeLocation);
                    }

            }
        }
        int queueSize = results.size();
        List<PlaceLocation> list = new ArrayList<>();
        for (int i = 0 ; i< queueSize; i++)
        {
            list.add(i, results.poll());
        }


        Collections.reverse(list);
        return list;
    }


}
