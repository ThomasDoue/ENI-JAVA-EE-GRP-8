package fr.eni.Enchere.dal;

import fr.eni.Enchere.bo.Utilisateur;

public interface UtilisateurDao {
	boolean connect (String username, String password) throws DALException;
	void inscription (Utilisateur user) throws DALException;
}
