package dp;

public class ZigZag {

	
	public static int longestZigZag(int[] sequence){
		
		class LongestSequencePair {
			int goingUp;
			int goingDown;
		}
		
		if ( sequence == null || sequence.length == 0 ) return -1;
		
		LongestSequencePair [] longestSequencePairs = new LongestSequencePair [sequence.length];
		
		longestSequencePairs [ 0 ] = new LongestSequencePair ();
		longestSequencePairs [ 0 ].goingUp = 1;
		longestSequencePairs [ 0 ].goingDown = 1;
		
		int maxUntilNow = 1;
		
		for ( int i = 1; i < sequence.length; i++){
			boolean stop = false;	//TODO: Future enhancement. Stop comparing 
									//if my longest sequence for this value is more than j.
			longestSequencePairs [ i ] = new LongestSequencePair ();

			longestSequencePairs [ i ].goingUp = 1;
			longestSequencePairs [ i ].goingDown = 1;
			for ( int j = i-1; j >=0 && !stop; j--){
				
				if ( sequence[j]<sequence[i]){
					//We're going up.
					if ( longestSequencePairs [ j ].goingDown + 1 > longestSequencePairs [ i ].goingUp){
						longestSequencePairs [ i ].goingUp = longestSequencePairs [ j ].goingDown + 1;
						maxUntilNow = Math.max(maxUntilNow, longestSequencePairs [ i ].goingUp);
					}
				}
				if ( sequence[j]>sequence[i]){
					//We're going down.
					if ( longestSequencePairs [ j ].goingUp + 1 > longestSequencePairs [ i ].goingDown){
						longestSequencePairs [ i ].goingDown = longestSequencePairs [ j ].goingUp + 1;
						maxUntilNow = Math.max(maxUntilNow, longestSequencePairs [ i ].goingDown);
					}
				}
			}
//			System.out.println (sequence [ i ] + ": " + 
//			longestSequencePairs [ i ].goingUp + " subiendo, " +
//			longestSequencePairs [ i ].goingDown + " bajando.");
		}
		
		for (int i = 0; i < longestSequencePairs.length; i++){
			
		}
		
		return maxUntilNow;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int [] sequence = { 374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 
				600, 947, 978, 46, 100, 953, 670, 862, 568, 188, 
				67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 
				477, 245, 413, 109, 659, 401, 483, 308, 609, 120, 
				249, 22, 176, 279, 23, 22, 617, 462, 459, 244 }
;
		//int [] sequence = { 1, 2, 3, 4, 2};
		
		System.out.println (longestZigZag(sequence));

	}

}
