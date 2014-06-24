import java.awt.AWTEvent;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.AWTEventListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.OutputStream;

import examenes.psicometrico.domain.Trama;
import examenes.psicometrico.domain.TramaPsicologico;


public class RobotTest implements Runnable,  AWTEventListener {
	private boolean run=true;
	private Trama trama;
	private int X;
	private int Y;

	public RobotTest() throws Exception{
		int i=0;
		Robot robot = new Robot();
		robot.delay(4000);

		//runExamenVisionAudio(robot);
		runExamenPsicometrico(robot);
	}
	
	public static void main(String[] args) throws Exception {
		

	   
	    
		RobotTest robotTest=new RobotTest();
		
		
	}
	
	public void runExamenPsicometrico(Robot robot) throws Exception
	{
		trama=new TramaPsicologico();
		TramaPsicologico tramaPsicologico=(TramaPsicologico)trama;
		trama.addCampo(tramaPsicologico.campoCabecera1);
		trama.addCampo(tramaPsicologico.campoCabecera2);
		trama.addCampo(tramaPsicologico.campoCabecera3);
		trama.addCampo(tramaPsicologico.campoCabecera4);
		
		trama.addCampo(new Byte(new Integer(0).byteValue()));
		trama.addCampo(new Byte(new Integer(0).byteValue()));
		trama.addCampo(new Byte(new Integer(0).byteValue()));
		trama.addCampo(new Byte(new Integer(0).byteValue()));//Freno 2 setFrenoPressed();
		trama.addCampo(new Byte(new Integer(0).byteValue()));
		
		trama.addCampo(tramaPsicologico.campoCierre1);
		
		(new Thread(this)).start();
		
		
		int i=0;
		while(run)
		{
			seleccionarPersonayExamenPsicometrico(robot);
			realizarAutoYFreno(robot);
			realizarCoordinacionBimanual(robot);
			realizarReaccionesMultiples(robot);
			realizarReaccionesSimple(robot);
			guardarExamenPsicometrico(robot);
			
			//irAPersona(robot);
			i++;
			robot.delay(1000);
			System.out.println("Cantidad: "+i);
		}
		
	}
	
	public void realizarCoordinacionBimanual(Robot robot) throws Exception
	{
		TramaPsicologico tramaPsicologico=(TramaPsicologico)trama;
	    mouseMove(robot,200, 270);//Realizar CoordinacionBimanual
	    leftClick(robot);

	    tramaPsicologico.setPotenciometroDerecho(new Byte(new Integer(0x75).byteValue()));
	    tramaPsicologico.setPotenciometroIzquierdo(new Byte(new Integer(0x55).byteValue()));
	    
	    mouseMove(robot,472, 280);//Realizar Examen
	    leftClick(robot);
	    Thread.sleep((60+12)*1000);//1.5 minutos
	    
	    mouseMove(robot, 723, 280);// Boton guardar
	    leftClick(robot);
	    robot.delay(400);
	    
	    mouseMove(robot, 870, 456);// Boton Aceptar
	    leftClick(robot);

	}
	
	public void realizarReaccionesMultiples(Robot robot) throws Exception
	{
		TramaPsicologico tramaPsicologico=(TramaPsicologico)trama;
	    mouseMove(robot,214, 333);//Realizar realizarReaccionesMultiples
	    leftClick(robot);
	    
	    Thread.sleep(1000);
	    
	    tramaPsicologico.setFrenoPressed();
	    
	    mouseMove(robot,472, 280);//Realizar Examen
	    leftClick(robot);
	    
	    long timeIni=System.currentTimeMillis();
	    
	    System.out.println("antes de la espera");
	    
	    Thread.sleep((45)*1000);
	    
	    System.out.println("despues de la espera: "+((System.currentTimeMillis())-timeIni));
	    
	    mouseMove(robot, 723, 280);// Boton guardar
	    leftClick(robot);
	    robot.delay(400);
	    
	    mouseMove(robot, 870, 456);// Boton Aceptar
	    leftClick(robot);

	}
	
	public void guardarExamenPsicometrico(Robot robot)
	{
	    mouseMove(robot,214,451);//Finalizar examen
	    leftClick(robot);
	    robot.delay(1000);
		
	    mouseMove(robot,874,676);//Radio guardar
	    leftClick(robot);
	    robot.delay(3000);
	    
	    mouseMove(robot,1118,89);//Cerrar reporte
	    leftClick(robot);
	    robot.delay(100);
	    
	    mouseMove(robot,889,460);//Cancelar seguir tomando
	    leftClick(robot);
	    robot.delay(100);
	}
	
