package dp;

public class Monedas {

	/**
	 * Resuelve este problema:
	 * ÀCu‡l es el m’nimo nœmero de monedas, de entre un grupo de denominaciones, 
	 * que son necesarias para conseguir cierta suma?
	 * @param denominaciones
	 * @param suma
	 * @return
	 */
	public static int minMonedas ( int [] denominaciones, int suma ){
		
		if  ( denominaciones == null || denominaciones.length == 0 ) return -1;
		
		int [] m = new int [ suma + 1 ];	//m [ 0 ] is 0 by default.
		
		for ( int i = 1; i < suma + 1; i++ ){
			m [ i ] = Integer.MAX_VALUE;
			for ( int d = 0; d < denominaciones.length; d++ ){
				if ( i - denominaciones [ d ] >= 0 ){
					m [ i ] = Math.min ( m [ i ], m [ i - denominaciones [ d ] ] + 1 );
				}
			}
		}
		
		return m [ suma ];
	}
	
	
	public static void main(String[] args) {

	int [] denominaciones = { 1,5,7,10 };
	int suma = 4479;
	System.out.println ( minMonedas(denominaciones, suma));
	}

}
