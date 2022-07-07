package fr.eni.Enchere.dal;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import fr.eni.Enchere.bll.BLLException;
import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.DtoEnchereComplete;

public interface EncheresDao {
	 List<DtoEnchereComplete> SelectAllEnchere() throws SQLException, DALException;
	 List<DtoEnchereComplete> SelectVenteByCategorie(int noCateg) throws SQLException, DALException;
	 List<DtoEnchereComplete> selectVenteByNomArticle(String nomArticle) throws SQLException, DALException;
	 List<DtoEnchereComplete> selectVenteByNomArticleAndCateg(int noCategorie,String nomArticle) throws SQLException, DALException;
	 DtoEnchereComplete selectVenteById(int noArticle) throws DALException, SQLException;
	 boolean updatePrixVenteEnchere(int noArticle,int montantEnchere,int noUtilisateur) throws SQLException, DALException;
	 DtoEnchereComplete getMontantEtPseudoDeLaMeilleurOffre (ArticleVendu articleVendu) throws DALException, SQLException;
	 List<DtoEnchereComplete> selectArticleEnVenteOfUser(int noUtilisateur)throws DALException, SQLException;
	 boolean FinEnchere(int noArticle)throws DALException;
	 
}
