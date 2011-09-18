package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;

/**
 *
 * @param <T1>
 * @param <T2>
 * @author rferranti
 */
public class BinaryDelegateToBinaryPredicate<T1, T2> implements BinaryPredicate<T1, T2> {

    private final BinaryDelegate<Boolean, T1, T2> adapted;

    public BinaryDelegateToBinaryPredicate(BinaryDelegate<Boolean, T1, T2> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null delegate");
        this.adapted = adapted;
    }

    @Override
    public boolean accept(T1 first, T2 second) {
        return adapted.perform(first, second);
    }
}