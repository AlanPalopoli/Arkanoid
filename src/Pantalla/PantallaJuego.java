package Pantalla;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Negocios.*;
import Controller.Controlador;

public class PantallaJuego extends JFrame implements KeyListener{
	
	private static final long serialVersionUID = -7636415361935249703L;

	JFrame principalaux;
	private JLabel[] ladrillos=new JLabel[25];
	private JLabel barra = new JLabel();
	private JLabel bola = new JLabel();
	private JLabel labelPuntosTotal = new JLabel("Puntos Totales: 0");
	private JLabel labelVidas = new JLabel("Cantidad de Vidas: 3");
	private JLabel labelNivel = new JLabel("Nivel: 1");
	private JButton botonSalir;
	
	private boolean iniciado = false;
	
	Image imagenFondoJuego;
	URL fondoJuego;
	private Controlador controlador;
	@SuppressWarnings("unused")

	
	private Timer movimientoBolaTimer;
	private Timer labelsTimer;
	private Timer colisionTimer;
	private Timer choqueBolaTimer1;
	private Timer choqueBolaTimer2;
	private int finalizoJuego = 0;
	
	private Container contenedor;
	
	public PantallaJuego(JFrame principal, Controlador controlador) {
		this.controlador = controlador;
		iniciarComponentes();
		this.principalaux = principal;
		eventos(contenedor);
	}

	private void iniciarComponentes() {
		this.setSize(900, 600);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.setTitle("Arkanoid");
		addKeyListener(this);
		
		contenedor = this.getContentPane();
		this.contenedor.setLayout(null);
		fondoJuego = this.getClass().getResource("/image/fondo_inicio.png");
		imagenFondoJuego = new ImageIcon(fondoJuego).getImage();
	

		
		botonSalir = new JButton("Salir");


		labelNivel.setBounds(20, 5, 75, 25);
		labelVidas.setBounds(370, 5, 140, 25);
		labelPuntosTotal.setBounds(785, 5, 150, 25);
		botonSalir.setBounds(20, 525, 60, 25);

		labelNivel.setText("Nivel: " + controlador.getNumeroNivel());
		labelVidas.setText("Cantidad de Vidas: " + controlador.getNumeroVidas());
		labelPuntosTotal.setText("Puntaje Total: " + controlador.getPuntaje());



		botonSalir.setFocusable(false);

		contenedor.add(labelNivel);
		contenedor.add(labelVidas);
		contenedor.add(labelPuntosTotal);
		contenedor.add(barra);
		contenedor.add(bola);
		contenedor.add(botonSalir);
		contenedor.add(panel);
		
		for (int i=0;i<25;i++){
        	ladrillos[i]=new JLabel();
		}
		mostrarBarra();
		mostrarLadrillo();
		mostrarBola();
		
	}
	@SuppressWarnings("serial")
	public JPanel panel = new JPanel(){
	 	public void paintComponent(Graphics g){
		    g.drawImage(imagenFondoJuego, 0, 0, getWidth(), getHeight(), this);
	    }
	};
	

