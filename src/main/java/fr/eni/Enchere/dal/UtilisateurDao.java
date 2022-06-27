package fr.eni.Enchere.dal;

public interface UtilisateurDao {
	boolean loginCheck (String login, String password) throws DALException;
}
