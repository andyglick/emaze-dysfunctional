package net.emaze.dysfunctional.filtering;

import net.emaze.dysfunctional.dispatching.logic.Predicate;
import java.util.Iterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Iterates on the iterator elements which the predicate matches
 * @param <E> 
 * @author rferranti
 */
public class FilteringIterator<E> implements Iterator<E> {

    private final Predicate<E> filter;
    private final Iterator<E> iterator;
    private Maybe<E> prefetched = Maybe.nothing();

    public FilteringIterator(Iterator<E> iterator, Predicate<E> filter) {
        dbc.precondition(iterator != null, "trying to create a FilteringIterator from a null iterator");
        dbc.precondition(filter != null, "trying to create a FilteringIterator from a null filter");
        this.iterator = iterator;
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        if (prefetched.hasValue()) {
            return true;
        }
        while (true) {
            if (!iterator.hasNext()) {
                return false;
            }
            final E element = iterator.next();
            if (filter.accept(element)) {
                prefetched = Maybe.just(element);
                return true;
            }
        }
    }

    @Override
    public E next() {
        if (prefetched.hasValue()) {
            final E element = prefetched.value();
            prefetched = Maybe.nothing();
            return element;
        }
        while (true) {
            final E element = iterator.next();
            if (filter.accept(element)) {
                return element;
            }
        }
    }

    @Override
    public void remove() {
        iterator.remove();
    }

}
