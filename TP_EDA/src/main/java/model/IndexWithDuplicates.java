package model;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IndexWithDuplicates <T extends Comparable> implements IndexService<T> {
    final static private int STEP = 5;
    T[] elements;
    int dim;


    public IndexWithDuplicates(){
        dim = 0;
        elements = (T[]) new Comparable<?>[]{};


    }
    public IndexWithDuplicates(T[] elements){
        if(elements == null)
            throw new NullPointerException();
        this.elements = elements;
        Arrays.sort(elements);
        dim = 0;
    }

    public T[] idex(){
        return elements;
    }
    @Override
    public void initialize(T[] elements) {
        if(elements == null)
            throw new NullPointerException();
        this.elements = elements;
        Arrays.sort(elements);
        dim = elements.length;
        }

    @Override
    public void sortedPrint() {
        System.out.println(Arrays.toString(elements));
    }

    @Override
    public Comparable[] range(T leftKey, T rightKey, boolean leftIncluded, boolean rightIncluded) {
        int indexLeft = 0;
        int indexRight = 0;
        boolean found = false;
        T[] range;
        if(leftKey.compareTo(rightKey) > 0)
            return range =  (T[]) new Comparable<?>[]{};;
        while(indexLeft < dim  && !found){
            if(elements[indexLeft].compareTo(leftKey)>0){
                found = true;

            }else{
                if(leftIncluded) {
                    if (elements[indexLeft].compareTo(leftKey)==0) {
                        found = true;
                    }
                }
            }
            indexLeft++;
        }
        if(found == false){
            return range = (T[]) new Comparable<?>[]{};
        }
        indexLeft = indexLeft - 1;
        if(indexLeft == dim){
            range = (T[]) new Object[]{elements[indexLeft]};
            return range;
        }
        found = false;
        indexRight = indexLeft;
        while(indexRight< dim && !found){
            if (elements[indexRight].compareTo(rightKey)==0) {
                if(!rightIncluded){
                    indexRight = indexRight -1;
                    found = true;
                }
            }
            if(elements[indexRight].compareTo(rightKey)  > 0){
                indexRight = indexRight - 1;
                found = true;
            }
            indexRight++;
        }
        range = Arrays.copyOfRange(elements,indexLeft,indexRight);
        return range;
    }

    // busca una key en el índice, O(log2 N)
    public boolean search(T key){
        if(dim == 0)
            return false;

        int index = getClosetPosition(key);
        if(index > dim - 1)
            return false;
        return  key.compareTo(elements[index]) == 0;
    }

/*    // inserta el key en pos correcta. Crece automáticamente de a chunks
    public void insert(T key) {
        if(dim % STEP == 0){
            elements = Arrays.copyOfRange(elements, 0,  elements.length+ STEP);
        }
        dim ++;
        if (dim == 1) {
            elements[0] = key;
            return;
        }
        for (int i = 0; i < dim; i++) {
            if (elements[i].compareTo(key) > 0) {
                elements[dim - 1] = elements[i];
                elements[i] = key;
                key = elements[dim - 1];
            } else {
                elements[elements.length - 1] = key;
            }
        }
    }
*/

    public void insert(T key){

        if (dim == 0) {
            elements = Arrays.copyOfRange(elements, 0,  STEP);
            elements[0] = key;
            dim++;
            return;
        }
        int index = getClosetPosition(key);
        if(dim >= elements.length){
            elements = Arrays.copyOfRange(elements, 0,  elements.length+ STEP);
        }
        dim ++;

        while(index < dim){
            elements[dim - 1] = elements[index];
            elements[index] = key;
            key = elements[dim - 1];
            index++;
        }
    }
    private int getClosetPosition(T key) {
        int left= 0;
        int right= dim-1;

        while (left <= right) {
            int mid= (left + right) / 2;
            if ( elements[ mid ].compareTo(key) == 0 ) //HACER LEVENSHTEIN
                return mid;
            if ( key.compareTo(elements[mid]) < 0)
                right = mid  - 1;
            else
                left = mid + 1;

        }

        return left;
    }
    // borra el key si lo hay, sino lo ignora.
    // decrece automáticamente de a chunks
    public void delete(T key){
        int index = getClosetPosition(key);
        if(elements[index].compareTo(key) != 0)
            return;
        for(int i = index + 1; i < dim; i++){
            elements[i - 1] = elements[i];
        }
        dim --;
    elements = Arrays.copyOfRange(elements, 0, dim);

    }

    // devuelve la cantidad de apariciones de la clave especificada
    public int occurrences(T key){
        int count = 0;
        for(int i = 0; i < dim; i++){
            if(elements[i].compareTo(key) == 0){
                count++;
            }
        }
        return count;
    }
    public T getMax() {
        if(dim == 0)
            throw new NoSuchElementException();
        return elements[dim - 1];
    }
    public T getMin(){
        if(dim == 0)
            throw new NoSuchElementException();
        return elements[0];
    }

    /*
    int[] elements;


    public IndexWithDuplicates(int [] elements){
        if(elements == null)
            throw new NullPointerException();
        this.elements = elements;
        Arrays.sort(elements);
    }
    public IndexWithDuplicates(){
        elements = new int[]{};

    }
    // elements serán los valores del índice, los anteriores se descartan.
    // lanza excepction si elements is null y deja los valores anteriores.
   public void initialize(int [] elements){
       if(elements != null)
           this.elements = elements;

       Arrays.sort(elements);
   }
/*
    public int getMax() {
        return elements[elements.length - 1];
    }
    public int getMin(){
        return elements[0];
    }

    @Override
    public void sortedPrint() {
        System.out.println(Arrays.toString(elements));
    }

    @Override
    public int[] range(int leftKey, int rightKey, boolean leftIncluded, boolean rightIncluded) {
        int indexLeft = 0;
        int indexRight = 0;
        boolean found = false;
        int[] range;
        if(leftKey > rightKey)
            return range = new int[]{};
        while(indexLeft < elements.length && !found){
            if(elements[indexLeft] > leftKey){
                found = true;

            }else{
                if(leftIncluded) {
                    if (elements[indexLeft] == leftKey) {
                        found = true;
                    }
                }
            }
            indexLeft++;
        }
        if(found == false){
           return range = new int[]{};
        }
        indexLeft = indexLeft - 1;
        if(indexLeft == elements.length){
            range = new int[]{elements[indexLeft]};
            return range;
        }
        found = false;
        indexRight = indexLeft;
        while(indexRight< elements.length && !found){
                    if (elements[indexRight] == rightKey) {
                        if(!rightIncluded){
                            indexRight = indexRight -1;
                            found = true;
                        }
                    }
                    if(elements[indexRight] > rightKey){
                        indexRight = indexRight - 1;
                        found = true;
                    }
                    indexRight++;
                }
        range = Arrays.copyOfRange(elements,indexLeft,indexRight);
        return range;
        }

    // busca una key en el índice, O(log2 N)
    public boolean search(int key){
       if(elements.length == 0)
           return false;
       int index = getClosetPosition(key);
       if(index > elements.length)
           return false;
       return getClosetPosition(key) == elements[key];
    }

    // inserta el key en pos correcta. Crece automáticamente de a chunks
   public void insert(int key) {
        elements = Arrays.copyOfRange(elements, 0, elements.length + 1);
        if (elements.length == 1) {
            elements[0] = key;
            return;
        }
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] > key) {
                elements[elements.length - 1] = elements[i];
                elements[i] = key;
                key = elements[elements.length - 1];
            } else {
                elements[elements.length - 1] = key;
            }
        }
    }

    public void insert(int key){

        if (elements.length == 0) {
            elements = Arrays.copyOfRange(elements, 0, elements.length + 1);
            elements[0] = key;
            return;
        }
        int index = getClosetPosition(key);
        elements = Arrays.copyOfRange(elements, 0, elements.length + 1);
        while(index < elements.length){
            elements[elements.length - 1] = elements[index];
            elements[index] = key;
            key = elements[elements.length - 1];
            index++;
        }
    }
    private int getClosetPosition(int key) {
        int left= 0;
        int right= elements.length-1;

        while (left <= right) {
            int mid= (left + right) / 2;
            if ( elements[ mid ] == key )
                return mid;
            if ( key < elements[mid]  )
                right = mid  - 1;
            else
                left = mid + 1;

        }

        return left;
    }
    // borra el key si lo hay, sino lo ignora.
    // decrece automáticamente de a chunks
    public void delete(int key){
        Pair<Integer,Integer> pair = binarySearch(elements, key);
        if(pair.getKey() == 1){
            for(int i = pair.getValue() + 1; i < elements.length; i++){
                    elements[i - 1] = elements[i];
            }
        }
        elements = Arrays.copyOfRange(elements, 0, elements.length - 1);
    }

    // devuelve la cantidad de apariciones de la clave especificada
    public int occurrences(int key){
        int count = 0;
        for(int i = 0; i < elements.length; i++){
            if(elements[i] == key){
                count++;
            }
        }
        return count;
    }

    public int recursiveBinarySearch(int[] arr, int elem){
        if(arr.length == 1){
            if(arr[0] == elem)
                return 0;
            else
                return -1;
        }

        if(arr.length == 0)
            return -1;

        int index = arr.length/2;

        if(arr[index] == elem)
            return index;

        if(arr[index] < elem) {
            arr = java.util.Arrays.copyOfRange(arr, index, arr.length);
            return recursiveBinarySearch(arr, elem);
        }
        arr = java.util.Arrays.copyOfRange(arr, 0, index - 1);
        return recursiveBinarySearch(arr, elem);
    }

    public Pair<Integer,Integer> binarySearch(int[] arr, int elem){
        if(arr.length == 0)
            return new Pair<>(-1,0);
        if(arr.length == 1) {
            if (arr[0] == elem)
                return new Pair<>(1,0);
            return new Pair<>(-1,0);
        }
        int index = arr.length/2;

        while(index < arr.length){
           if(arr[index] == elem)
               return new Pair<>(1,index);
           if(arr[index] < elem)
               index = index + index/2;
           else {
               if(index == 0)
                   return new Pair<>(-1,0);

               index = index / 2;
           }

        }
        return new Pair<>(-1,arr.length);

    }
*/
}
