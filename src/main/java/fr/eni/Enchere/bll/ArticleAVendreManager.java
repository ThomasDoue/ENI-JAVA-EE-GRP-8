package fr.eni.Enchere.bll;

import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.Retrait;

public interface ArticleAVendreManager {
	int NouvelleArticle (ArticleVendu nouvelleArticle )throws BLLException;
	
	public boolean FinDEnchere (int noArticle) throws BLLException;
	
	void insertionDonnerRetrait (Retrait adresseRetrait) throws BLLException;
	
}
