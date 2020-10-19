package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Nodo {
	private char valor;
	private ArrayList<Nodo> hijos;
	private String palabraTraducida;
	private Nodo padre;
	private boolean f;
	
	Nodo(char valor){
		this.valor=Character.toLowerCase(valor);
		this.f=false;
		this.hijos= new ArrayList<Nodo>();
	}
	
	Nodo(String palabraT){
		this.hijos= new ArrayList<Nodo>();
		this.valor='}';
		this.palabraTraducida=palabraT;
		this.f=true;
	}
	
	public int numNodoFinales() {
		int num=0;
		if(this.f==true) {
			num+=1;
		}else {
			for(Nodo nodo:this.hijos) {
				num+= nodo.numNodoFinales();
			}
		}
		return num;
	}
	
	public boolean esFinal() {
		return this.f;
	}
	
	public String getPalabraTraducida() {
		return palabraTraducida;
	}

	public char getValor() {
		return valor;
	}
	
	public Nodo hermanoMayor() {
		int i=this.padre.existe(this.valor);
		if(i>0) {
			return this.padre.getHijos().get(i-1);
		}
		return null;
	}
	
	public int profundidad() {
		int p=0;
		p=this.hijos.size();
		System.out.println("Nodo:"+this.valor+" hijos:"+this.hijos.size());
		if(p!=0) {
			
		}
		for(Nodo n:this.hijos) {
			p+=n.profundidad();
		}
		return p;
	}

	public void setValor(char valor) {
		this.valor=Character.toLowerCase(valor);
		this.valor = valor;
	}

	public ArrayList<Nodo> getHijos() {
		return hijos;
	}

	public Nodo addHijo(char valorhijo){
		if(Character.isLetter(valorhijo)) {
			Nodo nuevo=new Nodo(valorhijo);
			nuevo.setPadre(this);
			if(this.tieneHijos()) {
				int index=this.existe(valorhijo);
				if(index==-1) {
					this.hijos.add(nuevo);
					Collections.sort(this.hijos,Nodo.valorComparator);
					return nuevo;
				}else {
					return this.hijos.get(index);
				}
			}else {
				this.hijos.add(nuevo);
				return nuevo;
			}
		}
		return this;
	}
	
	public Nodo getPadre() {
		return padre;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public boolean tieneHijos() {
		return !this.hijos.isEmpty();
	}
	
	public int hermanoMenor() {
		int i=this.getPadre().existe(this.valor);
		int lasti=this.getPadre().hijos.size()-1;
		if(i==lasti) {
			return -1;
		}
		return i+1;
	}
	
	public int numHermanos() {
		return padre.hijos.size()-1;
	}
	
	public Nodo sigHermano() {
		if(this.hermanoMenor()!=-1) {
			return this.padre.hijos.get(this.hermanoMenor());
		}
		return null;
		
	}

	
	public void addFinal(String palabra) {
		Nodo Ultimo=new Nodo(palabra);
		Ultimo.setPadre(this);
		this.hijos.add(Ultimo);
	}
	
	public int existe(char valorhijo) {
		for(int i=0;i<this.hijos.size();i++) {
			if(hijos.get(i).getValor()==valorhijo) {
				return i;
			}
		}
		return -1;
	}

	public static Comparator<Nodo> valorComparator= new Comparator<Nodo>() {
		
		public int compare(Nodo n1, Nodo n2) {
			String valorNodo1= String.valueOf(n1.getValor());
			String valorNodo2= String.valueOf(n2.getValor());
			
			return valorNodo1.compareTo(valorNodo2);
		}
	};
	 
	
}
