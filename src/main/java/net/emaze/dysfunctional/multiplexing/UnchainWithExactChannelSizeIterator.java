package net.emaze.dysfunctional.multiplexing;

import java.util.Collection;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Supplier;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import java.util.Optional;
import net.emaze.dysfunctional.options.MaybeIterator;

/**
 * squared
 *
 * @param <C>
 * @param <T>
 * @author rferranti
 */
public class UnchainWithExactChannelSizeIterator<C extends Collection<Optional<T>>, T> extends ReadOnlyIterator<C> {

    private final Iterator<Optional<T>> iterator;
    private final Supplier<Optional<Integer>> channelsSizesProvider;
    private final Supplier<C> channelProvider;

    public UnchainWithExactChannelSizeIterator(Supplier<Optional<Integer>> channelsSizesProvider, Iterator<T> iterator, Supplier<C> channelProvider) {
        dbc.precondition(channelsSizesProvider != null, "cannot build a UnchainWithExactChannelSizeIterator with channelsSizesProvider < 1");
        dbc.precondition(iterator != null, "cannot build a UnchainWithExactChannelSizeIterator with a null iterator");
        dbc.precondition(channelProvider != null, "cannot build a UnchainWithExactChannelSizeIterator with a null channelProvider");
        this.iterator = new MaybeIterator<T>(iterator);
        this.channelsSizesProvider = channelsSizesProvider;
        this.channelProvider = channelProvider;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public C next() {
        final C out = channelProvider.get();
        final Optional<Integer> maybeChannelSize = channelsSizesProvider.get();
        dbc.state(maybeChannelSize.isPresent(), "unexpected channel size request (provider returned Nothing)");
        final int channelSize = maybeChannelSize.get();
        for (int i = 0; i != channelSize; ++i) {
            out.add(iterator.next());
        }
        return out;
    }
}
