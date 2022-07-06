package fr.eni.Enchere.bll;

import fr.eni.Enchere.bo.ArticleVendu;

public interface ArticleAVendreManager {
	int NouvelleArticle (ArticleVendu nouvelleArticle )throws BLLException,Exception;
}
