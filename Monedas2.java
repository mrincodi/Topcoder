package dp;

import javax.swing.text.Position;

public class Monedas2 {

	public static class info {int numMonedas; java.util.HashMap <Integer, Integer> h;}

	/**
	 * Resuelve este problema:
	 * ÀCu‡l es el m’nimo nœmero de monedas, de entre un grupo de denominaciones, 
	 * que son necesarias para conseguir cierta suma?
	 * Adem‡s, imprime el nœmero de monedas de cada denominaci—n.
	 * 
	 * MŽtodo: Programaci—n din‡mica:
	 * Hago un arreglo desde 0 hasta suma, inclusive.
	 * A–a
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
		
		
		for ( int i = 0; i < m.length; i++){
			m [ i ] = new info ();
			m [ i ].numMonedas = Integer.MAX_VALUE;
			m [ i ].h = new java.util.HashMap < Integer, Integer > ();
		}
		
		m [ 0 ].numMonedas = 0;

		for ( int j = 0; j < denominaciones.length; j++){
			int d = denominaciones [ j ];
			for ( int i = 0; i < m.length - d; i++){
				if ( m [ i ].numMonedas < Integer.MAX_VALUE ){
					
					int newValue = i + d;
					int newNumMonedas =  m [ i ].numMonedas + 1;
					
					if ( newNumMonedas < m [ newValue ].numMonedas ){
						
						m[ newValue ].numMonedas = newNumMonedas;
						
						m[ newValue ].h = new java.util.HashMap < Integer, Integer > (m [ i ].h);
						int cantidadD = 1;
						if ( m[ newValue ].h.containsKey(d)) cantidadD = m[ newValue ].h.get(d) + 1;
						
						m[ newValue ].h.put(d, cantidadD);
					}
				}
			}
		}

		return m;
	}


	public static void main(String[] args) {

		int [] denominaciones = {3,7,19};
		int suma = 1000;

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
