package dp;

public class LongestSequence {

	public static int longestSequence ( int [] a ) {
		if ( a == null || a.length == 0 ) return -1;

		int [] currentLongest = new int [ a.length ];

		currentLongest [ 0 ] = 1;
		int result = 1;

		
		for ( int i = 1; i < currentLongest.length; i++ ){
			currentLongest [ i ] = 1;
			for ( int j = 0; j < i; j++){
				if ( a [ j ] <= a [ i ] )
					if ( currentLongest [ j ] + 1 > currentLongest [ i ] )
						currentLongest [ i ] = currentLongest [ j ] + 1;
			}
			result = Math.max( result, currentLongest [ i ] );
		}

		
		return result;
	}

	public static void main ( String [] args ){
		int [] seq = { 5,4,3,2,1,3,3,2};

		System.out.println  ( longestSequence ( seq ));
	}

}
