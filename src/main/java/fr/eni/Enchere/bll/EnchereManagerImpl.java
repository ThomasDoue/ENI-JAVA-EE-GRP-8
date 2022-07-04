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
	public List<DtoEnchereComplete> SelectEncheresByCategorie(int noCategorie) throws DALException, SQLException,Exception {
		List<DtoEnchereComplete> listRetour = new ArrayList<DtoEnchereComplete>();
		try {
			listRetour = enchereDao.SelectEnchereByCategorie(noCategorie);
		}catch(Exception e) {
			throw new BLLException ("Erreur lors de la récupération des encheres par catégories dans la bll : " +  e);
		}
		return listRetour;
	}
	public  List<DtoEnchereComplete> SelectEnchereByNomArticle(String nomArticle) throws SQLException, BLLException{
		List<DtoEnchereComplete> listRetour = new ArrayList<DtoEnchereComplete>();
		try {
			System.out.println("Nom d'article passer en paramètres : " + nomArticle);
			listRetour = enchereDao.SelectEnchereByNomArticle(nomArticle);
		}catch(Exception e) {
			throw new BLLException ("Erreur lors de la récupération des encheres par nom d'article dans la bll : " +  e);
		}
		return listRetour;
	}
	public List<DtoEnchereComplete> SelectEnchereByNomArticleAndCateg(int noCategorie,String nomArticle) throws SQLException, BLLException{
		List<DtoEnchereComplete> listRetour = new ArrayList<DtoEnchereComplete>();
		try {
			listRetour = enchereDao.SelectEnchereByNomArticleAndCateg(noCategorie,nomArticle);
		}catch(Exception e) {
			throw new BLLException ("Erreur lors de la récupération des encheres par nom d'article et par catégories dans la bll : " +  e);
		}
		return listRetour;
	}
	
	public DtoEnchereComplete SelectEnchereById(int noArticle) throws SQLException, BLLException{
		DtoEnchereComplete ObjetRetour = new DtoEnchereComplete();
		try {
			ObjetRetour = enchereDao.SelectEnchereById(noArticle);
		}catch(Exception e) {
			throw new BLLException ("Erreur lors de la récupération des encheres par Id dans la BLL : " +  e);
		}
		return ObjetRetour;
		
	}
	
	public void updatePrixVenteEnchere(int noArticle,int montantEnchere,int noUtilisateur) throws SQLException, BLLException{
		try {
			enchereDao.updatePrixVenteEnchere(noArticle, montantEnchere, noUtilisateur);
		}catch(Exception e) {
			throw new BLLException ("Erreur lors de la récupération des encheres par Id dans la BLL : " +  e);
		}
	}
	
	public DtoEnchereComplete getMontantEtPseudoDeLaMeilleurOffre(ArticleVendu articleVendu) throws SQLException {
		return getMontantEtPseudoDeLaMeilleurOffre(articleVendu);
		
		
	}
}
