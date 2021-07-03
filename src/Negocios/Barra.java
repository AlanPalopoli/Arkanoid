package Negocios;

public class Barra {
	private int posicionX;
	private int posicionY;
	private int ancho;
	private int alto;
	private int velocidad;
	
	public Barra(int xInicio, int yInicio, int anchoPrede, int altoPrede, int velPrede) {
		this.setPosicionX(xInicio);
		this.setPosicionY(yInicio);
		this.setAncho(anchoPrede);
		this.setAlto(altoPrede);
		this.setVelocidad(velPrede);
	}
	/*-----------FUNCIONES------------*/
	public void moverDerecha() {
		this.setPosicionX(this.getPosicionX() + this.velocidad);
	}
	
	public void moverIzquierda() {
		this.setPosicionX(this.getPosicionX() - this.velocidad);
	}
	
	/*-----------GETTERS------------*/
	public int getPosicionY() {
		return posicionY;
	}
	
	public int getPosicionX() {
		return posicionX;
	}
	
	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	
	public int getVelocidad() {
		return velocidad;
	}

	/*-----------SETTERS------------*/
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public void setPosicionInicio(int xInicio, int yInicio) {
		this.setPosicionX(xInicio);
		this.setPosicionY(yInicio);
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}


	public void setAlto(int alto) {
		this.alto = alto;
	}

	
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

}
