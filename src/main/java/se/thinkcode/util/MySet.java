package se.thinkcode.util;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MySet<E> implements Set<E> {
    private final MyList<E> stuff;

    public MySet() {
        stuff = new MyList<>();
    }

    @Override
    public int size() {
        return stuff.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return stuff.contains(o);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return stuff.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public boolean add(E e) {
        if (!contains(e)) {
            return stuff.add(e);
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return stuff.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        // todo implement me
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void clear() {
        stuff.clear();
    }
}
