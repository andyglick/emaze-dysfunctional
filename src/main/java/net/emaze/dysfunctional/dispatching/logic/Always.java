package net.emaze.dysfunctional.dispatching.logic;

/**
 * a Null Unary Predicate always returning true
 * @param <E>
 * @author rferranti
 */
public class Always<E> implements Predicate<E>{

    /**
     * yields true.
     * @param element the ignored element
     * @return true. always.
     */
    @Override
    public boolean accept(E element) {
        return true;
    }

}