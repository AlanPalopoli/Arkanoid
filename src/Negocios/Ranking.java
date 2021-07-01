package Negocios;

import java.util.List;

public class Ranking {
	private String jugadores[];
	private int	puntajes[];
	
	public Ranking() {
		this.jugadores = new String [20];
		this.puntajes = new int []{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	}
	
	/*-----------FUNCIONES------------*/
	public boolean chequearPuntaje(int puntaje) {
		if (this.puntajes[19] < puntaje){
			return true;
		}
		else return false;
	}
	
	public void grabarGanador(int puntos, String nombreJugador) {
		int i=0;
		boolean encontrado = false;
		while  (i<=19 && !encontrado){
			if (this.puntajes[i] < puntos){
				encontrado = true;
			}
			else i++;
		}
		insertarEnLista(i,puntos,nombreJugador);
	}
	
	private void insertarEnLista(int posicion,int puntos, String nombreJugador) {
		for (int i = 18; i>posicion; i--) {
			this.puntajes[i+1] = this.puntajes[i];
			this.jugadores[i+1] = this.jugadores[i];
		}
		this.puntajes[posicion] = puntos;
		this.jugadores[posicion] = nombreJugador;
	}
	
	public String[] getJugadores(){
		return this.jugadores;
	}
	
	public int[] getPuntajes() {
		return this.puntajes;
	}
}
