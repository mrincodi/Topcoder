package topcoder;

public class ChessMetric {




	public static long howMany(int size, int[] start, int[] end, int numMoves) {

		long [][] oldM = new long [ size ][ size ];
		long [][] newM = new long [ size ][ size ];
		
		java.util.HashMap <String, java.util.LinkedHashSet < String > > neighborsStringHash = makeHashOfNeighbors ( size );

		//Manually make the first "old" matrix.
		int initialX = start [ 0 ];
		int initialY = start [ 1 ];
		int finalX   = end   [ 0 ];
		int finalY   = end   [ 1 ];
		
		
		
		for ( String pair : neighborsStringHash.get ( makePairString( initialX, initialY ))){
			int [] values = getValues(pair);
			oldM [ values [ 0 ] ][ values [ 1 ] ] = 1;
		}
   	
   		//iter represents the previous iteration.
		for ( int iter = 1; iter < numMoves; iter++){
			//Let's go over the "new" matrix!
			for ( int i = 0; i < size; i++ ){
				for ( int j = 0; j < size; j++ ){
					long suma = 0;
					String pairString = makePairString ( i, j);
					
					for ( String neighbor : neighborsStringHash.get( pairString ) ){
						
						int [] neighborPosArray = getValues ( neighbor );  

						int posX = neighborPosArray [ 0 ];
						int posY = neighborPosArray [ 1 ];
						
						suma = suma + oldM [ posX ][ posY ];
					}
					newM [ i ][ j ] = suma;
				}
			}
			oldM = newM;
			newM = new long [ size ][ size ];
			
		}
		
		return oldM [ finalX ][finalY ];

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
		int [] end = {0,0};

		long result = howMany (100, start, end, 3);

		System.out.println ( result );

		for ( int i = 1; i <= 100; i++){
			result = howMany (8, start, end, i);
			System.out.println ( i + ":" + result );
		}
		
	}

}
