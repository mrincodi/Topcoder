package topcoder;

public class AvoidRoads {

	
	
	
	
	/**
	 * Returns number of ways of getting to position (width, height) on the grid, without going through the "bad" lines.
	 * @param width
	 * @param height
	 * @param bad
	 * @return
	 */
	public static long numWays(int width, int height, String[] bad ){
		
		//TODO: Check that width and height are non-negative values, and smaller than (MAX_VALUE - 1).
		
		if ( bad == null ) bad = new String [0];
		else if ( bad [ 0 ] == "") bad = new String [0];
		
		//TODO: Let's start without bad streets, and then do this:
		//First, let's just make a quick hashMap for the "bad" streets.
		// It will have has as the key the destination, and as value a set with the origins
		
		String theCase = new String ();

		Integer [] key = new Integer [2];
		char c;
		
		java.util.HashMap <String, java.util.HashSet <Character> > h = new java.util.HashMap <String, java.util.HashSet <Character> > ();

		
		for ( int i = 0; i < bad.length; i++ ){
			
			Integer [] point1 = new Integer [ 2 ];
			Integer [] point2 = new Integer [ 2 ];
			
			theCase = bad [ i ];
			String [] numberLettersArray = theCase.split(" ");
			
			//TODO: if ( numberLettersArray.length != 4 ) throw an exception!
			point1 [ 0 ] = Integer.parseInt(numberLettersArray [ 0 ]);
			point1 [ 1 ] = Integer.parseInt(numberLettersArray [ 1 ]);
			point2 [ 0 ] = Integer.parseInt(numberLettersArray [ 2 ]);
			point2 [ 1 ] = Integer.parseInt(numberLettersArray [ 3 ]);

			//And now let's find out which one is the key and which one the value.
			//TODO: Check that the difference between them is actually one, that there are no negative numbers, etc.
			if ( point1 [ 0 ] < point2 [ 0 ] || point1 [ 1 ] < point2 [ 1 ]){//TODO: and supposing this difference is only "1", and the other numbers stay the same...
				key =  point2;
			}
			else {
				key =  point1;
			}
			
			//And now, let's find if it's horizontal or vertical.
			if ( point1 [ 0 ] != point2 [ 0 ] ){
				c= 'H';
			}
			else {
				c = 'V';
			}
			
			//Finally, let's add this to the hashMap.
			java.util.HashSet<Character> set = new java.util.HashSet<Character> ();
			String keyString = new String ();
			keyString = key[0] + "," + key [ 1 ];

			if ( !h.containsKey(keyString)){
				set.add(c);
				h.put(keyString, set);
			}
			else {
				set = h.get(keyString);
				set.add(c);
			}
		}
		
		long [][] map = new long [width + 1][height + 1];
		
		map [0][0] = 1;
		
		long sumH = 0, sumV = 0;

		for ( int i = 0; i <= width; i++){
			for ( int j = 0; j <= height; j++){
				int [] point = { i, j };
				sumH=0;
				sumV=0;
				
				if ( i != 0 || j != 0){
					if ( i != 0) sumH = map [ i - 1 ][ j ];
					if ( j != 0) sumV = map [ i ][ j - 1 ];

					//But first, let's see if this point is in the hashMap!
					String pointString = point [ 0 ] + "," + point [ 1 ];
					if ( h.containsKey(pointString)){
						java.util.HashSet<Character> set = h.get(pointString);
						if ( set.contains('H') ) sumH = 0;
						if ( set.contains('V') ) sumV = 0;
					}
					
					map [ i ][ j ] = sumH + sumV;
				}
			}
		}

		return map [ width ][height];
		
	}
			
			
			
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String [] qq = {"4 9 4 8", "0 3 1 3", "3 5 2 5", "7 6 7 7", "7 7 6 7", "8 3 7 3", "1 2 1 1", "5 8 6 8", "8 1 8 2", "8 9 8 10", "4 0 3 0", "0 2 0 1", "7 3 7 4", "1 7 0 7", "0 2 0 3", "4 7 4 8", "1 7 2 7", "7 3 6 3", "5 8 5 7", "5 0 5 1", "4 2 4 3", "3 9 2 9", "5 8 4 8", "5 3 5 4", "0 5 0 6", "2 1 1 1", "1 7 1 8", "0 1 1 1", "7 1 7 2", "3 9 3 10", "8 2 7 2", "1 1 0 1", "6 6 5 6", "5 4 5 3", "0 6 1 6", "2 1 3 1", "1 1 1 2", "3 2 2 2", "8 9 9 9", "9 6 8 6", "3 7 4 7", "2 9 2 10", "4 6 4 5", "6 4 7 4", "4 3 4 4", "9 0 10 0", "6 8 6 7", "1 2 1 3", "5 2 6 2", "2 9 2 8"};
		System.out.println ( numWays ( 19, 100, qq));
	}
	
}
