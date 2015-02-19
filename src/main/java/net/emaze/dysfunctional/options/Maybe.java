package net.emaze.dysfunctional.options;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;
import net.emaze.dysfunctional.iterations.EmptyIterator;
import net.emaze.dysfunctional.iterations.SingletonIterator;

/**
 * Holds an optional value.
 *
 * @param <E> the value type
 * @author rferranti
 */
public class Maybe<E> implements Iterable<E> {

    private final E element;
    private final boolean hasValue;

    public Maybe(E element, boolean hasValue) {
        this.element = element;
        this.hasValue = hasValue;
    }

    public boolean hasValue() {
        return hasValue;
    }

    public E value() {
        dbc.state(hasValue, "fetching value from nothing");
        return element;
    }

    public <T> Maybe<T> fmap(Function<E, T> delegate) {
        dbc.precondition(delegate != null, "cannot perform fmap with a null delegate");
        if (hasValue) {
            return Maybe.just(delegate.apply(element));
        }
        return Maybe.nothing();
    }

    public <T> T fold(Supplier<T> ifNothing, Function<E, T> ifJust) {
        dbc.precondition(ifJust != null, "cannot perform fold with a null delegate");
        return hasValue ? ifJust.apply(element) : ifNothing.get();
    }

    public <T> Either<T, E> either(Supplier<T> nothing) {
        if (hasValue) {
            return Either.right(element);
        }
        return Either.left(nothing.get());
    }

    public E orElse(E otherwise) {
        if (hasValue) {
            return element;
        }
        return otherwise;
    }

    public Optional<E> optional() {
        if (hasValue) {
            return Optional.ofNullable(element);
        }
        return Optional.empty();
    }

    public Maybe<E> orElse(Maybe<E> otherwise) {
        if (hasValue) {
            return this;
        }
        return otherwise;
    }
    private static Maybe<Object> NOTHING = new Maybe<Object>(null, false);

    public static <E> Maybe<E> nothing() {
        return (Maybe<E>) NOTHING;
    }

    public static <E> Maybe<E> just(E element) {
        return new Maybe<E>(element, true);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.hasValue).
                append(this.element).
                toHashCode();
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof Maybe == false) {
            return false;
        }
        final Maybe<E> other = (Maybe<E>) rhs;
        return new EqualsBuilder().append(this.hasValue, other.hasValue).
                append(this.element, other.element).
                isEquals();
    }

    @Override
    public String toString() {
        if (!hasValue) {
            return "Nothing";
        }
        return String.format("Just %s", element);
    }

    @Override
    public Iterator<E> iterator() {
        if (!hasValue) {
            return new EmptyIterator<E>();
        }
        return new SingletonIterator<E>(element);
    }
}
