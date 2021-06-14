package Negocios;

import java.util.ArrayList;
import Negocios.*;
import java.util.Random;

public class Partida {
	private int vidas;
	private int puntos;
	private int nivel;
	private boolean estadoPartida;
	private ArrayList <Ladrillo> ladrillos;
	private Bola bola;
	private Barra barra;
	private int xMayor;
	private int xMenor;
	private int yMayor;
	private int yMenor;
	
	public Partida(Barra barraCreada) {
		this.vidas 	= 3;
		this.setNivel(1);
		this.setPuntos(0);
		this.setEstadoPartida(false);
		this.bola = new Bola(0, 0, 0, 0, 0);
		this.barra = barraCreada;
		this.xMayor = 0; 	//definir valores
		this.xMenor = 0;	//definir valores
		this.yMayor = 0;	//definir valores
		this.yMenor = 0;	//definir valores
		this.ladrillos = new ArrayList <Ladrillo>();
		for (int i = 0; i<5; i++) {
			for(int j = 0 ; j<5; j++) {
				agregarLadrillo(this.ladrillos, (i+1)*10, this.xMenor + 10 + j*10, this.yMenor + 100 + i*10, 0, 0);
			}
			
		}
	}
	
	public void agregarLadrillo(ArrayList<Ladrillo>ladrillos, int valor, int x, int y, int ancho, int alto) {
		Ladrillo nuevoLadrillo = new Ladrillo(valor,x,y,ancho,alto);
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
	}
	
	public void pausarPartida() {
		this.setEstadoPartida(false);
	}
	
	public void reanudarPartida() {
		this.setEstadoPartida(true);
	}
	
	public int getVidas() {
		return this.vidas;
	}
	
	public boolean chocoLadrillo(Ladrillo ladrillo) {
		return (ladrillo.getPosicionX()==this.bola.getPosicionX()||ladrillo.getPosicionY()==this.bola.getPosicionY());
		
	}
	
	public boolean chocoBarra() {
		return (this.barra.getPosicionX()==this.bola.getPosicionX()||this.barra.getPosicionY()==this.bola.getPosicionY());
	}
	
	public boolean chocoPared() {
		return (this.bola.getPosicionX()==this.xMenor||this.bola.getPosicionX()==this.xMayor);
	}
	
	public boolean chocoTecho() {
		return this.bola.getPosicionY()==this.yMayor;
	}
	
	public boolean chocoPiso() {
		boolean choco = this.bola.getPosicionY() == this.yMenor;
		if (choco) {
			this.disminuyoVida();
		}
		return choco;
	}
	
	public void cambiarAnguloTecho() {
		if(esta1erCuadrante()) {
			this.bola.setAnguloMovimiento(-90);
		}
		else if (esta2doCuadrante()) {
			this.bola.setAnguloMovimiento(+90);
		}
	}
	
	public void cambiarAnguloBarra() {
		if(esta3erCuadrante() && this.bola.getPosicionX()<= this.barra.getPosicionX()) {
			this.bola.setAnguloMovimiento(-90);	
		}
		else if (esta3erCuadrante() && this.bola.getPosicionX()>this.barra.getPosicionX()) {
			this.bola.setAnguloMovimiento(-60);
		}
		else if (esta4toCuadrante() && this.bola.getPosicionX()<= this.barra.getPosicionX()) {
			this.bola.setAnguloMovimiento(+90);	
		}
		else if (esta4toCuadrante() && this.bola.getPosicionX()>this.barra.getPosicionX()) {
			this.bola.setAnguloMovimiento(+60);
		}
	}

	public void cambiarAnguloPared() {
		if(esta1erCuadrante()) {
			this.bola.setAnguloMovimiento(+90);
		}
		else if (esta2doCuadrante()) {
			this.bola.setAnguloMovimiento(-90);
		}
		else if (esta3erCuadrante()) {
			this.bola.setAnguloMovimiento(+90);
		}
		else if (esta4toCuadrante()) {
			this.bola.setAnguloMovimiento(-90);
		}
	}
	
	public void cambiarAnguloLadrillo(Ladrillo ladrillo) {
		Random num = new Random();
		if(esta1erCuadrante()) {
			if(this.bola.getPosicionX() == (ladrillo.getPosicionX()- ladrillo.getAncho())) {
				this.bola.setAnguloMovimiento(num.nextInt((95-85)+1)+85);
			}
			else if(this.bola.getPosicionY() == (ladrillo.getPosicionY()- ladrillo.getAlto())) {
				this.bola.setAnguloMovimiento(-(num.nextInt((95-85)+1)+85));
			}
		}
		else if (esta2doCuadrante()) {
			if(this.bola.getPosicionX() == (ladrillo.getPosicionX()+ ladrillo.getAncho())) {
				this.bola.setAnguloMovimiento(-(num.nextInt((95-85)+1)+85));
			}
			else if(this.bola.getPosicionY() == (ladrillo.getPosicionY()- ladrillo.getAlto())) {
				this.bola.setAnguloMovimiento(num.nextInt((95-85)+1)+85);
			}
		}
		else if (esta3erCuadrante()) {
			if(this.bola.getPosicionX() == (ladrillo.getPosicionX()+ ladrillo.getAncho())) {
				this.bola.setAnguloMovimiento(num.nextInt((95-85)+1)+85);
			}
			else if(this.bola.getPosicionY() == (ladrillo.getPosicionY()+ ladrillo.getAlto())) {
				this.bola.setAnguloMovimiento(-(num.nextInt((95-85)+1)+85));
			}
		}
		else if (esta4toCuadrante()) {
			if(this.bola.getPosicionX() == (ladrillo.getPosicionX()- ladrillo.getAncho())) {
				this.bola.setAnguloMovimiento(-(num.nextInt((95-85)+1)+85));
			}
			else if(this.bola.getPosicionY() == (ladrillo.getPosicionY()+ ladrillo.getAlto())) {
				this.bola.setAnguloMovimiento(num.nextInt((95-85)+1)+85);
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
		else {
			boolean choco = false;
			int cont = 0;
			while(cont< this.ladrillos.size() && !choco) {
				choco = chocoLadrillo(this.ladrillos.get(cont));
				cont++;
			}
			if(choco) {
				cambiarAnguloLadrillo(this.ladrillos.get(cont-1));
				this.setPuntos(this.getPuntos() + this.ladrillos.get(cont-1).destruir());
				colisiono = true;
			}
		}
		return colisiono;
	}
	
	public boolean esta1erCuadrante() {
		return (this.bola.getAnguloMovimiento()>0 && this.bola.getAnguloMovimiento()< 90);
	}
	public boolean esta2doCuadrante() {
		return (this.bola.getAnguloMovimiento()>90 && this.bola.getAnguloMovimiento()< 180);
	}
	public boolean esta3erCuadrante() {
		return (this.bola.getAnguloMovimiento()>180 && this.bola.getAnguloMovimiento()< 270);
	}
	public boolean esta4toCuadrante() {
		return (this.bola.getAnguloMovimiento()>270 && this.bola.getAnguloMovimiento()< 360);
	}

	public boolean getEstadoPartida() {
		return estadoPartida;
	}

	public void setEstadoPartida(boolean estadoPartida) {
		this.estadoPartida = estadoPartida;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public void resetearBola() {
		this.bola.setPosicionInicio(0,0);
	}
	public void resetearBarra() {
		this.barra.setPosicionInicio(0,0);
	}
	public void resetearLadrillo() {
		for(int i=0; i<this.ladrillos.size();i++) {
			this.ladrillos.get(i).resetearLadrillo();
		}
	}

	
}





