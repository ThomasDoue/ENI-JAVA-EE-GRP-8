package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.DtoEnchereComplete;

public class EnchereDaoImpl implements EncheresDao{

	private static final String SELECT_ALL = "select AV.nom_article nomArticle,prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, AV.no_article from  ARTICLES_VENDUS AV  INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur";
	private static final String SELECT_ENCHERE_BY_CATEG = "select AV.nom_article nomArticle,prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, AV.no_article from ARTICLES_VENDUS AV INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur where no_categorie = ?";
	private static final String SELECT_ENCHERE_BY_NOM_ARTICLE = "select AV.nom_article nomArticle,prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, AV.no_article from  ARTICLES_VENDUS AV  INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur where nom_article like ?";
	private static final String SELECT_ENCHERE_BY_NOM_ARTICLE_AND_CATEG = "select AV.nom_article nomArticle,prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, AV.no_article from  ARTICLES_VENDUS AV  INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur and nom_article like ? and no_categorie = ?";
	private static final String SELECT_ENCHERE_BY_ARTICLE_ID = "select AV.date_debut_encheres,AV.nom_article nomArticle,prix_vente PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, U.telephone, AV.no_article,description,libelle,AV.prix_initial,R.code_postal,R.rue,R.ville from ARTICLES_VENDUS AV INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie INNER JOIN RETRAITS R on AV.no_article = R.no_article and AV.no_article = ?";
	private static final String INSERT_ENCHERES = "INSERT INTO ENCHERES VALUES(GETDATE(),?,?,?)";
	private static final String UPDATE_ARTICLES_VENDUS = "update ARTICLES_VENDUS set prix_vente = ? where no_article = ?";
	private static final String GET_UTILISATEUR_AND_MEILLEURE_OFFRE = "select AV.nom_article nomArticle,montant_enchere PrixVente,AV.date_fin_encheres DateFinEnchere,U.pseudo, E.no_enchere, AV.no_article from ENCHERES E INNER JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article where montant_enchere = (select MAX(montant_enchere) from ENCHERES where no_article = AV.no_article)";
	private static final String GET_VENTE_BY_USER_ID = "select nom_article nomArticle,prix_vente PrixVente,date_fin_encheres DateFinEnchere,pseudo,av.no_article from ARTICLES_VENDUS av INNER JOIN UTILISATEURS U on av.no_utilisateur = u.no_utilisateur where av.no_utilisateur = ?";
	private static final String BALANCE_CHECK = "SELECT credit from UTILISATEURS where no_utilisateur= ? ";
	private static final String NUMBER_OLD_USER_AND_CREDIT_BEST_WINNER ="select no_utilisateur, montant_enchere  from ENCHERES where montant_enchere=(select max(montant_enchere) from ENCHERES where no_article= ? ) and no_article= ? ";
	private static final String UPDATE_CREDIT_NOT_WIN_USER="UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur= ? ";
	private static final String RETURN_CREDIT_USER ="SELECT credit FROM UTILISATEURS WHERE no_utilisateur = ? ";
	private static final String UPDATE_CREDIT_USER_WIN ="UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur= ? ";	
	private static final String SELECT_DATE_FIN_ENCHERES_BY_ID = "SELECT AV.date_fin_encheres FROM ENCHERES E INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article WHERE E.no_article=?";
	private static final String DATE_ACTUELLE = "SELECT CONVERT(date, getdate())";

