package fr.eni.Enchere.bll;

import fr.eni.Enchere.bo.Utilisateur;

public interface UtilisateurManager {
	
	boolean verifLogin(String username, String password) throws BLLException;
	
	Utilisateur selectUser(Integer no_utlisateur) throws BLLException;
	
	

}
