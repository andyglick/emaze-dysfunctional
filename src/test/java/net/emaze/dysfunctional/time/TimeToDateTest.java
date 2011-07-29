package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TimeToDateTest {

    private TimeToDate instance = new TimeToDate();

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotPassANullArgument() {
        instance.perform(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotPassAPairWithNullFirst() {
        instance.perform(Pair.of((Long) null, TimeUnit.DAYS));
    }

    @Test
    public void canConvertTimeToDate() {
        Assert.assertNotNull(instance.perform(Pair.of((long) 1, TimeUnit.DAYS)));
    }
}
