package fr.eni.Enchere.bll;

public interface UtilisateurManager {
	
	boolean verifLogin(String username, String password) throws BLLException;

}
