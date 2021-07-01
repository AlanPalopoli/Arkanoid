package Pantalla;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Controlador;
import Pantalla.PantallaInicio;
import Pantalla.PantallaRanking;

public class PantallaInicio extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Container contenedor;/*declaramos el contenedor*/
	JLabel titulo;/*declaramos el objeto Label para el titulo*/
	
	/*declaramos los objetos JLabels*/
	Image imagenFondo;
	URL fondo;
	private Controlador controlador;
	
	//private Controlador controlador;
	
	JButton jugar; //Boton para iniciar el juego
	
	JButton ranking; //Boton para ranking
	
	
	//JButton configuracion; //Boton modificar parametros
	
	JButton salir; //Boton para salir del juego
	
	public PantallaInicio(){
		
		/*Asigna un titulo a la barra de titulo*/
		setTitle("Arkanoid");
		/*tamaño de la ventana (x,y)*/
		setBounds(0, 0, 700, 500);
		/*pone la ventana en el Centro de la pantalla*/
		setLocationRelativeTo(null);
		/*impide que la ventana cambie de tamaño*/
		setResizable(false);
		/*permite iniciar las propiedades de los componentes*/
		iniciarComponentes();
		eventos();
	}
	
	private void iniciarComponentes() {
		
		this.controlador = new Controlador();
		
		contenedor = this.getContentPane();/*instanciamos el contenedor
		con esto definmos nosotros mismos los tamaños y posiciones de los componentes*/
		
        fondo = this.getClass().getResource("/image/fondo_inicio.png");
		
		imagenFondo = new ImageIcon(fondo).getImage();
		
		
		
		/*Definimos las propiedades de los componentes*/
		titulo = new JLabel();
		titulo.setText("Arkanoid");
		titulo.setFont(new java.awt.Font("Tahoma", 3, 30));
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(270, 15, 380, 40);
		

		jugar = new JButton();
		jugar.setText("Jugar");
		jugar.setBounds(50, 150, 280, 25);
		
		ranking = new JButton();
		ranking.setText("Ranking");
		ranking.setBounds(50, 220, 280, 25);
				
		//configuracion = new JButton();
		//configuracion.setText("Configuración");
		//configuracion.setBounds(50, 220, 280, 25);
		
		salir = new JButton();
		salir.setText("Salir");
		salir.setBounds(50, 290, 280, 25);
		
		this.jugar.setActionCommand("Jugar");
		this.ranking.setActionCommand("Ranking");
		this.salir.setActionCommand("Salir");
		
		
		/*Agregamos los componentes al Contenedor*/
		
		contenedor.add(titulo);
		contenedor.add(jugar);
		contenedor.add(ranking);
		contenedor.add(salir);
		contenedor.add(panel);
	}
	
	@SuppressWarnings("serial")
	public JPanel panel = new JPanel(){
	 	public void paintComponent(Graphics g){
		    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
	    }
	};

	private void eventos() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		/*configuracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaConfiguracion opciones = new VistaConfiguracion(PantallaPrincipal.this);
				opciones.setVisible(true);
				PantallaPrincipal.this.setVisible(false);
			}
		});*/
		
		jugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaJuego jugando = new PantallaJuego(PantallaInicio.this, controlador);
				jugando.setVisible(true);
				PantallaInicio.this.setVisible(false);
			}
		});
		
		ranking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaRanking jugando = new PantallaRanking(PantallaInicio.this, controlador.getRanking());
				jugando.setVisible(true);
				PantallaInicio.this.setVisible(false);
			}
		});
		
		
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Juego de colision");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				System.exit(0);
			}
		});

	}
}