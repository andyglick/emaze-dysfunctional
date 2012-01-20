package net.emaze.dysfunctional.casts;

import net.emaze.dysfunctional.Casts;
import junit.framework.Assert;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CastsTest {

    public static class A {
    }

    public static class B extends A {
    }

    @Test
    public void canUpcastUsingWiden() {
        final A a = new B();
        final B b = Casts.widen(a);
        Assert.assertNotNull(b);
    }

    @Test
    public void canDowncastUsingNarrow() {
        final B b = new B();
        final A a = Casts.narrow(b);
        Assert.assertNotNull(a);
    }

    @Test
    public void canGetWidener() {
        final A a = new B();
        final Delegate<B, A> widener = Casts.widen();
        Assert.assertNotNull(widener.perform(a));
    }

    @Test
    public void canGetNarrower() {
        final B b = new B();
        final Delegate<A, B> narrower = Casts.narrow();
        final A got = narrower.perform(b);
        Assert.assertNotNull(got);
    }

    @Test
    public void facadeIsNotFinal() {
        new Casts() {
        };
    }
}
