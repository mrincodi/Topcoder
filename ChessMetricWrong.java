package topcoder;

public class ChessMetricWrong {




	public static long howMany(int size, int[] start, int[] end, int numMoves) {

		//TODO: Check that start and end are not null, that they have size = 2, 
		//that their values are non-negatives smaller than size - 1.
		@SuppressWarnings("unchecked")
		java.util.HashMap <Integer, Long> [] [] board = (java.util.HashMap <Integer, Long>[][]) new java.util.HashMap [size] [size] ;

		java.util.HashMap <String, java.util.LinkedHashSet < String > > neighbors = makeHashOfNeighbors ( size );

		for ( int i = 0; i < size; i++ ){
			for ( int j = 0; j < size; j++ ){
				board [ i ][ j ] = new java.util.HashMap <Integer, Long> ();
			}
		}

		int initialX = start [ 0 ];
		int initialY = start [ 1 ];
		int finalX   = end   [ 0 ];
		int finalY   = end   [ 0 ];

		//The only initialized position is [ initialX ][ initialY ]
		board [ initialX ][ initialY ].put ( 0, Long.valueOf(1));

		for ( int iter = 0; iter < numMoves; iter++) {
			if ( iter == 1 ) board [ 0 ][ 0 ].remove(0);	//??
			for ( int i = 0; i < size; i++ ){
				for ( int j = 0; j < size; j++ ){

					java.util.HashMap <Integer, Long> mySteps = board [ i ][ j ];

					//Let's check all the positions around, and gather their info.
					//First, let's get the positions around.
					//TODO: This simple method. Actually, this is a hash to be made before starting this cycle.
					java.util.Set <String> myNeighbors = neighbors.get(makePairString(i,j));

					//Let's go over all positions.
					for ( String currentPosString: myNeighbors){

						int [] positions = getValues ( currentPosString );
						int posX = positions  [ 0 ];
						int posY = positions  [ 1 ];

						java.util.HashMap <Integer, Long> neighborSteps = board [ posX ][ posY ];

						for ( Integer numSteps: neighborSteps.keySet() ){
							//Add the current values to the ones I have in my position.
							if ( numSteps < numMoves) { //No need to keep track for paths with more than numMoves.
								int newNumSteps = numSteps + 1;
								Long addedPaths  = neighborSteps.get ( numSteps );

								if ( !mySteps.containsKey ( newNumSteps )){
									mySteps.put ( newNumSteps, Long.valueOf(0) );
								}

								mySteps.put ( newNumSteps, mySteps.get ( newNumSteps ) + addedPaths );
							}
						}
					}
				}
			}
		}
		
		if ( board [ finalX ] [ finalY ].containsKey(numMoves))
			return board [ finalX ] [ finalY ].get ( numMoves);
		else
			return 0;

	}

	public static java.util.HashMap <String, java.util.LinkedHashSet < String > > makeHashOfNeighbors ( int n ){

		java.util.HashMap <String, java.util.LinkedHashSet < String > > result = new java.util.HashMap <String, java.util.LinkedHashSet < String > > ();

		for ( int i = 0; i < n; i++ ){
			for ( int j = 0; j < n; j++ ){
				java.util.LinkedHashSet < String > currentSetForPos = new java.util.LinkedHashSet < String > ();
				//Let's do the entries for all the positions, 2 steps "around" the current position.

				for ( int k = i - 2 ; k <= i + 2; k++ ){
					for ( int l = j - 2 ; l <= j + 2; l++ ){
						boolean add = true;

						if ( 
								//The current position
								( k == i && l == j ) ||	
								//Illegal positions
								( k < 0 || k >= n || l < 0 || l >= n ) ||
								//Corner positions.
								( Math.abs ( k - i ) == 2 && Math.abs ( l - j ) == 2) ||
								//Middle square positions.
								( k == i && Math.abs ( l - j ) == 2) ||
								( l == j && Math.abs ( k - i ) == 2) ) {
							add = false;
						}

						if ( add ){ 
							currentSetForPos.add ( makePairString (k,l));
						}
					}
				}
				result.put(makePairString (i,j), currentSetForPos);
			}
		}
		return result;
	}


	public static String makePairString ( int x, int y ){
		return x + "," + y;
	}

	public static int [] getValues ( String s ){
		//TODO: Validate that s has the right format a,b...
		int [] result = new int [2];
		String [] sResult = s.split (",");
		result [ 0 ] = Integer.parseInt ( sResult [ 0 ] );
		result [ 1 ] = Integer.parseInt ( sResult [ 1 ] );

		return result;

	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int [] start = {0,0};
		int [] end = {2,2};

		long result = howMany ( 3, start, end, 2);

		System.out.println ( result );

	}

}
