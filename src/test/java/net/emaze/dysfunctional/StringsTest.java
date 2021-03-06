package net.emaze.dysfunctional;

import java.util.Arrays;
import java.util.Iterator;
import junit.framework.Assert;
import net.emaze.dysfunctional.iterations.ConstantIterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    StringsTest.Concat.class,
    StringsTest.Join.class,
    StringsTest.Interpose.class,
    StringsTest.Repeat.class,
    StringsTest.Facade.class
})
public class StringsTest {

    public static class Concat {

        @Test
        public void canConcatIterable() {
            final Iterable<String> values = Arrays.asList("1", "2", "3", "4");
            final String output = Strings.concat(values);
            Assert.assertEquals("1234", output);
        }

        @Test
        public void canConcatArray() {
            final String output = Strings.concat("1", "2", "3", "4");
            Assert.assertEquals("1234", output);
        }

        @Test
        public void canConcatEmptyIterator() {
            final Iterator<String> values = Arrays.<String>asList().iterator();
            final String output = Strings.concat(values);
            Assert.assertEquals("", output);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotConcatANullIterator() {
            final Iterator<String> nullIterator = null;
            Strings.concat(nullIterator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotConcatANullIterable() {
            final Iterable<String> nullIterable = null;
            Strings.concat(nullIterable);
        }
    }

    public static class Join {

        @Test
        public void canJoinIntegers() {
            final Iterator<Integer> values = Arrays.asList(1, 2, 3, 4).iterator();
            final String output = Strings.join(values);
            Assert.assertEquals("1234", output);
        }

        @Test
        public void canJoinIterable() {
            final Iterable<Integer> values = Arrays.asList(1, 2, 3, 4);
            final String output = Strings.join(values);
            Assert.assertEquals("1234", output);
        }

        @Test
        public void canJoinArray() {
            final Integer[] values = {1, 2, 3, 4};
            final String output = Strings.join(values);
            Assert.assertEquals("1234", output);
        }

        @Test
        public void canJoinEmptyIterator() {
            final Iterator<Object> values = Arrays.asList().iterator();
            final String output = Strings.join(values);
            Assert.assertEquals("", output);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotJoinANullIterator() {
            final Iterator<Object> nullIterator = null;
            Strings.join(nullIterator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotJoinANullIterable() {
            final Iterable<Object> nullIterable = null;
            Strings.join(nullIterable);
        }
    }

    public static class Interpose {

        @Test
        public void canInterposeIteratorIntegers() {
            final Iterator<Integer> values = Arrays.asList(1, 2, 3, 4, 5).iterator();
            final String output = Strings.interpose(values, 0);
            Assert.assertEquals("102030405", output);
        }

        @Test
        public void canInterposeIterable() {
            final Iterable<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
            final String output = Strings.interpose(values, 0);
            Assert.assertEquals("102030405", output);
        }

        @Test
        public void canInterposeArray() {
            final Integer[] values = {1, 2, 3, 4, 5};
            final String output = Strings.interpose(values, 0);
            Assert.assertEquals("102030405", output);
        }

        @Test
        public void canInterposeIterableWithMultipleSeparators() {
            final Iterable<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
            final Iterator<String> separators = Arrays.asList("-", "+", "-", "+").iterator();
            final String output = Strings.interpose(values, separators);
            Assert.assertEquals("1-2+3-4+5", output);
        }

        @Test
        public void canInterposeIteratorWithMultipleSeparators() {
            final Iterator<Integer> values = Arrays.asList(1, 2, 3, 4, 5).iterator();
            final Iterator<String> separators = Arrays.asList("-", "+", "-", "+").iterator();
            final String output = Strings.interpose(values, separators);
            Assert.assertEquals("1-2+3-4+5", output);
        }

        @Test
        public void canInterposeArrayWithMultipleSeparators() {
            final Integer[] values = {1, 2, 3, 4, 5};
            final Iterator<String> separators = Arrays.asList("-", "+", "-", "+").iterator();
            final String output = Strings.interpose(values, separators);
            Assert.assertEquals("1-2+3-4+5", output);
        }

        @Test(expected = IllegalArgumentException.class)
        public void interposingNullValuesIteratorYieldsException() {
            final Iterator<Integer> iterator = null;
            Strings.interpose(iterator, 0);
        }

        @Test(expected = IllegalArgumentException.class)
        public void interposingNullValuesIterableYieldsException() {
            final Iterable<Integer> iterable = null;
            Strings.interpose(iterable, 0);
        }

        @Test(expected = IllegalArgumentException.class)
        public void interposingNullValuesIterableWithSeparatorsYieldsException() {
            final Iterable<Integer> iterable = null;
            Strings.interpose(iterable, new ConstantIterator<Integer>(0));
        }

        @Test(expected = IllegalArgumentException.class)
        public void interposingNullValuesIteratorWithSeparatorsYieldsException() {
            final Iterator<Integer> iterator = null;
            Strings.interpose(iterator, new ConstantIterator<Integer>(0));
        }

        @Test(expected = IllegalArgumentException.class)
        public void interposingNullSeparatorYieldsException() {
            Strings.interpose(Arrays.<String>asList().iterator(), null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void interposingNullSeparatorsYieldsException() {
            Strings.interpose(Arrays.<String>asList().iterator(), (Iterator<String>) null);
        }
    }

    public static class Repeat {

        @Test
        public void canRepeatAChar() {
            final String got = Strings.repeat(' ', 3);
            Assert.assertEquals("   ", got);
        }

        @Test
        public void canRepeatAString() {
            final String got = Strings.repeat("Na", 3);
            Assert.assertEquals("NaNaNa", got);
        }

        @Test
        public void canRepeatAnEmptyString() {
            final String got = Strings.repeat("", 3);
            Assert.assertEquals("", got);
        }

        @Test(expected = IllegalArgumentException.class)
        public void nullSourceYieldsExcpetion() {
            Strings.repeat(null, 2);
        }

        @Test(expected = IllegalArgumentException.class)
        public void negativeTimesYieldsExceptionWithString() {
            Strings.repeat("A", -1);
        }

        @Test(expected = IllegalArgumentException.class)
        public void negativeTimesYieldsExceptionWithChar() {
            Strings.repeat('A', -1);
        }
    }

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Strings() {
            };
        }
    }
}
