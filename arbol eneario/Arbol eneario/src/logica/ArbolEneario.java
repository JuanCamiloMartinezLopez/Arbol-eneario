package logica;

import java.util.ArrayList;

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
	
       public void inOrden(Nodo nodo){
           if(nodo.hijos != null)
            System.out.println(nodo.hijos);
           inOrden(nodo.padre);
        } 
       public void preOrden(){
       
       } 
       public void posOrden(){
       
       } 

}
