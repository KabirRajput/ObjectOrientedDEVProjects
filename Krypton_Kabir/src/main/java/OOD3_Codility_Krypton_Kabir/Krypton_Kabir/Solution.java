package OOD3_Codility_Krypton_Kabir.Krypton_Kabir;

public class Solution {

	private int[][] A;
	private int count2 = 0, count5 = 0; 

	public Solution(int[][] a) {
		A = a;
	}


	public void findCount2_And_5(){

		for (int i = 0; i < A.length; i++) { 
			for (int j = 0; j < A.length; j++)
			{
				// count number of 2s in each element 
				while (A[i][j] % 2 == 0) { 
					A[i][j] = A[i][j] / 2; 
					count2++; 
				} 

				// count number of 5s in each element 
				while (A[i][j] % 5 == 0) { 
					A[i][j] = A[i][j] / 5; 
					count5++; 
				} 
			} 
		}
	}
	
	public int findTrailZeros() {
		int product = A[0][0];
		for(int i=0;i<A.length;i++) {

			product= product*A[i][i+1];

		}

		if(A[0][0]==0)
			return 1;
		return 0;
	}

}
