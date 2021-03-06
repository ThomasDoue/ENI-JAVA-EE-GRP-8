package fr.eni.Enchere.dal;

import fr.eni.Enchere.bo.Utilisateur;

public interface UtilisateurDao {
	
	// appelle de la fonction loginCheck 
	int connect(String username, String password) throws DALException, Exception;
	
	
	//appelle de la fonction inscription
	void inscription (Utilisateur user) throws DALException,Exception;
	
	//appelle de la fonction selectUser
	Utilisateur selectUser(Integer no_utlisateur) throws DALException;
	
	//appelle de la fonction delette
	void Delete(int id) throws DALException;
	
	//appelle de la fonction mise a jours
	void UpdateUser (Utilisateur modifUser) throws DALException;
	
	//Permet de récupérer l'id du vendeur à partir 
	public int SelectIdVendeurByIdEnchere(int idEnchere) throws DALException;
	
	
}
