package dp;

import javax.swing.text.Position;

public class MonedasMŽtodo2 {

	public static class info {int numMonedas; java.util.HashMap <Integer, Integer> h;}

	/**
	 * Resuelve este problema:
	 * ÀCu‡l es el m’nimo nœmero de monedas, de entre un grupo de denominaciones, 
	 * que son necesarias para conseguir cierta suma?
	 * Adem‡s, imprime el nœmero de monedas de cada denominaci—n.
	 * 
	 * MŽtodo: Programaci—n din‡mica:
	 * para cada cantidad "i" toma todas las denominaciones "d" y calcula el menor de 
	 * ((menor nœmero de monedas para i - d ) + 1).
	 * El menor nœmero de monedas para i - d ya ha sido calculado previamente.
	 * 
	 * @param denominaciones
	 * @param suma
	 * @return
	 */
	public static info [] minMonedas ( int [] denominaciones, int suma ){

		//
		if  ( denominaciones == null || denominaciones.length == 0 ) return null;

		info [] m = new info [ suma + 1 ];	//m [ 0 ] is 0 by default.
		m [ 0 ] = new info ();
		m [ 0 ].numMonedas = 0;
		m [ 0 ].h = new java.util.HashMap < Integer, Integer > ();
		for ( int i = 1; i < suma + 1; i++ ){
			m [ i ] = new info ();
			m [ i ].numMonedas = Integer.MAX_VALUE;
			m [ i ].h = new java.util.HashMap < Integer, Integer > ();
			for ( int d = 0; d < denominaciones.length; d++ ){
				int denominaci—n = denominaciones [ d ];
				if ( i - denominaci—n >= 0 ){
					int actualMin = m [ i ].numMonedas;

					//We need to do the following.
					//Otherwise, posibleMin may be: Integer.MAX_VALUE + 1 = Integer.MIN_VALUE
					int posibleMin = Integer.MAX_VALUE;
					if ( m [ i - denominaci—n ].numMonedas < Integer.MAX_VALUE)  
						posibleMin = m [ i - denominaci—n ].numMonedas + 1;

					if ( posibleMin < actualMin){
						m [ i ].numMonedas = posibleMin;
						m [ i ].h = new java.util.HashMap < Integer, Integer > (m [ i - denominaci—n ].h);
						int nuevoNumDeDenominaci—n = 1;
						if ( m [ i ].h.containsKey(denominaci—n)) nuevoNumDeDenominaci—n=m [ i ].h.get(denominaci—n) + 1;
						m [ i ].h.put(denominaci—n,nuevoNumDeDenominaci—n);
					}

				}
			}
		}

		//return m [ suma ];
		return m;
	}


	public static void main(String[] args) {

		int [] denominaciones = { 13,17 };
		int suma = 420;

		//info m = minMonedas(denominaciones, suma);
		info [] mArray = minMonedas(denominaciones, suma);

		int i = 0;

		for (info m: mArray){
			java.util.HashMap < Integer, Integer > cantidades = m.h;
			System.out.println ("\nCantidad: "+ i++);
			if (cantidades.size()==0) System.out.println ("No es posible formar esa cantidad exacta.");
			for ( int key : cantidades.keySet()){
				System.out.println ( cantidades.get(key) + " moneda(s) de " + key );
			}
		}
	}

}
