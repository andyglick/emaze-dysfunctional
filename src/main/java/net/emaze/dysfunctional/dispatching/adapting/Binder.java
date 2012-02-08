package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Unary to nullary delegate adapter. Adapting is performed by currying the
 * parameter of the adapted delegate.
 *
 * @param <R> the adapted delegate result type
 * @param <T> the adapted delegate only element type
 * @author rferranti
 */
public class Binder<R, T> implements Provider<R> {

    private final Delegate<R, T> adapted;
    private final T only;

    public Binder(Delegate<R, T> adaptee, T only) {
        dbc.precondition(adaptee != null, "cannot bind parameter of a null delegate");
        this.adapted = adaptee;
        this.only = only;
    }

    @Override
    public R provide() {
        return adapted.perform(only);
    }
}
