package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.Utilisateur;

public class ArticleAVendreDaoImpl implements ArticleAVendreDao{

	private static final String NEW_VENTE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?)";
	private static final String ARTICLE_VENTE = "SELECT * FROM ARTICLES_VENDUS";
	
	public int nouvelleArticle (ArticleVendu nouvelleArticle )throws DALException,Exception{
		int id_NouvelleArticle=0;
		
		try (Connection conn = ConnectionProvider.getConnection();){
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
			
		} catch (Exception e) {
			throw new DALException("erreur insert nouvelleArticle : ", e);
		}
		return id_NouvelleArticle;
	}
		
	
}

        