package Negocios;

import java.awt.Rectangle;
import java.util.ArrayList;
import Negocios.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.Timer;

public class Partida {
	private int vidas;
	private int puntos;
	private int puntosRelativos;
	private int nivel;
	private int estadoPartida;
	private ArrayList <Ladrillo> ladrillos;
	private Bola bola;
	private Barra barra;
	private int xMayor;
	private int xMenor;
	private int yMayor;
	private int yMenor;
	private int ubicacionFinalBolaX;
	private int choqueBola;
	
	public Partida(Barra barraCreada) {
		this.vidas 	= 3;
		this.setNivel(1);
		this.setPuntos(0);
		this.setEstadoPartida(0);
		this.bola = new Bola(414, 487, 4, 10, 0);
		this.barra = barraCreada;
		this.xMayor = 870; 	//Pared Derecha  //ANCHO=700 LARGO=500
		this.xMenor = 0;	//Pared Izquierda
		this.yMayor = 1000;	 //Piso
		this.yMenor = 0;	//Techo
		this.ubicacionFinalBolaX = 0;
		this.setChoqueBola(99);
		this.ladrillos = new ArrayList <Ladrillo>();
		for (int i = 0; i<5; i++) {
			for(int j = 0 ; j<5; j++) {
				agregarLadrillo(this.ladrillos, (5-j)*10, this.xMenor + 200 + i*100, this.yMenor + 100 + j*40, 89, 28, false);
			}
		}
	}
	
	/*-----------FUNCIONES------------*/
	public void agregarLadrillo(ArrayList<Ladrillo>ladrillos, int valor, int x, int y, int ancho, int alto, boolean estado) {
		Ladrillo nuevoLadrillo = new Ladrillo(valor,x,y,ancho,alto,estado);
		ladrillos.add(nuevoLadrillo);
	}
	
	public void aumentoVida() {
		this.vidas++;
	}
	
	public void disminuyoVida() {
		this.vidas--;	
	}
	
	public void sumarPuntaje(int puntaje) {
		this.setPuntos(this.getPuntos() + puntaje);
	}
	
	public void aumentarNivel() {
		this.setNivel(this.getNivel() + 1);
		this.bola.aumentarVelocidad(this.bola.getVelocidad()+0.3);
		this.pausarPartida();
		this.resetearLadrillo();
		this.resetearBola();
		this.resetearBarra();
	}
	
	public void pausarPartida() {
		this.setEstadoPartida(2);
	}
	
	public void reanudarPartida() {
		this.setEstadoPartida(3);
	}
	
	public boolean chocoLadrillo(Ladrillo ladrillo) {
		if (ladrillo != null) {
			boolean arriba = (this.bola.getPosicionY() + 2 * this.bola.getRadio() >= ladrillo.getPosicionY());
			boolean abajo = (this.bola.getPosicionY() - ladrillo.getAlto() <= ladrillo.getPosicionY());
			boolean izquierda = (this.bola.getPosicionX() + 2 * this.bola.getRadio() >= ladrillo.getPosicionX());
			boolean derecha = (this.bola.getPosicionX() - ladrillo.getAncho() <= ladrillo.getPosicionX());
			return (arriba && abajo && izquierda && derecha);
		}
		else {
			return false;
		}
	}
	
	public boolean chocoBarra() {
		return (this.barra.getPosicionX()<=this.bola.getPosicionX()+2*this.bola.getRadio() &&  this.barra.getPosicionX()>=this.bola.getPosicionX()-this.barra.getAncho() &&this.barra.getPosicionY()<=(this.bola.getPosicionY() + this.bola.getRadio()));
	}
	
	public boolean chocoPared() {
		return ((this.bola.getPosicionX() + this.bola.getRadio()) - this.xMayor >= 1.0||(this.bola.getPosicionX() ) - this.xMenor<= 1.0);
	}
	
	public boolean chocoTecho() {
		return (this.bola.getPosicionY()- this.bola.getRadio())<= this.yMenor;
	}
	
