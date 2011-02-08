package net.emaze.dysfunctional.delegates;

/**
 *
 * @author rferranti
 */
public abstract class Adapters {

    /**
     * 
     * @param <T>
     * @param adaptee
     * @return
     */
    public static <T> ActionToDelegate<T> action2delegate(Action<T> adaptee) {
        return new ActionToDelegate<T>(adaptee);
    }

    /**
     * 
     * @param <T1>
     * @param <T2>
     * @param adaptee
     * @return
     */
    public static <T1, T2> BinaryActionToBinaryDelegate<T1, T2> action2delegate(BinaryAction<T1, T2> adaptee) {
        return new BinaryActionToBinaryDelegate<T1, T2>(adaptee);
    }

    /**
     * 
     * @param <T1>
     * @param <T2>
     * @param <T3>
     * @param adaptee
     * @return
     */
    public static <T1, T2, T3> TernaryActionToTernaryDelegate<T1, T2, T3> action2delegate(TernaryAction<T1, T2, T3> adaptee) {
        return new TernaryActionToTernaryDelegate<T1, T2, T3>(adaptee);
    }

    /**
     * 
     * @param <R>
     * @param <T1>
     * @param <T2>
     * @param delegate
     * @param first
     * @return
     */
    public static <R, T1, T2> Delegate<R, T2> bind1st(BinaryDelegate<R, T1, T2> delegate, T1 first) {
        return new BinderFirst<R, T1, T2>(delegate, first);
    }

    /**
     * 
     * @param <R>
     * @param <T1>
     * @param <T2>
     * @param <T3>
     * @param delegate
     * @param first
     * @return
     */
    public static <R, T1, T2, T3> BinaryDelegate<R, T2, T3> bind1st(TernaryDelegate<R, T1, T2, T3> delegate, T1 first) {
        return new BinderFirstOfThree<R, T1, T2, T3>(delegate, first);
    }

    /**
     * 
     * @param <R>
     * @param <T1>
     * @param <T2>
     * @param delegate
     * @param first
     * @return
     */
    public static <R, T1, T2> Delegate<R, T1> bind2nd(BinaryDelegate<R, T1, T2> delegate, T2 first) {
        return new BinderSecond<R, T1, T2>(delegate, first);
    }

    /**
     *
     * @param <R>
     * @param <T1>
     * @param <T2>
     * @param <T3>
     * @param delegate
     * @param first
     * @return
     */
    public static <R, T1, T2, T3> BinaryDelegate<R, T1, T3> bind2nd(TernaryDelegate<R, T1, T2, T3> delegate, T2 first) {
        return new BinderSecondOfThree<R, T1, T2, T3>(delegate, first);
    }

    /**
     * 
     * @param <R>
     * @param <T1>
     * @param <T2>
     * @param <T3>
     * @param delegate
     * @param first
     * @return
     */
    public static <R, T1, T2, T3> BinaryDelegate<R, T1, T2> bind3rd(TernaryDelegate<R, T1, T2, T3> delegate, T3 first) {
        return new BinderThird<R, T1, T2, T3>(delegate, first);
    }
}