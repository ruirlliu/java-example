package example.jdk.mockjdk.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 * @author lr
 * @date 2021/3/12
 */
public class LinkedListMock<E> implements List<E> {

    int size = 0;

    private Node<E> head;

    private Node<E> tail;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        Node<E> t = tail;
        Node<E> newNode = new Node<>(t, e, null);
        tail = newNode;
        if (head == null) {
            head = newNode;
        } else {
            t.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (Node<E> n = head; n != null; n = n.next) {
            if (Objects.equals(o, n.e)) {
                Node<E> before = n.before;
                Node<E> next = n.next;
                if (before == null) {
                    head = next;
                } else if (next == null) {
                    tail = before;
                }

            }
        }
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            if (Objects.equals(node.e, o)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private static class Node<E> {
        private E e;

        private Node<E> before;
        private Node<E> next;
        Node(Node<E> before, E e, Node<E> after) {
            this.before = before;
            this.e = e;
            this.next = after;
        }

    }
}
