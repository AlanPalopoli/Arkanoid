package Negocios;

public class Ladrillo {
	private boolean destruido;
	private int valor;
	private int posicionX;
	private int posicionY;
	private int ancho;
	private int alto;
	
	public Ladrillo(int valorInicial, int xInicio, int yInicio, int anchoInicio, int altoInicio) {
		this.valor = valorInicial;
		this.setPosicionX(xInicio);
		this.setPosicionY(yInicio);
		this.setAncho(anchoInicio);
		this.setAlto(altoInicio);
		this.destruido = false;
	}
	
	public int destruir() {
		this.destruido = true;
		return this.valor;
	}
	public int getValorLadrillo() {
		return this.valor;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}
	public void resetearLadrillo() {
		this.destruido = false;
	}
	

}
