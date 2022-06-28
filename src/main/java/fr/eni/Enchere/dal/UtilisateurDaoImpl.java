package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.Enchere.bo.Utilisateur;


public class UtilisateurDaoImpl implements UtilisateurDao{
	private static final String CHECK_IDENTIFIER_PSEUDO = "SELECT pseudo, FROM dbo.UTILISATEURS WHERE pseudo=? AND mot_de_passe=?";
	private static final String CHECK_IDENTIFIER_EMAIL= "SELECT email, FROM dbo.UTILISATEURS WHERE pseudo=? AND mot_de_passe=?";
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,0,0)";
	private static final String CHECK_USER_EXIST = "SELECT * FROM UTILISATEURS WHERE pseudo = ? OR email = ?";
	private static final String SELECT_USER = "select * from UTILISATEURS where no_utilisateur= ?";
	
	/**
	 * @values(String) : identifier contient l'identifieur de l'utilisateur
	 * @values(String) : password contient le mot de passe associé à l'identifieur de l'utilisateur.
	 * retourne true si le un couple email/pseudo-mot_de_passe est trouvé dans la base de données Encheres>UTILSIATEURS, sinon false
	 * L'utilisateur rentre une chaine de caractères (je l'ai appelé $login).
	 * Je créé une chaine de caractere $type qui prend les valeurs "pseudo" ou "email"
	 * Je fais un test pour savoir si $login est un pseudo ou un email.
	 * Si c'est un email je fais une requête SQL qui récupère tous les emails, de même si c'est un pseudo.
	 * 
	 * Si le pseudo ou l'email existe je retourne true, si il n'existe pas je retourne false.
	 */
	public boolean connect(String identifier, String password) throws DALException {
		
		/* je créé une chaine de caractère $type qui contient les valeurs "pseudo" ou "email"
		 * Je parcours la chaîne de caractère $identifier, si elle contient le caractère "@" $type prend la valeur "email" sinon la valeur "pseudo".
		 * je créé un String $type qui prend la valeur "pseudo"
		 * je parcours la chaine de caractere $login, 
		 * si la chaine contient le caractère '@' alors $type prend la valeur "pseudo".
		 */
		String type = "pseudo";
		for (int i = 0; i < identifier.length(); i++) {
			if ('@' == identifier.charAt(i))
				type = "email";
		}
		/*
		 * connection à la base de donnée, création de deux PreparedStatement.
		 * Une pour vérifier le login, une pour vérifier le password
		 */
		try(Connection conn = ConnectionProvider.getConnection()) {
			/*
			 * je créé un PreparedStatement, si $type est un pseudo je lance la requête sql en fonction du pseudo, sinon en fonction de email
			 */
			PreparedStatement stmtLogin;
			if (type == "pseudo") {
				stmtLogin = conn.prepareStatement(CHECK_IDENTIFIER_PSEUDO);
			} else {
				stmtLogin = conn.prepareStatement(CHECK_IDENTIFIER_EMAIL);
			}
			/*
			 * Je renseigne les deux variables nécessaires à ma requête.
			 */
			stmtLogin.setString(1, identifier);
			stmtLogin.setString(2, password);
			
			ResultSet rsLogin = stmtLogin.executeQuery();	

			/*
			 * si il existe un résultat dans ma requête, 
			 * la fonction retourne true, sinon false.
			 * si $type a pour valeur "pseudo", j'execute la requete CHECK_PSEUDONYME qui stocke tous les pseudos et uniquement les pseudos 
			 * dans un ResultSet.
			 * J'execute ma requête et je compare  le $login rentré en paramètre de la fonction à chaques valeurs du resultSet.
			 * dans le cas où $login est trouvé dans le ResultSet, je renvoie TRUE
			 */

			if (rsLogin.next()) {
				return true;
			}
			return false;

		} catch (SQLException e) {
			throw new DALException("erreur checkLogin - userName = "+identifier+" - password = "+password, e);
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
	
	public Utilisateur selectUser(Integer no_utlisateur) throws DALException{
		//creation d'un utilistateur vide 
		Utilisateur user=null;
		//connection à la base de donnéer traitement de la fonction  
		try(Connection conn = ConnectionProvider.getConnection()) {
			//préparation a la requete SQL Selecte_user
			PreparedStatement stmtSelectUser = conn.prepareStatement(SELECT_USER);
			//valoriser les paramètre select_user
			stmtSelectUser.setInt(1, no_utlisateur);
			
			
			ResultSet rs = stmtSelectUser.executeQuery();;
			if (rs.next()) {
				user= new Utilisateur(rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),rs.getString("email"), rs.getString("telephone"),rs.getString("rue"),rs.getString("code_postal"), rs.getString("ville"));
				System.out.println("chargement profil utilisateur OK");
			
			}
			
			
			
		} //message d'erreu si probléme est rencontré
		catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur selectUser ");
		}
		
		
		return user;	
	}
	
	
	
}
			
		
			

