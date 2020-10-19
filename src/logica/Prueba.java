package logica;

import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {
		ArbolEneario ae= new ArbolEneario();
		Scanner text = new Scanner(System.in);
		while(true) {
			System.out.println("Texto:");
			String intext= text.nextLine();
			if(intext.equals("1")) {
				System.out.println(intext);
				break;
			}
			
			ae.addPalabra(intext, intext);
			System.out.println(ae.raiz.numNodoFinales());
		}
		
		
	}

}
