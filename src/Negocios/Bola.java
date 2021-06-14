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
		this.setRadio(radioPrede);
		this.setAnguloMovimiento(anguloPrede);
		
	}
	/*-----------FUNCIONES------------*/
	public void aumentarVelocidad(int velocidadAumentada) {
		this.setVelocidad(velocidadAumentada);
	}

	/*-----------GETTERS------------*/
	public double getPosicionX() {
		return posicionX;
	}
	
	public double getPosicionY() {
		return posicionY;
	}

	public double getAnguloMovimiento() {
		return anguloMovimiento;
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	public int getRadio() {
		return radio;
	}
	
	/*-----------SETTERS------------*/
	public void setPosicionX(double posX) {
		this.posicionX = posX;
	}

	public void setPosicionY(double posY) {
		this.posicionY = posY;
	}

	public void setAnguloMovimiento(double angulo) {
		this.anguloMovimiento += angulo;
	}
	
	public void setPosicionInicio(int xInicio, int yInicio) {
		this.setPosicionX(xInicio);
		this.setPosicionY(yInicio);
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}
	
}


