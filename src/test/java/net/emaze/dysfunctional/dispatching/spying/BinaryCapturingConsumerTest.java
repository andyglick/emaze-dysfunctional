package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryCapturingConsumerTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullActionYieldsException() {
        new BinaryCapturingConsumer<O, O>(null, Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullFirstParamBoxYieldsException() {
        new BinaryCapturingConsumer<O, O>(new BinaryNoop<O, O>(), null, Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullSecondParamBoxYieldsException() {
        new BinaryCapturingConsumer<O, O>(new BinaryNoop<O, O>(), Box.<O>empty(), null);
    }

    @Test
    public void firstParametersIsCaptured() {
        final Box<O> first = Box.empty();
        final Box<O> second = Box.empty();
        final BinaryCapturingConsumer<O, O> capturer = new BinaryCapturingConsumer<O, O>(new BinaryNoop<O, O>(), first, second);
        capturer.accept(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ONE, first.getContent());
    }

    @Test
    public void secondParameterIsCaptured() {
        final Box<O> first = Box.empty();
        final Box<O> second = Box.empty();
        final BinaryCapturingConsumer<O, O> capturer = new BinaryCapturingConsumer<O, O>(new BinaryNoop<O, O>(), first, second);
        capturer.accept(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ANOTHER, second.getContent());
    }
}
