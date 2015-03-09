package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;

/**
 * Adapts a ternary function with Boolean result type to a ternary predicate.
 *
 * @param <T1> the adapted function first parameter type
 * @param <T2> the adapted function second parameter type
 * @param <T3> the adapted function third parameter type
 * @author rferranti
 */
public class TernaryFunctionToPredicate<T1, T2, T3> implements TriPredicate<T1, T2, T3> {

    private final TriFunction<T1, T2, T3, Boolean> adapted;

    public TernaryFunctionToPredicate(TriFunction<T1, T2, T3, Boolean> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null ternary function to ternary predicate");
        this.adapted = adaptee;
    }

    @Override
    public boolean test(T1 first, T2 second, T3 third) {
        return adapted.apply(first, second, third);
    }
}