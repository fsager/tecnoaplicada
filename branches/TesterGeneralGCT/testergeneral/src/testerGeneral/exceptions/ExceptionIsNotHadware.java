package testerGeneral.exceptions;

public class ExceptionIsNotHadware extends RuntimeException {

	public ExceptionIsNotHadware()
	{
		super();
	}
	
	public ExceptionIsNotHadware(String error)
	{
		super(error);
	}
}
