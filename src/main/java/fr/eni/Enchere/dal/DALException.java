package fr.eni.Enchere.dal;

public class DALException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DALException() {
		
	}
	
	public DALException(String message) {
		super(message);
	}
	
	public DALException(String message, Throwable exc) {
		super(message, exc);
	}

}
