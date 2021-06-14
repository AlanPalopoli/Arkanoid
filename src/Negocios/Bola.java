package Negocios;

public class Bola {
	private int posicionX;
	private int posicionY;
	private int velocidad;
	private int radio;
	private int anguloMovimiento;

	public Bola (int xInicio, int yInicio, int velPrede, int radioPrede, int anguloPrede) {
		this.setPosicionX(xInicio);
		this.setPosicionY(yInicio);
		this.velocidad = velPrede;
		this.radio = radioPrede;
		this.setAnguloMovimiento(anguloPrede);
		
	}
	
	public void aumentarVelocidad(int velocidadAumentada) {
		this.velocidad = velocidadAumentada;
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


	public int getAnguloMovimiento() {
		return anguloMovimiento;
	}


	public void setAnguloMovimiento(int anguloMovimiento) {
		this.anguloMovimiento += anguloMovimiento;
	}
	
	public void setPosicionInicio(int xInicio, int yInicio) {
		this.setPosicionX(xInicio);
		this.setPosicionY(yInicio);
	}


}


