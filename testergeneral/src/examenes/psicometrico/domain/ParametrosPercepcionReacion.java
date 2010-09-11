package examenes.psicometrico.domain;

import java.awt.Image;

public class ParametrosPercepcionReacion {

	public final static String PULSADOR_0="isButtonNadaPress";//NADA
	public final static String PULSADOR_1="isButton1Press";
	public final static String PULSADOR_2="isButton2Press";
	public final static String PULSADOR_3="isButton3Press";
	public final static String PULSADOR_4="isButton4Press";
	public final static String PULSADOR_5="isButton5Press";
	public final static String PULSADOR_6="isButton6Press";
	
	
	
	public final static String PULSADOR_ACELERADOR="isAceleradorPressed";
	public final static String PULSADOR_FRENO="isFrenoPressed";
	
	private Image img=null;
	private String text=null;
	private String pulsador=null;
	private String sonido=null;
	private String textInfo=null;
	
	
	public ParametrosPercepcionReacion() {
	}
	
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPulsador() {
		return pulsador;
	}
	public void setPulsador(String pulsador) {
		this.pulsador = pulsador;
	}
	
	public String getSonido() {
		return sonido;
	}	
	public void setSonido(String sonido) {
		this.sonido = sonido;
	}
	
	public String getTextInfo() {
		return textInfo;
	}
	public void setTextInfo(String textInfo) {
		this.textInfo = textInfo;
	}
}
