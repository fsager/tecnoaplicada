package testerGeneral.exceptions;

public class NotImplementedException extends RuntimeException {

	public NotImplementedException()
	{
		super("M�todo no implementado");
	}
	
	public NotImplementedException(String error)
	{
		super(error);
	}
	
}
