package fr.eni.Enchere.bll;

import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.Retrait;
import fr.eni.Enchere.dal.ArticleAVendreDao;
import fr.eni.Enchere.dal.DALException;
import fr.eni.Enchere.dao.DaoFactory;

public class ArticleAVendreManagerImpl implements ArticleAVendreManager{

	private ArticleAVendreDao articleAvendreDao;
	
	//passage par la factori pour liaison faible
	public ArticleAVendreManagerImpl(){
		articleAvendreDao = DaoFactory.getArticleAVendreDao();
	}
	
	
	public int NouvelleArticle (ArticleVendu nouvelleArticle )throws BLLException{
		try {
			return articleAvendreDao.nouvelleArticle(nouvelleArticle);
		} catch (DALException e) {
			throw new BLLException("Erreur nouvelleArticle "+ nouvelleArticle,e);
		}
	}
	
	public  void insertionDonnerRetrait (Retrait adresseRetrait) throws BLLException{
		try {
			 articleAvendreDao.insertionDonnerRetrait(adresseRetrait);
		} catch (DALException e) {
			throw new BLLException ("Erreur insertionDonnerRetrait = "+ adresseRetrait , e);
		}
	}
	
}
