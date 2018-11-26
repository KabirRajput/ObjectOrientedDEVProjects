package OOD3_Codility_Krypton_Kabir.Krypton_Kabir;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestMatrix {
	
	@Test 
	public void test_1_By_1_Matrix_returns_1_trailing_Zeros() {
		
		int A[][]= new int[][] {
			{0}
		};
		Solution solution = new Solution(A);
		
		int result = solution.findTrailZeros();
		
		assertEquals(1,result);
		
	}
	
	@Test 
	public void test_2_By_2_Matrix_returns_0_trailing_Zeros() {
		
		int A[][]= new int[][] {
			{0,0},
			{0,0}
		};
		Solution solution = new Solution(A);
		
		int result = solution.findTrailZeros();
		
		assertEquals(0,result);
		
	}

}
