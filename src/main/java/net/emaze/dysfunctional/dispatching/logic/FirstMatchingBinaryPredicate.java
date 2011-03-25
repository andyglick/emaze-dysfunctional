package net.emaze.dysfunctional.dispatching.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite binary predicate yielding true when the first predicate matches
 * (no further predicate is evaluated beyond the first returning true)
 * @param <E1>
 * @param <E2>
 * @author rferranti
 */
public class FirstMatchingBinaryPredicate<E1, E2> implements CompositeBinaryPredicate<E1, E2> {

    private final List<BinaryPredicate<E1, E2>> predicates = new ArrayList<BinaryPredicate<E1, E2>>();

    @Override
    public boolean accept(E1 first, E2 second) {
        for (BinaryPredicate<E1, E2> predicate : predicates) {
            if (predicate.accept(first, second)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(BinaryPredicate<E1, E2> aPredicate) {
        dbc.precondition(aPredicate != null, "trying to add a null predicate");
        predicates.add(aPredicate);
    }

    @Override
    public boolean remove(BinaryPredicate<E1, E2> aPredicate) {
        dbc.precondition(aPredicate != null, "trying to remove a null predicate");
        return predicates.remove(aPredicate);
    }

    @Override
    public void setFunctors(Collection<BinaryPredicate<E1, E2>> functors) {
        dbc.precondition(functors != null, "functors cannot be null");
        this.predicates.clear();
        this.predicates.addAll(functors);
    }
}
