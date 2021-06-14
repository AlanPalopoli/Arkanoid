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
	//private JLabel[] ladrillos = new JLabel();
	private JLabel[] ladrillos=new JLabel[25];
	private JLabel barra = new JLabel();
	private JLabel bola = new JLabel();
	//private JLabel mira = new JLabel();
	//private JLabel explosion = new JLabel();
	
	//private JLabel labelProximaVidaEn = new JLabel("Vida Extra en: 300 pts");
	private JLabel labelPuntosTotal = new JLabel("Puntos Totales: 0");
	private JLabel labelVidas = new JLabel("Cantidad de Vidas: 3");
	private JLabel labelNivel = new JLabel("Nivel: 1");
	//private JLabel labelBarcosHundidos = new JLabel();
	//private JLabel labelBarcosRestantes = new JLabel();
	//private JLabel labelPotencia = new JLabel();
	private JButton botonSalir;
	
	
	//private int potencia = 1;
	//private boolean explotando = false;
	
	Image imagenFondoJuego;
	URL fondoJuego;
	private Controlador controlador;
	@SuppressWarnings("unused")
	//private Ranking ranking;
	
	//private Point puntomira;
	
	private Timer movimientoBolaTimer;
	//private Timer fueraContainerTimer;
	//private Timer explosionGifTimer;
	private Timer labelsTimer;
	//private Timer tiempoRecargaTimer;
	private Timer colisionTimer;
	
	private Container contenedor;
	//private String urlImagenMira = "C:\\Users\\alan\\eclipse-workspace\\TP_Juegos_BattleShips\\src\\image\\mira.png";
	
	public PantallaJuego(JFrame principal) {
		iniciarComponentes();
		this.principalaux = principal;
		eventos(contenedor);
	}

	private void iniciarComponentes() {
		this.controlador= new Controlador();

		contenedor = this.getContentPane();
		this.getContentPane().setBackground(Color.cyan);
		this.contenedor.setLayout(null);
		//fondoJuego = this.getClass().getResource("/image/fondo_inicio.png");
		//imagenFondoJuego = new ImageIcon(fondoJuego).getImage();
	
		this.setSize(900, 600);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.setTitle("Arkanoid");
		addKeyListener(this);
		
		
		botonSalir = new JButton("Salir");

		//puntomira = new Point(500, 50);

		labelNivel.setBounds(20, 5, 75, 25);
		labelVidas.setBounds(115, 5, 140, 25);
		//labelProximaVidaEn.setBounds(275, 5, 150, 25);
		labelPuntosTotal.setBounds(425, 5, 150, 25);
		//labelPotencia.setBounds(800, 525, 150, 25);
		//labelBarcosHundidos.setBounds(580, 5, 150, 25);
		//labelBarcosRestantes.setBounds(750, 5, 150, 25);
		botonSalir.setBounds(20, 525, 60, 25);

		//bola.setBounds(500, 600, 50, 50);
		//barra.setBounds(500, 75, 100, 50);
		//ladrillos.setBounds(500, 60, 60, 60);

		//mira.setIcon(new ImageIcon(urlImagenMira));

		labelNivel.setText("Nivel: " + controlador.getNumeroNivel());
		labelVidas.setText("Cantidad de Vidas: " + controlador.getNumeroVidas());
		//labelProximaVidaEn.setText("Nueva Vida en: " + (300 - controlador.getPuntaje()) + "pts");
		//labelBarcosHundidos.setText("Barcos Hundidos: " + controlador.getBarcosHundidos());
		labelPuntosTotal.setText("Puntaje Total: " + controlador.getPuntaje());
		//labelBarcosRestantes.setText("Barcos Restantes: " + ( 10 - controlador.getNumeroBarco()));
		//labelPotencia.setText("Potencia: " + potencia + "/5");


		//barco.setVisible(true);

		botonSalir.setFocusable(false);

		contenedor.add(labelNivel);
		contenedor.add(labelVidas);
		//contenedor.add(labelProximaVidaEn);
		contenedor.add(labelPuntosTotal);
		//contenedor.add(labelBarcosHundidos);
		//contenedor.add(labelBarcosRestantes);
		//contenedor.add(labelPotencia);
		contenedor.add(barra);
		//contenedor.add(ladrillos);
		contenedor.add(bola);
		contenedor.add(botonSalir);
		contenedor.add(panel);
		
		for (int i=0;i<25;i++){
        	ladrillos[i]=new JLabel();
		}
		mostrarBarra();
		mostrarLadrillo();
		mostrarBola();
		//inicializar labels de ladrillos
		
	
		//mostrarCañon(controlador.nuevoCañon(true));
		
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
				//PantallaJuego.this.tiempoRecargaTimer.stop();
				if((PantallaJuego.this.movimientoBolaTimer) != null)
					PantallaJuego.this.movimientoBolaTimer.stop();
				//PantallaJuego.this.fueraContainerTimer.stop();
				//PantallaJuego.this.explosionGifTimer.stop();
				PantallaJuego.this.labelsTimer.stop();
				PantallaJuego.this.colisionTimer.stop();
				//guardarPuntos();
				salirJuego();
			}
		});
		 
		System.out.println("nueva ventana");

		crearTempColision();
		crearTempLabels();
		//crearTempFueraContainer();
		//crearTempRecarga();
		//crearTempExplosion();
	}
	

	
	private void salirJuego() {
		controlador.terminarPartida();
	}
	
	/*private void crearTempRecarga() {
		ActionListener labelMisil = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.setRecargar(true);
			}
		};
		int pausa = (int) (this.controlador.getTiempoRecarga() * 1000);
		this.tiempoRecargaTimer = new Timer(pausa, labelMisil);
		this.tiempoRecargaTimer.start();
	}*/
	
	/*private void crearTempExplosion() {
		ActionListener gifExplosion = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (explotando == true) {
					explotando = false;
					explosion.setVisible(false);
					PantallaJuego.this.explosionGifTimer.stop();
				}
			}
		};
		this.explosionGifTimer = new Timer(1000, gifExplosion);
		this.explosionGifTimer.start();
	}*/
	
	/*private void crearTempFueraContainer() {
		ActionListener labelVision = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.revisarColision();
			}
		};
		this.fueraContainerTimer = new Timer(10, labelVision);
		this.fueraContainerTimer.start();
	}*/

	private void crearTempLabels() {
		ActionListener labelLabel = new ActionListener() {
			public void actionPerformed(ActionEvent l) {
				actualizarLabelCantVidas();
				actualizarLabelsSumaPuntaje();
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
								ladrillos[i].setVisible(false);
								ladrillos[i] = null;
							}
						}
					finalizoElNivel();
					}
			}
		};
		this.colisionTimer = new Timer(100, colision);
		this.colisionTimer.start();
	}
	
	private void crearTempMovimientoBola() {
		ActionListener movElem = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moverBola();
			}
		};
		this.movimientoBolaTimer = new Timer(10, movElem);
		this.movimientoBolaTimer.start();
	}
	
	private void moverBola() {
		controlador.moverBola();
		mostrarBola();
	}


	/*private void moverMisil() {
		Misil misil = controlador.moverMisil();
		mostrarMisil(misil);
	}*/

	
