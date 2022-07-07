package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.Retrait;
import fr.eni.Enchere.bo.Utilisateur;

public class ArticleAVendreDaoImpl implements ArticleAVendreDao{

	private static final String NEW_VENTE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,? ,?)";
	private static final String ARTICLE_VENTE = "SELECT * FROM ARTICLES_VENDUS";
	private static final String DATE_ACTUELLE = "SELECT DATE(NOW)";
	private static final String SELECT_DATE_FIN_ENCHERES_BY_ID = "SELECT date_fin_encheres FROM ARTICLES_VENDUS WHERE no_article=?";
	private static final String SELECT_USER_BY_ID = "Select rue,code_postal,ville from utilisateurs where no_utilisateur = ?";
	private static final String INSERT_RETRAITS_INFO = "INSERT INTO RETRAITS VALUES(?,?,?,?)";
	
	public int nouvelleArticle (ArticleVendu nouvelleArticle )throws DALException{
		int id_NouvelleArticle=0;
		Date DebutEncheres=nouvelleArticle.getDateDebutEncheres();
		Date FinEncheres=nouvelleArticle.getDateFinEncheres();
		//convertir une date java en dateJavaSQL

	 	long DebutEncherestimeInMilliSeconds = DebutEncheres.getTime();
        java.sql.Date SQLDebutEnchere = new java.sql.Date(DebutEncherestimeInMilliSeconds);

        long FinEncherestimeInMilliSeconds = FinEncheres.getTime();
        java.sql.Date SQLFinEncheres = new java.sql.Date(FinEncherestimeInMilliSeconds);



		Utilisateur user = new Utilisateur();		
		try (Connection conn = ConnectionProvider.getConnection()){
			//envoie de la requette en preparedStatement et recupération de la clée
			PreparedStatement stmtnew_vente = conn.prepareStatement(NEW_VENTE,PreparedStatement.RETURN_GENERATED_KEYS);
			stmtnew_vente.setString(1, nouvelleArticle.getNomArticle());
			stmtnew_vente.setString(2, nouvelleArticle.getDescription());
			stmtnew_vente.setDate(3, SQLDebutEnchere);
			stmtnew_vente.setDate(4, SQLFinEncheres);
			stmtnew_vente.setInt(5, nouvelleArticle.getPrixInitial());
			stmtnew_vente.setInt(6, nouvelleArticle.getPrixVente());
			stmtnew_vente.setInt(7, nouvelleArticle.getNoUtilisateur());
			stmtnew_vente.setInt(8, nouvelleArticle.getNoCategorie());
			//execution de la requette 
			stmtnew_vente.executeUpdate();
			
			
			//recuperation de la id-article
			ResultSet rs = stmtnew_vente.getGeneratedKeys();
			if (rs.next()) {
				id_NouvelleArticle= rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			
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
	
	/**
	 * @param adresseRetrait : objet recuper sur la servlet rempli par utlisateur
	 * @throws DALException
	 */
	public void insertionDonnerRetrait (Retrait adresseRetrait ) throws DALException {
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			// insertion de donner dans retrait
			PreparedStatement stmtInsertRetrait = conn.prepareStatement(INSERT_RETRAITS_INFO);
			stmtInsertRetrait.setInt(1,adresseRetrait.getNoArticle() );
			stmtInsertRetrait.setString(2, adresseRetrait.getRue());
			stmtInsertRetrait.setString(3, adresseRetrait.getCodePostal());
			stmtInsertRetrait.setString(4, adresseRetrait.getVille());
			stmtInsertRetrait.executeUpdate();
			System.out.println("Enregistrement dans la dal réussi");
			
		} catch (SQLException e) {
			throw new DALException("erreur insert insertionDonnerRetrait : ", e );
		}
		
		

	}
	
	
}

        