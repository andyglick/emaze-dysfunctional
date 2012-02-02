package net.emaze.dysfunctional.dispatching.composing;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * A composite ternary predicate yielding true when the first predicate matches
 * (no further predicate is evaluated beyond the first returning true)
 *
 * @param <E1>
 * @param <E2>
 * @param <E3>
 * @author rferranti
 */
public class FirstMatchingTernaryPredicate<E1, E2, E3> implements TernaryPredicate<E1, E2, E3> {

    private final Iterator<TernaryPredicate<E1, E2, E3>> predicates;

    public FirstMatchingTernaryPredicate(Iterator<TernaryPredicate<E1, E2, E3>> predicates) {
        dbc.precondition(predicates != null, "cannot create a FirstMatchingTernaryPredicate with a null iterator");
        this.predicates = predicates;
    }

    @Override
    public boolean accept(E1 first, E2 second, E3 third) {
        while (predicates.hasNext()) {
            if (predicates.next().accept(first, second, third)) {
                return true;
            }
        }
        return false;
    }
}