	public boolean chocoPiso() {
		boolean choco = this.bola.getPosicionY()>this.barra.getPosicionY();
		if (choco) {
			this.disminuyoVida();
		}
		return choco;
	}
	
	public void cambiarAnguloTecho() {
		if (this.getChoqueBola() != 1) {
			if(esta4toCuadrante()) {
				this.bola.setAnguloMovimiento(270-(360-this.bola.getAnguloMovimiento()));
			}
			else if(esta1erCuadrante()) {
				this.bola.setAnguloMovimiento(180-this.bola.getAnguloMovimiento());
			}
			this.setChoqueBola(1);
			if(this.bola.getAnguloMovimiento()< 0) {
				this.bola.setAnguloMovimiento(360);
			}
			else if (this.bola.getAnguloMovimiento()>360) {
				this.bola.setAnguloMovimiento(-360);
			}
		}
	}
	
	public void cambiarAnguloBarra() {
		if (this.getChoqueBola() != 2) {
			if(esta3erCuadrante() && this.bola.getPosicionX()<= this.barra.getPosicionX()) {
				this.bola.setAnguloMovimiento(360-(this.bola.getAnguloMovimiento()-180));//-90	
			}
			else if (esta3erCuadrante() && this.bola.getPosicionX()>this.barra.getPosicionX()) {
				this.bola.setAnguloMovimiento(360-(this.bola.getAnguloMovimiento()-180));//-60
			}
			else if (esta2doCuadrante() && this.bola.getPosicionX()<= this.barra.getPosicionX()) {
				this.bola.setAnguloMovimiento((180-this.bola.getAnguloMovimiento()- 90));//90-
			}
			else if (esta2doCuadrante() && this.bola.getPosicionX()>this.barra.getPosicionX()) {
				this.bola.setAnguloMovimiento((180-this.bola.getAnguloMovimiento()));//60-
			}
			this.setChoqueBola(2);
		}
	}

	public void cambiarAnguloPared() {
		if (this.getChoqueBola() != 3) {
			if(esta1erCuadrante()) {
				this.bola.setAnguloMovimiento(360-(this.bola.getAnguloMovimiento()));
			}
			else if (esta2doCuadrante()) {
				this.bola.setAnguloMovimiento(180+(180-this.bola.getAnguloMovimiento()));
			}
			else if (esta3erCuadrante()) {
				this.bola.setAnguloMovimiento(180-(this.bola.getAnguloMovimiento()-180));
			}
			else if (esta4toCuadrante()) {
				this.bola.setAnguloMovimiento(360-this.bola.getAnguloMovimiento());
			}
			this.setChoqueBola(3);
		}
	}

