package net.emaze.dysfunctional.delegates;

/**
 * A Binary functor with no return value
 * @author rferranti
 */
public interface BinaryAction<E1,E2> {
    /**
     * Performs an action for the given elements
     * @param former the former element
     * @param latter the latter element
     */
    public void perform(E1 former, E2 latter);
}