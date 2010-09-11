package examenes.psicometrico.domain;

public class TramaPsicologico implements Trama{
	
	private Byte campoCabecera1=new Byte(new Integer(0x31).byteValue());
	private Byte campoCabecera2=new Byte(new Integer(0x42).byteValue());
	private Byte campoCabecera3=new Byte(new Integer(0x43).byteValue());
	private Byte campoCabecera4=new Byte(new Integer(0x48).byteValue());
	private Byte campoCierre1=new Byte(new Integer(0x0D).byteValue());
	
	private int campoNro=0;
	private Byte[] tramaPsicologico=new Byte[10];
	

	public boolean addCampo(Byte campo)
	{
		if(campoNro<tramaPsicologico.length)
		{

			tramaPsicologico[campoNro]=campo;
			/*if(campoNro==5 || campoNro==4)
			{
				System.out.println("campo : "+campoNro+" "+Integer.toHexString((int)campo));
			}*/
			campoNro++;	
			
			return true;
		}
		
		campoNro=0;
		return false;	
	}
	
	public void setTrama(Byte[] tramaPsicologico)
	{
		this.tramaPsicologico=tramaPsicologico;
	}
	
	public Byte[] getTrama()
	{
		return tramaPsicologico;
	}
	
	public boolean sync(Byte campo)
	{
		//System.out.println("campo: "+Integer.toHexString((int)campo));
		tramaPsicologico[campoNro]=campo;
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
				return tramaPsicologico[campoNro].compareTo(campoCabecera1)!=0 ? false :  true;
			}
			case 1:{
				return tramaPsicologico[campoNro].compareTo(campoCabecera2)!=0 ? false :  true;
			}
			case 2:{
				return tramaPsicologico[campoNro].compareTo(campoCabecera3)!=0 ? false :  true;
			}
			case 3:{
				return tramaPsicologico[campoNro].compareTo(campoCabecera4)!=0 ? false :  true;
			}
			case 9:{
				return tramaPsicologico[campoNro].compareTo(campoCierre1)!=0 ? false :  true;
			}
			default: return true;
		}
	}
	
	public boolean isCabecaraValid()
	{
		try
		{
			if(tramaPsicologico[0].compareTo(campoCabecera1)!=0)
				return false;
			if(tramaPsicologico[1].compareTo(campoCabecera2)!=0)
				return false;
			if(tramaPsicologico[2].compareTo(campoCabecera3)!=0)
				return false;
			if(tramaPsicologico[3].compareTo(campoCabecera4)!=0)
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
			if(tramaPsicologico[9].compareTo(campoCierre1)!=0)
				return false;
		
		}
		catch (NullPointerException e) {
			return false;
		}
		return true;
	}
	
	public boolean isFrenoPressed()
	{
		int freno=2;
		
		if(tramaPsicologico[7]==freno)
			return true;
		
		return false;
	}
	
	public void setFrenoPressed()
	{
		tramaPsicologico[7]=2;
	}
	
	public boolean isAceleradorPressed()
	{
		int acelerador=1;
		
		if(tramaPsicologico[7]==acelerador)
			return true;
		
		return false;
	}
	
	public boolean isAnyButtonPress()
	{	
		//System.out.println("isAnyButtonPress: "+tramaPsicologico[6] +" "+ tramaPsicologico[7]);
		if(isButton1Press())
			return true;
		else if(isButton2Press())
			return true;
		else if(isButton3Press())
			return true;
		else if(isButton4Press())
			return true;
		else if(isButton5Press())
			return true;
		else if(isButton6Press())
			return true;
		else if(isFrenoPressed())
			return true;
		else if(isAceleradorPressed())
			return true;
		
		return false;
	}
	
	public boolean isButton1Press()
	{
		int btn=1;
		
		if(tramaPsicologico!=null && tramaPsicologico[6]!= null && tramaPsicologico[6]==btn)
			return true;
		
		return false;
	}
	
	public boolean isButton2Press()
	{
		int btn=2;
		
		if(tramaPsicologico!=null && tramaPsicologico[6]!= null && tramaPsicologico[6]==btn)
			return true;
		
		return false;
	}
	
	public boolean isButton3Press()
	{
		int btn=4;
		
		if(tramaPsicologico!=null && tramaPsicologico[6]!= null && tramaPsicologico[6]==btn)
			return true;
		
		return false;
	}
	
	public boolean isButton4Press()
	{
		int btn=8;
		
		if(tramaPsicologico!=null && tramaPsicologico[6]!= null && tramaPsicologico[6]==btn)
			return true;
		
		return false;
	}
	
	public boolean isButton5Press()
	{
		int btn=16;
		
		if(tramaPsicologico!=null && tramaPsicologico[6]!= null && tramaPsicologico[6]==btn)
			return true;
		
		return false;
	}
	
	public boolean isButton6Press()
	{
		int btn=32;
		
		if(tramaPsicologico!=null && tramaPsicologico[6]!= null && tramaPsicologico[6]==btn)
			return true;
		
		return false;
	}
	
	public boolean isButtonNadaPress()
	{
		int btn=0;
		
		if(tramaPsicologico!=null && tramaPsicologico[6]!= null && tramaPsicologico[6]==btn && !isAceleradorPressed() && !isFrenoPressed())
			return true;
		
		return false;
	}
	
	public void setButtonNadaPressed()
	{
		tramaPsicologico[6]=64;
	}

	public void init() {
		campoNro=0;
		tramaPsicologico=new Byte[10];

	}

	@Override
	public byte getByte(int pos) {
		return tramaPsicologico[pos];
	}
	
	public boolean isPalancaInInicio()
	{
		try
		{
			if(tramaPsicologico[8]==1)
				return true;
		}
		catch(Exception e)
		{return false;}
		
		return false;
	}

	@Override
	public Trama getInstance() {
		
		return new TramaPsicologico();
	}
	
	@Override
	public int getPotenciometroIzquierdo() {
		
		return tramaPsicologico[4];
	}
	
	@Override
	public int getPotenciometroDerecho() {
		
		return tramaPsicologico[5];
	}
}
