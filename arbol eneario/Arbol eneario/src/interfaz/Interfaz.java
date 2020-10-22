package interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.util.ArrayList;


import logica.ArbolEneario;
import logica.Nodo;

public class Interfaz extends JFrame{
	public int flag;
	public int WinWidth = 700;
	public int WinHeight = 600;
	public int LROffset = 170;
	public int DownOffset = 50;
	public int nodeD = 26;
	public int levelOffset = 50;
	public JPanel control;
	public JPanel lienzo;
	public JLabel Titulo;
	public JButton Ingresar;
        public JButton diccionario;
	public JLabel labelPalabra;
	public JLabel labelPalabraTraducida;
	public JTextField IngresarPalabra;
	public JTextField IngresarPalabraTraducida;
	public Graphics graphics;
	public Graphics2D g;
	public ArbolEneario ArbolE;
        public ArrayList<String> palabras;
        public ArrayList<String> palabrasT;
	
	public Interfaz() {
		this.ArbolE= new ArbolEneario();
                this.palabras = new ArrayList<>();
                this.palabrasT = new ArrayList<>();
		this.setTitle("Arbol Eneario");
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize( WinWidth,WinHeight);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Titulo= new JLabel("ARBOL ENEARIO");
		Titulo.setForeground(Color.RED);
		Titulo.setFont(new Font("Monospaced", Font.BOLD, 36));
		Titulo.setBounds((this.WinWidth/2)-140, 20, 350, 50);
		this.getContentPane().add(Titulo);
		
		control = new JPanel();
		control.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		control.setBounds((this.WinWidth/2)-((int) (this.WinWidth*0.60)/2), 80,(int) (this.WinWidth*0.60) , 90);
		System.out.println((int) (this.WinWidth*0.40));
		control.setLayout(null);
		this.getContentPane().add(control);
		
		
		lienzo= new JPanel();
		lienzo.setBounds(20, 190, this.WinWidth-50, (int) (this.WinHeight-240));
		lienzo.setBackground(Color.WHITE);
		lienzo.setBorder(BorderFactory.createLineBorder(Color.black));
		lienzo.setAutoscrolls(true);
		this.getContentPane().add(lienzo);
		
		
		
		labelPalabra = new JLabel("Palabra:");
		labelPalabra.setBounds(15, 30, 50, 20);
		//labelPalabra.setBorder(BorderFactory.createLineBorder(Color.black));
		control.add(labelPalabra);
		labelPalabraTraducida= new JLabel("Palabra Traducida:");
		labelPalabraTraducida.setBounds(185, 30, 115, 20);
		//labelPalabraTraducida.setBorder(BorderFactory.createLineBorder(Color.black));
		control.add(labelPalabraTraducida);
		IngresarPalabra= new JTextField();
		IngresarPalabra.setBounds(70, 30, 100, 20);
		IngresarPalabra.setVisible(true);
		control.add(IngresarPalabra);
		IngresarPalabraTraducida= new JTextField();
		IngresarPalabraTraducida.setBounds(300, 30, 100, 20);
		control.add(IngresarPalabraTraducida);
		Ingresar=new JButton("Ingresar");
		Ingresar.setBounds((control.getWidth()/2)-150, 60, 100, 20);
		Ingresar.addActionListener(new ActionListener(){  
			@Override
			public void actionPerformed(ActionEvent e) {
				String palabra=IngresarPalabra.getText();
				String palabraT= IngresarPalabraTraducida.getText();
                                palabras.add(palabra);
                                palabrasT.add(palabraT);
                                ArbolE.inOrden(ArbolE.raiz);
				try {
					ArbolE.addPalabra(palabra, palabraT);
                                        repintar();
					
				}catch(Exception error) {
					System.out.println(error.toString());	
				}
				IngresarPalabra.setText("");
				IngresarPalabraTraducida.setText("");
			
			}

			
	    });
		control.add(Ingresar);
                
                diccionario=new JButton("Diccionario");
		diccionario.setBounds((control.getWidth()/2), 60, 150, 20);
                diccionario.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        String diccionario = "";
                        for (int i = 0; i<palabras.size();i++){
                         diccionario = diccionario+ palabras.get(i)+" "+palabrasT.get(i)+"\n";
                        }
                        JOptionPane.showMessageDialog(null, diccionario, "Diccionario", WIDTH);
                    }
                });
                control.add(diccionario);
		control.revalidate();
		control.repaint();
	}
	
	public void repintar()
	{
		graphics = lienzo.getGraphics();
		lienzo.paint(graphics);
		g = (Graphics2D) graphics;
		paintArbolE(this.ArbolE.raiz,10,20 );
		//lienzo.repaint();
	}
	
	private void paintArbolE(Nodo node, int x, int y)
	{
		
		if(node != null)
		{	
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(2));
			g.drawString(String.valueOf(node.getValor()),x, y);
			g.setColor(Color.RED);
			if(node.tieneHijos()) {
				g.drawLine(x+3, y+1, x+3, y+29);
				paintArbolE(node.getHijos().get(0),x,y+41);
			
			}else {
				if(node.esFinal()) {
					g.setColor(Color.BLACK);
					g.drawString(String.valueOf(node.getPalabraTraducida()),x-9, y+12);
					pintarhermanos(node,x,y-41);
				}
			}
		}
	}
	
	public void pintarhermanos(Nodo node, int x, int y) {
		if(node.getPadre().sigHermano()==null) {
			pintarhermanos(node.getPadre(),x,y-41);
			return;
		}
		int ancho=node.getPadre().numNodoFinales();
		ancho=ancho-1;
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.RED);
		if(ancho==0) {
			g.drawLine(x+10, y-6, x+(30), y-6);
		}else {
			System.out.println(ancho);
			g.drawLine(x-(20*ancho)-(10*ancho), y-6, x+(36), y-6);
		}
		
		paintArbolE(node.getPadre().sigHermano(),x+36,y);
	}
	public static void main(String []Args) {
		new Interfaz();
	}
	

}