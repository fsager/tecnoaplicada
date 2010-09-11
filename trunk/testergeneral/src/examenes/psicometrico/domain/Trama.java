package examenes.psicometrico.domain;

public interface Trama {

	public abstract  Trama getInstance();
	public abstract boolean addCampo(Byte campo);
	
	public abstract void setTrama(Byte[] trama);
	
	public Byte[] getTrama();
	
	public abstract boolean sync(Byte campo);
	
	public abstract  boolean isValid();
	
	public abstract boolean isPositionValid();
	
	public abstract boolean isCabecaraValid();
	
	public abstract boolean isCierreValid();
	
	public boolean isFrenoPressed();
	
	public void setFrenoPressed();
	
	public boolean isAceleradorPressed();
	
	public boolean isAnyButtonPress();
	
	public boolean isButton1Press();
	
	public boolean isButton2Press();
	
	public boolean isButton3Press();
	
	public boolean isButton4Press();
	
	public boolean isButton5Press();
	
	public boolean isButton6Press();

	public void init();
	
	public byte getByte(int pos);
	
	public boolean isButtonNadaPress();
	
	public void setButtonNadaPressed();
	
	public boolean isPalancaInInicio();
	
	public int getPotenciometroIzquierdo();
	
	public int getPotenciometroDerecho();
}
