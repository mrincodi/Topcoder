package topcoder;

public class ChessMetricTester {

	@org.testng.annotations.DataProvider ( name = "provideStrings ")
	public Object [][] provideStrings (){
		Object [][] strings = {
				{"3","0,0","1,0","1","1"}, 
				{"3","0,0","1,2","1","1"}, 
				{"3","0,0","2,2","1","0"}, 
				{"3","0,0","0,0","2","5"}, 
				{"100","0,0","0,99","50","243097320072600"}, 
				{"8","4,4","4,4","6","246460"}, 
				{"8","2,3","7,7","9","69232032"}, 
				{"3","0,0","2,2","20","979171322101760"}, 
				{"10","5,5","9,9","4","133"}, 
				{"13","3,7","11,5","4","4"}, 
				{"13","3,7","11,5","14","96417727286208"}, 
				{"100","0,0","50,50","35","480451056515520"}, 
				{"100","0,0","50,50","34","485001159390"}, 
				{"100","99,99","0,0","50","0"}, 
				{"100","99,99","0,0","50","0"}, 
				{"100","99,99","0,0","50","0"}, 
				{"3","0,2","2,0","1","0"}, 
				{"3","0,0","0,0","1","0"}
		};

		return strings;
	}

	@org.testng.annotations.Test ( dataProvider = "provideStrings")
	public void t (String sizeString, String startPairString, String endPairString, String numMovesString, String expectedString){

		int size = 		Integer.parseInt ( sizeString );
		int numMoves = 	Integer.parseInt ( numMovesString );
		long expected = 	Long.parseLong(expectedString);

		String [] startStringArray = startPairString.split(",");
		String [] endStringArray =    endPairString.split(",");

		int [] start = new int [ 2 ];
		int [] end = new int [ 2 ];

		start [ 0 ] = 	Integer.parseInt ( startStringArray [ 0 ]);
		start [ 1 ] = 	Integer.parseInt ( startStringArray [ 1 ]);
		end   [ 0 ]	= 	Integer.parseInt ( endStringArray [ 0 ]);
		end   [ 1 ] = 	Integer.parseInt ( endStringArray [ 1 ]);

		long result = ChessMetric.howMany(size, start, end, numMoves);

		org.testng.Assert.assertEquals(result, expected);

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
