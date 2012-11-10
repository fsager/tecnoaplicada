package testerGeneral.exceptions;

public class NotImplementedException extends RuntimeException {

	public NotImplementedException()
	{
		super("Método no implementado");
	}
	
	public NotImplementedException(String error)
	{
		super(error);
	}
	
}
