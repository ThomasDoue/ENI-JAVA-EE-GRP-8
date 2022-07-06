package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.Categorie;
import fr.eni.Enchere.bo.DtoEnchereComplete;

public class EnchereDaoImpl implements EncheresDao{

	private static final String SELECT_ALL = "select AV.nom_article nomArticle,prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, AV.no_article from  ARTICLES_VENDUS AV  INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur";
	private static final String SELECT_ENCHERE_BY_CATEG = "select AV.nom_article nomArticle,prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, AV.no_article from ARTICLES_VENDUS AV INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur where no_categorie = ?";
	private static final String SELECT_ENCHERE_BY_NOM_ARTICLE = "select AV.nom_article nomArticle,prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, AV.no_article from  ARTICLES_VENDUS AV  INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur where nom_article like ?";
	private static final String SELECT_ENCHERE_BY_NOM_ARTICLE_AND_CATEG = "select AV.nom_article nomArticle,prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, AV.no_article from  ARTICLES_VENDUS AV  INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur and nom_article like ? and no_categorie = ?";
	private static final String SELECT_ENCHERE_BY_ARTICLE_ID = "select AV.nom_article nomArticle,prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, AV.no_article,description,libelle,AV.prix_initial,R.code_postal,R.rue,R.ville from ARTICLES_VENDUS AV INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie INNER JOIN RETRAITS R on AV.no_article = R.no_article and AV.no_article = ?";
	private static final String INSERT_ENCHERES = "INSERT INTO ENCHERES VALUES(GETDATE(),?,?,?)";
	private static final String UPDATE_ARTICLES_VENDUS = "update ARTICLES_VENDUS set prix_vente = ? where no_article = ?";
	private static final String GET_UTILISATEUR_AND_MEILLEURE_OFFRE = "select AV.nom_article nomArticle,montant_enchere PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, E.no_enchere, AV.no_article from ENCHERES E INNER JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article where montant_enchere = (select MAX(montant_enchere) from ENCHERES where no_article = AV.no_article)";
	private static final String GET_VENTE_BY_USER_ID = "select nom_article nomArticle,prix_vente prixVente,date_fin_encheres DateFinEnchere,pseudo,no_article from ARTICLES_VENDUS av INNER JOIN UTILISATEURS U on  av.no_utilisateur = u.no_utilisateur where AV.no_utilisateur = ?";
	private static final String SELECT_DATE_FIN_ENCHERES_BY_ID = "SELECT AV.date_fin_encheres FROM ENCHERES E INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article WHERE E.no_enchere=?";
	private static final String DATE_ACTUELLE = "SELECT CONVERT(date, getdate())";
	