	public void cambiarAnguloLadrillo(Ladrillo ladrillo) {
	double indice = 3.6;
	if(esta1erCuadrante()) {
		if(this.bola.getPosicionY() - this.bola.getRadio() - ladrillo.getAlto()/2 - this.bola.getVelocidad() * indice <= ladrillo.getPosicionY() && this.bola.getPosicionY() - this.bola.getRadio() - ladrillo.getAlto()/2 + this.bola.getVelocidad() * indice >= ladrillo.getPosicionY()) {
			this.bola.setAnguloMovimiento(this.bola.getAnguloMovimiento()+ThreadLocalRandom.current().nextInt(85, 95 + 1));
		} else {
			this.bola.setAnguloMovimiento(this.bola.getAnguloMovimiento()-ThreadLocalRandom.current().nextInt(85, 95 + 1));
		}
	} else if (esta2doCuadrante()) {
		if(this.bola.getPosicionY() + this.bola.getRadio() + ladrillo.getAlto()/2 - this.bola.getVelocidad() * indice <= ladrillo.getPosicionY() && this.bola.getPosicionY() + this.bola.getRadio() + ladrillo.getAlto()/2 + this.bola.getVelocidad() * indice >= ladrillo.getPosicionY()) {
			this.bola.setAnguloMovimiento(this.bola.getAnguloMovimiento()-ThreadLocalRandom.current().nextInt(85, 95 + 1));
		} else {
			this.bola.setAnguloMovimiento(this.bola.getAnguloMovimiento()+ThreadLocalRandom.current().nextInt(85, 95 + 1));
		}
	} else if (esta3erCuadrante()) {
		if(this.bola.getPosicionY() + this.bola.getRadio() + ladrillo.getAlto()/2 - this.bola.getVelocidad() * indice <= ladrillo.getPosicionY() && this.bola.getPosicionY() + this.bola.getRadio() + ladrillo.getAlto()/2 + this.bola.getVelocidad() * indice >= ladrillo.getPosicionY()) {
			this.bola.setAnguloMovimiento(this.bola.getAnguloMovimiento()+ThreadLocalRandom.current().nextInt(85, 95 + 1));
		} else {
			this.bola.setAnguloMovimiento(this.bola.getAnguloMovimiento()-ThreadLocalRandom.current().nextInt(85, 95 + 1));
		}
	} else if (esta4toCuadrante()) {
		if(this.bola.getPosicionY() - this.bola.getRadio() - ladrillo.getAlto()/2 - this.bola.getVelocidad() * indice <= ladrillo.getPosicionY() && this.bola.getPosicionY() - this.bola.getRadio() - ladrillo.getAlto()/2 + this.bola.getVelocidad() * indice >= ladrillo.getPosicionY()) {
			this.bola.setAnguloMovimiento(this.bola.getAnguloMovimiento()-ThreadLocalRandom.current().nextInt(85, 95 + 1));
		} else {
			this.bola.setAnguloMovimiento(this.bola.getAnguloMovimiento()+ThreadLocalRandom.current().nextInt(85, 95 + 1));
		}
	}
}
	
	public boolean chequearColision() {
		boolean colisiono = false;
		if (chocoBarra()) {
			cambiarAnguloBarra();
			colisiono = true;
		}
		else if (chocoTecho()) {
			cambiarAnguloTecho();
			colisiono = true;
		}
		else if (chocoPared()) {
			cambiarAnguloPared();
			colisiono = true;
		}
		else if(chocoPiso()) {					
			resetearBola();
			resetearBarra();
			randomInicioBola();
			this.estadoPartida = 4;
		}
		else {
			boolean choco = false;
			int cont = 0;
			while(cont< this.ladrillos.size() && !choco) {
				if(!this.ladrillos.get(cont).estadoLadrillo()) {
					choco = chocoLadrillo(this.ladrillos.get(cont));
				}
				cont++;
				
			}
			if(choco) {
				cambiarAnguloLadrillo(this.ladrillos.get(cont-1));
				this.setPuntos(this.getPuntos() + this.ladrillos.get(cont-1).destruir());
				this.setPuntosRelativos(this.getPuntosRelativos() + this.ladrillos.get(cont-1).destruir());
				if (this.getPuntosRelativos() >= 1000) {
					aumentoVida();
					this.setPuntosRelativos(this.getPuntosRelativos()-1000);
				}
				colisiono = true;
			}
		}
		return colisiono;
	}
	
	public boolean esta4toCuadrante() {
		return (Math.abs(this.bola.getAnguloMovimiento())>270 && Math.abs(this.bola.getAnguloMovimiento())< 360);
	}
	
	public boolean esta3erCuadrante() {
		return (Math.abs(this.bola.getAnguloMovimiento())>180 && Math.abs(this.bola.getAnguloMovimiento())< 270);
	}
	
	public boolean esta2doCuadrante() {
		return (Math.abs(this.bola.getAnguloMovimiento())>90 && Math.abs(this.bola.getAnguloMovimiento())< 180);
	}
	
	public boolean esta1erCuadrante() {
		return (Math.abs(this.bola.getAnguloMovimiento())>0 && Math.abs(this.bola.getAnguloMovimiento())< 90);
	}
	
