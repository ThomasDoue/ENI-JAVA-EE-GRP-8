package fr.eni.Enchere.bll;

import fr.eni.Enchere.bo.Utilisateur;

public interface UtilisateurManager {
	
	int verifConnect(String username, String password) throws BLLException, Exception;
	
	Utilisateur selectUser(Integer no_utlisateur) throws BLLException;

	void InscriptionUtilisateur(Utilisateur usr) throws BLLException;
	
	void miseAjourUtilisateur (Utilisateur modifUser) throws BLLException;
	
	void supprimerUtilisateur(int id) throws BLLException;
}
