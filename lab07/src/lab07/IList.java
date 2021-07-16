package lab07;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

interface IList<E> extends Iterable<E>
{
    boolean add(E e); 

    void add(int index, E element) throws NoSuchElementException; 

    void clear(); 

    boolean contains(E element); 

    E get(int index) throws NoSuchElementException;

    E set(int index, E element) throws NoSuchElementException; 

    int indexOf(E element); 

    boolean isEmpty();

    Iterator<E> iterator();

    ListIterator<E> listIterator() throws UnsupportedOperationException; 
 
    E remove(int index) throws NoSuchElementException; 

    boolean remove(E e); 

    int size();
}
