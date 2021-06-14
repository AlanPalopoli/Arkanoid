package Controller;
import java.awt.Rectangle;
import java.util.Random;

import Negocios.*;

public class Controlador {
	private Partida partida;
	private Ranking ranking;
	private Barra barra;
	private String nombreJugador;
	
	public Controlador(){
		this.barra = new Barra(380, 500, 0, 40, 20);
		this.partida = new Partida(this.barra);
		this.ranking = new Ranking();
	}
	public boolean estadoPartida(){
		return this.partida.getEstadoPartida();
	}
	
	public void controlDePausa() {
		if(this.partida.getEstadoPartida()) {
			this.partida.pausarPartida();
		}
		else 
		{
			this.partida.reanudarPartida();
		}
	}
	
	public void iniciarJuego() {
		this.partida.reanudarPartida();
	}
	
	public void controlarBarra(boolean direccion) {
		if(direccion) {
			this.barra.moverDerecha();
		}
		else {
			this.barra.moverIzquierda();
		}
	}
	
	public void ingresarNombre(String nombre) {
		this.nombreJugador = nombre;
	}
	
	public int getNumeroNivel() {
		return this.partida.getNivel();
	}
	
	public int getNumeroVidas() {
		return this.partida.getVidas();
	}
	public int getPuntaje() {
		return this.partida.getPuntos();
	}
	public void terminarPartida() {
		this.partida = new Partida(this.barra);
	}
	public boolean revisarColision() {
		return this.partida.chequearColision();
	}
	public void moverBola() {
		this.partida.movimientoBola();
	}
	public Rectangle getPosicionBola() {
		return this.partida.getPosicionBolaXY();
	}
	public boolean nivelSuperado() {
		boolean aumenta = this.partida.getLadrillosDestruidos() == 20;
		if(aumenta) {
			this.partida.aumentarNivel();
			this.partida.subirVelocidad();
			this.partida.resetearBarra();
			this.partida.resetearBola();
			this.partida.resetearLadrillo();
		}
		return aumenta;
	}
	
	public boolean entraEnTop(int puntaje) {
		return this.ranking.chequearPuntaje(puntaje);
	}
	
	public void registrarEnRanking(int puntos, String nombre) {
		ranking.grabarGanador(puntos, nombre);
	}
	public int getPosicionXBarra() {
		return this.barra.getPosicionX();
	}
	public int getPosicionYBarra() {
		return this.barra.getPosicionY();
	}
	public int setPosicionXBarra(int pos) {
		return this.setPosicionXBarra(pos);
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
	public boolean getEstadoLadrillo(int id) {
		return this.partida.estadoLadrillo(id);
	}
}








