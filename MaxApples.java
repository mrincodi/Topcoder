package topcoder;

public class MaxApples {

	/**
	 * A table composed of N x M cells, 
	 * each having a certain quantity of apples, is given. 
	 * You start from the upper-left corner. 
	 * At each step you can go down or right one cell. 
	 * 
	 * Find the maximum number of apples you can collect. 
	 * 
	 * From: "Dynamic Programming: From novice to advanced" 
	 * http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=dynProg
	 * 
	 * @param garden
	 * @return Maximum number of apples collected
	 */

	public static int maxApples ( int [] [] garden ){

		//TODO: Check that garden is not null, garden.length > 0, garden [0].length > 0; 
		//TODO: Give obvious response if garden.length = 1 and garden[0].length = 1; 

		int [] [] gardenCollect = new int [garden.length][garden[0].length];
		for ( int i = 0; i < garden.length;i++){
			for ( int j = 0; j < garden[i].length;j++){
				int add = 0;
				if ( i == 0 && j == 0 ){
					add = 0;
				}
				else if ( i == 0 ){
					add = gardenCollect [ i ][ j - 1 ]; 
				}
				else if ( j == 0 ){
					add = gardenCollect [ i - 1 ][ j ]; 
				}
				else {
					add = Math.max(gardenCollect [ i ][ j - 1 ],  gardenCollect [ i - 1 ][ j ]);
				}

				gardenCollect [ i ][ j ] = garden [ i ] [ j ] + add;
			}
		}

		return gardenCollect [ garden.length - 1 ][ garden[0].length - 1 ] ;

	}
	public static void main(String[] args) {

		int [] [] garden = {
				{5,4,1,2},
				{9,8,6,3},
				{5,0,9,1},
				{1,1,2,3}
		};
		
		int max = maxApples (garden );
		
		System.out.println ( max );
	}
}
