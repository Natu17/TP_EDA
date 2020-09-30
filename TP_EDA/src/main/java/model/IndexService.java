package model;

public interface IndexService<T extends Comparable> {

    // elements serán los valores del índice, los anteriores se descartan.
    // lanza excepction si elements is null y deja los valores anteriores.
    void initialize(T [] elements);

    // busca una key en el índice, O(log2 N)
    boolean search(T key);

    // inserta el key en pos correcta. Crece automáticamente de a chunks
    void insert(T key);

    // borra el key si lo hay, sino lo ignora.
    // decrece automáticamente de a chunks
    void delete(T key);

    // devuelve la cantidad de apariciones de la clave especificada
    int occurrences(T key);

    T getMax();
    T getMin();


    void sortedPrint();

    Comparable[] range(T leftKey, T rightKey, boolean leftIncluded, boolean rightIncluded);


}
