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

	private static final String SELECT_ALL = "select AV.nom_article nomArticle,montant_enchere PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, E.no_enchere, AV.no_article from ENCHERES E INNER JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article where montant_enchere = (select MAX(montant_enchere) from ENCHERES where no_article = AV.no_article)";
	private static final String SELECT_ENCHERE_BY_CATEG = "select AV.nom_article nomArticle,montant_enchere PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, E.no_enchere, AV.no_article from ENCHERES E INNER JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article where montant_enchere = (select MAX(montant_enchere) from ENCHERES where no_article = AV.no_article) and no_categorie = ?";
	private static final String SELECT_ENCHERE_BY_NOM_ARTICLE = "select AV.nom_article nomArticle,montant_enchere PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, E.no_enchere, AV.no_article from ENCHERES E INNER JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article where montant_enchere = (select MAX(montant_enchere) from ENCHERES where no_article = AV.no_article) and nom_article like ?";
	private static final String SELECT_ENCHERE_BY_NOM_ARTICLE_AND_CATEG = "select AV.nom_article nomArticle,montant_enchere PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, E.no_enchere, AV.no_article from ENCHERES E INNER JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article where montant_enchere = (select MAX(montant_enchere) from ENCHERES where no_article = AV.no_article) and nom_article like ? and no_categorie = ?";
	private static final String SELECT_ENCHERE_BY_ARTICLE_ID = "select AV.nom_article nomArticle,montant_enchere PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, E.no_enchere, AV.no_article,description,libelle,AV.prix_initial,R.code_postal,R.rue,R.ville from ENCHERES E INNER JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie INNER JOIN RETRAITS R on AV.no_article = R.no_article  where montant_enchere = (select MAX(montant_enchere) from ENCHERES where no_article = AV.no_article) and E.no_enchere = ?";
	private static final String INSERT_ENCHERES = "INSERT INTO ENCHERES VALUES(GETDATE(),?,?,?)";
	private static final String UPDATE_ARTICLES_VENDUS = "update ARTICLES_VENDUS set prix_vente = ? where no_article = ?";
	
	public List<DtoEnchereComplete> SelectAllEnchere() throws SQLException {
		List<DtoEnchereComplete> ListeRetour = new ArrayList<DtoEnchereComplete>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectAll = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = stmtSelectAll.executeQuery();
			while(rs.next()) {
				DtoEnchereComplete Enchere = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"),rs.getInt("no_enchere"),rs.getInt("no_article"));
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
				DtoEnchereComplete Enchere = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"),rs.getInt("no_enchere"),rs.getInt("no_article"));
				
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
				DtoEnchereComplete Enchere = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"),rs.getInt("no_enchere"),rs.getInt("no_article"));
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
		int meilleurOffre = 0;
		List<Integer> listArticle = new ArrayList<Integer>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectByNomArticleAndCateg = conn.prepareStatement(SELECT_ENCHERE_BY_NOM_ARTICLE_AND_CATEG);
			stmtSelectByNomArticleAndCateg.setString(1, "%" + nomArticle + "%");
			stmtSelectByNomArticleAndCateg.setInt(2, noCategorie);
			ResultSet rs = stmtSelectByNomArticleAndCateg.executeQuery();
			while(rs.next()) {
				DtoEnchereComplete Enchere = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"),rs.getInt("no_enchere"),rs.getInt("no_article"));
				ListeRetour.add(Enchere);
				System.out.println(Enchere.toString());
			}
			for(DtoEnchereComplete E : ListeRetour) {
				listArticle.add(E.getNoArticle());
				meilleurOffre = E.getPrixVente();
				for(Integer i : listArticle) {
					if(i == E.getNoArticle() && E.getPrixVente() < meilleurOffre) {
						ListeRetour.remove(E);
					}
				}
			}
		} catch (SQLException e) {
			throw new SQLException("Erreur SQL lors de la récupération des enchères par nom d'article et catégories : " + e);
		}catch(Exception e) {
			throw new SQLException("Erreur CRITIQUE lors de la récupération des enchères par nom d'article et catégories : " + e);
		}
		return ListeRetour;
	}
	
	public DtoEnchereComplete SelectEnchereById(int noArticle) throws SQLException{
		System.out.println("Details vente ID de d'enchère " + noArticle);
		DtoEnchereComplete ObjetRetour = new DtoEnchereComplete();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectByIdArticle= conn.prepareStatement(SELECT_ENCHERE_BY_ARTICLE_ID);
			stmtSelectByIdArticle.setInt(1, noArticle);
			ResultSet rs = stmtSelectByIdArticle.executeQuery();	
			while(rs.next()) {
				ObjetRetour = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"),rs.getInt("no_enchere"),rs.getInt("no_article"),rs.getString("libelle"),rs.getInt("prix_initial"),rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville"),rs.getString("description"));
				System.out.println(ObjetRetour.toString());
			}
		} catch (SQLException e) {
			throw new SQLException("Erreur SQL lors de la récupération de l'enchere par id : " + e);
		}catch(Exception e) {
			throw new SQLException("Erreur CRITIQUE lors de la récupération de l'enchere par id : " + e);
		}
			return ObjetRetour;
	}
	
	public void updatePrixVenteEnchere(int noArticle,int montantEnchere,int noUtilisateur) throws SQLException{
		System.out.println("Details vente ID de d'enchère " + noArticle);
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement InsertEnchere = conn.prepareStatement(INSERT_ENCHERES);
			InsertEnchere.setInt(1, montantEnchere);
			InsertEnchere.setInt(2, noArticle);
			InsertEnchere.setInt(3, noUtilisateur);
			InsertEnchere.executeUpdate();
			
			PreparedStatement UpdateArticle = conn.prepareStatement(UPDATE_ARTICLES_VENDUS);
			UpdateArticle.setInt(1, montantEnchere);
			UpdateArticle.setInt(2, noArticle);
			UpdateArticle.executeUpdate();
			
			System.out.println("Fin de la création de l'enchère");
		} catch (SQLException e) {
			throw new SQLException("Erreur SQL lors de la mise à jour du prix de l'article par id : " + e);
		}catch(Exception e) {
			throw new SQLException("Erreur CRITIQUE lors de la mise à jour du prix de l'article par id : " + e);
		}
	}
}

	

