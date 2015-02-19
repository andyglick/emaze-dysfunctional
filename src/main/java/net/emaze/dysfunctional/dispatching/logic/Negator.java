package net.emaze.dysfunctional.dispatching.logic;

import java.util.function.Predicate;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Negates a predicate.
 *
 * @param <T> the element type parameter
 * @author rferranti
 */
public class Negator<T> implements Predicate<T> {

    private final Predicate<T> predicate;

    public Negator(Predicate<T> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null predicate");
        this.predicate = predicate;
    }

    /**
     * Tests the nested predicate and negates it.
     *
     * @param element the element used to test the predicate
     * @return true if the inner predicate returns false, false otherwise
     */
    @Override
    public boolean test(T element) {
        return !predicate.test(element);
    }
}
