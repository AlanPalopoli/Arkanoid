package Pantalla;

import javax.swing.JFrame;
import javax.swing.JLabel;
import Controller.Controlador;
import Negocios.Ranking;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;




public class PantallaRanking extends JFrame {
	
  private static final long serialVersionUID = -7636415361935249703L;
  JFrame aux;
  JButton botonSalir;
  JLabel[] labelNombre = new JLabel[20];
  JLabel[] labelPuntos = new JLabel[20];
  private Ranking ranking;


  public PantallaRanking(JFrame pantallaInicio, Ranking ranking) {
	this.ranking = ranking;
    this.aux = pantallaInicio;
    realizarMatriz();
    salir();
  }

  private void realizarMatriz() {
    Container contenedor = this.getContentPane();
    contenedor.setLayout(null);
    this.getContentPane().setBackground(Color.cyan);
    setTitle("Top veinte");
    setLocationRelativeTo(null);
    setResizable(false);
    this.setSize(1000, 800);
    this.setResizable(false);
    botonSalir = new JButton("Salir");

    int a = 0;
    while (a < 20) {
      labelNombre[a] = new JLabel("-");
      labelPuntos[a] = new JLabel("0");
      a += 1;
    }

    a = 0;
    int suma = 50;
    while (a < 20) {
      labelNombre[a].setBounds(260, suma, 60, 40);
      suma += 30;
      a += 1;
    }

    a = 0;
    suma = 50;
    while (a < 20) {
      labelPuntos[a].setBounds(470, suma, 60, 40);
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
    
    botonSalir.setBounds(620, 25, 60, 25);
    contenedor.add(botonSalir);
    configurarRanking();
  }
  
  private void configurarRanking() {
	  String[] nombre = new String[10];
	  int[] puntajeJugador = new int[10];
	  nombre = this.ranking.getJugadores();
	  puntajeJugador = this.ranking.getPuntajes();
	  int i = 0;
	  while (i < 20 && nombre[i] != null) {
		  this.labelNombre[i].setText(nombre[i]);
		  String texto = Integer.toString(puntajeJugador[i]);
		  this.labelPuntos[i].setText(texto);
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