	public List<DtoEnchereComplete> SelectAllEnchere() throws SQLException {
		List<DtoEnchereComplete> ListeRetour = new ArrayList<DtoEnchereComplete>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectAll = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = stmtSelectAll.executeQuery();
			while(rs.next()) {
				DtoEnchereComplete Enchere = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"),rs.getInt("no_article"));
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
	public List<DtoEnchereComplete> SelectVenteByCategorie(int noCateg) throws SQLException {
		List<DtoEnchereComplete> ListeRetour = new ArrayList<DtoEnchereComplete>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectByCateg = conn.prepareStatement(SELECT_ENCHERE_BY_CATEG);
			stmtSelectByCateg.setInt(1, noCateg);
			ResultSet rs = stmtSelectByCateg.executeQuery();
			while(rs.next()) {
				DtoEnchereComplete Enchere = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"),rs.getInt("no_article"));
				
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
	public List<DtoEnchereComplete> selectVenteByNomArticle(String nomArticle) throws SQLException {
		List<DtoEnchereComplete> ListeRetour = new ArrayList<DtoEnchereComplete>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectByNomArticle = conn.prepareStatement(SELECT_ENCHERE_BY_NOM_ARTICLE);
			stmtSelectByNomArticle.setString(1, "%" + nomArticle + "%");
			System.out.println("Requete chercher par article : " + stmtSelectByNomArticle.toString());
			ResultSet rs = stmtSelectByNomArticle.executeQuery();
			while(rs.next()) {
				DtoEnchereComplete Enchere = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"),rs.getInt("no_article"));
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
	
	public List<DtoEnchereComplete> selectVenteByNomArticleAndCateg(int noCategorie,String nomArticle) throws SQLException {
		List<DtoEnchereComplete> ListeRetour = new ArrayList<DtoEnchereComplete>();
		int meilleurOffre = 0;
		List<Integer> listArticle = new ArrayList<Integer>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectByNomArticleAndCateg = conn.prepareStatement(SELECT_ENCHERE_BY_NOM_ARTICLE_AND_CATEG);
			stmtSelectByNomArticleAndCateg.setString(1, "%" + nomArticle + "%");
			stmtSelectByNomArticleAndCateg.setInt(2, noCategorie);
			ResultSet rs = stmtSelectByNomArticleAndCateg.executeQuery();
			while(rs.next()) {
				DtoEnchereComplete Enchere = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"),rs.getInt("no_article"));
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
	
	
	public DtoEnchereComplete selectVenteById(int noArticle) throws SQLException{
		System.out.println("Details vente ID de d'enchère " + noArticle);
		DtoEnchereComplete ObjetRetour = new DtoEnchereComplete();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectByIdArticle= conn.prepareStatement(SELECT_ENCHERE_BY_ARTICLE_ID);
			stmtSelectByIdArticle.setInt(1, noArticle);
			ResultSet rs = stmtSelectByIdArticle.executeQuery();	
			while(rs.next()) {
				ObjetRetour = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"),rs.getInt("no_article"),rs.getString("libelle"),rs.getInt("prix_initial"),rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville"),rs.getString("description"));
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


public DtoEnchereComplete getMontantEtPseudoDeLaMeilleurOffre(ArticleVendu articleVendu)throws DALException, SQLException {
	Connection cnx = ConnectionProvider.getConnection();
	DtoEnchereComplete result = new DtoEnchereComplete();
	try {
		PreparedStatement stmt = cnx.prepareStatement(GET_UTILISATEUR_AND_MEILLEURE_OFFRE);
		stmt.setInt(1, articleVendu.getNoArticle());
		stmt.execute();
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			result = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("prixVente") ,rs.getDate("DateFinEnchere"),rs.getString("pseudo") ,rs.getInt("montantEnchere"), rs.getString("rueRetrait"), rs.getString("codePostalRetrait"),rs.getString("villeRetrait"),rs.getString("description"));
			
		}
		
		cnx.close();
	} catch (SQLException e) {
		e.printStackTrace();
		DALException dalException = new DALException();
		throw dalException;
	}
	return result;
}

public List<DtoEnchereComplete> selectArticleEnVenteOfUser(int noUtilisateur)throws DALException, SQLException {
	Connection cnx = ConnectionProvider.getConnection();
	List<DtoEnchereComplete> result = new ArrayList<DtoEnchereComplete>();
	try {
		PreparedStatement stmt = cnx.prepareStatement(GET_VENTE_BY_USER_ID);
		stmt.setInt(1, noUtilisateur);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			var ObjetRetour  = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"),rs.getInt("no_article"));	
			result.add(ObjetRetour);
			}
	} catch (SQLException e) {
		e.printStackTrace();
		DALException dalException = new DALException();
		throw dalException;
	}
	return result;
}
//test
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
			System.out.println("il y a des lignes");
			if((rsDateNow.getDate(1)).after(rsDateFinEnchere.getDate(1))) {
				System.out.println("dateNow : "+rsDateNow.getDate(1));
				System.out.println("dateEnchere : "+rsDateFinEnchere.getDate(1));
				return true;
			} else {
				System.out.println("dateNow : "+rsDateNow.getDate(1));
				System.out.println("dateEnchere : "+rsDateFinEnchere.getDate(1));
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
	
}


}
	

