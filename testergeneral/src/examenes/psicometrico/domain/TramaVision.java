package examenes.psicometrico.domain;



import testerGeneral.exceptions.NotImplementedException;
import testerGeneral.threads.ThreadTrama;

public class TramaVision implements Trama{
	
	private Byte campoCabecera1=new Byte(new Integer(0x32).byteValue());
	private Byte campoCabecera2=new Byte(new Integer(0x42).byteValue());
	private Byte campoCabecera3=new Byte(new Integer(0x43).byteValue());
	private Byte campoCabecera4=new Byte(new Integer(0x48).byteValue());
	private Byte campoCierre1=new Byte(new Integer(0x0D).byteValue());
	
	
	
	private int campoNro=0;
	private Byte[] tramaVision=new Byte[7];
	
	public boolean addCampo(Byte campo)
	{
		if(campoNro<tramaVision.length)
		{
			tramaVision[campoNro]=campo;
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
		this.tramaVision=tramaVision;
	}
	
	public Byte[] getTrama()
	{
		return tramaVision;
	}
	
	public boolean sync(Byte campo)
	{
		//System.out.println("campo: "+Integer.toHexString((int)campo));
		tramaVision[campoNro]=campo;
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
				return tramaVision[campoNro].compareTo(campoCabecera1)!=0 ? false :  true;
			}
			case 1:{
				return tramaVision[campoNro].compareTo(campoCabecera2)!=0 ? false :  true;
			}
			case 2:{
				return tramaVision[campoNro].compareTo(campoCabecera3)!=0 ? false :  true;
			}
			case 3:{
				return tramaVision[campoNro].compareTo(campoCabecera4)!=0 ? false :  true;
			}
			case 7:{
				return tramaVision[campoNro].compareTo(campoCierre1)!=0 ? false :  true;
			}
			default: return true;
		}
	}
	
	public boolean isCabecaraValid()
	{
		try
		{
			if(tramaVision[0].compareTo(campoCabecera1)!=0)
				return false;
			if(tramaVision[1].compareTo(campoCabecera2)!=0)
				return false;
			if(tramaVision[2].compareTo(campoCabecera3)!=0)
				return false;
			if(tramaVision[3].compareTo(campoCabecera4)!=0)
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
			if(tramaVision[6].compareTo(campoCierre1)!=0)
				return false;
		
		}
		catch (NullPointerException e) {
			return false;
		}
		return true;
	}
	

	public void init() {
		campoNro=0;
		tramaVision=new Byte[7];

	}

	public boolean isDerButtonPress()
	{
		int buttonDer=1;
		
		if(tramaVision[4]==buttonDer)
			return true;
		
		return false;
	}
	
	public boolean isIzqButtonPress()
	{
		int buttonIzq=2;
		
		if(tramaVision[4]==buttonIzq)
			return true;
		
		return false;
	}
	
	
	@Override
	public byte getByte(int pos) {
		return tramaVision[pos];
	}
	
	
	@Override
	public Trama getInstance() {
		
		return new TramaVision();
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
		//TODO COMENTE ESTA LINEA PORQUE NO SE PORQUE ESTA 
		//threadTrama.sendOrden(0x5000);
		threadTrama.sendOrden(ThreadTrama.ORDEN_CAMBIA_ESTADO_LUZ_IZQ);
		threadTrama.sendOrden(ThreadTrama.ORDEN_CAMBIA_ESTADO_LUZ_DER);
		threadTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_TEST_LAMINAS);
		threadTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_TEST_PERIMETRIA);
	}
	
}
