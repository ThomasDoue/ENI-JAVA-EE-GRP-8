package fr.eni.Enchere.dal;

import fr.eni.Enchere.bo.Utilisateur;

public interface UtilisateurDao {
	
	// appelle de la fonction loginCheck 
	int connect(String username, String password) throws DALException, Exception;
	
	
	//appelle de la fonction inscription
	void inscription (Utilisateur user) throws DALException;
	
	//appelle de la fonction selectUser
	Utilisateur selectUser(Integer no_utlisateur) throws DALException;
	
	void Delete(int id) throws DALException;
	
}