	public void realizarReaccionesSimple(Robot robot) throws Exception
	{
		TramaPsicologico tramaPsicologico=(TramaPsicologico)trama;
	    mouseMove(robot,214, 389);//Realizar realizarReaccionesSimple
	    leftClick(robot);
	    
	    mouseMove(robot,472, 280);//Realizar Examen
	    leftClick(robot);
	    	    
	    //1
	    tramaPsicologico.setFrenoNotPressed();
	    tramaPsicologico.setAceleradorPressed();
	    Thread.sleep(3000);
	    tramaPsicologico.setAceleradorNotPressed();
	    tramaPsicologico.setFrenoPressed();
	    Thread.sleep(3000);
	    
	    //2
	    tramaPsicologico.setFrenoNotPressed();
	    tramaPsicologico.setAceleradorPressed();
	    Thread.sleep(3000);
	    tramaPsicologico.setAceleradorNotPressed();;
	    tramaPsicologico.setFrenoPressed();
	    Thread.sleep(3000);
	    
	    //3
	    tramaPsicologico.setFrenoNotPressed();
	    tramaPsicologico.setAceleradorPressed();
	    Thread.sleep(3000);
	    tramaPsicologico.setAceleradorNotPressed();;
	    tramaPsicologico.setFrenoPressed();
	    Thread.sleep(2000);
	    
	    mouseMove(robot, 723, 280);// Boton guardar
	    leftClick(robot);
	    robot.delay(400);
	    
	    mouseMove(robot, 870, 456);// Boton Aceptar
	    leftClick(robot);

	}
	
	public void seleccionarPersonayExamenPsicometrico(Robot robot)
	{
		mouseMove(robot,340, 300);//Boton buscar	    

		leftClick(robot);
	    
	    mouseMove(robot,100, 500);//Lista de personas
	    leftClick(robot);
	    robot.delay(700);
	    
	    mouseMove(robot,375, 128);//Boton realizar examen
	    leftClick(robot);
	    robot.delay(700);
	    
	    mouseMove(robot,687, 382);//Boton examen psicometrixo
	    leftClick(robot);
	    robot.delay(700);
	    

	}
	
	public void irAPersona(Robot robot) throws Exception
	{
		
	    mouseMove(robot,127, 72);//Opcion de menú persona
	    leftClick(robot);
	}
	
	public void realizarAutoYFreno(Robot robot) throws Exception
	{
		TramaPsicologico tramaPsicologico=(TramaPsicologico)trama;
		
	    mouseMove(robot,472, 280);//Realizar Examen
	    leftClick(robot);
	    Thread.sleep(4000);
	    
	    tramaPsicologico.setFrenoPressed();//Primera etapa
	    Thread.sleep(100);
	    tramaPsicologico.setFrenoNotPressed();
	    Thread.sleep(4000);
	    	    	
	    tramaPsicologico.setFrenoPressed();//Segunda etapa
	    Thread.sleep(100);
	    tramaPsicologico.setFrenoNotPressed();
	    Thread.sleep(4000);
	    
	    tramaPsicologico.setFrenoPressed();//Tercera etapa
	    Thread.sleep(100);
	    tramaPsicologico.setFrenoNotPressed();
	    Thread.sleep(4000);
	    
	    tramaPsicologico.setFrenoPressed();//Cuarta etapa
	    Thread.sleep(100);
	    tramaPsicologico.setFrenoNotPressed();
	    Thread.sleep(4000);
	    
	    tramaPsicologico.setFrenoPressed();//Quinta etapa
	    Thread.sleep(100);
	    tramaPsicologico.setFrenoNotPressed();
	    Thread.sleep(4000);
	    
	    tramaPsicologico.setFrenoPressed();//Sexta etapa
	    Thread.sleep(100);
	    tramaPsicologico.setFrenoNotPressed();
	    robot.delay(1000);
	    
	    mouseMove(robot, 723, 280);// Boton guardar
	    leftClick(robot);
	    robot.delay(400);
	    
	    mouseMove(robot, 870, 456);// Boton Aceptar
	    leftClick(robot);

	}
	
	
	
