package net.emaze.dysfunctional.ranges;

import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class SortedNonOverlappingRangesTransformerTest {

    /**
     * |---| |---|
     */
    @Test
    public void canMergeTwoContiguousNonOverlappingRanges() {
        final DenseRange<Integer> a = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 1, 1);
        final DenseRange<Integer> b = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 2, 2);
        final List<DenseRange<Integer>> got = new SortedNonOverlappingRangesTransformer<Integer>(RangeMother.sequencer, RangeMother.comparator).perform(Arrays.asList(a, b));
        final List<DenseRange<Integer>> expected = Arrays.asList(RangeMother.r(1, 2));
        Assert.assertEquals(expected, got);
    }

    /**
     * |---| |---|
     */
    @Test
    public void canMergeTwoOverlappingRanges() {
        final DenseRange<Integer> a = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 1, 2);
        final DenseRange<Integer> b = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 2, 3);
        final List<DenseRange<Integer>> got = new SortedNonOverlappingRangesTransformer<Integer>(RangeMother.sequencer, RangeMother.comparator).perform(Arrays.asList(a, b));
        final List<DenseRange<Integer>> expected = Arrays.asList(RangeMother.r(1, 3));
        Assert.assertEquals(expected, got);
    }

    /**
     * |---| |-|
     */
    @Test
    public void canMergeTwoOverlappingRangesWhenLatterIsSubset() {
        final DenseRange<Integer> a = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 1, 2);
        final DenseRange<Integer> b = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 2, 2);
        final List<DenseRange<Integer>> got = new SortedNonOverlappingRangesTransformer<Integer>(RangeMother.sequencer, RangeMother.comparator).perform(Arrays.asList(a, b));
        final List<DenseRange<Integer>> expected = Arrays.asList(RangeMother.r(1, 2));
        Assert.assertEquals(expected, got);
    }

    /**
     * |-| |---|
     */
    @Test
    public void canMergeTwoOverlappingRangesWhenFormerIsSubset() {
        final DenseRange<Integer> a = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 1, 1);
        final DenseRange<Integer> b = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 1, 2);
        final List<DenseRange<Integer>> got = new SortedNonOverlappingRangesTransformer<Integer>(RangeMother.sequencer, RangeMother.comparator).perform(Arrays.asList(a, b));
        final List<DenseRange<Integer>> expected = Arrays.asList(RangeMother.r(1, 2));
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canMergeSameEmptyRanges() {
        final DenseRange<Integer> a = RangeMother.r(Endpoints.IncludeLeft, 0, 0);
        final DenseRange<Integer> b = RangeMother.r(Endpoints.IncludeLeft, 0, 0);
        final List<DenseRange<Integer>> got = new SortedNonOverlappingRangesTransformer<Integer>(RangeMother.sequencer, RangeMother.comparator).perform(Arrays.asList(a, b));
        final List<DenseRange<Integer>> expected = Arrays.asList(RangeMother.r(Endpoints.IncludeLeft, 0, 0));
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canMergeDifferentEmptyRanges() {
        final DenseRange<Integer> a = RangeMother.r(Endpoints.IncludeLeft, 0, 0);
        final DenseRange<Integer> b = RangeMother.r(Endpoints.IncludeLeft, 1, 1);
        final List<DenseRange<Integer>> got = new SortedNonOverlappingRangesTransformer<Integer>(RangeMother.sequencer, RangeMother.comparator).perform(Arrays.asList(a, b));
        final List<DenseRange<Integer>> expected = Arrays.asList(RangeMother.r(Endpoints.IncludeLeft, 0, 0));
        Assert.assertEquals(expected, got);
    }

    /**
     * |-|. .|-|
     */
    @Test
    public void nonOverlappingRangesNonCountiguousRangesAreNotMerged() {
        final DenseRange<Integer> a = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 1, 1);
        final DenseRange<Integer> b = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 3, 3);
        final List<DenseRange<Integer>> got = new SortedNonOverlappingRangesTransformer<Integer>(RangeMother.sequencer, RangeMother.comparator).perform(Arrays.asList(a, b));
        final List<DenseRange<Integer>> expected = Arrays.asList(RangeMother.r(1, 1), RangeMother.r(3, 3));
        Assert.assertEquals(expected, got);
    }

    @Test(expected = ClassCastException.class)
    public void callingErasureWithWrongTypeYieldsException() {
        Delegate d = new SortedNonOverlappingRangesTransformer<Integer>(RangeMother.sequencer, RangeMother.comparator);
        d.perform(new Object());
    }
}
