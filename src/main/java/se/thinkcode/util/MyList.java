package se.thinkcode.util;

import java.util.*;

public class MyList<E> implements List<E> {
    private static class Data<E> {
        E value;
        Data<E> next;
    }

    private Data<E> root = null;

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        int count = 1;
        Data<E> current = root;
        while (current.next != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public boolean contains(Object o) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Data<E> current = root;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E val = current.value;
                current = current.next;
                return val;
            }
        };
    }


    @Override
    public Object[] toArray() {
        int s = size();
        Object[] arr = new Object[s];
        int index = 0;
        for (Object o : this) {
            arr[index] = o;
            index++;
        }
        return arr;
    }


    @Override
    public <T> T[] toArray(T[] a) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public boolean add(E e) {
        Data<E> data = new Data<>();
        data.value = e;
        data.next = null;
        if (root == null) {
            root = data;
            return true;
        }
        Data<E> current = root;
        while (current.next != null) {
            current = current.next;
        }
        current.next = data;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Data<E> current = root;
        if (root.value.equals(o)) {
            root = current.next;
            return true;
        }
        while (current.next != null) {
            if (current.next.value.equals(o)) {
                current.next = current.next.next;
            }
            current = current.next;
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void clear() {
// todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public E get(int index) {
        Object[] arr = toArray();
        return (E) arr[index];
    }

    @Override
    public E set(int index, E element) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void add(int index, E element) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public E remove(int index) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public int indexOf(Object o) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public int lastIndexOf(Object o) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }


    @Override
    public ListIterator<E> listIterator() {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }


    @Override
    public ListIterator<E> listIterator(int index) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }


    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }
}