/*private void mostrarExplosion(int x, int y) {
		if (explosion != null) {
			String urlImagen = "C:\\Users\\alan\\eclipse-workspace\\TP_Juegos_BattleShips\\src\\image\\explosion.gif";
			if (this.explosion == null ){
				this.explosion = new JLabel();
			}
			explotando = true;
			this.explosion.setBounds( x, y, 216, 122);
			this.explosion.setIcon(new ImageIcon(urlImagen));
			explosionGifTimer.restart();
			this.explosion.setVisible(true);
			try {
				contenedor.add(this.explosion);
			} catch(NullPointerException e){
				System.out.print("NullPointer por borrado de explosion ");
			}
		}
	}*/

	
	/*private void mostrarBarco(Barco barco) {
		if (barco != null) {
			Rectangle bounds = barco.getPosicion();
			if (this.barco == null ){
				this.barco = new JLabel();
			}
			this.barco.setBounds(bounds.x, bounds.y, 216,122);
			if (barco.getUbicacionBarco() == true) {
				String urlImagen = "C:\\Users\\alan\\eclipse-workspace\\TP_Juegos_BattleShips\\src\\image\\barcoDerecha.png";
				this.barco.setIcon(new ImageIcon(urlImagen));
			}
			else {
				String urlImagen = "C:\\Users\\alan\\eclipse-workspace\\TP_Juegos_BattleShips\\src\\image\\barcoIzquierda.png";
				this.barco.setIcon(new ImageIcon(urlImagen));
			}
			this.barco.setVisible(true);
			try {
				contenedor.add(this.barco);
			} catch(NullPointerException e){
				System.out.print("NullPointer por borrado de barco ");
			}
		}
	}*/

	private void mostrarBola() {
		
			String urlImagen = "C:\\Users\\alan\\eclipse-workspace\\TP-IOO\\src\\Image\\bola.png";
			Rectangle bounds = controlador.getPosicionBola();
			//System.out.println("La posicion de la bola es: " + controlador.getPosicionBola());
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
		String urlImagen = "C:\\Users\\alan\\eclipse-workspace\\TP-IOO\\src\\Image\\ladrillo.png";
		int sizeArray = 0;
		int sizeArray2 = controlador.sizeLadrillos(sizeArray);
		for (int i=0;i<sizeArray2;i++){
			this.ladrillos[i].setBounds(controlador.PosXLadrillo(i),controlador.PosYLadrillo(i),controlador.AnchoLadrillo(i),controlador.AlturaLadrillo(i));
			this.ladrillos[i].setIcon(new ImageIcon(urlImagen));
			this.ladrillos[i].setVisible(true);
			contenedor.add(this.ladrillos[i]);

        }
    }
		
		
	
	/*private void MisilFueraDeContainer() {
		Rectangle posicion = controlador.getPosicionMisil();
		if (this.misil != null && posicion != null) {
			if (fueraDeContainer(posicion) == true) {
				this.controlador.borrarMisil();
			}
		}
	}

	private void BarcoFueraDeContainer() {
		Rectangle posicion = controlador.posicionBarco();
		boolean direccion = controlador.getDireccion();
		if (this.barco != null && posicion != null) {
			if (this.barcoAFueraDeContainer(direccion, posicion)) {
				this.controlador.borrarBarco();
			}
		}
	}
	private boolean fueraDeContainer(Rectangle posicion) {
		int arriba = (int) posicion.getMinY();
		int abajo = (int) posicion.getMaxY();
		int izq = (int) posicion.getMinX();
		int der = (int) posicion.getMaxX();
		if (izq > 900 || der < 0 || arriba > 600 || abajo < 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean barcoAFueraDeContainer(boolean direccion, Rectangle posicion) {
		int izq = (int) posicion.getMinX();
		int der = (int) posicion.getMaxX();
		if (direccion == true) {
			return izq > 900;
		} else {
			return der < -200;
			
		}
	}
	*/
	/*
	private void colisiono() {
		if (controlador.verificarImpacto() == true) {
			int posX  =  barco.getX();
			int posY = barco.getY();
			this.misil.setVisible(false);
			this.barco.setVisible(false);
			if (explotando == false)
				mostrarExplosion(posX, posY);
			this.misil = null;
			this.barco = null;
		}
		actualizarLabelsBarcos();
	}
	private void actualizarLabelsBarcos() {
		labelBarcosHundidos.setText("Barcos Hundidos: " + controlador.getBarcosHundidos());
		labelBarcosRestantes.setText("Barcos Restantes: " + ( 10 - controlador.getNumeroBarco()));
	}	*/

	private void actualizarLabelsSumaPuntaje() {
		//int puntos = controlador.getPuntaje();
		//labelProximaVidaEn.setText("Nueva Vida en: " + (300 - puntos) + "pts");
		labelPuntosTotal.setText("Puntaje Total: " + controlador.getPuntaje());
	}
	private void actualizarLabelCantVidas() {
		int vidas = controlador.getNumeroVidas();
		labelVidas.setText("Cantidad de Vidas: " + vidas);
	}
	
	private void finalizoElNivel() {
		if (controlador.nivelSuperado() == true) {
			JOptionPane.showMessageDialog(this.contenedor, "Nivel " + (controlador.getNumeroNivel() - 1) + " superado!");
		} else {
			if (controlador.getNumeroVidas() <= 0) {
				JOptionPane.showMessageDialog(this.contenedor, "¡Juego terminado! Puntuacion: " + controlador.getPuntaje());
				PantallaJuego.this.setVisible(false);
				JFrame frame = new JFrame();
				if(controlador.entraEnTop(controlador.getPuntaje())) {
					String name = JOptionPane.showInputDialog(frame, "Ingresa tu nombre para registrarlo en el ranking:");
					//ranking = new Ranking(controlador.getPuntaje());
					controlador.registrarEnRanking(controlador.getPuntaje(), name);
					//controlador.setNombreUsuario(name);
					//controlador.setFinalScore(controlador.getPuntaje(), principalaux);
					this.setDefaultCloseOperation(EXIT_ON_CLOSE);
					salirJuego();
					
				}
				else
				{
				JOptionPane.showMessageDialog(this.contenedor, "¡Lo siento! Con tu puntaje no lograste entrar al top 20, seguí intentando.");
				}
				
			} 
		}
		labelNivel.setText("Nivel: " + controlador.getNumeroNivel());
	}
	
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		switch(keyCode) {
			/*case KeyEvent.VK_UP:
				this.potencia = this.potencia + 1;
				if (this.potencia > 5) {
					this.potencia = 5;
				}
				labelPotencia.setText("Potencia: " + this.potencia + "/5");
				break;

			case KeyEvent.VK_DOWN:
				this.potencia = this.potencia - 1;
				if (this.potencia < 1) {
					this.potencia = 1;
				}
				labelPotencia.setText("Potencia: " + this.potencia + "/5");
				break;
			*/
			case KeyEvent.VK_LEFT:
				controlador.controlarBarra(false);
				if (controlador.getPosicionXBarra() < 0) {
					controlador.setPosicionXBarra(0);
				}
				if(controlador.estadoPartida() == false)
					mostrarBola();
				mostrarBarra();
				break;

			case KeyEvent.VK_RIGHT :
				controlador.controlarBarra(true);
				if (controlador.getPosicionXBarra() > 800) {
					controlador.setPosicionXBarra(800);
				}
				if(controlador.estadoPartida() == false)
					mostrarBola();
				mostrarBarra();
				break;

			case KeyEvent.VK_SPACE :
				if(controlador.estadoPartida() == false) {
					controlador.iniciarJuego();
					controlador.inicioBola();
					crearTempMovimientoBola();
					break;
				}
				
		}
	}
	private void mostrarBarra() {
		String urlImagen = "C:\\Users\\alan\\eclipse-workspace\\TP-IOO\\src\\Image\\barra.png";
		//Rectangle bounds = cañon.getPosicion();
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
