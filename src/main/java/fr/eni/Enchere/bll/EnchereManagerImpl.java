package fr.eni.Enchere.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.DtoEnchereComplete;
import fr.eni.Enchere.dal.CategorieDao;
import fr.eni.Enchere.dal.DALException;
import fr.eni.Enchere.dal.EncheresDao;
import fr.eni.Enchere.dao.DaoFactory;

public class EnchereManagerImpl implements EnchereManager {
	
private EncheresDao enchereDao;
	
	public EnchereManagerImpl() {
		enchereDao = DaoFactory.getEncheresDao();
	}
	
	public List<DtoEnchereComplete> SelectAllEnchere() throws DALException, SQLException,Exception{
		List<DtoEnchereComplete> listRetour = new ArrayList<DtoEnchereComplete>();
		try {
		listRetour = enchereDao.SelectAllEnchere();
		}catch(Exception e) {
			throw new BLLException ("Erreur lors de la récupération des encheres dans la bll : " +  e);
		}
		return listRetour;
	}
	public List<DtoEnchereComplete> selectVenteByCateg(int noCategorie) throws DALException, SQLException,Exception {
		List<DtoEnchereComplete> listRetour = new ArrayList<DtoEnchereComplete>();
		try {
			listRetour = enchereDao.SelectVenteByCategorie(noCategorie);
		}catch(Exception e) {
			throw new BLLException ("Erreur lors de la récupération des encheres par catégories dans la bll : " +  e);
		}
		return listRetour;
	}
	public  List<DtoEnchereComplete> selectVenteByNomArticle(String nomArticle) throws SQLException, BLLException{
		List<DtoEnchereComplete> listRetour = new ArrayList<DtoEnchereComplete>();
		try {
			System.out.println("Nom d'article passer en paramètres : " + nomArticle);
			listRetour = enchereDao.selectVenteByNomArticle(nomArticle);
		}catch(Exception e) {
			throw new BLLException ("Erreur lors de la récupération des encheres par nom d'article dans la bll : " +  e);
		}
		return listRetour;
	}
	public List<DtoEnchereComplete> selectVenteByNomArticleAndCateg(int noCategorie,String nomArticle) throws SQLException, BLLException{
		List<DtoEnchereComplete> listRetour = new ArrayList<DtoEnchereComplete>();
		try {
			listRetour = enchereDao.selectVenteByNomArticleAndCateg(noCategorie,nomArticle);
		}catch(Exception e) {
			throw new BLLException ("Erreur lors de la récupération des encheres par nom d'article et par catégories dans la bll : " +  e);
		}
		return listRetour;
	}
	
	public DtoEnchereComplete selectVenteById(int noArticle) throws SQLException, BLLException{
		DtoEnchereComplete ObjetRetour = new DtoEnchereComplete();
		try {
			ObjetRetour = enchereDao.selectVenteById(noArticle);
		}catch(DALException e) {
			throw new BLLException ("Erreur lors de la récupération des encheres par Id dans la BLL : " +  e);
		}
		return ObjetRetour;
		
	}
	
	public boolean updatePrixVenteEnchere(int noArticle,int montantEnchere,int noUtilisateur) throws SQLException, BLLException{
		boolean resultat=false;
		try {
			enchereDao.updatePrixVenteEnchere(noArticle, montantEnchere, noUtilisateur);
		}catch(Exception e) {
			throw new BLLException ("Erreur lors de la mise à jour du prix de vente d'un article suite à une enchère dans la BLL : " +  e);
		}
		return resultat;
	}
	
	public DtoEnchereComplete getMontantEtPseudoDeLaMeilleurOffre(ArticleVendu articleVendu) {
		return getMontantEtPseudoDeLaMeilleurOffre(articleVendu);
		
		
	}
	
	public List<DtoEnchereComplete> selectArticleEnVenteOfUser(int noUtilisateur)throws SQLException, BLLException{
		List<DtoEnchereComplete> ListeRetour = new ArrayList<DtoEnchereComplete>();
		try {
			ListeRetour = enchereDao.selectArticleEnVenteOfUser(noUtilisateur);
		}catch(Exception e) {
			throw new BLLException ("Erreur lors de la mise à jour du prix de vente d'un article suite à une enchère dans la BLL : " +  e);
		}
		return ListeRetour;
	}
	
	public boolean FinDEnchere (int noArticle) throws BLLException {
		try {
			return enchereDao.FinEnchere(noArticle);
		} catch (DALException e) {
			throw new BLLException("Erreur Fin d'enchère, no_Article = "+noArticle, e);
		}
	}
}
