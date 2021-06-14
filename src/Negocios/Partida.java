package Negocios;

import java.awt.Rectangle;
import java.util.ArrayList;
import Negocios.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
	private int ubicacionFinalBolaX;
	
	public Partida(Barra barraCreada) {
		this.vidas 	= 3;
		this.setNivel(1);
		this.setPuntos(0);
		this.setEstadoPartida(false);
		this.bola = new Bola(422, 505, 2, 20, 0);
		this.barra = barraCreada;
		this.xMayor = 870; 	//Pared Derecha  //ANCHO=700 LARGO=500
		this.xMenor = 0;	//Pared Izquierda
		this.yMayor = 1000;	 //Piso
		this.yMenor = 0;	//Techo
		this.ubicacionFinalBolaX = 0;
		this.ladrillos = new ArrayList <Ladrillo>();
		for (int i = 0; i<5; i++) {
			for(int j = 0 ; j<5; j++) {
				agregarLadrillo(this.ladrillos, (i+1)*10, this.xMenor + 200 + i*100, this.yMenor + 100 + j*40, 89, 28, false);
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
	}
	
	public void pausarPartida() {
		this.setEstadoPartida(false);
	}
	
	public void reanudarPartida() {
		this.setEstadoPartida(true);
	}
	
	public boolean chocoLadrillo(Ladrillo ladrillo) {
		if (ladrillo != null)
		{
			int posBolaXAumentada = (int)this.bola.getPosicionX() + 30;
			int posBolaXDisminuida = (int)this.bola.getPosicionX() - 30;
			int posBolaYAumentada = (int)this.bola.getPosicionY() +30;
			int posBolaYDisminuida = (int)this.bola.getPosicionY() -30;
			return (ladrillo.getPosicionX()>=posBolaXDisminuida && ladrillo.getPosicionX()<=posBolaXAumentada && ladrillo.getPosicionY()>=posBolaYDisminuida && ladrillo.getPosicionY()>=posBolaYAumentada);
		}
		else 
			return false;
	}
	
	public boolean chocoBarra() {
		return (this.barra.getPosicionX()==this.bola.getPosicionX()||this.barra.getPosicionY()==this.bola.getPosicionY());
	}
	
	public boolean chocoPared() {
		return (this.bola.getPosicionX()>=this.xMayor||this.bola.getPosicionX()<=this.xMenor);
	}
	
	public boolean chocoTecho() {
		return this.bola.getPosicionY()<= this.yMenor;
	}
	
	public boolean chocoPiso() {
		boolean choco = this.bola.getPosicionY()>=this.yMayor;
																									//this.xMayor = 870; Pared Derecha  //ANCHO=700 LARGO=500
																									//this.xMenor = 0;	 Mueve derecha //Pared Izquierda
																									//this.yMayor = 1000; // Piso
																									//this.yMenor = 0; //Techo
		if (choco) {
			this.disminuyoVida();
		}
		return choco;
	}
	
	public void cambiarAnguloTecho() {
		if(esta1erCuadrante()) {
			this.bola.setAnguloMovimiento(-180);
			/*int posx = (int) this.bola.getPosicionX();
			posx *= -1;
			this.bola.setPosicionX(posx);*/
			System.out.println("ENTRO EN COLISION TECHO POSITIVO");
		}
		else if (esta2doCuadrante()) {
			this.bola.setAnguloMovimiento(+180);
			/*int posx = (int) this.bola.getPosicionX();
			posx *= -1;
			this.bola.setPosicionX(posx);*/
			System.out.println("ENTRO EN COLISION TECHO POSITIVO");
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
			this.bola.setAnguloMovimiento(+180);
			System.out.println("ENTRO EN COLISION PARED");
		}
		else if (esta2doCuadrante()) {
			this.bola.setAnguloMovimiento(-180);
			System.out.println("ENTRO EN COLISION PARED");
		}
		else if (esta3erCuadrante()) {
			this.bola.setAnguloMovimiento(+180);
			System.out.println("ENTRO EN COLISION PARED");
		}
		else if (esta4toCuadrante()) {
			this.bola.setAnguloMovimiento(-180);
			System.out.println("ENTRO EN COLISION PARED");
		}
	}
	
	public void cambiarAnguloLadrillo(Ladrillo ladrillo) {
		//Random num = new Random();
		if(esta1erCuadrante()) {
			//System.out.println("Angulo PRIMER cuadrante: ");  
			//----ME QUEDE ACA, LA BOLA LE PEGA AL LADRILLO  Y LO BORRA PERO NO CAMBIA EL ANGULO POR QUE NO ENTRA EN EL PRIMER IF DE CADA CUADRANTE QUE CHEQUEA POS+ANCHO/ALTO

			if(this.bola.getPosicionX() == (ladrillo.getPosicionX()- ladrillo.getAncho())) {
				this.bola.setAnguloMovimiento(ThreadLocalRandom.current().nextInt(95, 85 + 1));
			}
			else if(this.bola.getPosicionY() == (ladrillo.getPosicionY()- ladrillo.getAlto())) {
				this.bola.setAnguloMovimiento(-(ThreadLocalRandom.current().nextInt(95, 85 + 1)));
			}
		}
		else if (esta2doCuadrante()) {
			//System.out.println("Angulo SEGUNDO cuadrante: ");
			if(this.bola.getPosicionX() == (ladrillo.getPosicionX()+ ladrillo.getAncho())) {
				this.bola.setAnguloMovimiento(-(ThreadLocalRandom.current().nextInt(95, 85 + 1)));
			}
			else if(this.bola.getPosicionY() == (ladrillo.getPosicionY()- ladrillo.getAlto())) {
				this.bola.setAnguloMovimiento(ThreadLocalRandom.current().nextInt(95, 85 + 1));
			}
		}
		else if (esta3erCuadrante()) {
			//System.out.println("Angulo TERCER cuadrante: ");
			if(this.bola.getPosicionX() == (ladrillo.getPosicionX()+ ladrillo.getAncho())) {
				this.bola.setAnguloMovimiento(ThreadLocalRandom.current().nextInt(95, 85 + 1));
			}
			else if(this.bola.getPosicionY() == (ladrillo.getPosicionY()+ ladrillo.getAlto())) {
				this.bola.setAnguloMovimiento(-(ThreadLocalRandom.current().nextInt(95, 85 + 1)));
			}
		}
		else if (esta4toCuadrante()) {
			//System.out.println("Angulo CUARTO cuadrante: ");
			if(this.bola.getPosicionX() == (ladrillo.getPosicionX()- ladrillo.getAncho())) {
				this.bola.setAnguloMovimiento(-(ThreadLocalRandom.current().nextInt(95, 85 + 1)));
			}
			else if(this.bola.getPosicionY() == (ladrillo.getPosicionY()+ ladrillo.getAlto())) {
				this.bola.setAnguloMovimiento(ThreadLocalRandom.current().nextInt(95, 85 + 1));
			}
		}
		
	}
	
	public boolean chequearColision() {
		boolean colisiono = false;
		if (chocoBarra()) {
			cambiarAnguloBarra();
			System.out.println("ENTRO EN COLISION BARRA");
			colisiono = true;
		}
		else if (chocoTecho()) {
			cambiarAnguloTecho();
			System.out.println("ENTRO EN COLISION TECHO");
			colisiono = true;
		}
		else if (chocoPared()) {
			cambiarAnguloPared();
			System.out.println("ENTRO EN COLISION pared");
			colisiono = true;
		}
		else if(chocoPiso()) {
			System.out.println("ENTRO EN COLISION PISO");						
			resetearBola();
			resetearBarra();
		}
		else {
			boolean choco = false;
			int cont = 0;
			while(cont< this.ladrillos.size() && !choco) {
				choco = chocoLadrillo(this.ladrillos.get(cont));
				cont++;
				
			}
			if(choco) {
				//System.out.println("CHOQUE LADRILLO: ENTRO ACA");
				cambiarAnguloLadrillo(this.ladrillos.get(cont-1));
				this.setPuntos(this.getPuntos() + this.ladrillos.get(cont-1).destruir());
				colisiono = true;
			}
		}
		return colisiono;
	}
	
	public boolean esta1erCuadrante() {
		return (Math.abs(this.bola.getAnguloMovimiento())>0 && Math.abs(this.bola.getAnguloMovimiento())< 90);
	}
	
	public boolean esta2doCuadrante() {
		return (Math.abs(this.bola.getAnguloMovimiento())>90 && Math.abs(this.bola.getAnguloMovimiento())< 180);
	}
	
	public boolean esta3erCuadrante() {
		return (Math.abs(this.bola.getAnguloMovimiento())>180 && Math.abs(this.bola.getAnguloMovimiento())< 270);
	}
	
	public boolean esta4toCuadrante() {
		return (Math.abs(this.bola.getAnguloMovimiento())>270 && Math.abs(this.bola.getAnguloMovimiento())< 360);
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
	
	public void movimientoBola() {
		double angulo = this.bola.getAnguloMovimiento();
		//System.out.println("Angulo: " + this.bola.getAnguloMovimiento());
		//System.out.println("Angulo : " + angulo);
		
		double xVelocidad = (this.bola.getVelocidad())*1 * Math.sin(angulo);
		double yVelocidad =  (this.bola.getVelocidad())*1 * Math.cos(angulo);
		//System.out.println("Angulo EQUIS: " + xVelocidad);
		//System.out.println("Angulo Y: " + yVelocidad);
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
		//System.out.println("PosX: " + posX);
		//System.out.println("PosY: " + posY);
	}
	
	public void subirVelocidad() {
		this.bola.aumentarVelocidad(1);
	}
	
	public void randomInicioBola() {
		//Random num = new Random();
		//int angulo = (num.nextInt((0-180)+1)+0);
		int posX =	ThreadLocalRandom.current().nextInt(0, 870 + 1);
		int posY =  ThreadLocalRandom.current().nextInt(0, 1000 + 1);
		System.out.println("PosX" + posX);
		System.out.println("PosY" + posY);
		this.ubicacionFinalBolaX = posX;
		double angulo =   Math.atan2(Math.abs(posX - 422), Math.abs(505- posY ));
		System.out.println("Angulo INICIO" + angulo);
		
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
	public boolean getEstadoPartida() {
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
	
	/*-----------SETTERS------------*/
	public void setEstadoPartida(boolean estadoPartida) {
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

}