	public void mouseMove(Robot robot,int x,int y)
	{
		if(run)
		{
			this.X=x;
			this.Y=y;
			robot.mouseMove(x,y);
		}
		
	}
	
	
	public void runExamenVisionAudio(Robot robot)
	{
		trama=new TramaPsicologico();
		TramaPsicologico tramaPsicologico=(TramaPsicologico)trama;
		trama.addCampo(tramaPsicologico.campoCabecera1);
		trama.addCampo(tramaPsicologico.campoCabecera2);
		trama.addCampo(tramaPsicologico.campoCabecera3);
		trama.addCampo(tramaPsicologico.campoCabecera4);
		
		(new Thread(this)).start();
		int i=0;
		while(run)
		{
			seleccionarPersonayExamenDeVision(robot);
			realizarExamenAgudezaVisial(robot);
			realizarExamenIshijara(robot);
			realizarExamenProfundidad(robot);
			realizarExamenTestForia(robot);
			realizarExamenVisionNocturna(robot);
			realizarExamenEncandilamiento(robot);
			realizarExamenRecEncandilamiento(robot);
			realizarExamenFotocromatica(robot);
			realizarExamenCampimetria(robot);
			realizarExamenAudio(robot);
			guardarExamenVisualAudio(robot);
			
			i++;
			robot.delay(10000);
			System.out.println("Cantidad: "+i);
		}
		

	}
	
	@Override
	public void eventDispatched(AWTEvent event) {
		System.out.println(event);
		
	}
	
