package examenes.psicometrico.domain;


public class ParametrosAnticipacion {

	private Integer x_pos;
	private Integer y_pos;
	private Integer x_posPared;
	private Integer y_posPared;
	private Integer x_posPare;
	private Integer y_posPare;
	private Integer x_speed;	
	private Integer widthObjeto;
	private Integer heigthObjeto;
	private Integer width;
	private Integer posicionObjetivo;	
	
	public ParametrosAnticipacion() {

	}

	
	public Integer getPosicionObjetivo() {
		return posicionObjetivo;
	}


	public void setPosicionObjetivo(Integer posicionObjetivo) {
		this.posicionObjetivo = posicionObjetivo;
	}


	public Integer getX_posPared() {
		return x_posPared;
	}

	public void setX_posPared(Integer pared) {
		x_posPared = pared;
	}

	public Integer getY_posPared() {
		return y_posPared;
	}

	public void setY_posPared(Integer pared) {
		y_posPared = pared;
	}

	public Integer getX_posPare() {
		return x_posPare;
	}

	public void setX_posPare(Integer pare) {
		x_posPare = pare;
	}

	public Integer getY_posPare() {
		return y_posPare;
	}

	public void setY_posPare(Integer pare) {
		y_posPare = pare;
	}
	
	public Integer getX_pos() {
		return x_pos;
	}

	public void setX_pos(Integer x_pos) {
		this.x_pos = x_pos;
	}

	public Integer getY_pos() {
		return y_pos;
	}

	public void setY_pos(Integer y_pos) {
		this.y_pos = y_pos;
	}

	public Integer getX_speed() {
		return x_speed;
	}

	public void setX_speed(Integer x_speed) {
		this.x_speed = x_speed;
	}

	public Integer getWidthObjeto() {
		return widthObjeto;
	}

	public void setWidthObjeto(Integer widthObjeto) {
		this.widthObjeto = widthObjeto;
	}

	public Integer getHeigthObjeto() {
		return heigthObjeto;
	}

	public void setHeigthObjeto(Integer heigthObjeto) {
		this.heigthObjeto = heigthObjeto;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}	
	
}
