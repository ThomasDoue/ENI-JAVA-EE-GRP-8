package fr.eni.Enchere.bll;

public class BLLException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BLLException( ) {
		
	}
	
	public BLLException(String message) {
		super(message);
	}
	
	public BLLException(String message, Exception exc) {
		super(message, exc);
	}

}