	@Override
	public void run() {
		try{
	
			OutputStream out=new FileOutputStream("C://temp//tramaInput.txt");
		
			while(run)
			{
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				int x = (int) b.getX();
				int y = (int) b.getY();
				
//				System.out.println("X "+X+" , x "+x);
//				System.out.println("Y "+Y+" , y "+x);
				
				if(x==0 || y==0)
				{
					System.out.println("SALIRRRRRRR");
					run=false;
				}

				byte[] toWrite=new byte[trama.getTrama().length];
				for(int i=0;i<toWrite.length;i++)
				{
					toWrite[i]=trama.getByte(i);
					
					
				}
				
				out.write(toWrite);
				Thread.sleep(50);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
	}

	
	
	
	public void seleccionarPersonayExamenDeVision(Robot robot)
	{
	    mouseMove(robot,340, 300);//Boton buscar	    
	    leftClick(robot);
	    
	    mouseMove(robot,100, 500);//Lista de personas
	    leftClick(robot);
	    robot.delay(500);
	    
	    mouseMove(robot,375, 128);//Boton realizar examen
	    leftClick(robot);
	    robot.delay(500);
	    
	    mouseMove(robot,600, 500);//Boton Examend de audio
	    leftClick(robot);
	    robot.delay(1000);
	}
	
	public void realizarExamenAgudezaVisial(Robot robot)
	{
		mouseMove(robot,560, 575);//Linea correcta vision lejana (izq)
	    leftClick(robot);
	    keyPress(robot,KeyEvent.VK_8);
	    robot.delay(100);
	    
	    mouseMove(robot,635,295);//Radio der
	    leftClick(robot);
	    keyPress(robot,KeyEvent.VK_8);
	    robot.delay(100);
	    
	    mouseMove(robot,900,295);//Radio ambos
	    leftClick(robot);
	    keyPress(robot,KeyEvent.VK_8);
	    robot.delay(100);
   
	    mouseMove(robot,541,260);//Radio cercana
	    leftClick(robot);
	    robot.delay(100);
	    
	    mouseMove(robot,358,295);//Radio izq
	    leftClick(robot);
	    keyPress(robot,KeyEvent.VK_8);
	    robot.delay(100);
	    
	    mouseMove(robot,635,295);//Radio der
	    leftClick(robot);
	    keyPress(robot,KeyEvent.VK_8);
	    robot.delay(100);
	    
	    mouseMove(robot,900,295);//Radio ambos
	    leftClick(robot);
	    keyPress(robot,KeyEvent.VK_8);
	    robot.delay(100);
	    
	    mouseMove(robot,1146,716);// boton guardar
	    leftClick(robot);
	    robot.delay(100);
	}
	
	public void realizarExamenIshijara(Robot robot)
	{
	    mouseMove(robot,440, 420);//Boton 6
	    leftClick(robot);
	    robot.delay(100);
	    
	    mouseMove(robot,440, 586);//Boton 5
	    leftClick(robot);
	    robot.delay(100);
	    
	    mouseMove(robot,695,650);//Radio guardar
	    leftClick(robot);
	    robot.delay(100);
	}
	
	public void realizarExamenProfundidad(Robot robot)
	{
	    mouseMove(robot,412 ,661);//primer plano
	    leftClick(robot);
	    robot.delay(100);	    

	    
	    mouseMove(robot,733,708);//Radio guardar
	    leftClick(robot);
	    robot.delay(100);
	}
	
	public void realizarExamenTestForia(Robot robot)
	{
		keyPress(robot,KeyEvent.VK_4);
		keyPress(robot,KeyEvent.VK_TAB);
		keyPress(robot,KeyEvent.VK_A);
	    //robot.delay(1000);	   
	    
	    mouseMove(robot,952,558);//Radio guardar
	    leftClick(robot);
	    robot.delay(100);
	}
	
	public void realizarExamenVisionNocturna(Robot robot)
	{
		mouseMove(robot,674,478);//Boton rectangulo
		leftClick(robot);
	    robot.delay(100);
	    
	    mouseMove(robot,952,558);//Radio guardar
	    leftClick(robot);
	    robot.delay(100);
	}
	
	public void realizarExamenEncandilamiento(Robot robot)
	{
	    mouseMove(robot,1240,684);//Radio guardar
	    leftClick(robot);
	    robot.delay(100);
	}
	
	public void realizarExamenRecEncandilamiento(Robot robot)
	{
		robot.delay(500);
	    mouseMove(robot,448,484);//Radio guardar
	    leftClick(robot);
	    robot.delay(100);
	    
	    mouseMove(robot,910,618);//Radio guardar
	    leftClick(robot);
	    robot.delay(100);
	}
	
	public void realizarExamenFotocromatica(Robot robot)
	{
	    mouseMove(robot,391,471);//B
	    leftClick(robot);
	    robot.delay(100);
	    
	    mouseMove(robot,556,471);//B2
	    leftClick(robot);
	    robot.delay(100);
	    
	    mouseMove(robot,888,707);//Boton guardar
	    leftClick(robot);
	    robot.delay(100);
	}
	
	public void realizarExamenCampimetria(Robot robot)
	{
	    mouseMove(robot,457,340);// der 40 grados
	    leftClick(robot);
	    mouseMove(robot,457,383);// der 85
	    leftClick(robot);

	    mouseMove(robot,1230,580);//Boton guardar
	    leftClick(robot);

	}
	
	public void realizarExamenAudio(Robot robot)
	{
	    mouseMove(robot,1208,676);//Radio guardar
	    leftClick(robot);
	    robot.delay(100);
	}
	
	public void guardarExamenVisualAudio(Robot robot)
	{
	    mouseMove(robot,874,676);//Radio guardar
	    leftClick(robot);
	    robot.delay(3000);
	    
	    mouseMove(robot,1118,89);//Cerrar reporte
	    leftClick(robot);
	    robot.delay(100);
	    
	    mouseMove(robot,889,460);//Cancelar seguir tomando
	    leftClick(robot);
	    robot.delay(100);
	}
	
	
	
	private void leftClick(Robot robot)
	  {
		if(run){
		    robot.mousePress(InputEvent.BUTTON1_MASK);
		    robot.delay(200);
		    robot.mouseRelease(InputEvent.BUTTON1_MASK);
		    robot.delay(200);
		}

	  }
	
	private void keyPress(Robot robot,int key)
	  {
		if(run){
			robot.keyPress(key);
		}

	  }
	  
	
	
	  private void type(Robot robot,int i)
	  {
	    robot.delay(40);
	    keyPress(robot,i);
	    robot.keyRelease(i);
	  }

	  private void type(Robot robot,String s)
	  {
	    byte[] bytes = s.getBytes();
	    for (byte b : bytes)
	    {
	      int code = b;
	      // keycode only handles [A-Z] (which is ASCII decimal [65-90])
	      if (code > 96 && code < 123) code = code - 32;
	      robot.delay(40);
	      keyPress(robot,code);
	      robot.keyRelease(code);
	    }
	  }


}
