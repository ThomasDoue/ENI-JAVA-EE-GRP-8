package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UtilisateurDaoImpl implements UtilisateurDao{
	private static final String CHECK_LOGIN = "SELECT ? FROM dbo.UTILISATEURS";
	private static final String CHECK_PASSWORD= "SELECT mot_de_passe FROM dbo.UTILISATEURS WHERE ?=?";
	
	
	/**
	 * L'utilisateur rentre une chaine de caractères (je l'ai appelé $login).
	 * Je créé une chaine de caractere $type qui prend les valeurs "pseudo" ou "email"
	 * Je fais un test pour savoir si $login est un pseudo ou un email.
	 * Si c'est un email je fais une requête SQL qui récupère tous les emails, de même si c'est un pseudo.
	 * 
	 * Si le pseudo ou l'email existe je retourne true, si il n'existe pas je retourne false.
	 */
	public boolean loginCheck(String login, String password) throws DALException {
		
		/*
		 * je créé un String $type qui prend la valeur "pseudo"
		 * je parcours la chaine de caractere $login, 
		 * si la chaine contient le caractère '@' alors $type prend la valeur "pseudo".
		 */
		String type = "pseudo";
		for (int i = 0; i < login.length(); i++) {
			if ('@' == login.charAt(i))
				type = "email";
		}
		/*
		 * connection à la base de donnée, création de deux PreparedStatement.
		 * Une pour vérifier le login, une pour vérifier le password
		 */
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtLogin = conn.prepareStatement(CHECK_LOGIN);
			PreparedStatement stmtPassword = conn.prepareStatement(CHECK_PASSWORD);
			
			stmtLogin.setString(1, type);
			ResultSet rsLogin = stmtLogin.executeQuery();	
			
			stmtPassword.setString(1, type);
			stmtPassword.setString(2, login);
			ResultSet rsPassword = stmtPassword.executeQuery();
						
			/*
			 * si $type a pour valeur "pseudo", j'execute la requete CHECK_PSEUDONYME qui stocke tous les pseudos et uniquement les pseudos 
			 * dans un ResultSet.
			 * J'execute ma requête et je compare  le $login rentré en paramètre de la fonction à chaques valeurs du resultSet.
			 * dans le cas où $login est trouvé dans le ResultSet, je renvoie TRUE
			 */

			while(rsLogin.next()) {
				if(login.equals(rsLogin.getString(type))) {
					if(password.equals(rsPassword.getString("password")))
						return true;
				}
			}
			return false;
			
		} catch (SQLException e) {
			throw new DALException("erreur checkLogin - userName = "+login, e);
		}
	}
}
		/*
		 * Si on arrive jusqu'ici c'est que le $login n'a pas été trouvé dans le ResultSet, on retourne faux.
		 */	
		
			

