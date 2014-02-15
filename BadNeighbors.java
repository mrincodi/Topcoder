package dp;

public class BadNeighbors {

	
	public static int maxDonations(int[] donations){
		if ( donations == null ) return -1;
		if ( donations.length == 1 ) return donations [ 0 ];
		if ( donations.length == 2 ) return Math.max(donations [ 0 ], donations [ 1 ]);
		
		//Array S will have best (non consecutive) sum for all values
		//Array S1 will have best (non consecutive) sum for all values, except the first one (S1[0]=0) !
		
		int [] S = new int [ donations.length ];
		int [] S1 = new int [ donations.length ];
		
		S[0]=donations [ 0 ];
		S[1]=donations [ 1 ];
		
		S1[0]=0;
		S1[1]=donations [ 1 ];

		for ( int i = 2; i < donations.length - 1; i++){
			S[i]=Math.max(S[i-1], S[i-2] + donations[i]);
			S1[i]=Math.max(S1[i-1], S1[i-2] + donations[i]);
		}

		//Final value will be the max between 
		//   (for the array that considers all values, best answer until one less the last one) and 
		//   (for the array that considers all values except the first one, 
		//		best answer until two less the final one, plus the final one).
		int valorFinal = Math.max(S[donations.length - 2], S1[donations.length - 3] + donations[donations.length - 1]); 
		
		return valorFinal;
	}
	
	public static void main(String[] args) {
		int [] A =      { 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,  
				  6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
				  52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 };
		System.out.println ( maxDonations ( A ));
	}

}
