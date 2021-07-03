package Controller;
import java.awt.Rectangle;
import java.util.Random;


import Negocios.*;

public class Controlador {
	private Partida partida;
	private Ranking ranking;
	private Barra barra;
	
	
	public Controlador(){
		this.barra = new Barra(380, 500, 100, 20, 30);
		this.partida = new Partida(this.barra);
		this.ranking = new Ranking();
	}
	/*-----------FUNCIONES------------*/
	public int estadoPartida(){
		return this.partida.getEstadoPartida();
	}
	
	public void controlDePausa() {
		if(this.estadoPartida() == 1 || this.estadoPartida() == 3) {
			this.partida.pausarPartida();
		}
		else if(this.estadoPartida() == 2)
		{
			this.partida.reanudarPartida();
		}
		else if(this.estadoPartida() == 0) {
			this.partida.setEstadoPartida(1);
		}
		else if(this.estadoPartida() == 4) {
			this.partida.reanudarPartida();
		}
	}
	
	public void setEstadoPartida(int estadoPartida) {
		this.partida.setEstadoPartida(estadoPartida);
	}
	
	public void iniciarJuego() {
		this.partida.reanudarPartida();
	}
	
	public void controlarBarra(boolean direccion) {
		if(direccion) {
			this.barra.moverDerecha();
			if(this.estadoPartida() == 0 || this.estadoPartida() == 4)  {
				this.partida.aumentarPosicionBolaEnBarra(this.barra.getVelocidad());
				if (this.partida.getPosXBola() > 842) {
					this.partida.setPosXBola(842);
				}
			}
		}
		else {
			this.barra.moverIzquierda();
			if(this.estadoPartida() == 0 || this.estadoPartida() == 4) {
				this.partida.restarPosicionBolaEnBarra(this.barra.getVelocidad());
				if (this.partida.getPosXBola() < 42) {
					this.partida.setPosXBola(42);
				}
			}	
		}
	}
	
	
	public void terminarPartida() {
		this.partida = new Partida(this.barra);
	}
	
	public boolean revisarColision() {
		if(this.estadoPartida() == 1 || this.estadoPartida() == 3)
			return this.partida.chequearColision();
		else 
			return false;
	}
	
	public void moverBola() {
		this.partida.movimientoBola();
	}
		
	public boolean nivelSuperado() {
		boolean aumenta = this.partida.getLadrillosDestruidos() == 25;
		if(aumenta) {
			this.partida.aumentarNivel();
		}
		return aumenta;
	}
	
	public boolean entraEnTop(int puntaje) {
		return this.ranking.chequearPuntaje(puntaje);
	}
	
	public void registrarEnRanking(int puntos, String nombre) {
		ranking.grabarGanador(puntos, nombre);
	}
	
	public void inicioBola() {
		this.partida.randomInicioBola();
	}
	
	public int sizeLadrillos(int cont) {
		return this.partida.tamLadrillos(cont);
	}
	
	public int PosXLadrillo(int id) {
		return this.partida.getPosXLadrillo(id);
	}
	
	public int PosYLadrillo(int id) {
		return this.partida.getPosYLadrillo(id);
	}
	
	public int AlturaLadrillo(int id) {
		return this.partida.getAlturaLadrillo(id);
	}
	
	public int AnchoLadrillo(int id) {
		return this.partida.getAnchoLadrillo(id);
	}
	
	public void randomInicioBola() {
		this.partida.randomInicioBola();
	}
	
	public void resetearBarra() {
		this.partida.resetearBarra();
	}
	
	/*-----------GETTERS------------*/
	public int getNumeroNivel() {
		return this.partida.getNivel();
	}
	
	public int getNumeroVidas() {
		return this.partida.getVidas();
	}
	
	public int getPuntaje() {
		return this.partida.getPuntos();
	}
	
	public Rectangle getPosicionBola() {
		return this.partida.getPosicionBolaXY();
	}
	
	public int getPosicionXBarra() {
		return this.barra.getPosicionX();
	}
	
	public int getPosicionYBarra() {
		return this.barra.getPosicionY();
	}
	
	public int getAnchoBarra() {
		return this.barra.getAncho();
	}
	
	public int getAltoBarra() {
		return this.barra.getAlto();
	}
	
	public boolean getEstadoLadrillo(int id) {
		return this.partida.estadoLadrillo(id);
	}
	
	public int getEstadoChoqueBola() {
		return this.partida.getChoqueBola();
	}
	
	public double getVelocidadBola() {
		return this.partida.getVelocidadBola();
	}
	
	public Ranking getRanking() {
		return this.ranking;
	}
	/*-----------SETTERS------------*/
	public void setPosicionXBarra(int pos) {
		this.barra.setPosicionX(pos);
	}

	public void setAltoBarra(int pos) {
		this.barra.setAlto(pos);
	}
	
	public void setAnchoBarra(int pos) {
		this.barra.setAncho(pos);
	}
	
	public void setEstadoChoqueBola(int valor) {
		this.partida.setChoqueBola(valor);
	}
}








