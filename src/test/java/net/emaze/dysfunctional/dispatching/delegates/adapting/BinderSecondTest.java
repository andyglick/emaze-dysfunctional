package net.emaze.dysfunctional.dispatching.delegates.adapting;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.adapting.BinderSecond;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinderSecondTest {

    @Test(expected=IllegalArgumentException.class)
    public void creatingBinderSecondWithNullPredicateYieldsException() {
        new BinderSecond<String, String, String>(null, "useless");
    }

    @Test
    public void secondParamIsCorrectlyBound() {
        Delegate<String,String> delegate = new BinderSecond<String, String, String>(new ConcatenateString(), "bound");
        String got = delegate.perform("passed");
        Assert.assertEquals("passedbound", got);
    }

}