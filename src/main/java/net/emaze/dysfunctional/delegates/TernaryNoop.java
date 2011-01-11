package net.emaze.dysfunctional.delegates;

/**
 * A null ternary functor with no return value effectively doing nothing.
 * Even better than BinaryNoop<T1,T2>.
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @author rferranti
 */
public class TernaryNoop<T1, T2, T3> implements TernaryAction<T1, T2, T3> {

    @Override
    public void perform(T1 first, T2 second, T3 third) {
    }
}