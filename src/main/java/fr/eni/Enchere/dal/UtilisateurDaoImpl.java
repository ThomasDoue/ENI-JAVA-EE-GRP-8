package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.Enchere.bo.Utilisateur;


public class UtilisateurDaoImpl implements UtilisateurDao{
	private static final String CHECK_LOGIN = "SELECT ? FROM dbo.UTILISATEURS";
	private static final String CHECK_PASSWORD= "SELECT mot_de_passe FROM dbo.UTILISATEURS WHERE ?=?";
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,0,0)";
	private static final String CHECK_USER_EXIST = "SELECT * FROM UTILISATEURS WHERE pseudo = ? OR email = ?";
	
	
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
	
	public void inscription(Utilisateur user )throws DALException{
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtCheckUserExist = conn.prepareStatement(CHECK_USER_EXIST);
			
			stmtCheckUserExist.setString(1,user.getPseudo());
			stmtCheckUserExist.setString(2,user.getEmail());
			ResultSet rsUserCheck = stmtCheckUserExist.executeQuery();
			
			
			int i = 0;
			while(rsUserCheck.next()) {
				i++;
			}
			if(i == 0) {
				throw new DALException("L'utilisateur existe déja en base - user = "+user.toString()); 
			}
			PreparedStatement stmtInsert = conn.prepareStatement(INSERT_USER);

			stmtInsert.setString(1, user.getPseudo());
			stmtInsert.setString(2, user.getNom());
			stmtInsert.setString(3, user.getPseudo());
			stmtInsert.setString(4, user.getPrenom());
			stmtInsert.setString(5, user.getEmail());
			stmtInsert.setString(6, user.getTelephone());
			stmtInsert.setString(7, user.getRue());
			stmtInsert.setString(8, user.getCodePostal());
			stmtInsert.setString(9, user.getVille());
			stmtInsert.setString(10, user.getMotDePasse());
			stmtInsert.executeUpdate();

		}catch(SQLException e) {
			throw new DALException("erreur lors de l'insertion du client en base - user = "+user.toString(), e);
		}
	}
}
		/*
		 * Si on arrive jusqu'ici c'est que le $login n'a pas été trouvé dans le ResultSet, on retourne faux.
		 */	
		
			

