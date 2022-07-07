package fr.eni.Enchere.dal;

import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.Retrait;

public interface ArticleAVendreDao {

	int nouvelleArticle (ArticleVendu nouvelleArticle )throws DALException;
	
	void insertionDonnerRetrait (Retrait adresseRetrait ) throws DALException;
}