	public void resetearBola() {
		this.bola.setPosicionInicio(414,487);
	}
	
	public void resetearBarra() {
		this.barra.setPosicionInicio(380,500);
	}
	
	public void resetearLadrillo() {
		for(int i=0; i<this.ladrillos.size();i++) {
			this.ladrillos.get(i).resetearLadrillo();
		}
	}
	
	public void movimientoBola() {
		double angulo = this.bola.getAnguloMovimiento();
		double xVelocidad = (this.bola.getVelocidad())*1 * Math.sin(Math.toRadians(angulo));
		double yVelocidad =  (this.bola.getVelocidad())*1 * Math.cos(Math.toRadians(angulo));
		double posX = this.bola.getPosicionX();
		double posY = this.bola.getPosicionY();
		if (this.ubicacionFinalBolaX >= 400) { 
			posX = posX + xVelocidad;
			this.bola.setPosicionX(posX);
		} else {
			posX = posX - xVelocidad;
			this.bola.setPosicionX(posX);
		}
		posY = posY - yVelocidad;
		this.bola.setPosicionY(posY);
	}
	
	public void randomInicioBola() {
		int posX = ThreadLocalRandom.current().nextInt(0, 870 + 1);
		int posY = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
		
		this.ubicacionFinalBolaX = posX;
		double angulo =  Math.toDegrees(Math.atan2(Math.abs(posX - this.bola.getPosicionX()), Math.abs(this.bola.getPosicionY()- posY ))); 
		this.bola.setAnguloMovimiento(angulo);
		this.bola.setPosicionInicio((int)this.bola.getPosicionX(), (int)this.bola.getPosicionY());
	}
	
	public void aumentarPosicionBolaEnBarra(int velocidad) {
		this.bola.setPosicionX(this.bola.getPosicionX()+ velocidad);
	}
	
	public void restarPosicionBolaEnBarra(int velocidad) {
		this.bola.setPosicionX(this.bola.getPosicionX()- velocidad);
	}
	
	public int tamLadrillos(int cont) {
		return cont = this.ladrillos.size();
	}

	public boolean estadoLadrillo(int id) {
		return this.ladrillos.get(id).estadoLadrillo();
	}

	/*-----------GETTERS------------*/
	public int getEstadoPartida() {
		return estadoPartida;
	}
	
	public int getVidas() {
		return this.vidas;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public Rectangle getPosicionBolaXY() {
		return new Rectangle((int) this.bola.getPosicionX(), (int) this.bola.getPosicionY(), 20, 20);
	}
	
	public int getLadrillosDestruidos() {
		int destruido = 0;
		for(int i=0; i<this.ladrillos.size();i++) {
			if(this.ladrillos.get(i).estadoLadrillo()) {
				destruido++;
			}		
		}
		return destruido;
	}
	
	public double getPosXBola() {
		return this.bola.getPosicionX();
	}
	
	public int getPosXLadrillo(int id) {
		return this.ladrillos.get(id).getPosicionX();
	}
	
	public int getPosYLadrillo(int id) {
		return this.ladrillos.get(id).getPosicionY();
	}
	
	public int getAlturaLadrillo(int id) {
		return this.ladrillos.get(id).getAlto();
	}
	
	public int getAnchoLadrillo(int id) {
		return this.ladrillos.get(id).getAncho();
	}
	
	public int getChoqueBola() {
		return choqueBola;
	}
	
	public double getVelocidadBola() {
		return this.bola.getVelocidad();
	}
	
	public int getPuntosRelativos() {
		return this.puntosRelativos;
	}
	
	/*-----------SETTERS------------*/
	public void setEstadoPartida(int estadoPartida) {
		this.estadoPartida = estadoPartida;
	}
	
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	public void setPosXBola(int pos) {
		this.bola.setPosicionX(pos);
	}
	
	public void setChoqueBola(int valor) {
		this.choqueBola = valor;
	}
	
	public void setPuntosRelativos(int puntos) {
		this.puntosRelativos = puntos;
	}

}






