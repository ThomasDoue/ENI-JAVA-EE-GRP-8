package fr.eni.Enchere.dao;
import fr.eni.Enchere.dal.ArticleAVendreDao;
import fr.eni.Enchere.dal.ArticleAVendreDaoImpl;
import fr.eni.Enchere.dal.CategorieDao;
import fr.eni.Enchere.dal.CategorieDaoImpl;
import fr.eni.Enchere.dal.EnchereDaoImpl;
import fr.eni.Enchere.dal.EncheresDao;
import fr.eni.Enchere.dal.UtilisateurDao;

import fr.eni.Enchere.dal.UtilisateurDaoImpl;

public class DaoFactory {
	
	public static UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoImpl();
	}
	public static CategorieDao getCategorieDao() {
		return new CategorieDaoImpl();
	}
	public static EncheresDao getEncheresDao() {
		return new EnchereDaoImpl();
	}
	public static ArticleAVendreDao getArticleAVendreDao() {
		return new ArticleAVendreDaoImpl();
	}


}
