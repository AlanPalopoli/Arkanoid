package Negocios;

public class Bola {
	private double posicionX;
	private double posicionY;
	private int velocidad;
	private int radio;
	private double anguloMovimiento;

	public Bola (int xInicio, int yInicio, int velPrede, int radioPrede, int anguloPrede) {
		this.setPosicionX(xInicio);
		this.setPosicionY(yInicio);
		this.setVelocidad(velPrede);
		this.radio = radioPrede;
		this.setAnguloMovimiento(anguloPrede);
		
	}
	
	public void aumentarVelocidad(int velocidadAumentada) {
		this.setVelocidad(velocidadAumentada);
	}


	public double getPosicionX() {
		return posicionX;
	}


	public void setPosicionX(double posX) {
		this.posicionX = posX;
	}


	public double getPosicionY() {
		return posicionY;
	}


	public void setPosicionY(double posY) {
		this.posicionY = posY;
	}


	public double getAnguloMovimiento() {
		return anguloMovimiento;
	}


	public void setAnguloMovimiento(double angulo) {
		this.anguloMovimiento += angulo;
	}
	
	public void setPosicionInicio(int xInicio, int yInicio) {
		this.setPosicionX(xInicio);
		this.setPosicionY(yInicio);
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
}


