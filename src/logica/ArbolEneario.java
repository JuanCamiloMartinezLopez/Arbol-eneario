package logica;

public class ArbolEneario {
	
	public Nodo raiz;
	
	public ArbolEneario(){
		 this.raiz=new Nodo('?');
	}
	
	public void addPalabra(String palabra,String palabraT) {
		Nodo sig=this.raiz;
		for(int i=0;i<palabra.length();i++) {
			
			sig=sig.addHijo(palabra.charAt(i));
		}
		sig.addFinal(palabraT);
	}
	

}
