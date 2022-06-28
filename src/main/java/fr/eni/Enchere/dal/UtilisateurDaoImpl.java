package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UtilisateurDaoImpl implements UtilisateurDao{
	private static final String CHECK_IDENTIFIER = "SELECT ?, mot_de_passe FROM dbo.UTILISATEURS WHERE ? = ? AND mot_de_passe = ?";
	
	/**
	 * @values(String) : identifier contient l'identifieur de l'utilisateur
	 * @values(String) : password contient le mot de passe associé à l'identifieur de l'utilisateur.
	 */
	public boolean loginCheck(String username, String password) throws DALException {
		/*
		 * je créé une chaine de caractère $type qui contient les valeurs "pseudo" ou "email"
		 * Je parcours la chaîne de caractère $identifier, si elle contient le caractère "@" $type prend la valeur "email" sinon la valeur "pseudo".
		 */
		String type = "pseudo";
		for (int i = 0; i < username.length(); i++) {
			if ('@' == username.charAt(i))
				type = "email";
		}
		/*
		 * Connection à la base de donnée
		 */
		try(Connection conn = ConnectionProvider.getConnection()) {
			/*
			 * je créé un PreparedStatement avec une requete sql contenant 4 variables
			 */
			PreparedStatement stmtLogin = conn.prepareStatement(CHECK_IDENTIFIER);
			/*
			 * je renseigne les 4 variables de ma requete sql.
			 * Ma requête doit me retourner le couple (email/pseudo, mot_de_passe) en fonction des paramètres passés par l'utilisateur
			 */
			stmtLogin.setString(1, type);
			stmtLogin.setString(2, type);
			stmtLogin.setString(3, username);
			stmtLogin.setString(4, password);
			ResultSet rsLogin = stmtLogin.executeQuery();	
			
			/*
			 * si il existe un résultat dans ma requête, 
			 * la fonction retourne true, sinon false.
			 */
			if(rsLogin.next()) {
				return true;
			}
			return false;
			
		} catch (SQLException e) {
			throw new DALException("erreur checkLogin - userName = "+username, e);
		}
	}
}		

