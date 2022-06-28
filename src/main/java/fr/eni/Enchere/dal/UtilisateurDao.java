package fr.eni.Enchere.dal;

public interface UtilisateurDao {
	boolean loginCheck (String username, String password) throws DALException;
}
