package fr.eni.Enchere.dal;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import fr.eni.Enchere.bll.BLLException;
import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.DtoEnchereComplete;

public interface EncheresDao {
	 List<DtoEnchereComplete> SelectAllEnchere() throws SQLException;
	 List<DtoEnchereComplete> SelectVenteByCategorie(int noCateg) throws SQLException;
	 List<DtoEnchereComplete> selectVenteByNomArticle(String nomArticle) throws SQLException;
	 List<DtoEnchereComplete> selectVenteByNomArticleAndCateg(int noCategorie,String nomArticle) throws SQLException,BLLException;
	 DtoEnchereComplete selectVenteById(int noArticle) throws SQLException;
	 boolean updatePrixVenteEnchere(int noArticle,int montantEnchere,int noUtilisateur) throws SQLException;
	 DtoEnchereComplete getMontantEtPseudoDeLaMeilleurOffre (ArticleVendu articleVendu) throws DALException, SQLException;
}
