package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.Utilisateur;

public class ArticleAVendreDaoImpl implements ArticleAVendreDao{

	private static final String NEW_VENTE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?)";
	private static final String ARTICLE_VENTE = "SELECT * FROM ARTICLES_VENDUS";
	private static final String DATE_ACTUELLE = "SELECT DATE(NOW)";
	private static final String SELECT_DATE_FIN_ENCHERES_BY_ID = "SELECT date_fin_encheres FROM ARTICLES_VENDUS WHERE no_article=?";
	private static final String SELECT_USER_BY_ID = "Select rue,code_postal,ville from utilisateurs where no_utilisateur = ?";
	private static final String INSERT_RETRAITS_INFO = "INSERT INTO RETRAITS VALUES(?,?,?,?)";
	
	public int nouvelleArticle (ArticleVendu nouvelleArticle )throws DALException,Exception{
		int id_NouvelleArticle=0;
		Utilisateur user = new Utilisateur();		
		try (Connection conn = ConnectionProvider.getConnection()){
			//envoie de la requette en preparedStatement et recupération de la clée
			PreparedStatement stmtnew_vente = conn.prepareStatement(NEW_VENTE,PreparedStatement.RETURN_GENERATED_KEYS);
			stmtnew_vente.setString(1, nouvelleArticle.getNomArticle());
			stmtnew_vente.setString(2, nouvelleArticle.getDescription());
			stmtnew_vente.setDate(3, nouvelleArticle.getDateDebutEncheres());
			stmtnew_vente.setDate(4, nouvelleArticle.getDateFinEncheres());
			stmtnew_vente.setInt(5, nouvelleArticle.getPrixInitial());
			stmtnew_vente.setInt(6, nouvelleArticle.getNoUtilisateur());
			stmtnew_vente.setInt(7, nouvelleArticle.getNoCategorie());
			//execution de la requette 
			stmtnew_vente.executeUpdate();
			
			
			//recuperation de la id-article
			ResultSet rs = stmtnew_vente.getGeneratedKeys();
			if (rs.next()) {
				id_NouvelleArticle= rs.getInt(1);
			}
			PreparedStatement stmtUser = conn.prepareStatement(SELECT_USER_BY_ID);
			ResultSet rsUser = stmtUser.executeQuery();
			while(rs.next()) {
				user = new Utilisateur(rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville"));
			}
			PreparedStatement stmtInsertRetrait = conn.prepareStatement(INSERT_RETRAITS_INFO);
			stmtInsertRetrait.setInt(1, id_NouvelleArticle);
			stmtInsertRetrait.setString(2, user.getRue());
			stmtInsertRetrait.setString(3, user.getCodePostal());
			stmtInsertRetrait.setString(4, user.getVille());
			stmtInsertRetrait.executeUpdate();
			System.out.println("Enregistrement dans la dal réussi");
		} catch (Exception e) {
			throw new DALException("erreur insert nouvelleArticle : ", e);
		}
		return id_NouvelleArticle;
	}
	
	/**
	 * @noArticle : numero d'article mis en vente.
	 * Je créé deux statement, un pour réupérer la date actuelle, un autre pour récupérer la date de fin d'enchères
	 * Dans le cas où la date de fin d'enchères est plus grande que la date actuelle je retourne true pour signaler la fin de l'enchère
	 * sinon false.	 *
	 */
	public boolean FinEnchere(int noArticle)throws DALException {
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(SELECT_DATE_FIN_ENCHERES_BY_ID);
			stmt.setInt(1, noArticle);
			ResultSet rsDateFinEnchere = stmt.executeQuery();
			
			Statement stmt2 = conn.createStatement();
			ResultSet rsDateNow = stmt2.executeQuery(DATE_ACTUELLE);
			
			if (rsDateFinEnchere.next() && rsDateNow.next()) {
				if(rsDateFinEnchere.getInt(1) > rsDateNow.getInt(1))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
}

        