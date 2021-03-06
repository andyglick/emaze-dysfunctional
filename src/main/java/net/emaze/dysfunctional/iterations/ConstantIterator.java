package net.emaze.dysfunctional.iterations;

/**
 * An infinite Iterator always yielding the same value.
 *
 * @param <T> the element type
 * @author rferranti
 */
public class ConstantIterator<T> extends ReadOnlyIterator<T> {

    private final T value;

    public ConstantIterator(T value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        return value;
    }
}