	public List<DtoEnchereComplete> SelectAllEnchere() throws SQLException, DALException {
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
			throw new DALException("Erreur SQL lors de la r??cup??ration de toutes les ench??res : " + e);
		}catch(Exception e) {
			throw new DALException("Erreur CRITIQUE lors de la r??cup??ration de toutes les ench??res : " + e);
		}
		return ListeRetour;
	}
	public List<DtoEnchereComplete> SelectVenteByCategorie(int noCateg) throws SQLException, DALException {
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
			throw new DALException("Erreur SQL lors de la r??cup??ration des ench??res par cat??gories : " + e);
		}catch(Exception e) {
			throw new DALException("Erreur CRITIQUE lors de la r??cup??ration des ench??res par cat??gories : " + e);
		}
		return ListeRetour;
	}
	public List<DtoEnchereComplete> selectVenteByNomArticle(String nomArticle) throws SQLException, DALException {
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
			throw new DALException("Erreur SQL lors de la r??cup??ration des ench??res par nom d'article : " + e);
		}catch(Exception e) {
			throw new DALException("Erreur CRITIQUE lors de la r??cup??ration des ench??res par nom d'article : " + e);
		}
		return ListeRetour;
	}
	
	public List<DtoEnchereComplete> selectVenteByNomArticleAndCateg(int noCategorie,String nomArticle) throws SQLException, DALException {
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
			throw new DALException("Erreur SQL lors de la r??cup??ration des ench??res par nom d'article et cat??gories : " + e);
		}catch(Exception e) {
			throw new DALException("Erreur CRITIQUE lors de la r??cup??ration des ench??res par nom d'article et cat??gories : " + e);
		}
		return ListeRetour;
	}
	
	//m??thode pour s??lectionner une vente par id
	public DtoEnchereComplete selectVenteById(int noArticle) throws SQLException, DALException{
		System.out.println("Details vente ID de d'ench??re " + noArticle);
		DtoEnchereComplete ObjetRetour = new DtoEnchereComplete();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectByIdArticle= conn.prepareStatement(SELECT_ENCHERE_BY_ARTICLE_ID);
			stmtSelectByIdArticle.setInt(1, noArticle);
			ResultSet rs = stmtSelectByIdArticle.executeQuery();	
			while(rs.next()) {
				ObjetRetour = new DtoEnchereComplete(rs.getString("nomArticle"),rs.getInt("PrixVente"),rs.getDate("DateFinEnchere"),rs.getString("pseudo"), rs.getString("telephone"), rs.getInt("no_article"),rs.getString("libelle"),rs.getInt("prix_initial"),rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville"),rs.getString("description"),rs.getDate("date_debut_encheres"));
				System.out.println(ObjetRetour.toString());
			}
		} catch (SQLException e) {
			throw new DALException("Erreur SQL lors de la r??cup??ration de l'enchere par id : " + e);
		}
		return ObjetRetour;
	}
	
	
	/**
	 *
	 */
	public boolean updatePrixVenteEnchere(int noArticle,int montantEnchere,int noUtilisateur) throws SQLException, DALException{
		boolean resulat=false;
		int soldeEncherisseur=0;
		int creditacrediter=0;
		int no_utilisateuracrediter=0;
		int utilisateursolde =0;
		int newSoldeEncherisseur = 0;
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//teste pour v??rifier si l'encherisseur peux acheter le produit
			PreparedStatement VerificationSoldeEncherisseur = conn.prepareStatement(BALANCE_CHECK );
			VerificationSoldeEncherisseur.setInt(1,noUtilisateur);
			ResultSet rsSoldeEncherisseur = VerificationSoldeEncherisseur.executeQuery();
			//permet de rentre dans le tableau et d'afficher le credit
			if ( rsSoldeEncherisseur.next()) {
				soldeEncherisseur=rsSoldeEncherisseur.getInt("credit");
			}
			//**************************************************************************
			System.out.println("solde de la personne qui ench??rie "+soldeEncherisseur);
			//**************************************************************************

			/**
			 *fonction qui va verifier si l'acheteur ?? assez de cr??dit  .
			 *recr??diter l'ancien encherisseur 
			 *debiter le winner de l'encherisseur de l'argent 
			 */
			if (soldeEncherisseur>= montantEnchere) {
				//retour de l'ancien encherisseur pour le recr??diter
				PreparedStatement retour_noUser_solde = conn.prepareStatement(NUMBER_OLD_USER_AND_CREDIT_BEST_WINNER);
				retour_noUser_solde.setInt(1,noArticle);
				retour_noUser_solde.setInt(2,noArticle);
				ResultSet rsretour_noUser_solde = retour_noUser_solde.executeQuery();
				//permet de rentre dans le tableau et d'afficher le credit
				if ( rsretour_noUser_solde.next()) {
					creditacrediter =rsretour_noUser_solde.getInt("montant_enchere");
					no_utilisateuracrediter =rsretour_noUser_solde.getInt("no_utilisateur");
				}
					PreparedStatement soldedeutilisateur = conn.prepareStatement(RETURN_CREDIT_USER);
					soldedeutilisateur.setInt(1,no_utilisateuracrediter);
					ResultSet rssoldedeutilisateur = soldedeutilisateur.executeQuery();
					//permet de recup??re le solde ?? recrediter dans le tableau
					if ( rssoldedeutilisateur.next() ) {
						utilisateursolde = rssoldedeutilisateur.getInt("credit");
					}
					creditacrediter = creditacrediter+ utilisateursolde;
					//requet pour recr??diter le compte de non winner de l'ench??re 
					PreparedStatement update_credit =conn.prepareStatement(UPDATE_CREDIT_NOT_WIN_USER);
					update_credit.setInt(1,creditacrediter);
					update_credit.setInt(2,no_utilisateuracrediter);
					update_credit.executeUpdate();
					System.out.println("retour credit de " + no_utilisateuracrediter +"de ="+ creditacrediter );
					
					//debiter le winner de l'ench??re de l'argent
					newSoldeEncherisseur =soldeEncherisseur-montantEnchere;
					PreparedStatement upadateCreditUserWin = conn.prepareStatement(UPDATE_CREDIT_USER_WIN);
					upadateCreditUserWin.setInt(1, newSoldeEncherisseur);
					upadateCreditUserWin.setInt(2, noUtilisateur);
					upadateCreditUserWin.execute();
					System.out.println(" vous avez "+ newSoldeEncherisseur + "le solde olde " +soldeEncherisseur+" le ontant de l'ench??re est de   "+ montantEnchere);
					
					// insert le montant de l'enchre dans la base de donn??e table ench??re
					PreparedStatement InsertEnchere = conn.prepareStatement(INSERT_ENCHERES);
					InsertEnchere.setInt(1, montantEnchere);
					InsertEnchere.setInt(2, noArticle);
					InsertEnchere.setInt(3, noUtilisateur);
					InsertEnchere.executeUpdate();
					
					// mise a jours  le montant de l'ench??re dans la base de donn??e article Vendu
					PreparedStatement UpdateArticle = conn.prepareStatement(UPDATE_ARTICLES_VENDUS);
					UpdateArticle.setInt(1, montantEnchere);
					UpdateArticle.setInt(2, noArticle);
					UpdateArticle.executeUpdate();
					System.out.println("tu peux encherire renvoie dans du r??sultat true");
					resulat=true;
					
			}else {
				resulat=false;
				System.out.println("tu n'as pas asser de cr??dit ");
			}
			System.out.println("Fin de la cr??ation de l'ench??re");
		} catch (SQLException e) {
			throw new DALException("Erreur SQL lors de la mise ?? jour du prix de l'article par id : " + e);
		}catch(Exception e) {
			throw new DALException("Erreur CRITIQUE lors de la mise ?? jour du prix de l'article par id : " + e);
		}
		return resulat;
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
		throw new DALException("erreur getMontantEtPseudoDeLaMeilleurOffre articleVendu = "+articleVendu, e);
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
		throw new DALException("erreur selectArticleEnVenteOfUser noUtilisateur = "+noUtilisateur, e);
	}
	return result;
}
//test
/**
 * @noArticle : numero d'article mis en vente.
 * Je cr???? deux statement, un pour r??up??rer la date actuelle, un autre pour r??cup??rer la date de fin d'ench??res
 * Dans le cas o?? la date de fin d'ench??res est plus grande que la date actuelle je retourne true pour signaler la fin de l'ench??re
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
			System.out.println(".....................................................................................................");
			System.out.println("il y a des ligne"+rsDateNow);
			
			if((rsDateNow.getDate(1)).after(rsDateFinEnchere.getDate(1)))
				return true;
		}
	} catch (SQLException e) {
		throw new DALException("erreur FinEnchere - noArticle:"+noArticle, e);
	}
	return false;
	
}

}
	

