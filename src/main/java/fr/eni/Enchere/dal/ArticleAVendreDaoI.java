package fr.eni.Enchere.dal;

import fr.eni.Enchere.bo.ArticleVendu;

public interface ArticleAVendreDaoI {

	
	int nouvelleArticle (ArticleVendu nouvelleArticle )throws DALException,Exception;
	
}
