package net.emaze.dysfunctional.dispatching.composing;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;

/**
 * A composite binary predicate yielding true when the first predicate matches
 * (no further predicate is evaluated beyond the first returning true)
 *
 * @param <E1>
 * @param <E2>
 * @author rferranti
 */
public class FirstMatchingBinaryPredicate<E1, E2> implements BinaryPredicate<E1, E2> {

    private final Iterator<BinaryPredicate<E1, E2>> predicates;

    public FirstMatchingBinaryPredicate(Iterator<BinaryPredicate<E1, E2>> predicates) {
        dbc.precondition(predicates != null, "cannot create a FirstMatchingBinaryPredicate with a null iterator");
        this.predicates = predicates;
    }

    @Override
    public boolean accept(E1 first, E2 second) {
        while (predicates.hasNext()) {
            if (predicates.next().accept(first, second)) {
                return true;
            }
        }
        return false;
    }
}