package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.Enchere.bo.Utilisateur;


public class UtilisateurDaoImpl implements UtilisateurDao{
	private static final String CHECK_IDENTIFIER_PSEUDO = "SELECT no_utilisateur, pseudo, mot_de_passe FROM dbo.UTILISATEURS WHERE pseudo=? AND mot_de_passe=?";
	private static final String CHECK_IDENTIFIER_EMAIL= "SELECT no_utilisateur, email, mot_de_passe FROM dbo.UTILISATEURS WHERE email=? AND mot_de_passe=?";
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,0,0)";
	private static final String CHECK_USER_EXIST = "SELECT * FROM UTILISATEURS WHERE pseudo = ? OR email = ?";
	private static final String SELECT_USER = "select * from UTILISATEURS where no_utilisateur= ?";
	private static final String UPDATE_USER ="UPDATE UTILISATEURS SET pseudo= ? , nom= ? ,prenom= ? ,email= ? ,telephone = ?,rue= ? ,code_postal= ? ,ville= ? ,mot_de_passe= ? WHERE no_utilisateur = ?";
	private static final String DELETE ="DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String SELECT_USER_ID_BY_ENCHERE_ID = "select AV.no_utilisateur from ENCHERES E INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article where no_enchere = ?"; 

	/**
	 * @values(String) : identifier contient l'identifieur de l'utilisateur
	 * @values(String) : password contient le mot de passe associé à l'identifieur de l'utilisateur.
	 * retourne l'id si le un couple email/pseudo-mot_de_passe est trouvé dans la base de données Encheres>UTILSIATEURS, sinon 0
	 * L'utilisateur rentre une chaine de caractères (je l'ai appelé $login).
	 * Je créé une chaine de caractere $type qui prend les valeurs "pseudo" ou "email"
	 * Je fais un test pour savoir si $login est un pseudo ou un email.
	 * Si c'est un email je fais une requête SQL qui récupère tous les emails, de même si c'est un pseudo.
	 * 
	 */
	public int connect(String identifier, String password) throws DALException, Exception {
		
		/* je créé une chaine de caractère $type qui contient les valeurs "pseudo" ou "email"
		 * Je parcours la chaîne de caractère $identifier, si elle contient le caractère "@" $type prend la valeur "email" sinon la valeur "pseudo".
		 * je créé un String $type qui prend la valeur "pseudo"
		 * je parcours la chaine de caractere $login, 
		 * si la chaine contient le caractère '@' alors $type prend la valeur "pseudo".
		 * 
		 * Création d'une variable int id nommé "id" initialisé à 0, indiquant qu'il n'existe pas d'identifiant associé à 
		 */
		
		int id = 0;
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
				id = rsLogin.getInt("no_utilisateur");
			}
			return id;

		} catch (SQLException e) {
			throw new DALException("erreur checkLogin - userName = "+identifier+" - password = "+password, e);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void inscription(Utilisateur user )throws DALException,Exception{
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtCheckUserExist = conn.prepareStatement(CHECK_USER_EXIST);
			
			stmtCheckUserExist.setString(1,user.getPseudo());
			stmtCheckUserExist.setString(2,user.getEmail());
			ResultSet rsUserCheck = stmtCheckUserExist.executeQuery();
			
			
			int i = 0;
			while(rsUserCheck.next()) {
				i++;
			}
			if(i != 0) {
				throw new DALException("L'utilisateur existe déja en base - user = "+user.toString()); 
			}
			PreparedStatement stmtInsert = conn.prepareStatement(INSERT_USER);

			stmtInsert.setString(1, user.getPseudo());
			stmtInsert.setString(2, user.getNom());
			stmtInsert.setString(3, user.getPrenom());
			stmtInsert.setString(4, user.getEmail());
			stmtInsert.setString(5, user.getTelephone());
			stmtInsert.setString(6, user.getRue());
			stmtInsert.setString(7, user.getCodePostal());
			stmtInsert.setString(8, user.getVille());
			stmtInsert.setString(9, user.getMotDePasse());
			stmtInsert.executeUpdate();

		}catch(SQLException e) {
			throw new DALException("erreur lors de l'insertion du client en base - user = "+user.toString(), e);
		}catch(Exception e) {
			throw new Exception("Erreur critique lors de la récupération des données dans la couche dal");
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
		
			
			ResultSet rs = stmtSelectUser.executeQuery();
			if (rs.next()) {
				user= new Utilisateur(rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),rs.getInt("credit"));
				System.out.println("chargement profil utilisateur OK");
			}
			
		} //message d'erreu si probléme est rencontré
		catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur selectUser ");
		}
		
		
		return user;	
	}
	
	/**
	 *mise a jour des information de l'utilsateur
	 */
	public void UpdateUser (Utilisateur modifUser) throws DALException {
	
		//connection et envoi dela requette SQL
		try (Connection conn = ConnectionProvider.getConnection();){
			PreparedStatement stmtCheckUserUpdate = conn.prepareStatement(UPDATE_USER);
			//incrémentation de la requête
			System.out.println(modifUser);
			stmtCheckUserUpdate.setString(1, modifUser.getPseudo());
			stmtCheckUserUpdate.setString(2, modifUser.getNom());
			stmtCheckUserUpdate.setString(3, modifUser.getPrenom());
			stmtCheckUserUpdate.setString(4, modifUser.getEmail());
			stmtCheckUserUpdate.setString(5, modifUser.getTelephone());
			stmtCheckUserUpdate.setString(6, modifUser.getRue());
			stmtCheckUserUpdate.setString(7, modifUser.getCodePostal());
			stmtCheckUserUpdate.setString(8, modifUser.getVille());
			stmtCheckUserUpdate.setString(9, modifUser.getMotDePasse());
			stmtCheckUserUpdate.setInt(10, modifUser.getNoUtilisateur());
			System.out.println(stmtCheckUserUpdate);
			//envoie de la requette 
			stmtCheckUserUpdate.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DALException("Erreur UpdateUser " + e);
		}
	
	}

	
	
	public void Delete (int id) throws DALException{
		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1,  id);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete utilisateur failed - id : "+id, e);
		}
	}
	
	public int SelectIdVendeurByIdEnchere(int idEnchere) throws DALException{
		int idUtilisateur = 0;
		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(SELECT_USER_ID_BY_ENCHERE_ID);
			stmt.setInt(1, idEnchere);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				idUtilisateur = rs.getInt("no_utilisateur");
			}else {
				throw new DALException("Immpossible de récupérer l'utilisateur à partir de l'id d'enchere : "+ idEnchere);
			}
		} catch (SQLException e) {
			throw new DALException("Immpossible de récupérer l'utilisateur à partir de l'id d'enchere : "+ idEnchere, e);
		}
		return idUtilisateur;
	}
	

}
			
		
			

