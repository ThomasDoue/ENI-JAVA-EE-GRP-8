package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UtilisateurDaoImpl implements UtilisateurDao{
	private static final String CHECK_IDENTIFIER_PSEUDO = "SELECT pseudo, mot_de_passe FROM UTILISATEURS WHERE pseudo = ? AND mot_de_passe = ?";
	private static final String CHECK_IDENTIFIER_EMAIL = "SELECT email, mot_de_passe FROM UTILISATEURS WHERE email = ? AND mot_de_passe = ?";
	
	
	
	/**
	 * @values(String) : identifier contient l'identifieur de l'utilisateur
	 * @values(String) : password contient le mot de passe associé à l'identifieur de l'utilisateur.
	 * retourne true si le un couple email/pseudo-mot_de_passe est trouvé dans la base de données Encheres>UTILSIATEURS, sinon false
	 */
	public boolean loginCheck(String identifier, String password) throws DALException {
		/*
		 * je créé une chaine de caractère $type qui contient les valeurs "pseudo" ou "email"
		 * Je parcours la chaîne de caractère $identifier, si elle contient le caractère "@" $type prend la valeur "email" sinon la valeur "pseudo".
		 */
		String type = "pseudo";
		for (int i = 0; i < identifier.length(); i++) {
			if ('@' == identifier.charAt(i))
				type = "email";
		}
		/*
		 * Connection à la base de donnée
		 */
		try(Connection conn = ConnectionProvider.getConnection()) {
			/*
			 * je créé un PreparedStatement, si $type est un pseudo je lance la requête sql en fonction du pseudo, sinon en fonction de email
			 */
			PreparedStatement stmtLogin;
			if (type == "pseudo") {
				stmtLogin = conn.prepareStatement(CHECK_IDENTIFIER_PSEUDO);}
			else {
				stmtLogin = conn.prepareStatement(CHECK_IDENTIFIER_EMAIL); }
			/*
			 * Je renseigne les deux variables nécessaires à ma requête.
			 */
			stmtLogin.setString(1, identifier);
			stmtLogin.setString(2, password);
			ResultSet rsLogin = stmtLogin.executeQuery();	
			
			/*
			 * si il existe un résultat dans ma requête, 
			 * la fonction retourne true, sinon false.
			 */
			
			if (rsLogin.next()) {
				return true;
				}
			return false;
			
		} catch (SQLException e) {
			throw new DALException("erreur checkLogin - userName = "+identifier+" - password = "+password, e);
		}
	}
}		

