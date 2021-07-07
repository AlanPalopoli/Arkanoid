package Pantalla;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Controlador;
import Negocios.Ranking;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;




public class PantallaRanking extends JFrame {
	
  private static final long serialVersionUID = -7636415361935249703L;
  private Container contenedor;
  JFrame aux;
  JButton botonSalir;
  JLabel[] labelNombre = new JLabel[20];
  JLabel[] labelPuntos = new JLabel[20];
  JLabel titulo;
  Image imagenFondo;
  URL fondo;

  private Ranking ranking;


  public PantallaRanking(JFrame pantallaInicio, Ranking ranking) {
	this.ranking = ranking;
    this.aux = pantallaInicio;
    setTitle("Top veinte");
    setBounds(0, 0, 1000, 750);
    setLocationRelativeTo(null);
    setResizable(false);
    
    realizarMatriz();
    salir();
  }

  private void realizarMatriz() {
    contenedor = this.getContentPane();
    //contenedor.setLayout(null);
    //this.getContentPane().setBackground(Color.white);
    
    fondo = this.getClass().getResource("/Image/fondo_ranking.png");
	imagenFondo = new ImageIcon(fondo).getImage();

    botonSalir = new JButton();
    titulo = new JLabel("Ranking");
    titulo.setBounds(460, 20 , 60 , 40 );
    titulo.setForeground(Color.RED);
    botonSalir.setText("Salir");
    botonSalir.setBounds(870, 660, 60, 25);
    this.botonSalir.setActionCommand("Salir");

    int a = 0;
    while (a < 20) {
      labelNombre[a] = new JLabel("-");
      labelPuntos[a] = new JLabel("0");
      a += 1;
    }

    a = 0;
    int suma = 50;
    while (a < 20) {
      labelNombre[a].setBounds(380, suma, 60, 40);
      labelNombre[a].setForeground(Color.RED);
      suma += 30;
      a += 1;
    }

    a = 0;
    suma = 50;
    while (a < 20) {
      labelPuntos[a].setBounds(590, suma, 60, 40);
      labelPuntos[a].setForeground(Color.RED);
      suma += 30;
      a += 1;
    }
    a = 0;
    while (a < 20) {
      contenedor.add(labelNombre[a]);
      a += 1;
    }
    a = 0;
    while (a < 20) {
      contenedor.add(labelPuntos[a]);
      a += 1;
    }

    configurarRanking();
    contenedor.add(botonSalir);
    contenedor.add(titulo);
    contenedor.add(panel);

  }
  
  @SuppressWarnings("serial")
	public JPanel panel = new JPanel(){
	 	public void paintComponent(Graphics g){
		    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
	    }
	};
  
  private void configurarRanking() {
	  String[] nombre = new String[10];
	  int[] puntajeJugador = new int[10];
	  nombre = this.ranking.getJugadores();
	  puntajeJugador = this.ranking.getPuntajes();
	  int i = 0;
	  while (i < 20 && nombre[i] != null) {
		  this.labelNombre[i].setText(nombre[i]);
		  //this.labelNombre[i].set(nombre[i]);
		  this.labelNombre[i].setForeground(Color.RED);
		  String texto = Integer.toString(puntajeJugador[i]);
		  this.labelPuntos[i].setText(texto);
		  this.labelPuntos[i].setForeground(Color.RED);
		  i += 1;
	  }
  }
  private void salir() {
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	    botonSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					aux.setVisible(true);
					PantallaRanking.this.setVisible(false);
				}
			});
	  }

}
