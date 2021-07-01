package Negocios;

public class Bola {
	private double posicionX;
	private double posicionY;
	private double velocidad;
	private int radio;
	private double anguloMovimiento;

	public Bola (int xInicio, int yInicio, double velPrede, int radioPrede, int anguloPrede) {
		this.setPosicionX(xInicio);
		this.setPosicionY(yInicio);
		this.setVelocidad(velPrede);
		this.setRadio(radioPrede);
		this.setAnguloMovimiento(anguloPrede);
		
	}
	/*-----------FUNCIONES------------*/
	public void aumentarVelocidad(double velocidadAumentada) {
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
	
	public double getVelocidad() {
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
		this.anguloMovimiento = angulo;
		if(this.anguloMovimiento< 0) {
			this.setAnguloMovimiento(360+this.anguloMovimiento);
		}
		else if (this.anguloMovimiento>360) {
			this.setAnguloMovimiento(this.anguloMovimiento-360);
		}
	}
	
	public void setPosicionInicio(int xInicio, int yInicio) {
		this.setPosicionX(xInicio);
		this.setPosicionY(yInicio);
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}
	
}


