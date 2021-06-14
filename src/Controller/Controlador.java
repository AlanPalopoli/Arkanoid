package Controller;
import Negocios.*;

public class Controlador {
	private Partida partida;
	private Ranking ranking;
	private Barra barra;
	private String nombreJugador;
	
	public Controlador(){
		this.barra = new Barra(0, 0, 0, 0, 0);
		this.partida = new Partida(this.barra);
		this.ranking = new Ranking();
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
	public void resetearBola() {
		this.
	}
	
	public boolean colisionoPiso() {
		return this.partida.chocoPiso();
	}
}
