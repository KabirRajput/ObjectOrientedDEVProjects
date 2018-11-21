package IntervalInterviewQuestion.interval;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IntevalTest {
	



	@Test
	public void test1_interval_getValue() {
		
		//arrange
		int[] a= {7,10};
		IntervalMerger mergeInterval = new IntervalMerger();

		//act
		mergeInterval.addInterval(a);
		mergeInterval.merge();
		List<int[]> result = mergeInterval.getResult();
		
		//assert
		assertArrayEquals(a, result.get(0));
		
	
	}

	@Test
	public void test2_Adding_2_interval_merges_without_overlapping_intervals() {
		
		//arrange
		int[] a= {7,10};
		int[] b= {11,12};
		IntervalMerger mergeInterval = new IntervalMerger();

		//act
		mergeInterval.addInterval(a);
		mergeInterval.addInterval(b);
		mergeInterval.merge();
		List<int[]> result = mergeInterval.getResult();
		
		//assert
		assertArrayEquals(b, result.get(1));

	}

	@Test
	public void test3_Adding_2_interval_merges_with_overlapping_intervals() {
		
		//arrange
		int[] a= {7,10};
		int[] b= {9,12};
		IntervalMerger mergeInterval = new IntervalMerger();

		//act
		mergeInterval.addInterval(a);
		mergeInterval.addInterval(b);
		mergeInterval.merge();
		List<int[]> result = mergeInterval.getResult();
		
		//assert
		assertArrayEquals(b, result.get(1));

	}
	
	

}
