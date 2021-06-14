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
		this.ancho = anchoPrede;
		this.alto = altoPrede;
		this.velocidad = velPrede;
	}
	
	public void moverDerecha() {
		this.setPosicionX(this.getPosicionX() + this.velocidad);
	}
	
	public void moverIzquierda() {
		this.setPosicionX(this.getPosicionX() - this.velocidad);
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public void setPosicionInicio(int xInicio, int yInicio) {
		this.setPosicionX(xInicio);
		this.setPosicionY(yInicio);
	}

}
