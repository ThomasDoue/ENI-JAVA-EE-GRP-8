package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.Enchere.bo.Categorie;
import fr.eni.Enchere.bo.DtoEnchereComplete;

public class EnchereDaoImpl implements EncheresDao{

	private static final String SELECT_ALL = "select AV.nom_article nomArticle,AV.prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo from ENCHERES E INNER JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article";
	private static final String SELECT_ENCHERE_BY_CATEG = "select AV.nom_article nomArticle,AV.prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo from ENCHERES E INNER JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article where no_categorie = ?";
	private static final String SELECT_ENCHERE_BY_NOM_ARTICLE = "select AV.nom_article nomArticle,AV.prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo from ENCHERES E INNER JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article where nom_article like ?";
	private static final String SELECT_ENCHERE_BY_NOM_ARTICLE_AND_CATEG = "select AV.nom_article nomArticle,AV.prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo from ENCHERES E INNER JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article where nom_article like ? and no_categorie = ?";
	
	public List<DtoEnchereComplete> SelectAllEnchere() throws SQLException {
		List<DtoEnchereComplete> ListeRetour = new ArrayList<DtoEnchereComplete>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectAll = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = stmtSelectAll.executeQuery();
			while(rs.next()) {
				DtoEnchereComplete Enchere = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"));
				ListeRetour.add(Enchere);
				System.out.println(Enchere.toString());
			}

		} catch (SQLException e) {
			throw new SQLException("Erreur SQL lors de la récupération de toutes les enchères : " + e);
		}catch(Exception e) {
			throw new SQLException("Erreur CRITIQUE lors de la récupération de toutes les enchères : " + e);
		}
		return ListeRetour;
	}
	public List<DtoEnchereComplete> SelectEnchereByCategorie(int noCateg) throws SQLException {
		List<DtoEnchereComplete> ListeRetour = new ArrayList<DtoEnchereComplete>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectByCateg = conn.prepareStatement(SELECT_ENCHERE_BY_CATEG);
			stmtSelectByCateg.setInt(1, noCateg);
			ResultSet rs = stmtSelectByCateg.executeQuery();
			while(rs.next()) {
				DtoEnchereComplete Enchere = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"));
				
				ListeRetour.add(Enchere);
				System.out.println(Enchere.toString());
			}
		} catch (SQLException e) {
			throw new SQLException("Erreur SQL lors de la récupération des enchères par catégories : " + e);
		}catch(Exception e) {
			throw new SQLException("Erreur CRITIQUE lors de la récupération des enchères par catégories : " + e);
		}
		return ListeRetour;
	}
	public List<DtoEnchereComplete> SelectEnchereByNomArticle(String nomArticle) throws SQLException {
		List<DtoEnchereComplete> ListeRetour = new ArrayList<DtoEnchereComplete>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectByNomArticle = conn.prepareStatement(SELECT_ENCHERE_BY_NOM_ARTICLE);
			stmtSelectByNomArticle.setString(1, "%" + nomArticle + "%");
			System.out.println("Requete chercher par article : " + stmtSelectByNomArticle.toString());
			ResultSet rs = stmtSelectByNomArticle.executeQuery();
			while(rs.next()) {
				DtoEnchereComplete Enchere = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"));
				ListeRetour.add(Enchere);
				System.out.println("Enchere pas nom d'aticle : " + Enchere.toString());
			}
		} catch (SQLException e) {
			throw new SQLException("Erreur SQL lors de la récupération des enchères par nom d'article : " + e);
		}catch(Exception e) {
			throw new SQLException("Erreur CRITIQUE lors de la récupération des enchères par nom d'article : " + e);
		}
		return ListeRetour;
	}
	
	public List<DtoEnchereComplete> SelectEnchereByNomArticleAndCateg(int noCategorie,String nomArticle) throws SQLException {
		List<DtoEnchereComplete> ListeRetour = new ArrayList<DtoEnchereComplete>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectByNomArticleAndCateg = conn.prepareStatement(SELECT_ENCHERE_BY_NOM_ARTICLE_AND_CATEG);
			stmtSelectByNomArticleAndCateg.setString(1, "%" + nomArticle + "%");
			stmtSelectByNomArticleAndCateg.setInt(2, noCategorie);
			ResultSet rs = stmtSelectByNomArticleAndCateg.executeQuery();
			while(rs.next()) {
				DtoEnchereComplete Enchere = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"));
				ListeRetour.add(Enchere);
				System.out.println(Enchere.toString());
			}
		} catch (SQLException e) {
			throw new SQLException("Erreur SQL lors de la récupération des enchères par nom d'article et catégories : " + e);
		}catch(Exception e) {
			throw new SQLException("Erreur CRITIQUE lors de la récupération des enchères par nom d'article et catégories : " + e);
		}
		return ListeRetour;
	}
}
	

