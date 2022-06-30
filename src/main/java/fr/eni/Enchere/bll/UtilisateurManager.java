package fr.eni.Enchere.bll;

import fr.eni.Enchere.bo.Utilisateur;

public interface UtilisateurManager {
	
	int verifConnect(String username, String password) throws BLLException, Exception;
	
	Utilisateur selectUser(Integer no_utlisateur) throws BLLException;

	void InscriptionUtilisateur(Utilisateur usr) throws BLLException;
	
	void UpdateUser(Utilisateur user) throws BLLException;
}
