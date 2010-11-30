package examenes.psicometrico.domain;

import org.apache.commons.lang.NotImplementedException;

import testerGeneral.threads.ThreadTrama;

public class TramaAudio implements Trama{
	
	private Byte campoCabecera1=new Byte(new Integer(0x33).byteValue());
	private Byte campoCabecera2=new Byte(new Integer(0x42).byteValue());
	private Byte campoCabecera3=new Byte(new Integer(0x43).byteValue());
	private Byte campoCabecera4=new Byte(new Integer(0x48).byteValue());
	private Byte campoCierre1=new Byte(new Integer(0x0D).byteValue());
	
	private int campoNro=0;
	private Byte[] trama=new Byte[10];
	
	public boolean addCampo(Byte campo)
	{
		if(campoNro<trama.length)
		{
			trama[campoNro]=campo;
			/*if(campoNro==5 || campoNro==4)
			{
				
			}*/
			//System.out.println("campo : "+campoNro+" "+Integer.toHexString((int)campo));
			campoNro++;	
			
			return true;
		}
		
		campoNro=0;
		return false;	
	}
	
	public void setTrama(Byte[] tramaVision)
	{
		this.trama=tramaVision;
	}
	
	public Byte[] getTrama()
	{
		return trama;
	}
	
	public boolean sync(Byte campo)
	{
		//System.out.println("campo: "+Integer.toHexString((int)campo));
		trama[campoNro]=campo;
		if(isPositionValid())
		{
			campoNro++;
			if(isValid())
				return true;
		}
		else
			campoNro=0;
		
		return false;
	}
	
	public boolean isValid()
	{
		
		if(!isCabecaraValid() || !isCierreValid())
		{
			return false;
		}
		return true;
	}
	
	public boolean isPositionValid()
	{
		switch (campoNro) {
			case 0:{
				return trama[campoNro].compareTo(campoCabecera1)!=0 ? false :  true;
			}
			case 1:{
				return trama[campoNro].compareTo(campoCabecera2)!=0 ? false :  true;
			}
			case 2:{
				return trama[campoNro].compareTo(campoCabecera3)!=0 ? false :  true;
			}
			case 3:{
				return trama[campoNro].compareTo(campoCabecera4)!=0 ? false :  true;
			}
			case 9:{
				return trama[campoNro].compareTo(campoCierre1)!=0 ? false :  true;
			}
			default: return true;
		}
	}
	
	public boolean isCabecaraValid()
	{
		try
		{
			if(trama[0].compareTo(campoCabecera1)!=0)
				return false;
			if(trama[1].compareTo(campoCabecera2)!=0)
				return false;
			if(trama[2].compareTo(campoCabecera3)!=0)
				return false;
			if(trama[3].compareTo(campoCabecera4)!=0)
				return false;
		}
		catch (NullPointerException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean isCierreValid()
	{
		try
		{
			if(trama[9].compareTo(campoCierre1)!=0)
				return false;
		
		}
		catch (NullPointerException e) {
			return false;
		}
		return true;
	}
	
	public void init() {
		campoNro=0;
		trama=new Byte[10];
	}

	public int getFrecuencia()
	{
		return trama[4];
	}
	
	public String getFrecuencia(int frecuencia)
	{
		switch (frecuencia) {
			case 1: return "250 Hz";
			case 2: return "500 Hz";
			case 3: return "1000 Hz";
			case 4: return "2000 Hz";
			case 5: return "3000 Hz";
			case 6: return "4000 Hz";
			case 7: return "6000 Hz";
			case 8: return "8000 Hz";
		default:
			throw new RuntimeException("Descripcion para la frecuencia: "+frecuencia+" no definido.");
		}
	}
	
	public int getDbSubida()
	{
		return trama[5];
	}
	
	public int getDbBajanda()
	{
		return trama[6];
	}
	
	public int getOido()
	{
		return trama[7];
	}
	
	public String getOido(int oido)
	{
		switch (oido) {
			case 0: return "Oído derecho";
			case 1: return "Oído izquierdo";
			case 2: return "Ambos oídos";
		default:
			throw new RuntimeException("Descripcion para el oido: "+oido+" no definido.");
		}
	}
	
	public boolean isTestNotRunning()
	{	
		if(trama!=null && trama[8]!= null && trama[8]==0)
			return true;
		
		return false;
	}
	
	public boolean isDerButtonPress()
	{
		throw new NotImplementedException();
	}
	
	public boolean isIzqButtonPress()
	{
		throw new NotImplementedException();
	}
	
	
	@Override
	public byte getByte(int pos) {
		return trama[pos];
	}
	
	
	@Override
	public Trama getInstance() {
		
		return new TramaAudio();
	}

	@Override
	public int getPotenciometroDerecho() {
		throw new NotImplementedException();
	}

	@Override
	public int getPotenciometroIzquierdo() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isAceleradorPressed() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isAnyButtonPress() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isButton1Press() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isButton2Press() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isButton3Press() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isButton4Press() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isButton5Press() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isButton6Press() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isButtonNadaPress() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isFrenoPressed() {
		throw new NotImplementedException();
	}

	@Override
	public boolean isPalancaInInicio() {
		throw new NotImplementedException();
	}

	@Override
	public void setButtonNadaPressed() {
		throw new NotImplementedException();
		
	}

	@Override
	public void setFrenoPressed() {
		throw new NotImplementedException();
		
	}

	@Override
	public void desconnect(ThreadTrama threadTrama) {
		threadTrama.sendOrden(threadTrama.ORDEN_STOP_AUTOMATICO);
		//throw new NotImplementedException();
	}
	
}
