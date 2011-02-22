package net.emaze.dysfunctional.strings.lexcasts;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IntegerParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void excedingMaxRadixYieldsException() {
        new IntegerParser(Character.MAX_RADIX + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lessThanMinRadixYieldsException() {
        new IntegerParser(Character.MIN_RADIX - 1);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void parsingNullStringYieldsException() {
        new IntegerParser(10).perform(null);
    }
    
    @Test(expected=NumberFormatException.class)
    public void parsingInvalidStringYieldsException() {
        new IntegerParser(10).perform("A");
    }
    
    @Test
    public void parsingValidStringYieldsValue() {
        final int got = new IntegerParser(10).perform("1");
        Assert.assertEquals(1, got);
    }
}