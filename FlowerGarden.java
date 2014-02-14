package topcoder;



public class FlowerGarden {
	public static int [] getOrdering ( int []heigth, int [] bloom, int [] wilt ){

		//TODO: Validate all entries are the same length, and no nulls. Also, give obvious answer if there is only one element.

		int [] result = new int [ heigth.length ];

		boolean [] used = new boolean [ heigth.length ];

		for ( int i = 0; i < result.length; i++ ){
			//Let's calculate the i-th entry.
			int currentMax = -1;
			int currentMaxPos = -1;

			//First step is to go over the height array, and look into each one of the unused entries...
			for ( int j = 0; j < heigth.length; j++ ){
				if ( !used [ j ] && heigth [ j ] > currentMax){ // <- Small improvement to prevent unnecessary iterations.
					//...And compare it against all the (unused) others, just to see if it's being blocked.
					boolean blocked = false;
					for ( int k = 0; k < heigth.length && !blocked; k++ ){
						if ( !used [ k ] ){
							if ( heigth [ j ] > heigth [ k ] ) {
								if ( wilt [ k ] >= bloom [ j ] && bloom [ k ] <= wilt [ j ]){ 
									blocked=true;	//It's been blocked. Break from this "k" for and go to the next entry in the "j" for.
								}
							}
						}
					}	
					if ( !blocked ){
						//Great! We have a candidate.
						if ( heigth [ j ] > currentMax ){
							//This one is better!
							currentMax = heigth [ j ];
							currentMaxPos = j;
						}
					}
				}
			}
			result [ i ] = currentMax;
			used [ currentMaxPos ] = true;
		}

		return result;
	}

	public static void main ( String [] args ){
		int [] heigth = { 2, 3, 4, 5, 7};
		int [] bloom = { 1, 1, 2, 4, 13 };
		int [] wilt = { 5, 2, 3, 10, 20 };

		int [] result = getOrdering ( heigth , bloom, wilt );

		for ( int i = 0; i < result.length - 1; i++){
			System.out.print ( result [ i ] + ", ");
		}
		System.out.println ( result.length - 1 );
	}
}