	private void eventos(Container contenedor) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				principalaux.setVisible(true);
				PantallaJuego.this.setVisible(false);
				PantallaJuego.this.labelsTimer.stop();
				PantallaJuego.this.colisionTimer.stop();	
				PantallaJuego.this.movimientoBolaTimer.stop();
				controlador.revisarColision();
				salirJuego();
			}
		});
		 
		System.out.println("nueva ventana");

		crearTempColision();
		crearTempLabels();
		crearTempChoqueBola1();
	}
	

	
	private void salirJuego() {
		controlador.terminarPartida();
		PantallaJuego.this.colisionTimer.stop();	
		PantallaJuego.this.movimientoBolaTimer.stop();
		PantallaJuego.this.labelsTimer.stop();
		controlador.resetearBarra();
	}
	
	private void crearTempLabels() {
		ActionListener labelLabel = new ActionListener() {
			public void actionPerformed(ActionEvent l) {
				actualizarLabelCantVidas();
				actualizarLabelsSumaPuntaje();
				finalizoElNivel();
				if(controlador.estadoPartida() == 4) {
					mostrarBola();
					mostrarBarra();
				}
			}
		};
		this.labelsTimer = new Timer(100, labelLabel);
		this.labelsTimer.start();
	}
	private void crearTempColision() {
		ActionListener colision = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(controlador.revisarColision()) {
						for (int i=0;i<25;i++){
							boolean estado = controlador.getEstadoLadrillo(i);
							if (estado) {
								if(ladrillos[i] != null) {
									ladrillos[i].setVisible(false);
									ladrillos[i] = null;
								}
							}
						}
					}
			}
		};
		this.colisionTimer = new Timer(100, colision);
		this.colisionTimer.start();
	}
	private void crearTempChoqueBola1() {
		if(controlador.getEstadoChoqueBola() != 0) {
			ActionListener chocoBola = new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
						controlador.setEstadoChoqueBola(0);
				}
			};
			this.choqueBolaTimer1 = new Timer(1000, chocoBola);
			this.choqueBolaTimer1.start();
		}
	}
	
	private void crearTempMovimientoBola() {
		ActionListener movElem = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(controlador.estadoPartida() == 1 || controlador.estadoPartida() == 3) {
					moverBola();
				}
			}
		};
		this.movimientoBolaTimer = new Timer(10, movElem);
		this.movimientoBolaTimer.start();
	}
	
	private void moverBola() {
		controlador.moverBola();
		mostrarBola();
	}

	private void mostrarBola() {
		
			String urlImagen = "C:\\Users\\alan\\eclipse-workspace\\TP-IOO\\src\\Image\\bola.PNG";
			Rectangle bounds = controlador.getPosicionBola();
			if (this.bola == null) {
				this.bola = new JLabel();
			}
			this.bola.setBounds(bounds);
			this.bola.setIcon(new ImageIcon(urlImagen));
			this.bola.setVisible(true);
			try {
				contenedor.add(this.bola);
			} catch(NullPointerException e){
				System.out.print("NullPointer por borrado de bola ");
			}
	}
	private void mostrarLadrillo() {
		String urlImagen = "C:\\Users\\alan\\eclipse-workspace\\TP-IOO\\src\\Image\\ladrillo.PNG";
		int sizeArray = 0;
		int sizeArray2 = controlador.sizeLadrillos(sizeArray);
		for (int i=0;i<sizeArray2;i++){
			boolean estado = controlador.getEstadoLadrillo(i);
			if (!estado) {
				this.ladrillos[i].setBounds(controlador.PosXLadrillo(i),controlador.PosYLadrillo(i),controlador.AnchoLadrillo(i),controlador.AlturaLadrillo(i));
				this.ladrillos[i].setIcon(new ImageIcon(urlImagen));
				this.ladrillos[i].setVisible(true);
				contenedor.add(this.ladrillos[i]);
			}
        }
    }
		
	private void actualizarLabelsSumaPuntaje() {
		labelPuntosTotal.setText("Puntaje Total: " + controlador.getPuntaje());
	}
	private void actualizarLabelCantVidas() {
		int vidas = controlador.getNumeroVidas();
		labelVidas.setText("Cantidad de Vidas: " + vidas);
	}
	
	private void finalizoElNivel() {
		if (controlador.nivelSuperado() == true) {
			JOptionPane.showMessageDialog(this.contenedor, "Nivel " + (controlador.getNumeroNivel() - 1) + " superado!");
			for (int i=0;i<25;i++){
	        	ladrillos[i]=new JLabel();
			}
			mostrarBarra();
			mostrarLadrillo();
			mostrarBola();
			controlador.setEstadoPartida(0);
			this.iniciado = false;
			
		} else {
			if (controlador.getNumeroVidas() == 0) {
				JOptionPane.showMessageDialog(this.contenedor, "¡Juego terminado! Puntuacion: " + controlador.getPuntaje());
				PantallaJuego.this.setVisible(false);
				this.principalaux.setVisible(true);
				JFrame frame = new JFrame();
				if(controlador.entraEnTop(controlador.getPuntaje())) {
					String name = JOptionPane.showInputDialog(frame, "Ingresa tu nombre para registrarlo en el ranking:");
					controlador.registrarEnRanking(controlador.getPuntaje(), name);
					this.setDefaultCloseOperation(EXIT_ON_CLOSE);
					
				}
				else
				{
				JOptionPane.showMessageDialog(this.contenedor, "¡Lo siento! Con tu puntaje no lograste entrar al top 20, seguí intentando.");
				}
				salirJuego();
			} 
		}
		labelNivel.setText("Nivel: " + controlador.getNumeroNivel());
	}
	
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT) {
			if(controlador.estadoPartida() == 0 || controlador.estadoPartida() == 1 ||  controlador.estadoPartida() == 3 || controlador.estadoPartida() == 4)
			{
				controlador.controlarBarra(false);
				if (controlador.getPosicionXBarra() < 0) {
					controlador.setPosicionXBarra(0);
				}
				if(controlador.estadoPartida() == 0 || controlador.estadoPartida() == 4)
					mostrarBola();
				mostrarBarra();
			}
		} else if (keyCode == KeyEvent.VK_RIGHT)			
			{
				if(controlador.estadoPartida() == 0 || controlador.estadoPartida() == 1 ||  controlador.estadoPartida() == 3 || controlador.estadoPartida() == 4)
				{
					controlador.controlarBarra(true);
					if (controlador.getPosicionXBarra() > 800) {
						controlador.setPosicionXBarra(800);
					}
					if(controlador.estadoPartida() == 0 || controlador.estadoPartida() == 4)
						mostrarBola();
					mostrarBarra();
				}
			} else if (keyCode == KeyEvent.VK_SPACE)
			{
				if(controlador.estadoPartida() == 0) {
					if(!iniciado) {
						controlador.controlDePausa();
						controlador.inicioBola();
						crearTempMovimientoBola();
						this.iniciado = true;
					}
				}else if (controlador.estadoPartida() == 2 || controlador.estadoPartida() == 4) {
					controlador.controlDePausa();
				}
			} else if (keyCode == KeyEvent.VK_P)
			{
				if(controlador.estadoPartida() == 1 || controlador.estadoPartida() == 3) {
					controlador.controlDePausa();
				}
			}
	}


	private void mostrarBarra() {
		String urlImagen = "C:\\Users\\alan\\eclipse-workspace\\TP-IOO\\src\\Image\\barra.PNG";
		if (this.barra == null ){
			this.barra = new JLabel();
		}
		this.barra.setBounds(controlador.getPosicionXBarra(),controlador.getPosicionYBarra(), controlador.getAnchoBarra(), controlador.getAltoBarra());
		this.barra.setIcon(new ImageIcon(urlImagen));
		this.barra.setVisible(true);
		contenedor.add(this.barra);

	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
